package db;

import java.util.List;

import ctrl.DataAccessException;
import model.Employee;

/**
 * 
 * @author knol
 * @version 2018-08-30
 */
public interface EmployeeDBIF {
	List<Employee> findByName(String name, boolean fullAssociation) throws DataAccessException;
	Employee findBySSN(String ssn, boolean fullAssociation) throws DataAccessException;
	List<Employee> findAll(boolean fullAssociation) throws DataAccessException;
	Employee insert(Employee employee) throws DataAccessException;
}
