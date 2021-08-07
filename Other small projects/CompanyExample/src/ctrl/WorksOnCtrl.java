package ctrl;

import java.util.ArrayList;
import java.util.List;

import db.WorksOnDB;
import db.WorksOnDBIF;
import model.Employee;
import model.WorksOn;

/**
 * 
 * @author knol
 * @version 2018-08-30
 */
public class WorksOnCtrl {

	private WorksOnDBIF worksOnDB;

	public WorksOnCtrl() throws DataAccessException {
		worksOnDB = new WorksOnDB();
	}

	public List<WorksOn> getWorksOnList(Employee employee, boolean fullAssociation) throws DataAccessException {
		List<WorksOn> res = new ArrayList<>(0);
		if (employee != null) {
			res = worksOnDB.findByEmployee(employee, true);
		}
		return res;
	}

}
