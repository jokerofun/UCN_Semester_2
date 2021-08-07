package control;

import java.sql.SQLException;

import db.CustomerDb;
import model.Customer;

public class CustomerController {
	private CustomerDb customerDb;

	public CustomerController() throws DataAccessException {
		customerDb = new CustomerDb();
	}

	/**
	 * Used to add a new Customer to database
	 * 
	 * @param customerName
	 * @param phoneNumber
	 * @param CVR
	 * @return boolean
	 * @throws SQLException
	 */
	public boolean addCustomer(String customerName, String phoneNumber, String CVR, String street, String city,
			String zipcode) throws SQLException {
		Customer c = null;
		try {
			c = customerDb.create(customerName, phoneNumber, CVR, street, city, zipcode);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return (c != null);
	}

	/**
	 * Used to remove a Customer from database
	 * 
	 * @param customerId
	 * @return boolean
	 */
	public boolean removeCustomer(int customerId) {
		boolean res = false;
		try {
			res = customerDb.delete(customerId);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return res;
	}

	/**
	 * Used to update the info of a Customer in database
	 * 
	 * @param customerId
	 * @param customerName
	 * @param phoneNumber
	 * @param CVR
	 * @param street
	 * @param city
	 * @param zipcode
	 * @return boolean
	 */
	public boolean updateCustomer(int customerId, String customerName, String phoneNumber, String CVR, String street,
			String city, String zipcode) {
		boolean res = false;
		try {
			res = customerDb.update(customerId, customerName, phoneNumber, CVR, street, city, zipcode);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return res;
	}

	/**
	 * Used to retrieve a Customer from database
	 * 
	 * @param customerId
	 * @return Customer
	 */
	public Customer findCustomerById(int customerId) throws DataAccessException {
		Customer res = null;
		try {
			res = customerDb.findById(customerId);
		} catch (DataAccessException e) {
			throw new DataAccessException("No customer found", e);
		}
		return res;
	}
}
