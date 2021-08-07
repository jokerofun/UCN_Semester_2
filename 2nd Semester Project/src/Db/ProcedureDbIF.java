package Db;

import java.math.BigDecimal;
import java.util.List;

import controller.DataAccessException;
import model.Procedure;
import model.ProcedureEntry;

public interface ProcedureDbIF {

	Procedure create(String procedureName, BigDecimal pricePerHour, String employeeTypeRequired)
			throws DataAccessException;

	List<Procedure> findByName(String procedureName) throws DataAccessException;

	Procedure findByCode(int code) throws DataAccessException;

	boolean updateDone(int code) throws DataAccessException;

	boolean updateInWork(int code) throws DataAccessException;

	boolean updateNotDone(int code) throws DataAccessException;

	boolean update(String procedureName, BigDecimal pricePerHour, int code, String employeeTypeRequired)
			throws DataAccessException;

	boolean delete(int code) throws DataAccessException;

	List<Procedure> getAll() throws DataAccessException;

	List<ProcedureEntry> getAllProductAndProcedureToIt(String productName) throws DataAccessException;
}
