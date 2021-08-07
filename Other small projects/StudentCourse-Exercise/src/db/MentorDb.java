package db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import control.DataAccessException;
import model.Mentor;


public class MentorDb implements MentorDbIF {

	@Override
	public Mentor createWithIdentity(String name) throws DataAccessException {
		Mentor mentor = null;
		String sql = String.format("insert into Mentor (Name) values ('%s')", name);
		int id = DBConnection.getInstance().executeInsertWithIdentity(sql);
		mentor = findById(id);
		return mentor;
	}

	@Override
	public Mentor update(Mentor mentor) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(int id) throws DataAccessException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Mentor findById(int id) throws DataAccessException {
		Mentor res = null;
		String sql = "select * from dbo.Mentor where ID = " + id;
		try(Statement s = DBConnection.getInstance().getConnection().createStatement();
			ResultSet rs = s.executeQuery(sql)) {
			if(rs.next()) {
				res = buildObject(rs);
			}
		} catch (SQLException e) {
			throw new DataAccessException("Could not read result set", e);
		}
		return res;				
	}
	

	
	private Mentor buildObject(ResultSet rs) throws DataAccessException {
		Mentor b = null;
		try {
			b = new Mentor(rs.getInt("ID"),rs.getString("Name") );
		} catch (SQLException e) {
			throw new DataAccessException("Could not read result set", e);
		}
		return b;
	}

}
