package db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLType;
import java.sql.Statement;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ctrl.DataAccessException;
import model.Department;
import model.Employee;

/**
 * 
 * @author knol
 * @version 2018-08-30
 */
public class EmployeeDB implements EmployeeDBIF {

	private DepartmentDBIF departmentDB;

	// query: select fname, minit, lname, ssn, bdate, address, sex, salary,
	// super_ssn, dno from employee where fname like '%j%' or minit = 'j' or lname
	// like '%j%'
	private static final String FIND_ALL_Q = "select fname, minit, lname, ssn, bdate, address, sex, salary, super_ssn, dno from employee";
	private PreparedStatement findAllPS;

	private static final String FIND_BY_NAME_OR_MINIT_Q = FIND_ALL_Q
			+ " where fname like ? or minit = ? or lname like ?";
	private PreparedStatement findByNameOrMinitPS;

	private static final String FIND_BY_SSN_Q = FIND_ALL_Q + " where ssn = ?";
	private PreparedStatement findBySsnPS;

	private static final String INSERT_Q = "insert into employee (fname, minit, lname, ssn, bdate, address, sex, salary, super_ssn, dno) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private PreparedStatement insertPS;

	public EmployeeDB(DepartmentDBIF departmentDB) throws DataAccessException {
		this.departmentDB = departmentDB;
		init();
	}

	public EmployeeDB() throws DataAccessException {
		departmentDB = new DepartmentDB(this);
		init();
	}

	private void init() throws DataAccessException {
		Connection con = DBConnection.getInstance().getConnection();
		try {
			findAllPS = con.prepareStatement(FIND_ALL_Q);
			findByNameOrMinitPS = con.prepareStatement(FIND_BY_NAME_OR_MINIT_Q);
			findBySsnPS = con.prepareStatement(FIND_BY_SSN_Q);
			insertPS = con.prepareStatement(INSERT_Q);// using identity, we'd have to add
														// Statement.RETURN_GENERATED_KEYS as a second argument
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new DataAccessException(DBMessages.COULD_NOT_PREPARE_STATEMENT, e);
		}
	}

	@Override
	public List<Employee> findByName(String name, boolean fullAssociation) throws DataAccessException {
		List<Employee> res = null;
		try {
			findByNameOrMinitPS.setString(1, "%" + name + "%");
			if (name != null && name.length() == 1) {
				findByNameOrMinitPS.setString(2, name);
			} else {
				findByNameOrMinitPS.setString(2, "");
			}
			findByNameOrMinitPS.setString(3, "%" + name + "%");
			ResultSet rs = findByNameOrMinitPS.executeQuery();
			res = buildObjects(rs, fullAssociation);
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new DataAccessException(DBMessages.COULD_NOT_BIND_OR_EXECUTE_QUERY, e);
		}
		return res;
	}

	@Override
	public Employee findBySSN(String ssn, boolean fullAssociation) throws DataAccessException {
		Employee res = null;
		try {
			findBySsnPS.setString(1, ssn);
			ResultSet rs = findBySsnPS.executeQuery();
			if (rs.next()) {
				res = buildObject(rs, fullAssociation);
			}
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new DataAccessException(DBMessages.COULD_NOT_BIND_OR_EXECUTE_QUERY, e);
		}
		return res;
	}

	private List<Employee> buildObjects(ResultSet rs, boolean fullAssociation) throws DataAccessException {
		List<Employee> res = new ArrayList<>();
		try {
			while (rs.next()) {
				Employee currEmployee = buildObject(rs, fullAssociation);
				res.add(currEmployee);
			}
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new DataAccessException(DBMessages.COULD_NOT_READ_RESULTSET, e);
		}
		return res;
	}

	private Employee buildObject(ResultSet rs, boolean fullAssociation) throws DataAccessException {
		Employee currEmployee = new Employee();
		try {
			currEmployee.setFname(rs.getString("fname"));
			currEmployee.setMinit(rs.getString("minit"));
			currEmployee.setLname(rs.getString("lname"));
			currEmployee.setSsn(rs.getString("ssn"));
			currEmployee.setBdate(rs.getDate("bdate").toLocalDate());
			currEmployee.setAddress(rs.getString("address"));
			currEmployee.setSex(rs.getString("sex"));
			currEmployee.setSalary(rs.getDouble("salary"));
			currEmployee.setSupervisor(new Employee(rs.getString("super_ssn")));
			currEmployee.setDepartment(new Department(rs.getInt("dno")));
			if (fullAssociation) {
				Employee supervisor = findBySSN(currEmployee.getSupervisor().getSsn(), false); // set to false to avoid
																								// potentially rolling
																								// up the entire
																								// database
				currEmployee.setSupervisor(supervisor);
				Department department = this.departmentDB.findByDnumber(currEmployee.getDept().getDnumber(), false);
				currEmployee.setDepartment(department);
			}
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new DataAccessException(DBMessages.COULD_NOT_READ_RESULTSET, e);
		}
		return currEmployee;
	}

	@Override
	public List<Employee> findAll(boolean fullAssociation) throws DataAccessException {
		ResultSet rs;
		try {
			rs = this.findAllPS.executeQuery();
		} catch (SQLException e) {
			// e.printStackTsrace();
			throw new DataAccessException(DBMessages.COULD_NOT_READ_RESULTSET, e);
		}
		List<Employee> res = buildObjects(rs, fullAssociation);
		return res;
	}

	@Override
	public Employee insert(Employee employee) throws DataAccessException {
		// fname, minit, lname, ssn, bdate, address, sex, salary, super_ssn, dno
		try {
			insertPS.setString(1, employee.getFname());
			insertPS.setString(2, employee.getMinit());
			insertPS.setString(3, employee.getLname());
			insertPS.setString(4, employee.getSsn());
			insertPS.setDate(5, Date.valueOf(employee.getBdate()));
			insertPS.setString(6, employee.getAddress());
			insertPS.setString(7, employee.getSex());
			insertPS.setDouble(8, employee.getSalary());
			if (employee.getSupervisor() != null) {
				insertPS.setString(9, employee.getSupervisor().getSsn());
			} else {
				insertPS.setNull(9, Types.CHAR);
			}
			insertPS.setInt(10, employee.getDept().getDnumber());
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new DataAccessException(DBMessages.COULD_NOT_BIND_PS_VARS_INSERT, e);
		}
		try {
			insertPS.executeUpdate();
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new DataAccessException(DBMessages.COULD_NOT_INSERT, e);
		}

		return employee;
	}

}
