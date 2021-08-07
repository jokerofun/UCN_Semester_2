package ctrl;

import java.util.List;

import db.DepartmentDB;
import db.DepartmentDBIF;
import model.Department;
/**
 * 
 * @author knol
 * @version 2018-08-30
 */
public class DepartmentCtrl {
	private DepartmentDBIF deptDB;

	public DepartmentCtrl() throws DataAccessException {
		deptDB = new DepartmentDB();
	}

	public List<Department> findAll() throws DataAccessException {
		List<Department> res = this.deptDB.findAll(true);
		return res;
	}
}
