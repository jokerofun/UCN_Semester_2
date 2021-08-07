package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import control.DataAccessException;

public class DBConnection {
	private Connection connection = null; // the connection to the database
	private static DBConnection dbConnection; // unique instance of the class

	// information about the connection to the database
	private static final String DBNAME = "School";
	private static final String SERVERNAME = "localhost";
	private static final String PORTNUMBER = "1433";
	private static final String USERNAME = "sa";
	private static final String PASSWORD = "153759mM";

	// constructor - private because of singleton pattern
	private DBConnection() throws DataAccessException {
		String urlString = String.format("jdbc:sqlserver://%s:%s;databaseName=%s", SERVERNAME, PORTNUMBER, DBNAME);

		try {
			connection = DriverManager.getConnection(urlString, USERNAME, PASSWORD);
		} catch (SQLException e) {
			throw new DataAccessException(String.format("Could not connect to database %s@%s:%s user %s", DBNAME,
					SERVERNAME, PORTNUMBER, USERNAME), e);

		}

	}

	public static DBConnection getInstance() throws DataAccessException {
		if (dbConnection == null) {
			dbConnection = new DBConnection();
		}
		return dbConnection;
	}

	public Connection getConnection() {
		return connection;
	}

	public void disconnect() throws DataAccessException {
		try {
			connection.close();
		} catch (SQLException e) {
			throw new DataAccessException(String.format("Could not close connection to the database %s@%s:%s user %s",
					DBNAME, SERVERNAME, PORTNUMBER, USERNAME), e);

		}
	}

	public int executeUpdate(String sql) throws DataAccessException {
		int res = -1;
		try (Statement s = connection.createStatement()) {
			res = s.executeUpdate(sql);
		} catch (SQLException e) {
			throw new DataAccessException("Could not execute update", e);

		}
		return res;
	}

	/* return the generated key */
	public int executeInsertWithIdentity(String sql) throws DataAccessException {
		ResultSet rs;
		int generatedKey = -1;
		try (Statement s = connection.createStatement()) {
			s.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
			rs = s.getGeneratedKeys();
			if (rs.next()) {
				generatedKey = rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new DataAccessException("Could not execute update", e);
		}
		return generatedKey;
	}
}
