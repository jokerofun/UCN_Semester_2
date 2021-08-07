package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ctrl.DataAccessException;
import model.Employee;
import model.Project;
import model.WorksOn;

/**
 * 
 * @author knol
 * @version 2018-08-30
 */
public class WorksOnDB implements WorksOnDBIF {
	private static final String FIND_ALL_Q = "select essn, pno, hours from works_on";
	private static final String FIND_BY_ESSN_Q = FIND_ALL_Q + " where essn = ?";

	private PreparedStatement findAllPS;
	private PreparedStatement findByEssnPS;
	private ProjectDBIF projectDB;
	private EmployeeDBIF employeeDB;

	public WorksOnDB() throws DataAccessException {
		projectDB = new ProjectDB();
		employeeDB = new EmployeeDB();
		Connection connection = DBConnection.getInstance().getConnection();
		try {
			findAllPS = connection.prepareStatement(FIND_ALL_Q);
			findByEssnPS = connection.prepareStatement(FIND_BY_ESSN_Q);
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new DataAccessException(DBMessages.COULD_NOT_PREPARE_STATEMENT, e);
		}
	}

	@Override
	public List<WorksOn> findByEmployee(Employee employee, boolean fullAssociation) throws DataAccessException {
		List<WorksOn> res = new ArrayList<>(0);
		if (employee != null) {
			try {
				findByEssnPS.setString(1, employee.getSsn());
				ResultSet rs = findByEssnPS.executeQuery();
				res = buildObjects(rs, fullAssociation);
			} catch (SQLException e) {
				// e.printStackTrace();
				throw new DataAccessException(DBMessages.COULD_NOT_BIND_OR_EXECUTE_QUERY, e);
			}
		}
		return res;
	}

	private List<WorksOn> buildObjects(ResultSet rs, boolean fullAssociation) throws DataAccessException {
		List<WorksOn> res = new ArrayList<>();
		try {
			while (rs.next()) {
				WorksOn currWorksOn = buildObject(rs, fullAssociation);
				res.add(currWorksOn);
			}
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new DataAccessException(DBMessages.COULD_NOT_READ_RESULTSET, e);
		}
		return res;
	}

	private WorksOn buildObject(ResultSet rs, boolean fullAssociation) throws DataAccessException {
		WorksOn res = new WorksOn();
		try {
			res.setEmp(new Employee(rs.getString("essn")));
			res.setProject(new Project(rs.getInt("pno")));
			res.setHours(rs.getDouble("hours"));
			if (fullAssociation) {
				Project currProject = projectDB.findByPnumber(res.getProject().getPnumber(), false);
				res.setProject(currProject);
				Employee currEmployee = employeeDB.findBySSN(res.getEmployee().getSsn(), false);
				res.setEmp(currEmployee);
			}
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new DataAccessException(DBMessages.COULD_NOT_READ_RESULTSET, e);
		}
		return res;
	}

}
