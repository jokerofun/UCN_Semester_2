package db;

import ctrl.DataAccessException;
import model.Project;

/**
 * 
 * @author knol
 * @version 2018-08-30
 */
public interface ProjectDBIF {
	Project findByPnumber(int pnumber, boolean fullAssociation) throws DataAccessException;
}
