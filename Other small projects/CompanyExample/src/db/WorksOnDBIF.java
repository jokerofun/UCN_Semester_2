package db;

import java.util.List;

import ctrl.DataAccessException;
import model.Employee;
import model.WorksOn;

/**
 * 
 * @author knol
 * @version 2018-08-30
 */
public interface WorksOnDBIF {
	List<WorksOn> findByEmployee(Employee employee, boolean fullAssociation) throws DataAccessException;
}
