package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Db.DBReset;
import controller.CustomerController;
import controller.DataAccessException;
import model.Customer;

public class CustomerDbTest {

	private CustomerController controller;
	private Customer returnValue;

	public CustomerDbTest() throws DataAccessException {
		controller = new CustomerController();
	}

	@BeforeEach
	public void setUp() {
		try {
			DBReset.resetDB();
			returnValue = controller.createCustomer("Misfits", "Danmarksgade 66", "9000", "Aalborg", "65894R",
					"86549856", "misfits@ucn.dk");
			controller.createCustomer("Something", "Hobrovej 777", "9000", "Aalborg", "452G4", "8528452",
					"something@ucn.dk");
			controller.createCustomer("Hmmm", "idk", "9200", "Copenhagen", "4598V4", "123456789", "hmmm@ucn.dk");
		} catch (Exception e) {
			throw new RuntimeException("Could not reset the database", e);
		}
	}

	@Test
	public void testInsert() {
		assertNotNull(returnValue);
		assertEquals("Misfits", returnValue.getCompanyName());
		assertEquals("Danmarksgade 66", returnValue.getStreet());
		assertEquals("9000", returnValue.getZipcode());
		assertEquals("Aalborg", returnValue.getCity());
		assertEquals("65894R", returnValue.getCUI());
		assertEquals("86549856", returnValue.getPhoneNumber());
		assertEquals("misfits@ucn.dk", returnValue.getEmail());
	}

	@Test
	public void testFindById() {
		try {
			Customer customer = controller.findCustomerById(1);
			assertEquals("Misfits", customer.getCompanyName());
			customer = controller.findCustomerById(2);
			assertEquals("452G4", customer.getCUI());
			customer = controller.findCustomerById(3);
			assertEquals("Copenhagen", customer.getCity());
		} catch (DataAccessException e) {
			e.printStackTrace();
			fail("Error: DataAccess exception");
		}
	}

	@Test
	public void testFindByName() {
		try {
			Customer customer = controller.findCustomerByName("Misfits");
			assertEquals("Misfits", customer.getCompanyName());
			// Testing if it accepts both Upper and Lower case
			customer = controller.findCustomerByName("something");
			assertEquals("452G4", customer.getCUI());
			customer = controller.findCustomerByName("Hmmm");
			assertEquals("Copenhagen", customer.getCity());
		} catch (DataAccessException e) {
			e.printStackTrace();
			fail("Error: DataAccess exception");
		}
	}

	@Test
	public void testGetAll() {
		try {
			List<Customer> customers = controller.getAllCustomers();
			assertTrue(customers.size() == 3);
		} catch (DataAccessException e) {
			e.printStackTrace();
			fail("Error: DataAccess exception");
		}
	}

	@Test
	public void testUpdate() {
		try {
			returnValue.setCUI("testingUC");
			returnValue.setCity("testingUA");
			assertTrue(controller.updateCustomer(returnValue));
			returnValue = controller.findCustomerById(1);
			assertEquals("testingUC", returnValue.getCUI());
			assertEquals("testingUA", returnValue.getCity());
		} catch (DataAccessException e) {
			e.printStackTrace();
			fail("Error: DataAccess exception");
		}
	}

	@Test
	public void testDelete() {
		try {
			List<Customer> customers = controller.getAllCustomers();
			assertTrue(customers.size() == 3);
			assertTrue(controller.deleteCustomer(1));
			customers = controller.getAllCustomers();
			assertTrue(customers.size() == 2);
			assertTrue(controller.findCustomerById(1) == null);
			assertTrue(controller.findCustomerById(2).getCompanyName().equals("Something"));
		} catch (DataAccessException e) {
			e.printStackTrace();
			fail("Error: DataAccess exception");
		}
	}

}
