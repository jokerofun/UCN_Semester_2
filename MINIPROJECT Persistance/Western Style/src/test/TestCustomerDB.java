package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import control.DataAccessException;
import db.CustomerDb;
import db.CustomerDbIF;
import db.DBReset;
import model.Customer;

class TestCustomerDB {

	private CustomerDbIF customerDb;

	@BeforeEach
	public void setUp() throws SQLException, DataAccessException {
		try {
			DBReset.resetDB();
		} catch (Exception e) {
			throw new RuntimeException("Could not reset the database", e);
		}
		customerDb = new CustomerDb();
	}

	@Test
	public void testInsert() throws SQLException {
		try {
			Customer returnValue = customerDb.create("Simeon", "123456654", null, "Anier 45", "Viborg", "9874");
			assertNotNull(returnValue);
			assertEquals("Simeon", returnValue.getCustomerName());
			assertEquals("123456654", returnValue.getPhoneNumber());
			assertEquals("Anier 45", returnValue.getAddress().getStreet());
			assertEquals("Viborg", returnValue.getAddress().getCity());
			assertEquals("9874", returnValue.getAddress().getZipcode());
		} catch (DataAccessException e) {
			e.printStackTrace();
			fail("Error: SQL exception");
		}
	}

	@Test
	public void testUpdate() {
		try {
			// The current test data: 'Franklin', '86753475','f6ds1f' ; Address:
			// 'ugirewodkpr 123', 'VIborg', '78786'
			customerDb.update(2, "Sebastian", "45865589", null, "Abgfd 12", "Copenhagen", "5000");
			Customer returnValue = customerDb.findById(2);
			assertNotNull(returnValue);
			assertEquals("Sebastian", returnValue.getCustomerName());
			assertEquals("45865589", returnValue.getPhoneNumber());
			assertEquals("Abgfd 12", returnValue.getAddress().getStreet());
			assertEquals("Copenhagen", returnValue.getAddress().getCity());
			assertEquals("5000", returnValue.getAddress().getZipcode());
		} catch (DataAccessException e) {
			e.printStackTrace();
			fail("Error: SQL exception");
		}
	}

//	@Test
//	public void testDelete() {
//		try {
//			Customer customer = customerDb.findById(3);
//			assertTrue(customer != null);
//			assertTrue(customerDb.delete(3));
//		} catch (DataAccessException e) {
//			e.printStackTrace();
//			fail("Error: DataAccess exception");
//		}
//	}

	@Test
	public void testFindById() {
		try {
			// The current test data: 'Joyce Richard','87654687',null ; Address: 'WFgrtefr
			// 34', 'Aaalborg', '456'
			Customer customer = customerDb.findById(3);
			assertEquals("Joyce Richard", customer.getCustomerName());
			assertEquals("87654687", customer.getPhoneNumber());
			assertEquals("WFgrtefr 34", customer.getAddress().getStreet());
			assertEquals("Aaalborg", customer.getAddress().getCity());
			assertEquals("456", customer.getAddress().getZipcode());
		} catch (DataAccessException e) {
			e.printStackTrace();
			fail("Error: DataAccess exception");
		}
	}

	@Test
	public void testGetAll() {
		try {
			List<Customer> customers = customerDb.getAll();
			assertTrue(customers.size() == 4);
		} catch (DataAccessException e) {
			e.printStackTrace();
			fail("Error: DataAccess exception");
		}
	}

}
