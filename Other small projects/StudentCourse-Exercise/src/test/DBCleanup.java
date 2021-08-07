package test;

import java.sql.SQLException;
import java.sql.Statement;
import control.DataAccessException;

import db.DBConnection;
/**
 * Utility class used by all the @Before methods in the test suite
 * that access the database. All tables are dropped, re-created and 
 * filled with data. By dropping the table, we reset the auto-generated
 * indices s.t. IDENTITY(1,1) starts on 1 and we can also count on the
 * ids of the records to be the same in all cases.
 * @author knol
 * @version 2017-02-20
 * @author gibe
 * @version feb. 2018
 * @author gibe
 * @version feb. 2020
 *
 */
public class DBCleanup {
	public static void main(String[] args) throws SQLException, DataAccessException {
		cleanDB(); // call to the utility class that resets the database
		System.out.println("cleaned");
		restoreDB();
		System.out.println("restored");
	}


	public static void cleanDB() throws SQLException, DataAccessException {
		Statement stmt = DBConnection.getInstance().getConnection().createStatement();
		e(stmt, "drop table if exists dbo.Student; drop table if exists dbo.Team; drop table if exists dbo.Mentor");
		stmt.close();
	}

	public static void restoreDB() throws DataAccessException {

		try (Statement stmt = DBConnection.getInstance().getConnection().createStatement()) {
			for(int i = 0 ; i < script.length; i++) {
				e(stmt, script[i]);
			}
		} catch (SQLException e) {
			throw new DataAccessException("Could not connect and restore the db", e);
		} 
	}

	private static void e(Statement stmt, String sql) throws SQLException {
		stmt.executeUpdate(sql);
	}

	private static final String[] script = {

"CREATE TABLE dbo.Mentor (ID int PRIMARY KEY IDENTITY(100,1), [Name] VARCHAR(25) NOT NULL)",
"CREATE TABLE dbo.Team (ID int PRIMARY KEY NOT NULL, Prefix VARCHAR(25) NOT NULL,  [Description] VARCHAR(100) NULL)",
"CREATE TABLE dbo.Student (ID int PRIMARY KEY IDENTITY(100,1),[Name] VARCHAR(25) NOT NULL," + 
	"MentorID int NULL,TeamID int NULL," + 
	"CONSTRAINT StudentMentorFK  FOREIGN KEY (MentorID) REFERENCES Mentor(ID)ON DELETE SET NULL, " +
	"CONSTRAINT StudentTeamFK FOREIGN KEY (TeamID) REFERENCES Team(ID)ON DELETE SET NULL)",

	"INSERT INTO Team(ID, Prefix) VALUES(100,'dmai0920')",
	"INSERT INTO Team(ID, Prefix) VALUES(101,'dmaj0920')",
	"INSERT INTO Mentor(Name) VALUES('Mark')",
	"INSERT INTO Mentor(Name) VALUES('Mary')",
	"INSERT INTO Mentor( Name) VALUES('Max')",
	"INSERT INTO Student(Name) VALUES('Simon')",
	"INSERT INTO Student(Name, MentorID, TeamID) VALUES('Sebastian',102,100)",
	"INSERT INTO Student(Name, MentorID, TeamID) VALUES('Sarah',101,100)",	
	"INSERT INTO Student(Name, MentorID, TeamID) VALUES('Steven',100,101)"	
	};
}