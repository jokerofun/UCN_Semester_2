package Db;

import java.util.HashMap;

import controller.DataAccessException;
import model.Employee;
import model.Procedure;

public interface SchedulerDbIF {

	boolean insertEmployeeAndProcedure(HashMap<Integer, Integer> hash) throws DataAccessException;

	HashMap<Employee, Procedure> getEmployeeAndProcedure() throws DataAccessException;
}
