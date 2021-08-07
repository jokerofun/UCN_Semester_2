package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import control.DataAccessException;
import control.NullPointerException;
import db.DBReset;
import db.SaleOrderDb;
import db.SaleOrderDbIF;
import model.Date;
import model.SaleOrder;
import model.SalesLine;

class TestSaleOrder {
	private SaleOrderDbIF saleOrderDb;

	@BeforeEach
	public void setUp() throws SQLException, DataAccessException {
		try {
			DBReset.resetDB();
		} catch (Exception e) {
			throw new RuntimeException("Could not reset the database");
		}
		saleOrderDb = new SaleOrderDb();
	}

	@Test
	public void testInsert() throws SQLException, NullPointerException {
		try {
			BigDecimal d1 = new BigDecimal(59.5);
			BigDecimal d2 = new BigDecimal(60.48);
			BigDecimal d3 = new BigDecimal(32.8);
			BigDecimal d4 = new BigDecimal(234.87);
			ArrayList<SalesLine> salesLines = new ArrayList<>();
			SalesLine sl1 = new SalesLine(1, 5, false, d1);
			SalesLine sl2 = new SalesLine(3, 4, false, d2);
			SalesLine sl3 = new SalesLine(2, 6, true, d3);
			Date date = new Date(1989, 06, 10);
			salesLines.add(sl1);
			salesLines.add(sl2);
			salesLines.add(sl3);
			SaleOrder returnValue = saleOrderDb.create(3, false, d4, salesLines, date, null);
			assertNotNull(returnValue);
			assertEquals(3, returnValue.getCustomerId());
			assertEquals(BigDecimal.valueOf(234.87), returnValue.getTotalPrice());
			assertEquals(date, returnValue.getDate());
			assertEquals(null, returnValue.getDeliveryDate());
		} catch (DataAccessException e) {
			e.printStackTrace();
			fail("Error: SQL exception");
		}
	}

//	@Test
//	public void testUpdate() {
//		try {
//			// The current test data: 'Franklin', '86753475','f6ds1f' ; Address:
//			// 'ugirewodkpr 123', 'VIborg', '78786'
//			customerDb.update(2, "Sebastian", "45865589", null, "Abgfd 12", "Copenhagen", "5000");
//			Customer returnValue = customerDb.findById(2);
//			assertNotNull(returnValue);
//			assertEquals("Sebastian", returnValue.getCustomerName());
//			assertEquals("45865589", returnValue.getPhoneNumber());
//			assertEquals("Abgfd 12", returnValue.getAddress().getStreet());
//			assertEquals("Copenhagen", returnValue.getAddress().getCity());
//			assertEquals("5000", returnValue.getAddress().getZipcode());
//		} catch (DataAccessException e) {
//			e.printStackTrace();
//			fail("Error: SQL exception");
//		}
//	}

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

//	@Test
//	public void testFindById() {
//		try {
//			// The current test data: 'Joyce Richard','87654687',null ; Address: 'WFgrtefr
//			// 34', 'Aaalborg', '456'
//			Customer customer = customerDb.findById(3);
//			assertEquals("Joyce Richard", customer.getCustomerName());
//			assertEquals("87654687", customer.getPhoneNumber());
//			assertEquals("WFgrtefr 34", customer.getAddress().getStreet());
//			assertEquals("Aaalborg", customer.getAddress().getCity());
//			assertEquals("456", customer.getAddress().getZipcode());
//		} catch (DataAccessException e) {
//			e.printStackTrace();
//			fail("Error: DataAccess exception");
//		}
//	}

}
