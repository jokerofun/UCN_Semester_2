package Db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import controller.DataAccessException;
import model.Tool;

public class ToolDb implements ToolDbIF {
	// private DBConnection dbConnection = DBConnection.getInstance();

//	public ToolDb() throws DataAccessException {
//	}

	@Override
	public Tool create(String name, String type, float length, float diameter, float wear, int inStockQuantity)
			throws DataAccessException {
		DBConnection dbConnection = DBConnection.getInstance();
		Tool tool = null;
		String sql;
		try {
			dbConnection.startTransaction();
			sql = String.format(
					"insert into dbo.Tool(toolName,toolType,toolLength,toolDiameter,toolWear,toolQuantity,version) values('%s','%s','%f','%f','%f','%d','%d')",
					name, type, length, diameter, wear, inStockQuantity, 1);
			int id = dbConnection.executeInsertWithIdentity(sql);
			tool = findById(id);
			// ???execute after executewithidentity?
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
		return tool;
	}

	@Override
	public Tool findById(int id) throws DataAccessException {
		DBConnection dbConnection = DBConnection.getInstance();
		Tool tool = null;
		String sql = "select * from dbo.tool where toolId = " + id;
		try (Statement s = dbConnection.getConnection().createStatement(); ResultSet rs = s.executeQuery(sql)) {
			if (rs.next()) {
				tool = buildObject(rs);
			}
		} catch (SQLException e) {
			throw new DataAccessException("Could not read result set", e);
		}
		return tool;
	}

	private int findVersion(int id) throws DataAccessException {
		DBConnection dbConnection = DBConnection.getInstance();
		String sql = String.format("select version from dbo.Tool where toolId = '%d'", id);
		int version = dbConnection.executeUpdate(sql);
		return version;
	}

	private int findId(String name, String type, float length, float diameter) throws DataAccessException {
		DBConnection dbConnection = DBConnection.getInstance();
		String sql = String.format(
				"select id from dbo.Tool where toolName = '%s',toolType = '%s' toolLength = '%f',toolDiameter = '%f'",
				name, type, length, diameter);
		int id = dbConnection.executeUpdate(sql);
		return id;
	}

	@Override
	public boolean update(String initialName, String initialType, float initialLength, float initialDiameter,
			String name, String type, float length, float diameter, float wear, int inStockQuantity)
			throws DataAccessException {
		DBConnection dbConnection = DBConnection.getInstance();
		int id = findId(initialName, initialType, initialLength, initialDiameter);
		int version;
		int res;
		String sql = "update dbo.Tool set ";
		try {
			version = findVersion(id);
			String sql1 = "update dbo.Tool set version='" + version + 1 + "'";
			dbConnection.startTransaction();
			if (name != null) {
				sql += "toolName='" + name + "'";
			}
			if (type != null) {
				sql += "toolType='" + type + "'";
			}
			// -1 means not changed
			if (length != -1) {
				sql += "toolLength='" + length + "'";
			}
			if (diameter != -1) {
				sql += "toolDiameter='" + diameter + "'";
			}
			if (wear != -1) {
				sql += "toolWear='" + wear + "'";
			}
			if (inStockQuantity != -1) {
				sql += "toolQuantity='" + inStockQuantity + "'";
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
			throw new DataAccessException("Could not update tool", e);
		}
		return res > 0;
	}

	@Override
	public boolean delete(int id) throws DataAccessException {
		String sql = "delete from dbo.Tool where toolId = " + id;
		int res = DBConnection.getInstance().executeUpdate(sql);
		return res > 0;
	}

	@Override
	public List<Tool> getAll() throws DataAccessException {
		ResultSet rs;
		List<Tool> l;
		String sql = "select * from dbo.tool";
		try (Statement s = DBConnection.getInstance().getConnection().createStatement()) {
			rs = s.executeQuery(sql);
			l = buildObjects(rs);
		} catch (SQLException e) {
			throw new DataAccessException("Could not execute query", e);
		}
		return l;
	}

	private Tool buildObject(ResultSet rs) throws DataAccessException {
		Tool tool;
		try {
			int id = rs.getInt("toolId");
			String name = rs.getString("toolName");
			String type = rs.getString("toolType");
			float length = rs.getFloat("toolLength");
			float diameter = rs.getFloat("toolDiameter");
			float wear = rs.getFloat("toolWear");
			int quantity = rs.getInt("toolQuantity");
			tool = new Tool(name, type, id, length, diameter, wear, quantity);
		} catch (SQLException e) {
			throw new DataAccessException("Could not read result set - single object", e);
		}
		return tool;
	}

	private List<Tool> buildObjects(ResultSet rs) throws DataAccessException {
		ArrayList<Tool> list = new ArrayList<>();
		try {
			while (rs.next()) {
				Tool b = buildObject(rs);
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
