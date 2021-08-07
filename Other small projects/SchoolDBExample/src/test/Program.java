package test;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Program {// The values of the constants should be read from a configuration file, etc.
	// You need to change the constants to the connection properties of your DB
	private static final String DBNAME = "school";
	private static final String SERVERNAME = "localhost";
	private static final String PORTNUMBER = "1433";
	private static final String USERNAME = "sa";
	private static final String PASSWORD = "153759mM";

	public static void main(String[] args) {/*
											 * format URL
											 * https://docs.microsoft.com/en-us/sql/connect/jdbc/building-the-connection
											 * -url?view=sql-server-ver15
											 */
		String urlString = String.format("jdbc:sqlserver://%s:%s;databaseName=%s", SERVERNAME, PORTNUMBER, DBNAME);
		new Program().connectAndGetMetadata(urlString, USERNAME, PASSWORD);
	}

	public void connectAndGetMetadata(String urlString, String username, String password) {
		try (Connection connection = DriverManager.getConnection(urlString, username, password)) {
			DatabaseMetaData metadata = connection.getMetaData();
			printMetaData(metadata);
			connectToAndQueryDatabase(urlString, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void printMetaData(DatabaseMetaData metadata) throws SQLException {
		System.out.println("Database Product Name: " + metadata.getDatabaseProductName());
		System.out.println("Database Product Version: " + metadata.getDatabaseProductVersion());
		System.out.println("Logged User: " + metadata.getUserName());
		System.out.println("JDBC Driver: " + metadata.getDriverName());
		System.out.println("Driver Version: " + metadata.getDriverVersion());
		System.out.println("\n");
	}

	// Depends on the Table, Columns
	public void connectToAndQueryDatabase(String urlString, String username, String password) {
		try (Connection con = DriverManager.getConnection(urlString, username, password);
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM student")) {
			while (rs.next()) {
				int id = rs.getInt("studentID");
				String name = rs.getString("studentName");

				System.out.println("Name, id: \t" + name + "\t" + id);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
