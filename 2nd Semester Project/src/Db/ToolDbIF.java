package Db;

import java.util.List;

import controller.DataAccessException;
import model.Tool;

public interface ToolDbIF {

	Tool create(String name, String type, float length, float diameter, float wear, int inStockQuantity)
			throws DataAccessException;

	Tool findById(int id) throws DataAccessException;

	boolean update(String initialName, String initialType, float initialLength, float initialDiameter, String name,
			String type, float length, float diameter, float wear, int inStockQuantity) throws DataAccessException;

	boolean delete(int id) throws DataAccessException;

	List<Tool> getAll() throws DataAccessException;
}
