package Db;

import java.util.List;

import controller.DataAccessException;
import model.Material;

public interface MaterialDbIF {

	Material create(String name, String type, String dimension)
			throws DataAccessException;

	Material findById(int id) throws DataAccessException;

	boolean update(String initialName, String initialType, String initialDimension, String name,
			String type, String dimension) throws DataAccessException;

	boolean delete(int id) throws DataAccessException;

	List<Material> getAll() throws DataAccessException;
}