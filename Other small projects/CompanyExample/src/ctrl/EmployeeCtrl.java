package ctrl;

import java.util.List;

import db.EmployeeDB;
import db.EmployeeDBIF;
import model.Department;
import model.Employee;
import model.WorksOn;

/**
 * 
 * @author knol
 * @version 2018-08-30
 */
public class EmployeeCtrl {
	private EmployeeDBIF empDB;
	private DepartmentCtrl deptCtrl;
	private WorksOnCtrl worksOnCtrl;

	public EmployeeCtrl() throws DataAccessException {
		empDB = new EmployeeDB();
		deptCtrl = new DepartmentCtrl();
		worksOnCtrl = new WorksOnCtrl();
	}

	public List<Employee> findByName(String name) throws DataAccessException {
		List<Employee> res = empDB.findByName(name, true);
		return res;
	}

	public List<Employee> findAll() throws DataAccessException {
		List<Employee> res = empDB.findAll(false);
		return res;
	}

	public List<Department> findAllDepartments() throws DataAccessException {
		return deptCtrl.findAll();
	}

	public List<WorksOn> getWorksOnList(Employee employee) throws DataAccessException {
		return worksOnCtrl.getWorksOnList(employee, true);
	}

	public Employee insert(Employee employee) throws DataAccessException {
		Employee res = empDB.insert(employee);
		return res;
	}

}
