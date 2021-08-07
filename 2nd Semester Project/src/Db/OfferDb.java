package Db;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import controller.DataAccessException;
import model.Offer;
import model.OfferEntry;
import model.Procedure;

public class OfferDb implements OfferDbIF {

	private DBConnection dbConnection;

	// "select p.*, o.* from Procedures p join OfferProcedures op on op.code =
	// p.code join Offer o on o.offerNumber = op.offerNumber"
	private static final String FIND_OFFER = "select * from Offer";
	private PreparedStatement findOffer;

	private static final String FIND_BY_NUMBER = FIND_OFFER + " where offerNumber = ?";
	private PreparedStatement findByNumber;

	private static final String FIND_VERSION = "select version from dbo.Offer where offerNumber = ?";
	private PreparedStatement findVersion;

	private static final String FIND_PROCEDURES = "select p.* from Procedures p join OfferProcedures op on op.code = p.code where op.offerNumber = ?";
	private PreparedStatement findProcedures;

	private static final String INSERT_OFFER = "insert into " + "dbo.Offer "
			+ "(dueDate, materialPrice, totalPrice, version) values (?,?,?,?)";
	private PreparedStatement insertOffer;

	private static final String INSERT_PROCEDURE = "insert into " + "dbo.OfferProcedures "
			+ "(offerNumber, productName, quantity, hours, code, version) values (?,?,?,?,?,?)";
	private PreparedStatement insertProcedure;

	private static final String UPDATE_OFFER = "update " + "dbo.Offer "
			+ "set dueDate = ?, materialPrice = ?, totalPrice = ?, version = ? where offerNumber = ? and version = ?";
	private PreparedStatement updateOffer;

	private static final String DELETE_OFFER = "delete from dbo.Offer where offerNumber = ?";
	private PreparedStatement deleteOffer;

	public OfferDb() throws DataAccessException {
		dbConnection = DBConnection.getInstance();
		init();
	}

	private void init() throws DataAccessException {
		Connection con = DBConnection.getInstance().getConnection();
		try {
			findOffer = con.prepareStatement(FIND_OFFER);
			findByNumber = con.prepareStatement(FIND_BY_NUMBER);
			findVersion = con.prepareStatement(FIND_VERSION);
			findProcedures = con.prepareStatement(FIND_PROCEDURES);
			insertOffer = con.prepareStatement(INSERT_OFFER, Statement.RETURN_GENERATED_KEYS);
			insertProcedure = con.prepareStatement(INSERT_PROCEDURE);
			updateOffer = con.prepareStatement(UPDATE_OFFER);
			deleteOffer = con.prepareStatement(DELETE_OFFER);

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new DataAccessException("Could not prepare statements", e);
		}
	}

	@Override
	public Offer create(Date dueDate, BigDecimal materialPrice, BigDecimal totalPrice, List<OfferEntry> offerEntries)
			throws DataAccessException {
		Offer offer = null;
		ResultSet rs;
		int offerNumber = -1;
		for (int i = 0; i <= 5; i++) {
			try {
				dbConnection.startTransaction();

				insertOffer.setDate(1, dueDate);
				insertOffer.setBigDecimal(2, materialPrice);
				insertOffer.setBigDecimal(3, totalPrice);
				insertOffer.setInt(4, 0);

				insertOffer.executeUpdate();
				rs = insertOffer.getGeneratedKeys();
				if (rs.next()) {
					offerNumber = rs.getInt(1);
				}

				for (OfferEntry offerEntry : offerEntries) {

					for (Map.Entry<Procedure, Integer> entry : offerEntry.getProcedureHour().entrySet()) {
						Procedure key = entry.getKey();
						Integer value = entry.getValue();

						insertProcedure.setInt(1, offerNumber);
						insertProcedure.setString(2, offerEntry.getProductName());
						insertProcedure.setInt(3, offerEntry.getQuantity());
						insertProcedure.setInt(4, value);
						insertProcedure.setInt(5, key.getCode());
						insertProcedure.setInt(6, 0);

						insertProcedure.executeUpdate();
					}
				}

				dbConnection.commitTransaction();
				i = 5;
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				if (dbConnection.getConnection() != null) {
					System.err.print("Transaction is being rolled back");
					dbConnection.rollbackTransaction();
				}
			}
		}
		offer = findByNumber(offerNumber);

		return offer;
	}

	@Override
	public Offer findByNumber(int offerNumber) throws DataAccessException {
		Offer res = null;
		try {
			findByNumber.setInt(1, offerNumber);
			ResultSet rs = findByNumber.executeQuery();
			if (rs.next()) {
				res = buildObject(rs);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new DataAccessException("Could not read result set", e);
		}
		return res;
	}

	private int findVersion(int offerNumber) throws DataAccessException {
		int version = -1;
		try {
			findVersion.setInt(1, offerNumber);
			ResultSet rs = findVersion.executeQuery();
			if (rs.next()) {
				version = rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new DataAccessException("Could not read result set", e);
		}
		return version;
	}

	@Override
	public boolean update(Offer offer) throws DataAccessException {
		int res = 0;
		for (int i = 0; i <= 5; i++) {
			try {
				dbConnection.startTransaction();
				int version = findVersion(offer.getOfferNumber());

				updateOffer.setDate(1, offer.getDueDate());
				updateOffer.setBigDecimal(2, offer.getMaterialPrice());
				updateOffer.setBigDecimal(3, offer.getTotalPrice());
				updateOffer.setInt(4, version + 1);
				updateOffer.setInt(5, offer.getOfferNumber());
				updateOffer.setInt(6, version);

				res += updateOffer.executeUpdate();

				// TODO maybe procedures ??
				dbConnection.commitTransaction();
				i = 5;
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				if (dbConnection.getConnection() != null) {
					System.err.print("Transaction is being rolled back");
					dbConnection.rollbackTransaction();
				}
			}
		}
		return res > 0;
	}

	@Override
	public boolean delete(int offerNumber) throws DataAccessException {
		try {
			deleteOffer.setInt(1, offerNumber);
			return deleteOffer.executeUpdate() > 0;
		} catch (SQLException e) {
			throw new DataAccessException("Could not delete from database", e);
		}
	}

	@Override
	public List<Offer> getAll() throws DataAccessException {
		ResultSet rs;
		List<Offer> l;
		try {
			rs = findOffer.executeQuery();
			l = buildObjects(rs);
		} catch (SQLException e) {
			throw new DataAccessException("Could not execute query", e);
		}
		return l;
	}

	private Offer buildObject(ResultSet rs) throws DataAccessException {
		Offer offer;
		List<Procedure> procedures = new ArrayList<>();
		ResultSet rs2;
		try {
			int offerNumber = rs.getInt("offerNumber");
			Date dueDate = rs.getDate("dueDate");
			BigDecimal materialPrice = rs.getBigDecimal("materialPrice");
			BigDecimal totalPrice = rs.getBigDecimal("totalPrice");

			findProcedures.setInt(1, offerNumber);
			rs2 = findProcedures.executeQuery();

			while (rs2.next()) {
				String procedureName = rs2.getString("procedureName");
				BigDecimal pricePerHour = rs2.getBigDecimal("pricePerHour");
				int code = rs2.getInt("code");
				String employeeTypeRequired = rs2.getString("employeeTypeRequired");
				Procedure procedure = new Procedure(procedureName, pricePerHour, code, employeeTypeRequired);
				procedures.add(procedure);
			}

			offer = new Offer(offerNumber, dueDate, materialPrice, totalPrice);
			offer.setProcedures(procedures);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new DataAccessException("Could not read result set - single object", e);
		}
		return offer;
	}

	private List<Offer> buildObjects(ResultSet rs) throws DataAccessException {
		ArrayList<Offer> list = new ArrayList<>();
		try {
			while (rs.next()) {
				Offer e = buildObject(rs);
				list.add(e);
			}
		} catch (SQLException e) {
			throw new DataAccessException("Could not read result set - next()", e);
		} catch (DataAccessException e) {
			throw new DataAccessException("Could not build object", e);
		}
		return list;
	}

}
