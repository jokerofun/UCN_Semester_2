package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
	private Connection connection = null; // the connection to the database
	private static DBConnection dbConnection; // unique instance of the class

	// information about the connection to the database
	private static final String dbName = "minibank";
	private static final String serverName = "localhost";
	private static final String instanceName = "SQLEXPRESS17";
	private static final String portNumber = "1433";

	// constructor - private because of singleton pattern
	private DBConnection() {
		String urlString = String.format("jdbc:sqlserver://%s\\%s:%s;databaseName=%s", serverName, instanceName,
				portNumber, dbName);

		String userName = getUserName();
		String password = getPassword();

		try {
			connection = DriverManager.getConnection(urlString, userName, password);
		} catch (SQLException e) {
			System.err.println("Could not connect to database ");
			System.err.println("URl string was: " + urlString);
			e.printStackTrace();
		}

	}

	public static DBConnection getInstance() {
		if (dbConnection == null) {
			dbConnection = new DBConnection();
		}
		return dbConnection;
	}

	public Connection getConnection() {
		return connection;
	}

	public void disconnect() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int executeUpdate(String sql) throws SQLException {
		int res = -1;
		try (Statement s = connection.createStatement()) {
			res = s.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return res;
	}

	/* return the generated key */
	public int executeInsertWithIdentity(String sql) throws SQLException {

		int res = -1;
		try (Statement s = connection.createStatement()) {
			res = s.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
			if (res > 0) {
				ResultSet rs = s.getGeneratedKeys();
				rs.next();
				res = rs.getInt(1);
			}
			// s.close(); -- the try block does this for us now

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return res;
	}

	public void startTransaction() throws SQLException {
		connection.setAutoCommit(false);
	}

	public void commitTransaction() throws SQLException {
		connection.commit();
		connection.setAutoCommit(true);
	}

	public void rollbackTransaction() throws SQLException {
		connection.rollback();
		connection.setAutoCommit(true);
	}

	private String getUserName() {
		// here we should read from a configuration file or ...
		return "sa";
	}

	private String getPassword() {
		// here we should read from a configuration file or ...
		return "153759mM";
	}

}