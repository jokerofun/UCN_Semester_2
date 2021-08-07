package db;



import java.util.List;

import control.DataAccessException;
import model.Customer;

public interface CustomerDbIF {

	Customer create(String customerName, String phoneNumber, String CVR, String street, String city, String zipcode)
			throws DataAccessException, SQLException;

	Customer createAddress(int id, String street, String city, String zipcode) throws DataAccessException;

	boolean update(int customerId, String customerName, String phoneNumber, String CVR, String street, String city,
			String zipcode) throws DataAccessException;

	boolean delete(int id) throws DataAccessException;

	Customer findById(int id) throws DataAccessException;

	List<Customer> getAll() throws DataAccessException;
}
