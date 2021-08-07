package db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import control.DataAccessException;
import model.Mentor;
import model.Student;

public class StudentDb implements StudentDbIF {

	@Override
	public Student createWithIdentity(String name, boolean fullAssociation) throws DataAccessException {
		Student student = null;
		String sql = String.format("insert into Student (Name) values ('%s')", name);
		int id = DBConnection.getInstance().executeInsertWithIdentity(sql);
		student = findById(id, fullAssociation);
		return student;
	}

	@Override
	public Student update(Student student) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(int id) throws DataAccessException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Student findById(int id, boolean fullAssociation) throws DataAccessException {
		Student res = null;
		String sql = "select * from dbo.Student where ID =  " + id;
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

	private Student buildObject(ResultSet rs, boolean fullAssociation) throws DataAccessException {
		Student s = null;
		Mentor m = null;
		MentorDb mentorDb = new MentorDb();
		try {
			s = new Student(rs.getInt("ID"), rs.getString("Name"));
			int mentorId = rs.getInt("MentorID");
			if (fullAssociation) {
				m = mentorDb.findById(mentorId);
			} else {
				m = new Mentor(mentorId);
			}
			s.setMentor(m);
		} catch (SQLException e) {
			throw new DataAccessException("Could not read result set", e);
		}
		return s;
	}

	@Override
	public List<Student> findByName(String name, boolean fullAssociation) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Student> getAll(boolean fullAssociation) throws DataAccessException {
		List<Student> students = new ArrayList<>();
		String sql = String.format("select * from Student", "");
		try (Statement s = DBConnection.getInstance().getConnection().createStatement();
				ResultSet rs = s.executeQuery(sql)) {
			while (rs.next()) {
				students.add(buildObject(rs, false));
			}
		} catch (SQLException e) {
			throw new DataAccessException("Could not read result set", e);
		}
		return null;
	}

	@Override
	public List<Student> findByTeamId(int teamId, boolean fullAssociation) throws DataAccessException {
		return null;
	}

}
