package controller;

import java.util.List;

import Db.MachineDb;
import model.Machine;

public class MachineController {
	private MachineDb db = new MachineDb();

//	public MachineController() throws DataAccessException {
//
//	}

	public Machine createMachine(String name, String type, String dimension)
			throws DataAccessException {

		return db.create(name, type, dimension);
	}

	public boolean updateMachine(String initialName, String initialType, String initialDimension, String name, String type, String dimension)
			throws DataAccessException {
		return db.update(initialName, initialType, initialDimension, name, type, dimension);
	}

	public boolean deleteMachine(int id) throws DataAccessException {
		return db.delete(id);
	}

	public Machine findMachine(int id) throws DataAccessException {
		return db.findById(id);
	}

	public List<Machine> getMachine() throws DataAccessException {
		return db.getAll();
	}
}
