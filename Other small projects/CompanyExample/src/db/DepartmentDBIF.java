package db;

import java.util.List;

import ctrl.DataAccessException;
import model.Department;

/**
 * 
 * @author knol
 * @version 2018-08-30
 */
public interface DepartmentDBIF {

	Department findByDnumber(int dnumber, boolean fullAssociation) throws DataAccessException;

	List<Department> findAll(boolean fullAssociation) throws DataAccessException;

}
