package controller;

import java.math.BigDecimal;
import java.util.List;

import Db.ProcedureDb;
import model.Procedure;
import model.ProcedureEntry;

public class ProcedureController {

	private ProcedureDb db;

	public ProcedureController() throws DataAccessException {
		db = new ProcedureDb();
	}

	public Procedure createProcedure(String procedureName, BigDecimal pricePerHour, String employeeTypeRequired)
			throws DataAccessException {
		return db.create(procedureName, pricePerHour, employeeTypeRequired);
	}

	public boolean updateProcedure(String procedureName, BigDecimal pricePerHour, int code, String employeeTypeRequired)
			throws DataAccessException {
		return db.update(procedureName, pricePerHour, code, employeeTypeRequired);
	}

	public boolean updateDone(int code) throws DataAccessException {
		return db.updateDone(code);
	}

	public boolean updateInWork(int code) throws DataAccessException {
		return db.updateInWork(code);
	}

	public boolean updateNotDone(int code) throws DataAccessException {
		return db.updateNotDone(code);
	}

	public boolean deleteProcedure(int code) throws DataAccessException {
		return db.delete(code);
	}

	public List<Procedure> findProcedureByName(String procedureName) throws DataAccessException {
		return db.findByName(procedureName);
	}

	public Procedure findProcedureByCode(int code) throws DataAccessException {
		return db.findByCode(code);
	}

	public List<Procedure> getAllProcedures() throws DataAccessException {
		return db.getAll();
	}

	public List<ProcedureEntry> getAllProductAndProcedureToIt(String productName) throws DataAccessException {
		return db.getAllProductAndProcedureToIt(productName);
	}
}
