package controller;

import java.util.List;

import Db.MaterialDb;
import model.Material;

public class MaterialController {
	private MaterialDb db = new MaterialDb();

//	public MaterialController() throws DataAccessException {
//
//	}

	public Material createMaterial(String name, String type, String dimension)
			throws DataAccessException {

		return db.create(name, type, dimension);
	}

	public boolean updateMaterial(String initialName, String initialType, String initialDimension, String name, String type, String dimension)
			throws DataAccessException {
		return db.update(initialName, initialType, initialDimension, name, type, dimension);
	}

	public boolean deleteMaterial(int id) throws DataAccessException {
		return db.delete(id);
	}

	public Material findMaterial(int id) throws DataAccessException {
		return db.findById(id);
	}

	public List<Material> getMaterials() throws DataAccessException {
		return db.getAll();
	}
}
