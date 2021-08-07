package controller;

import java.util.List;

import Db.CustomerDb;
import model.Customer;

public class CustomerController {
	private CustomerDb db;

	public CustomerController() throws DataAccessException {
		db = new CustomerDb();
	}

	public Customer createCustomer(String companyName, String street, String zipcode, String city, String CUI,
			String phoneNumber, String email) throws DataAccessException {

		return db.create(companyName, street, zipcode, city, CUI, phoneNumber, email);
	}

	public boolean updateCustomer(Customer customer) throws DataAccessException {
		return db.update(customer);
	}

	public boolean deleteCustomer(int id) throws DataAccessException {
		return db.delete(id);
	}

	public Customer findCustomerById(int id) throws DataAccessException {
		return db.findById(id);
	}

	public Customer findCustomerByName(String name) throws DataAccessException {
		return db.findByName(name);
	}

	public List<Customer> findCustomerByNameRegex(String name) throws DataAccessException {
		return db.findByNameRegex(name);
	}

	public List<Customer> getAllCustomers() throws DataAccessException {
		return db.getAll();
	}

}
