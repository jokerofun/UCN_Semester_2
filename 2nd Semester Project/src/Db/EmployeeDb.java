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
import model.Employee;
import model.Position;

public class EmployeeDb implements EmployeeDbIF {

	private DBConnection dbConnection;

	private static final String FIND_EMPLOYEE = "select e.employeeId, e.firstName, e.middleName, e.lastName, e.salaryPerHour, e.phoneNumber, e.email, a.zipcode, a.street, a.city, p.positionName, p.clearance from (dbo.Employee e inner join dbo.Address a on (e.employeeId=a.employeeId)) inner join dbo.Position p on (e.position=p.positionName)";
	private PreparedStatement findEmployee;

	private static final String FIND_BY_ID = FIND_EMPLOYEE + " where e.employeeId = ?";
	private PreparedStatement findById;

	private static final String FIND_BY_NAME = FIND_EMPLOYEE
			+ " where firstName like ? or middleName like ? or lastName like ?";
	private PreparedStatement findByName;

	private static final String FIND_VERSION = "select version from dbo.Employee where employeeId = ?";
	private PreparedStatement findVersion;

	private static final String INSERT_E = "insert into " + "dbo.Employee "
			+ "(firstName, middleName, lastName, salaryPerHour, phoneNumber, email, position, version) values (?,?,?,?,?,?,?,?)";
	private PreparedStatement insertEmployee;

	private static final String INSERT_A = "insert into " + "dbo.Address "
			+ "(zipcode, city, street, employeeId, version) values (?,?,?,?,?)";
	private PreparedStatement insertAddress;

	private static final String UPDATE_EMPLOYEE = "update " + "dbo.Employee "
			+ "set firstName = ?, middleName = ?, lastName = ?, salaryPerHour = ?, phoneNumber = ?, email = ?, position = ?, version = ? where employeeId = ? and version = ?";
	private PreparedStatement updateEmployee;

	private static final String UPDATE_ADDRESS = "update " + "dbo.Address "
			+ "set zipcode = ?, street = ?, city = ?, version = ? where employeeId = ? and version = ?";
	private PreparedStatement updateAddress;

	private static final String DELETE_EMPLOYEE = "delete from dbo.Employee where employeeId = ?";
	private PreparedStatement deleteEmployee;

	public EmployeeDb() throws DataAccessException {
		dbConnection = DBConnection.getInstance();
		init();
	}

	private void init() throws DataAccessException {
		Connection con = DBConnection.getInstance().getConnection();
		try {
			findEmployee = con.prepareStatement(FIND_EMPLOYEE);
			findById = con.prepareStatement(FIND_BY_ID);
			findByName = con.prepareStatement(FIND_BY_NAME);
			findVersion = con.prepareStatement(FIND_VERSION);
			insertEmployee = con.prepareStatement(INSERT_E, Statement.RETURN_GENERATED_KEYS);
			insertAddress = con.prepareStatement(INSERT_A);
			updateEmployee = con.prepareStatement(UPDATE_EMPLOYEE);
			updateAddress = con.prepareStatement(UPDATE_ADDRESS);
			deleteEmployee = con.prepareStatement(DELETE_EMPLOYEE);

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new DataAccessException("Could not prepare statements", e);
		}
	}

	@Override
	public Employee create(String firstName, String middleName, String lastName, String street, String zipcode,
			String city, String phoneNumber, String email, BigDecimal salaryPerHour, String positionName)
			throws DataAccessException {
		Employee employee = null;
		ResultSet rs;
		int id = -1;
		for (int i = 0; i <= 5; i++) {
			try {
				dbConnection.startTransaction();

				insertEmployee.setString(1, firstName);
				insertEmployee.setString(2, middleName);
				insertEmployee.setString(3, lastName);
				insertEmployee.setBigDecimal(4, salaryPerHour);
				insertEmployee.setString(5, phoneNumber);
				insertEmployee.setString(6, email);
				insertEmployee.setString(7, positionName);
				insertEmployee.setInt(8, 0);

				insertEmployee.executeUpdate();
				rs = insertEmployee.getGeneratedKeys();
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
					System.err.println("Transaction is being rolled back" + i);
					dbConnection.rollbackTransaction();
				}
			}
		}
		employee = findById(id);

		return employee;
	}

	@Override
	public Employee findById(int id) throws DataAccessException {
		Employee res = null;
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
	public Employee findByName(String fname, String mname, String lname) throws DataAccessException {
		Employee res = null;
		try {
			findByName.setString(1, fname);
			findByName.setString(2, mname);
			findByName.setString(3, lname);
			ResultSet rs = findByName.executeQuery();
			if (rs.next()) {
				res = buildObject(rs);
			}
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
	public boolean update(Employee employee) throws DataAccessException {
		int res = 0;
		for (int i = 0; i <= 5; i++) {
			try {
				dbConnection.startTransaction();
				int version = findVersion(employee.getId());

				updateEmployee.setString(1, employee.getFirstName());
				updateEmployee.setString(2, employee.getMiddleName());
				updateEmployee.setString(3, employee.getLastName());
				updateEmployee.setBigDecimal(4, employee.getSalaryPerHour());
				updateEmployee.setString(5, employee.getPhoneNumber());
				updateEmployee.setString(6, employee.getEmail());
				updateEmployee.setString(7, employee.getPosition().getPositionName());
				updateEmployee.setInt(8, version + 1);
				updateEmployee.setInt(9, employee.getId());
				updateEmployee.setInt(10, version);

				res += updateEmployee.executeUpdate();

				updateAddress.setString(1, employee.getZipcode());
				updateAddress.setString(2, employee.getStreet());
				updateAddress.setString(3, employee.getCity());
				updateAddress.setInt(4, version + 1);
				updateAddress.setInt(5, employee.getId());
				updateAddress.setInt(6, version);

				res += updateAddress.executeUpdate();

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
		return res > 1;
	}

	@Override
	public boolean delete(int id) throws DataAccessException {
		try {
			deleteEmployee.setInt(1, id);
			return deleteEmployee.executeUpdate() > 0;
		} catch (SQLException e) {
			throw new DataAccessException("Could not delete from database", e);
		}
	}

	@Override
	public List<Employee> getAll() throws DataAccessException {
		ResultSet rs;
		List<Employee> l;
		try {
			rs = findEmployee.executeQuery();
			l = buildObjects(rs);
		} catch (SQLException e) {
			throw new DataAccessException("Could not execute query", e);
		}
		return l;
	}

	private Employee buildObject(ResultSet rs) throws DataAccessException {
		Employee employee;
		try {
			int id = rs.getInt("employeeId");
			String firstName = rs.getString("firstName");
			String middleName = rs.getString("middleName");
			String lastName = rs.getString("lastName");
			String street = rs.getString("street");
			String zipcode = rs.getString("zipcode");
			String city = rs.getString("city");
			String phone = rs.getString("phoneNumber");
			String email = rs.getString("email");
			BigDecimal salary = rs.getBigDecimal("salaryPerHour");
			String positionName = rs.getString("positionName");
			String clearance = rs.getString("clearance");

			Position position = new Position(positionName, clearance);
			employee = new Employee(id, firstName, middleName, lastName, street, zipcode, city, phone, email, salary,
					position);
		} catch (SQLException e) {
			throw new DataAccessException("Could not read result set - single object", e);
		}
		return employee;
	}

	private List<Employee> buildObjects(ResultSet rs) throws DataAccessException {
		ArrayList<Employee> list = new ArrayList<>();
		try {
			while (rs.next()) {
				Employee e = buildObject(rs);
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
