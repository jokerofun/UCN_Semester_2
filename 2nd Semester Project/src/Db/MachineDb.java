package Db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import controller.DataAccessException;
import model.Machine;

public class MachineDb implements MachineDbIF {
	// private DBConnection dbConnection = DBConnection.getInstance();

//	public MaterialDb() throws DataAccessException {
//	}

	@Override
	public Machine create(String name, String type, String dimension)
			throws DataAccessException {
		DBConnection dbConnection = DBConnection.getInstance();
		Machine machine = null;
		String sql;
		try {
			dbConnection.startTransaction();
			sql = String.format(
					"insert into dbo.Machine(machineName,machineType,machineDimension,version) values('%s','%s','%s,'%d')",
					name, type, dimension);
			int id = dbConnection.executeInsertWithIdentity(sql);
			machine = findById(id);
			dbConnection.commitTransaction();
		} catch (DataAccessException e) {
			System.out.println(e.getMessage());
			if (dbConnection.getConnection() != null) {
				try {
					dbConnection.rollbackTransaction();
				} catch (DataAccessException ee) {
					System.out.println(ee.getMessage());
					throw new DataAccessException("Could not rollback transaction", ee);
				}
			}
			throw new DataAccessException("Could not rollback transaction", e);
		}
		return machine;
	}

	@Override
	public Machine findById(int id) throws DataAccessException {
		DBConnection dbConnection = DBConnection.getInstance();
		Machine machine = null;
		String sql = "select * from dbo.machine where machineId = " + id;
		try (Statement s = dbConnection.getConnection().createStatement(); ResultSet rs = s.executeQuery(sql)) {
			if (rs.next()) {
				machine = buildObject(rs);
			}
		} catch (SQLException e) {
			throw new DataAccessException("Could not read result set", e);
		}
		return machine;
	}

	private int findVersion(int id) throws DataAccessException {
		DBConnection dbConnection = DBConnection.getInstance();
		String sql = String.format("select version from dbo.Machine where machineId = '%d'", id);
		int version = dbConnection.executeUpdate(sql);
		return version;
	}

	private int findId(String name, String type, String dimension) throws DataAccessException {
		DBConnection dbConnection = DBConnection.getInstance();
		String sql = String.format(
				"select id from dbo.Machine where machineName = '%s',machineType = '%s' machineDimension = '%s'",
				name, type, dimension);
		int id = dbConnection.executeUpdate(sql);
		return id;
	}

	@Override
	public boolean update(String initialName, String initialType, String initialDimension, String name, String type, String dimension)
			throws DataAccessException {
		DBConnection dbConnection = DBConnection.getInstance();
		int id = findId(initialName, initialType, initialDimension);
		int version;
		int res;
		String sql = "update dbo.Machine set ";
		try {
			version = findVersion(id);
			String sql1 = "update dbo.Machine set version='" + version + 1 + "'";
			dbConnection.startTransaction();
			if (name != null) {
				sql += "machineName='" + name + "'";
			}
			if (type != null) {
				sql += "machineType='" + type + "'";
			}
			if (dimension != null) {
				sql += "machineDimension='" + dimension + "'";
			}
			res = dbConnection.executeUpdate(sql);
			if (version == findVersion(id)) {
				dbConnection.executeUpdate(sql1);
				dbConnection.commitTransaction();
			} else {
				dbConnection.rollbackTransaction();
			}
		} catch (DataAccessException e) {
			if (dbConnection.getConnection() != null) {
				dbConnection.rollbackTransaction();
			}
			throw new DataAccessException("Could not update machine", e);
		}
		return res > 0;
	}

	@Override
	public boolean delete(int id) throws DataAccessException {
		String sql = "delete from dbo.Machine where machineId = " + id;
		int res = DBConnection.getInstance().executeUpdate(sql);
		return res > 0;
	}

	@Override
	public List<Machine> getAll() throws DataAccessException {
		ResultSet rs;
		List<Machine> l;
		String sql = "select * from dbo.machine";
		try (Statement s = DBConnection.getInstance().getConnection().createStatement()) {
			rs = s.executeQuery(sql);
			l = buildObjects(rs);
		} catch (SQLException e) {
			throw new DataAccessException("Could not execute query", e);
		}
		return l;
	}

	private Machine buildObject(ResultSet rs) throws DataAccessException {
		Machine machine;
		try {
			int id = rs.getInt("machineId");
			String name = rs.getString("machineName");
			String type = rs.getString("machineType");
			String dimension = rs.getString("machineDimension");
			machine = new Machine(name, type, id, dimension);
		} catch (SQLException e) {
			throw new DataAccessException("Could not read result set - single object", e);
		}
		return machine;
	}

	private List<Machine> buildObjects(ResultSet rs) throws DataAccessException {
		ArrayList<Machine> list = new ArrayList<>();
		try {
			while (rs.next()) {
				Machine b = buildObject(rs);
				list.add(b);
			}
		} catch (SQLException e) {
			throw new DataAccessException("Could not read result set - next()", e);
		} catch (DataAccessException e) {
			throw new DataAccessException("Could not build object", e);
		}
		return list;
	}
}