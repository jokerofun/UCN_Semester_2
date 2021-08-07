package Db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controller.DataAccessException;
import model.Entry;

public class ProcedureODDb implements ProcedureODDbIF {
	private DBConnection dbConnection;

	public ProcedureODDb() throws DataAccessException {
		dbConnection = DBConnection.getInstance();
	}

	@Override
	public void create(int procedureA, int procedureB) throws DataAccessException {
		String sql;
		try {
			dbConnection.startTransaction();
			sql = String.format("insert into dbo.ProcedureOrder(procedureA,procedureB) values('%d','%d')", procedureA,
					procedureB);
			dbConnection.executeUpdate(sql);
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
	}

	@Override
	public ArrayList<Entry> getAllEntries() throws DataAccessException {
		ArrayList<Entry> entryList = new ArrayList<>();
		String sql;
		ResultSet rs;
		try (Statement s = dbConnection.getConnection().createStatement()) {
			sql = String.format("select * from ProcedureOrder");
			rs = s.executeQuery(sql);
			while (rs.next()) {
				Entry entry = new Entry(rs.getInt("procedureA"), rs.getInt("procedureB"));
				entryList.add(entry);
			}
		} catch (SQLException e) {
			throw new DataAccessException("Could not read result set", e);
		}
		return entryList;
	}

}
