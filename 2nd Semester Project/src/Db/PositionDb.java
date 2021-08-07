package Db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import controller.DataAccessException;
import model.Position;

public class PositionDb implements PositionDbIF {

	private DBConnection dbConnection;

	private static final String FIND_POSITION = "select * from dbo.Position";
	private PreparedStatement findPosition;

	private static final String FIND_BY_NAME = FIND_POSITION + " where positionName = ?";
	private PreparedStatement findByName;

	private static final String FIND_VERSION = "select version from dbo.Position where positionName = ?";
	private PreparedStatement findVersion;

	private static final String INSERT_P = "insert into " + "dbo.Position "
			+ "(positionName, clearance, version) values (?,?,?)";
	private PreparedStatement insertPosition;

	private static final String UPDATE_POSITION = "update " + "dbo.Position "
			+ "set clearance = ?, version = ? where positionName = ? and version = ?";
	private PreparedStatement updatePosition;

	private static final String DELETE_POSITION = "delete from dbo.Position where positionName = ?";
	private PreparedStatement deletePosition;

	public PositionDb() throws DataAccessException {
		dbConnection = DBConnection.getInstance();
		init();
	}

	private void init() throws DataAccessException {
		Connection con = DBConnection.getInstance().getConnection();
		try {
			findPosition = con.prepareStatement(FIND_POSITION);
			findByName = con.prepareStatement(FIND_BY_NAME);
			findVersion = con.prepareStatement(FIND_VERSION);
			insertPosition = con.prepareStatement(INSERT_P);
			updatePosition = con.prepareStatement(UPDATE_POSITION);
			deletePosition = con.prepareStatement(DELETE_POSITION);

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new DataAccessException("Could not prepare statements", e);
		}
	}

	@Override
	public Position create(String positionName, String clearance) throws DataAccessException {
		Position position = null;
		for (int i = 0; i <= 5; i++) {
			try {
				dbConnection.startTransaction();

				insertPosition.setString(1, positionName);
				insertPosition.setString(2, clearance);
				insertPosition.setInt(3, 0);

				insertPosition.executeUpdate();

				dbConnection.commitTransaction();
				i = 5;

			} catch (SQLException e) {
				if (dbConnection.getConnection() != null) {
					System.err.println("Transaction is being rolled back" + i);
					dbConnection.rollbackTransaction();
				}
			}
		}

		position = findByName(positionName);

		return position;
	}

	@Override
	public Position findByName(String name) throws DataAccessException {
		Position res = null;
		try {
			findByName.setString(1, name);
			ResultSet rs = findByName.executeQuery();
			if (rs.next()) {
				res = buildObject(rs);
			}
		} catch (SQLException e) {
			throw new DataAccessException("Could not read result set", e);
		}
		return res;
	}

	private int findVersion(String name) throws DataAccessException {
		int version = -1;
		try {
			findVersion.setString(1, name);
			ResultSet rs = findVersion.executeQuery();
			if (rs.next()) {
				version = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new DataAccessException("Could not read result set", e);
		}
		return version;
	}

	@Override
	public boolean update(String positionName, String clearance) throws DataAccessException {
		int res = 0;
		try {
			dbConnection.startTransaction();
			int version = findVersion(positionName);

			updatePosition.setString(1, clearance);
			updatePosition.setInt(2, version + 1);
			updatePosition.setString(3, positionName);
			updatePosition.setInt(4, version);

			res += updatePosition.executeUpdate();

			dbConnection.commitTransaction();
		} catch (SQLException e) {
			if (dbConnection.getConnection() != null) {
				System.err.print("Transaction is being rolled back");
				dbConnection.rollbackTransaction();
			}
		}
		return res > 0;
	}

	@Override
	public boolean delete(String positionName) throws DataAccessException {
		try {
			deletePosition.setString(1, positionName);
			return deletePosition.executeUpdate() > 0;
		} catch (SQLException e) {
			throw new DataAccessException("Could not delete from database", e);
		}
	}

	@Override
	public List<Position> getAll() throws DataAccessException {
		ResultSet rs;
		List<Position> l;
		try {
			rs = findPosition.executeQuery();
			l = buildObjects(rs);
		} catch (SQLException e) {
			throw new DataAccessException("Could not execute query", e);
		}
		return l;
	}

	private Position buildObject(ResultSet rs) throws DataAccessException {
		Position position;
		try {
			String positionName = rs.getString("positionName");
			String clearance = rs.getString("clearance");

			position = new Position(positionName, clearance);
		} catch (SQLException e) {
			throw new DataAccessException("Could not read result set - single object", e);
		}
		return position;
	}

	private List<Position> buildObjects(ResultSet rs) throws DataAccessException {
		ArrayList<Position> list = new ArrayList<>();
		try {
			while (rs.next()) {
				Position p = buildObject(rs);
				list.add(p);
			}
		} catch (SQLException e) {
			throw new DataAccessException("Could not read result set - next()", e);
		} catch (DataAccessException e) {
			throw new DataAccessException("Could not build object", e);
		}
		return list;
	}

}
