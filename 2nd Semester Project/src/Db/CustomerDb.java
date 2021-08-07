package Db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import controller.DataAccessException;
import model.Customer;

public class CustomerDb implements CustomerDbIF {

	private DBConnection dbConnection;

	private static final String FIND_CUSTOMER = "select c.customerId, c.companyName, c.CUI, c.phoneNumber, c.email, a.zipcode, a.street, a.city from dbo.Customer c inner join dbo.Address a on c.customerId=a.customerId";
	private PreparedStatement findCustomer;

	private static final String FIND_BY_ID = FIND_CUSTOMER + " where c.customerId = ?";
	private PreparedStatement findById;

	private static final String FIND_BY_NAME = FIND_CUSTOMER + " where c.companyName = ?";
	private PreparedStatement findByName;

	private static final String FIND_BY_NAME_REGEX = FIND_CUSTOMER + " where c.companyName like '%' + ? + '%'";
	private PreparedStatement findByNameRegex;

	private static final String FIND_VERSION = "select version from dbo.Customer where customerId = ?";
	private PreparedStatement findVersion;

	private static final String INSERT_C = "insert into " + "dbo.Customer "
			+ "(companyName, CUI, phoneNumber, email, version) values (?,?,?,?,?)";
	private PreparedStatement insertCustomer;

	private static final String INSERT_A = "insert into " + "dbo.Address "
			+ "(zipcode, city, street, customerId, version) values (?,?,?,?,?)";
	private PreparedStatement insertAddress;

	private static final String UPDATE_CUSTOMER = "update " + "dbo.Customer "
			+ "set companyName = ?, CUI = ?, phoneNumber = ?, email = ?, version = ? where customerId = ? and version = ?";
	private PreparedStatement updateCustomer;

	private static final String UPDATE_ADDRESS = "update " + "dbo.Address "
			+ "set zipcode = ?, street = ?, city = ?, version = ? where customerId = ? and version = ?";
	private PreparedStatement updateAddress;

	private static final String DELETE_CUSTOMER = "delete from dbo.Customer where customerId = ?";
	private PreparedStatement deleteCustomer;

	public CustomerDb() throws DataAccessException {
		dbConnection = DBConnection.getInstance();
		init();
	}

	private void init() throws DataAccessException {
		Connection con = DBConnection.getInstance().getConnection();
		try {
			findCustomer = con.prepareStatement(FIND_CUSTOMER);
			findById = con.prepareStatement(FIND_BY_ID);
			findByName = con.prepareStatement(FIND_BY_NAME);
			findByNameRegex = con.prepareStatement(FIND_BY_NAME_REGEX);
			findVersion = con.prepareStatement(FIND_VERSION);
			insertCustomer = con.prepareStatement(INSERT_C, Statement.RETURN_GENERATED_KEYS);
			insertAddress = con.prepareStatement(INSERT_A);
			updateCustomer = con.prepareStatement(UPDATE_CUSTOMER);
			updateAddress = con.prepareStatement(UPDATE_ADDRESS);
			deleteCustomer = con.prepareStatement(DELETE_CUSTOMER);

		} catch (SQLException e) {
			throw new DataAccessException("Could not prepare statements", e);
		}
	}

	@Override
	public Customer create(String companyName, String street, String zipcode, String city, String CUI,
			String phoneNumber, String email) throws DataAccessException {

		Customer customer = null;
		ResultSet rs;
		int id = -1;
		for (int i = 0; i <= 5; i++) {
			try {
				dbConnection.startTransaction();

				insertCustomer.setString(1, companyName);
				insertCustomer.setString(2, CUI);
				insertCustomer.setString(3, phoneNumber);
				insertCustomer.setString(4, email);
				insertCustomer.setInt(5, 0);

				insertCustomer.executeUpdate();
				rs = insertCustomer.getGeneratedKeys();
				if (rs.next()) {
					id = rs.getInt(1);
				}

				insertAddress.setString(1, zipcode);
				insertAddress.setString(2, city);
				insertAddress.setString(3, street);
				insertAddress.setInt(4, id);
				insertAddress.setInt(5, 0);

				insertAddress.executeUpdate();

				dbConnection.commitTransaction();
				i = 5;
			} catch (SQLException e) {
				if (dbConnection.getConnection() != null) {
					System.err.print("Transaction is being rolled back");
					dbConnection.rollbackTransaction();
				}
			}
		}
		customer = findById(id);

		return customer;
	}

	@Override
	public Customer findById(int id) throws DataAccessException {
		Customer res = null;
		try {
			findById.setInt(1, id);
			ResultSet rs = findById.executeQuery();
			if (rs.next()) {
				res = buildObject(rs);
			}
		} catch (SQLException e) {
			throw new DataAccessException("Could not read result set", e);
		}
		return res;
	}

	@Override
	public Customer findByName(String name) throws DataAccessException {
		Customer res = null;
		try {
			findByName.setString(1, name);
			ResultSet rs = findByName.executeQuery();
			if (rs.next()) {
				res = buildObject(rs);
			}
		} catch (SQLException e) {
			throw new DataAccessException("Could not read result set", e);
		}
		return res;
	}

	@Override
	public List<Customer> findByNameRegex(String name) throws DataAccessException {
		List<Customer> res = null;
		try {
			findByNameRegex.setString(1, name);
			ResultSet rs = findByNameRegex.executeQuery();
			res = buildObjects(rs);
		} catch (SQLException e) {
			throw new DataAccessException("Could not read result set", e);
		}
		return res;
	}

	private int findVersion(int id) throws DataAccessException {
		int version = -1;
		try {
			findVersion.setInt(1, id);
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
	public boolean update(Customer customer) throws DataAccessException {
		int res = 0;
		for (int i = 0; i <= 5; i++) {
			try {
				dbConnection.startTransaction();
				int version = findVersion(customer.getId());

				updateCustomer.setString(1, customer.getCompanyName());
				updateCustomer.setString(2, customer.getCUI());
				updateCustomer.setString(3, customer.getPhoneNumber());
				updateCustomer.setString(4, customer.getEmail());
				updateCustomer.setInt(5, version + 1);
				updateCustomer.setInt(6, customer.getId());
				updateCustomer.setInt(7, version);

				res += updateCustomer.executeUpdate();

				updateAddress.setString(1, customer.getZipcode());
				updateAddress.setString(2, customer.getStreet());
				updateAddress.setString(3, customer.getCity());
				updateAddress.setInt(4, version + 1);
				updateAddress.setInt(5, customer.getId());
				updateAddress.setInt(6, version);

				res += updateAddress.executeUpdate();

				dbConnection.commitTransaction();
				i = 5;
			} catch (SQLException e) {
				if (dbConnection.getConnection() != null) {
					System.err.print("Transaction is being rolled back");
					dbConnection.rollbackTransaction();
				}
			}
		}
		return res > 1;
	}

	@Override
	public boolean delete(int id) throws DataAccessException {
		try {
			deleteCustomer.setInt(1, id);
			return deleteCustomer.executeUpdate() > 0;
		} catch (SQLException e) {
			throw new DataAccessException("Could not delete from database", e);
		}
	}

	@Override
	public List<Customer> getAll() throws DataAccessException {
		ResultSet rs;
		List<Customer> l;
		try {
			rs = findCustomer.executeQuery();
			l = buildObjects(rs);
		} catch (SQLException e) {
			throw new DataAccessException("Could not execute query", e);
		}
		return l;
	}

	private Customer buildObject(ResultSet rs) throws DataAccessException {
		Customer customer;
		try {
			int id = rs.getInt("customerId");
			String name = rs.getString("companyName");
			String street = rs.getString("street");
			String zipcode = rs.getString("zipcode");
			String city = rs.getString("city");
			String CUI = rs.getString("CUI");
			String phoneNumber = rs.getString("phoneNumber");
			String email = rs.getString("email");

			customer = new Customer(id, name, street, zipcode, city, CUI, phoneNumber, email);
		} catch (SQLException e) {
			throw new DataAccessException("Could not read result set - single object", e);
		}
		return customer;
	}

	private List<Customer> buildObjects(ResultSet rs) throws DataAccessException {
		ArrayList<Customer> list = new ArrayList<>();
		try {
			while (rs.next()) {
				Customer c = buildObject(rs);
				list.add(c);
			}
		} catch (SQLException e) {
			throw new DataAccessException("Could not read result set - next()", e);
		} catch (DataAccessException e) {
			throw new DataAccessException("Could not build object", e);
		}
		return list;
	}

}
