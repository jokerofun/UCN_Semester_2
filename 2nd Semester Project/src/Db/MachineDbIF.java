package Db;

import java.util.List;

import controller.DataAccessException;
import model.Machine;

public interface MachineDbIF {

	Machine create(String name, String type, String dimension)
			throws DataAccessException;

	Machine findById(int id) throws DataAccessException;

	boolean update(String initialName, String initialType, String initialDimension, String name,
			String type, String dimension) throws DataAccessException;

	boolean delete(int id) throws DataAccessException;

	List<Machine> getAll() throws DataAccessException;
}