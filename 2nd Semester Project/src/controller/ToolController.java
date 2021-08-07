package controller;

import java.util.List;

import Db.ToolDb;
import model.Tool;

public class ToolController {
	private ToolDb db = new ToolDb();

//	public ToolController() throws DataAccessException {
//
//	}

	public Tool createTool(String name, String type, float length, float diameter, float wear, int inStockQuantity)
			throws DataAccessException {

		// I think we don't have to create the product here, we can just pass the things
		// to the db, cuz we don't need to create id manually
		// Tool tool = new Tool(name, type, id, length, diameter, wear,
		// inStockQuantity);
		return db.create(name, type, length, diameter, wear, inStockQuantity);
	}

	public boolean updateTool(String initialName, String initialType, float initialLength, float initialDiameter,
			String name, String type, float length, float diameter, float wear, int inStockQuantity)
			throws DataAccessException {
		return db.update(initialName, initialType, initialLength, initialDiameter, name, type, length, diameter, wear,
				inStockQuantity);
	}

	public boolean deleteTool(int id) throws DataAccessException {
		return db.delete(id);
	}

	public Tool findTool(int id) throws DataAccessException {
		return db.findById(id);
	}

	public List<Tool> getTools() throws DataAccessException {
		return db.getAll();
	}
}
