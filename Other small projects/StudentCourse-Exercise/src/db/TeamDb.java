package db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import control.DataAccessException;
import model.Student;
import model.Team;

public class TeamDb implements TeamDbIF {

	@Override
	public Team createWithIdentity(String prefix, String description) throws DataAccessException {
		Team team = null;
		String sql = String.format("insert into Team values ('%s')", prefix);
		int id = DBConnection.getInstance().executeInsertWithIdentity(sql);
		team = findById(id, false);
		return team;
	}

	@Override
	public Team update(Team team) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(int id) throws DataAccessException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Team findById(int id, boolean fullAssociation) throws DataAccessException {
		Team res = null;
		String sql = "select * from dbo.Team where ID= " + id;
		try (Statement s = DBConnection.getInstance().getConnection().createStatement();
				ResultSet rs = s.executeQuery(sql)) {
			if (rs.next()) {
				res = buildObject(rs, fullAssociation);
			}
		} catch (SQLException e) {
			throw new DataAccessException("Could not read result set", e);
		}
		return res;
	}

	public Team buildObject(ResultSet rs, boolean fullAssociation) throws DataAccessException {
		Team t = null;
		List<Student> s = null;
		// Student s = null;
		StudentDb studentDb = new StudentDb();
		try {
			t = new Team(rs.getInt("ID"), rs.getString("Prefix"), rs.getString("Description"));
			t.setListStudent(studentDb.findByTeamId(rs.getInt("ID"), false));
		} catch (SQLException e) {
			throw new DataAccessException("Could not reat result set", e);
		}
		return null;
	}

	@Override
	public Team findByPrefix(String prefix, boolean fullAssociation) throws DataAccessException {

		return null;
	}

	@Override
	public List<Team> getAll(boolean fullAssociation) throws DataAccessException {
		List<Team> teams = new ArrayList<>();
		String sql = String.format("select * from Team", "");
		try (Statement s = DBConnection.getInstance().getConnection().createStatement();
				ResultSet rs = s.executeQuery(sql)) {
			while (rs.next()) {
				teams.add(buildObject(rs, false));
			}
		} catch (SQLException e) {
			throw new DataAccessException("Could not read result set", e);
		}
		return null;
	}
}
