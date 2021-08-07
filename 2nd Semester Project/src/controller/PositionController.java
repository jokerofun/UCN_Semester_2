package controller;

import java.util.List;

import Db.PositionDb;
import model.Position;

public class PositionController {
	private PositionDb db;

	public PositionController() throws DataAccessException {
		db = new PositionDb();
	}

	public Position createPosition(String positionName, String clearance) throws DataAccessException {
		return db.create(positionName, clearance);
	}

	public boolean updatePosition(String positionName, String clearance) throws DataAccessException {
		return db.update(positionName, clearance);
	}

	public boolean deletePosition(String positionName) throws DataAccessException {
		return db.delete(positionName);
	}

	public Position findPositionByName(String name) throws DataAccessException {
		return db.findByName(name);
	}

	public List<Position> getAllPositions() throws DataAccessException {
		return db.getAll();
	}
}
