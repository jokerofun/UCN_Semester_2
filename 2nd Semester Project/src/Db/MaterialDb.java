package Db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import controller.DataAccessException;
import model.Material;

public class MaterialDb implements MaterialDbIF {
	// private DBConnection dbConnection = DBConnection.getInstance();

//	public MaterialDb() throws DataAccessException {
//	}

	@Override
	public Material create(String name, String type, String dimension)
			throws DataAccessException {
		DBConnection dbConnection = DBConnection.getInstance();
		Material material = null;
		String sql;
		try {
			dbConnection.startTransaction();
			sql = String.format(
					"insert into dbo.Material(materialName,materialType,materialDimension,version) values('%s','%s','%s,'%d')",
					name, type, dimension);
			int id = dbConnection.executeInsertWithIdentity(sql);
			material = findById(id);
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
		return material;
	}

	@Override
	public Material findById(int id) throws DataAccessException {
		DBConnection dbConnection = DBConnection.getInstance();
		Material material = null;
		String sql = "select * from dbo.material where materialId = " + id;
		try (Statement s = dbConnection.getConnection().createStatement(); ResultSet rs = s.executeQuery(sql)) {
			if (rs.next()) {
				material = buildObject(rs);
			}
		} catch (SQLException e) {
			throw new DataAccessException("Could not read result set", e);
		}
		return material;
	}

	private int findVersion(int id) throws DataAccessException {
		DBConnection dbConnection = DBConnection.getInstance();
		String sql = String.format("select version from dbo.Material where MaterialId = '%d'", id);
		int version = dbConnection.executeUpdate(sql);
		return version;
	}

	private int findId(String name, String type, String dimension) throws DataAccessException {
		DBConnection dbConnection = DBConnection.getInstance();
		String sql = String.format(
				"select id from dbo.Material where materialName = '%s',materialType = '%s' materialDimension = '%s'",
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
		String sql = "update dbo.Material set ";
		try {
			version = findVersion(id);
			String sql1 = "update dbo.Tool set version='" + version + 1 + "'";
			dbConnection.startTransaction();
			if (name != null) {
				sql += "materialName='" + name + "'";
			}
			if (type != null) {
				sql += "materialType='" + type + "'";
			}
			if (dimension != null) {
				sql += "materialDimension='" + dimension + "'";
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
	public List<Material> getAll() throws DataAccessException {
		ResultSet rs;
		List<Material> l;
		String sql = "select * from dbo.material";
		try (Statement s = DBConnection.getInstance().getConnection().createStatement()) {
			rs = s.executeQuery(sql);
			l = buildObjects(rs);
		} catch (SQLException e) {
			throw new DataAccessException("Could not execute query", e);
		}
		return l;
	}

	private Material buildObject(ResultSet rs) throws DataAccessException {
		Material material;
		try {
			int id = rs.getInt("materialId");
			String name = rs.getString("materialName");
			String type = rs.getString("materialType");
			String dimension = rs.getString("materialDimension");
			material = new Material(name, type, id, dimension);
		} catch (SQLException e) {
			throw new DataAccessException("Could not read result set - single object", e);
		}
		return material;
	}

	private List<Material> buildObjects(ResultSet rs) throws DataAccessException {
		ArrayList<Material> list = new ArrayList<>();
		try {
			while (rs.next()) {
				Material b = buildObject(rs);
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