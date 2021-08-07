package Db;

import java.math.BigDecimal;
import java.util.List;

import controller.DataAccessException;
import model.Employee;

public interface EmployeeDbIF {

	Employee create(String firstName, String middleName, String lastName, String street, String zipcode, String city,
			String phoneNumber, String email, BigDecimal salaryPerHour, String positionName) throws DataAccessException;

	Employee findById(int id) throws DataAccessException;

	Employee findByName(String fname, String mname, String lname) throws DataAccessException;

	boolean update(Employee customer) throws DataAccessException;

	boolean delete(int id) throws DataAccessException;

	List<Employee> getAll() throws DataAccessException;
}
