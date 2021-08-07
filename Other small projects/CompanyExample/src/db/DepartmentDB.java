package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public class DepartmentDB implements DepartmentDBIF {
	private EmployeeDBIF employeeDB;

	private static final String FIND_ALL_Q = "select dname, dnumber, mgr_ssn, mgr_start_date from department";
	private PreparedStatement findAllPS;

	private static final String FIND_BY_DNUMBER_Q = FIND_ALL_Q + " where dnumber = ?";
	private PreparedStatement findByDnumberPS;

	public DepartmentDB(EmployeeDB employeeDB) throws DataAccessException {
		init();
		this.employeeDB = employeeDB;
	}

	public DepartmentDB() throws DataAccessException {
		init();
		this.employeeDB = new EmployeeDB(this);
	}

	private void init() throws DataAccessException {
		Connection connection = DBConnection.getInstance().getConnection();
		try {
			findAllPS = connection.prepareStatement(FIND_ALL_Q);
			findByDnumberPS = connection.prepareStatement(FIND_BY_DNUMBER_Q);
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new DataAccessException(DBMessages.COULD_NOT_PREPARE_STATEMENT, e);
		}
	}

	@Override
	public Department findByDnumber(int dnumber, boolean fullAssociation) throws DataAccessException {
		Department res = null;
		try {
			findByDnumberPS.setInt(1, dnumber);
			ResultSet rs = findByDnumberPS.executeQuery();
			if (rs.next()) {
				res = buildObject(rs, fullAssociation);
			}
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new DataAccessException(DBMessages.COULD_NOT_BIND_OR_EXECUTE_QUERY, e);
		}
		return res;
	}

	private List<Department> buildObjects(ResultSet rs, boolean fullAssociation) throws DataAccessException {
		List<Department> res = new ArrayList<>();
		try {
			while (rs.next()) {
				Department currDepartment = buildObject(rs, fullAssociation);
				res.add(currDepartment);
			}
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new DataAccessException(DBMessages.COULD_NOT_READ_RESULTSET, e);
		}
		return res;
	}

	private Department buildObject(ResultSet rs, boolean fullAssociation) throws DataAccessException {
		Department res = new Department();
		try {
			res.setDname(rs.getString("dname"));
			res.setDnumber(rs.getInt("dnumber"));
			res.setManager(new Employee(rs.getString("mgr_ssn")));
			res.setStartDate(rs.getString("mgr_start_date"));
			if (fullAssociation) {
				Employee manager = employeeDB.findBySSN(res.getManager().getSsn(), false);
				res.setManager(manager);
			}
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new DataAccessException(DBMessages.COULD_NOT_READ_RESULTSET, e);
		}
		return res;
	}

	@Override
	public List<Department> findAll(boolean fullAssociation) throws DataAccessException {
		List<Department> res = new ArrayList<>(0);

		try {
			ResultSet rs = this.findAllPS.executeQuery();
			res = buildObjects(rs, fullAssociation);
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new DataAccessException(DBMessages.COULD_NOT_READ_RESULTSET, e);
		}
		return res;
	}
}
