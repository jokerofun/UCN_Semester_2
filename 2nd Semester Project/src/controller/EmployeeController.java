package controller;

import java.math.BigDecimal;
import java.util.List;

import Db.EmployeeDb;
import model.Employee;

public class EmployeeController {
	private EmployeeDb db;

	public EmployeeController() throws DataAccessException {
		db = new EmployeeDb();
	}

	public Employee createEmployee(String firstName, String middleName, String lastName, String street, String zipcode,
			String city, String phoneNumber, String email, BigDecimal salaryPerHour, String positionName)
			throws DataAccessException {

		return db.create(firstName, middleName, lastName, street, zipcode, city, phoneNumber, email, salaryPerHour,
				positionName);
	}

	public boolean updateEmployee(Employee employee) throws DataAccessException {
		return db.update(employee);
	}

	public boolean deleteEmployee(int id) throws DataAccessException {
		return db.delete(id);
	}

	public Employee findEmployeeById(int id) throws DataAccessException {
		return db.findById(id);
	}

	public Employee findEmployeeByName(String fname, String mname, String lname) throws DataAccessException {
		return db.findByName(fname, mname, lname);
	}

	public List<Employee> getAllEmployees() throws DataAccessException {
		return db.getAll();
	}
}
