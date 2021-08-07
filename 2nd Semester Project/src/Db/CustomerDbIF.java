package Db;

import java.util.List;

import controller.DataAccessException;
import model.Customer;

public interface CustomerDbIF {

	Customer create(String companyName, String street, String zipcode, String city, String CUI, String phoneNumber,
			String email) throws DataAccessException;

	Customer findById(int id) throws DataAccessException;

	Customer findByName(String name) throws DataAccessException;

	List<Customer> findByNameRegex(String name) throws DataAccessException;

	boolean update(Customer customer) throws DataAccessException;

	boolean delete(int id) throws DataAccessException;

	List<Customer> getAll() throws DataAccessException;
}
