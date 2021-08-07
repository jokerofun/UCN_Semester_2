package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import ctrl.DataAccessException;
import model.Department;
import model.Project;

/**
 * 
 * @author knol
 * @version 2018-08-30
 */
public class ProjectDB implements ProjectDBIF {
	private static final String FIND_ALL_Q = "select pname, pnumber, plocation, dnum from project";
	private static final String FIND_BY_PNUMBER_Q = FIND_ALL_Q + " where pnumber = ?";

	private PreparedStatement findAllPS;
	private PreparedStatement findByPnumber;
	private DepartmentDBIF departmentDB;

	public ProjectDB() throws DataAccessException {
		Connection connection = DBConnection.getInstance().getConnection();
		departmentDB = new DepartmentDB();
		try {
			findAllPS = connection.prepareStatement(FIND_ALL_Q);
			findByPnumber = connection.prepareStatement(FIND_BY_PNUMBER_Q);
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new DataAccessException(DBMessages.COULD_NOT_PREPARE_STATEMENT, e);
		}
	}

	@Override
	public Project findByPnumber(int pnumber, boolean fullAssociation) throws DataAccessException {
		Project currProject = null;
		try {
			findByPnumber.setInt(1, pnumber);
			ResultSet rs = findByPnumber.executeQuery();
			if (rs.next()) {
				currProject = buildObject(rs, fullAssociation);
			}
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new DataAccessException(DBMessages.COULD_NOT_READ_RESULTSET, e);
		}
		return currProject;
	}

	private Project buildObject(ResultSet rs, boolean fullAssociation) throws DataAccessException {
		Project res = new Project();
		try {
			res.setName(rs.getString("pname"));
			res.setPnumber(rs.getInt("pnumber"));
			res.setPlocation(rs.getString("plocation"));
			res.setDepartment(new Department(rs.getInt("dnum")));
			if (fullAssociation) {
				Department department = departmentDB.findByDnumber(res.getDepartment().getDnumber(), false);
				res.setDepartment(department);
			}
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new DataAccessException(DBMessages.COULD_NOT_READ_RESULTSET, e);
		}
		return res;
	}

}
