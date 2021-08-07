package Db;

import java.util.List;

import controller.DataAccessException;
import model.Position;

public interface PositionDbIF {

	Position create(String positionName, String clearance) throws DataAccessException;

	Position findByName(String name) throws DataAccessException;

	boolean update(String positionName, String clearance) throws DataAccessException;

	boolean delete(String positionName) throws DataAccessException;

	List<Position> getAll() throws DataAccessException;
}
