package Db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import controller.DataAccessException;
import model.Employee;
import model.Procedure;

public class SchedulerDb implements SchedulerDbIF {

	private DBConnection dbConnection;
	private ProcedureDb procedureDb;
	private EmployeeDb employeeDb;

	private static final String INSERT_EMPLOYEEANDPROCEDURES = "insert into " + "dbo.employeeProcedure "
			+ "(employeeId, code) values (?,?)";
	private PreparedStatement insertEmployeeAndProcedure;

	private static final String GET_EMPLOYEEANDPROCEDURES = "select * from dbo.employeeProcedure";
	private PreparedStatement getEmployeeAndProcedure;

	public SchedulerDb() throws DataAccessException {
		dbConnection = DBConnection.getInstance();
		procedureDb = new ProcedureDb();
		employeeDb = new EmployeeDb();
		init();
	}

	private void init() throws DataAccessException {
		Connection con = DBConnection.getInstance().getConnection();
		try {
			insertEmployeeAndProcedure = con.prepareStatement(INSERT_EMPLOYEEANDPROCEDURES);
			getEmployeeAndProcedure = con.prepareStatement(GET_EMPLOYEEANDPROCEDURES);

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new DataAccessException("Could not prepare statements", e);
		}
	}
	
	public void reset() throws SQLException {
		String sql;
		sql = String.format("drop table employeeProcedure\r\n " +
		                        "create table employeeProcedure(\r\n" +
								"employeeId int, \r\n" + 
								"code int  \r\n" + 
								");\r\n");
		Statement s = dbConnection.getConnection().createStatement();
		s.execute(sql);
	}

	@Override
	public boolean insertEmployeeAndProcedure(HashMap<Integer, Integer> hash) throws DataAccessException {
		int res = 0;
		for (int i = 0; i <= 5; i++) {
			try {
				dbConnection.startTransaction();

				for (Map.Entry<Integer, Integer> entry : hash.entrySet()) {
					Integer key = entry.getKey();
					Integer value = entry.getValue();

					insertEmployeeAndProcedure.setInt(1, key);
					insertEmployeeAndProcedure.setInt(2, value);

					res = insertEmployeeAndProcedure.executeUpdate();
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

		return res > 0;
	}

	@Override
	public HashMap<Employee, Procedure> getEmployeeAndProcedure() throws DataAccessException {
		ResultSet rs;
		HashMap<Employee, Procedure> hash;
		try {
			rs = getEmployeeAndProcedure.executeQuery();
			hash = buildObjects(rs);
		} catch (SQLException e) {
			throw new DataAccessException("Could not execute query", e);
		}
		return hash;
	}

	private HashMap<Employee, Procedure> buildObjects(ResultSet rs) throws DataAccessException {
		HashMap<Employee, Procedure> hash = new HashMap<Employee, Procedure>();
		try {
			while (rs.next()) {
				Employee employee = employeeDb.findById(rs.getInt("employeeId"));
				Procedure procedure = procedureDb.findByCode(rs.getInt("code"));
				hash.put(employee, procedure);
			}
		} catch (SQLException e) {
			throw new DataAccessException("Could not read result set - next()", e);
		} catch (DataAccessException e) {
			throw new DataAccessException("Could not build object", e);
		}
		return hash;
	}

}
