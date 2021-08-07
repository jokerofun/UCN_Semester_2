package bt._safe;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class InjectTest {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		Connection con = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;databaseName=school;user=sa;password=masterkey");
		System.out.println("connected");
		
		// Just test the query
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery("select * from students;");
		displayResultSet(rs);
		rs.close();
		
		// Test with a parametrized update
		String name = "Robert";
		PreparedStatement prepS = con.prepareStatement("insert into students values(?, ?, ?, ?);");
		prepS.setString(1, "Nerdy Mom");
		prepS.setDate(2, Date.valueOf(LocalDate.of(2008, 8, 8)));
		prepS.setString(3, "3-14 3-14");
		prepS.setString(4, name);
		prepS.execute();
		rs = s.executeQuery("select * from students;");
		displayResultSet(rs);
		rs.close();
		
		// Test with the full name of "Robert"
		name = "Robert'); drop table students; --";
		// reusing the old prepared statement for a new insert!
		prepS.setString(1, "Nerdy Mom");
		prepS.setDate(2, Date.valueOf(LocalDate.of(2009, 10, 11)));
		prepS.setString(3, "3-14 3-14");
		prepS.setString(4, name);
		prepS.execute();
		rs = s.executeQuery("select * from students;");
		displayResultSet(rs);
		rs.close();		
		
		prepS.close();
		s.close();
		con.close();
	}
	
	private static void displayResultSet(ResultSet rs) throws SQLException {
		while(rs.next()) {
			int colCount = rs.getMetaData().getColumnCount();
			String output = "";
			for(int i = 1; i <= colCount; i++){
				output += rs.getString(i) + "\t";
			}
			System.out.println(output);
		}
		System.out.println("-----------------------------------------------------------------");

	}
}
