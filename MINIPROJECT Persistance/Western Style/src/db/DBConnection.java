package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import control.DataAccessException;

public class DBConnection {
	private Connection connection = null;
	private static DBConnection dbConnection;

	// TODO change SERVERNAME to Hildur
	private static final String DBNAME = "dmai0919_1081927";
	private static final String SERVERNAME = "Hildur.ucn.dk";
	private static final String PORTNUMBER = "1433";
	private static final String USERNAME = "dmai0919_1081927";
	private static final String PASSWORD = "Password1!";

	private DBConnection() throws DataAccessException {
		String urlString = String.format("jdbc:sqlserver://%s:%s;databaseName=%s", SERVERNAME, PORTNUMBER, DBNAME);

		try {
			connection = DriverManager.getConnection(urlString, USERNAME, PASSWORD);
		} catch (SQLException e) {
			throw new DataAccessException(String.format("Could not connect to database %s@%s:%s user %s", DBNAME,
					SERVERNAME, PORTNUMBER, USERNAME), e);
		}
	}

	public static synchronized DBConnection getInstance() throws DataAccessException {
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
		System.out.println("DBConnection, Inserting: " + sql);
		int res = -1;
		try (Statement s = connection.createStatement()) {
			res = s.executeUpdate(sql);
		} catch (SQLException e) {
			throw new DataAccessException("Could not execute update", e);
		}
		return res;
	}

	public int executeInsertWithIdentity(String sql) throws DataAccessException {
		System.out.println("DBConnection, Inserting: " + sql);
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

	public void startTransaction() throws DataAccessException {
		try {
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			throw new DataAccessException("Could not start transaction.", e);
		}
	}

	public void commitTransaction() throws DataAccessException {
		try {
			try {
				connection.commit();
			} catch (SQLException e) {
				throw e;
			} finally {
				connection.setAutoCommit(true);
			}
		} catch (SQLException e) {
			throw new DataAccessException("Could not commit transaction", e);
		}
	}

	public void rollbackTransaction() throws DataAccessException {
		try {
			try {
				connection.rollback();
			} catch (SQLException e) {
				throw e;
			} finally {
				connection.setAutoCommit(true);
			}
		} catch (SQLException e) {
			throw new DataAccessException("Could not rollback transaction", e);
		}
	}
}
