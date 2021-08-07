package Db;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import controller.DataAccessException;
import model.Procedure;
import model.ProcedureEntry;

public class ProcedureDb implements ProcedureDbIF {

	private DBConnection dbConnection;

	private static final String FIND_PROCEDURE = "select * from dbo.Procedures";
	private PreparedStatement findProcedure;

	private static final String FIND_BY_NAME = FIND_PROCEDURE + " where procedureName like '%' + ? + '%'";
	private PreparedStatement findByName;

	// old one: where code like ? + '%' or code like '%' + ? + '%'
	private static final String FIND_BY_CODE = FIND_PROCEDURE + " where code = ?";
	private PreparedStatement findByCode;

	private static final String FIND_VERSION = "select version from dbo.Procedures where code = ?";
	private PreparedStatement findVersion;

	private static final String INSERT_PROCEDURE = "insert into " + "dbo.Procedures "
			+ "(procedureName, pricePerHour, employeeTypeRequired, done, version) values (?,?,?,?,?)";
	private PreparedStatement insertProcedure;

	private static final String UPDATE_DONE = "update dbo.Procedures set done = ?, version = ? where code = ? and version = ?";
	private PreparedStatement updateDone;

	private static final String UPDATE_PROCEDURE = "update " + "dbo.Procedures "
			+ "set procedureName = ?, pricePerHour = ?, employeeTypeRequired = ?, version = ? where code = ? and version = ?";
	private PreparedStatement updateProcedure;

	private static final String DELETE_PROCEDURE = "delete from dbo.Procedures where code = ?";
	private PreparedStatement deleteProcedure;

	private static final String GET_PRODUCTANDPROCEDURETOIT = "select op.hours, op.code from dbo.OfferProcedures op inner join dbo.Procedures p on op.code=p.code where op.productName = ? and p.done = '0'";
	private PreparedStatement getProductAndProcedureToIt;

	public ProcedureDb() throws DataAccessException {
		dbConnection = DBConnection.getInstance();
		init();
	}

	private void init() throws DataAccessException {
		Connection con = DBConnection.getInstance().getConnection();
		try {
			findProcedure = con.prepareStatement(FIND_PROCEDURE);
			findByName = con.prepareStatement(FIND_BY_NAME);
			findByCode = con.prepareStatement(FIND_BY_CODE);
			findVersion = con.prepareStatement(FIND_VERSION);
			insertProcedure = con.prepareStatement(INSERT_PROCEDURE, Statement.RETURN_GENERATED_KEYS);
			updateDone = con.prepareStatement(UPDATE_DONE);
			updateProcedure = con.prepareStatement(UPDATE_PROCEDURE);
			deleteProcedure = con.prepareStatement(DELETE_PROCEDURE);
			getProductAndProcedureToIt = con.prepareStatement(GET_PRODUCTANDPROCEDURETOIT);

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new DataAccessException("Could not prepare statements", e);
		}
	}

	@Override
	public Procedure create(String procedureName, BigDecimal pricePerHour, String employeeTypeRequired)
			throws DataAccessException {
		Procedure procedure = null;
		ResultSet rs;
		int code = -1;
		for (int i = 0; i <= 5; i++) {
			try {
				dbConnection.startTransaction();

				insertProcedure.setString(1, procedureName);
				insertProcedure.setBigDecimal(2, pricePerHour);
				insertProcedure.setString(3, employeeTypeRequired);
				insertProcedure.setInt(4, 0);
				insertProcedure.setInt(5, 0);

				insertProcedure.executeUpdate();
				rs = insertProcedure.getGeneratedKeys();
				if (rs.next()) {
					code = rs.getInt(1);
				}

				dbConnection.commitTransaction();
				i = 5;
			} catch (SQLException e) {
				if (dbConnection.getConnection() != null) {
					System.err.print("Transaction is being rolled back");
					dbConnection.rollbackTransaction();
				}
			}
		}
		procedure = findByCode(code);

		return procedure;
	}

	@Override
	public List<Procedure> findByName(String procedureName) throws DataAccessException {
		List<Procedure> l;
		try {
			findByName.setString(1, procedureName);
			ResultSet rs = findByName.executeQuery();
			l = buildObjects(rs);
		} catch (SQLException e) {
			throw new DataAccessException("Could not read result set", e);
		}
		return l;
	}

	@Override
	public Procedure findByCode(int code) throws DataAccessException {
		Procedure res = null;
		try {
			findByCode.setInt(1, code);
			ResultSet rs = findByCode.executeQuery();
			if (rs.next()) {
				res = buildObject(rs);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new DataAccessException("Could not read result set", e);
		}
		return res;
	}

	private int findVersion(int code) throws DataAccessException {
		int version = -1;
		try {
			findVersion.setInt(1, code);
			ResultSet rs = findVersion.executeQuery();
			if (rs.next()) {
				version = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new DataAccessException("Could not read result set", e);
		}
		return version;
	}

	@Override
	public boolean updateDone(int code) throws DataAccessException {
		int res = 0;
		for (int i = 0; i <= 5; i++) {
			try {
				dbConnection.startTransaction();
				int version = findVersion(code);

				updateDone.setInt(1, 2);
				updateDone.setInt(2, version + 1);
				updateDone.setInt(3, code);
				updateDone.setInt(4, version);

				res += updateDone.executeUpdate();

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
	public boolean updateInWork(int code) throws DataAccessException {
		int res = 0;
		for (int i = 0; i <= 5; i++) {
			try {
				dbConnection.startTransaction();
				int version = findVersion(code);

				updateDone.setInt(1, 1);
				updateDone.setInt(2, version + 1);
				updateDone.setInt(3, code);
				updateDone.setInt(4, version);

				res += updateDone.executeUpdate();

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
	public boolean updateNotDone(int code) throws DataAccessException {
		int res = 0;
		for (int i = 0; i <= 5; i++) {
			try {
				dbConnection.startTransaction();
				int version = findVersion(code);

				updateDone.setInt(1, 0);
				updateDone.setInt(2, version + 1);
				updateDone.setInt(3, code);
				updateDone.setInt(4, version);

				res += updateDone.executeUpdate();

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
	public boolean update(String procedureName, BigDecimal pricePerHour, int code, String employeeTypeRequired)
			throws DataAccessException {
		int res = 0;
		for (int i = 0; i <= 5; i++) {
			try {
				dbConnection.startTransaction();
				int version = findVersion(code);

				updateProcedure.setString(1, procedureName);
				updateProcedure.setBigDecimal(2, pricePerHour);
				updateProcedure.setString(3, employeeTypeRequired);
				updateProcedure.setInt(4, version + 1);
				updateProcedure.setInt(5, code);
				updateProcedure.setInt(6, version);

				res += updateProcedure.executeUpdate();

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
	public boolean delete(int code) throws DataAccessException {
		try {
			deleteProcedure.setInt(1, code);
			return deleteProcedure.executeUpdate() > 0;
		} catch (SQLException e) {
			throw new DataAccessException("Could not delete from database", e);
		}
	}

	@Override
	public List<Procedure> getAll() throws DataAccessException {
		ResultSet rs;
		List<Procedure> l;
		try {
			rs = findProcedure.executeQuery();
			l = buildObjects(rs);
		} catch (SQLException e) {
			throw new DataAccessException("Could not execute query", e);
		}
		return l;
	}

	private Procedure buildObject(ResultSet rs) throws DataAccessException {
		Procedure procedure;
		try {
			String procedureName = rs.getString("procedureName");
			BigDecimal pricePerHour = rs.getBigDecimal("pricePerHour");
			int code = rs.getInt("code");
			String employeeTypeRequired = rs.getString("employeeTypeRequired");

			procedure = new Procedure(procedureName, pricePerHour, code, employeeTypeRequired);
		} catch (SQLException e) {
			throw new DataAccessException("Could not read result set - single object", e);
		}
		return procedure;
	}

	private List<Procedure> buildObjects(ResultSet rs) throws DataAccessException {
		ArrayList<Procedure> list = new ArrayList<>();
		try {
			while (rs.next()) {
				Procedure p = buildObject(rs);
				list.add(p);
			}
		} catch (SQLException e) {
			throw new DataAccessException("Could not read result set - next()", e);
		} catch (DataAccessException e) {
			throw new DataAccessException("Could not build object", e);
		}
		return list;
	}

	public List<ProcedureEntry> getAllProductAndProcedureToIt(String productName) throws DataAccessException {
		List<ProcedureEntry> res;
		try {
			getProductAndProcedureToIt.setString(1, productName);
			ResultSet rs = getProductAndProcedureToIt.executeQuery();
			res = buildProcedureEntries(rs);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new DataAccessException("Could not read result set", e);
		}
		return res;
	}

	private List<ProcedureEntry> buildProcedureEntries(ResultSet rs) throws DataAccessException {
		List<ProcedureEntry> list = new ArrayList<ProcedureEntry>();

		try {
			while (rs.next()) {
				int hours = rs.getInt("hours");
				Procedure procedure = findByCode(rs.getInt("code"));
				list.add(new ProcedureEntry(hours, procedure));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

}
