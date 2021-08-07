package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Db.DBReset;
import controller.CustomerController;
import controller.DataAccessException;
import controller.OfferController;
import controller.ProcedureController;
import controller.SaleOrderController;
import model.OfferEntry;
import model.Procedure;
import model.Product;
import model.SaleOrder;

public class SaleOrderDbTest {

	private SaleOrderController controller;
	private CustomerController controller3;
	private OfferController controller4;
	private ProcedureController controller5;
	SaleOrder returnValue;

	public SaleOrderDbTest() throws DataAccessException {
		controller = new SaleOrderController();
		controller3 = new CustomerController();
		controller4 = new OfferController();
		controller5 = new ProcedureController();
	}

	@BeforeEach
	public void setUp() {
		try {
			DBReset.resetDB();

			controller3.createCustomer("Misfits", "Danmarksgade 66", "9000", "Aalborg", "65894R", "86549856",
					"misfits@ucn.dk");
			controller3.createCustomer("Something", "Hobrovej 777", "9000", "Aalborg", "452G4", "8528452",
					"something@ucn.dk");

			List<OfferEntry> offerEntries = new ArrayList<>();
			List<OfferEntry> offerEntries2 = new ArrayList<>();
			OfferEntry offerEntry1 = new OfferEntry("Machine", 5);
			OfferEntry offerEntry2 = new OfferEntry("Gear", 12);
			Procedure procedure1 = controller5.createProcedure("Cut", BigDecimal.valueOf(23.56), "a lot");
			Procedure procedure2 = controller5.createProcedure("Hammer", BigDecimal.valueOf(5.23), "some");
			Procedure procedure3 = controller5.createProcedure("Break", BigDecimal.valueOf(89.12), "idk");
			Procedure procedure4 = controller5.createProcedure("shetr", BigDecimal.valueOf(4.65), "gwe");
			Procedure procedure5 = controller5.createProcedure("hsr", BigDecimal.valueOf(7.89), "gre");
			offerEntry1.addProcedureAndHour(procedure1, 4);
			offerEntry1.addProcedureAndHour(procedure3, 6);
			offerEntry1.addProcedureAndHour(procedure5, 8);
			offerEntry2.addProcedureAndHour(procedure2, 4);
			offerEntry2.addProcedureAndHour(procedure3, 2);
			offerEntry2.addProcedureAndHour(procedure4, 1);
			offerEntries.add(offerEntry1);
			offerEntries.add(offerEntry2);
			offerEntries2.add(offerEntry2);
			Date date = Date.valueOf("2012-05-03");

			controller4.createOffer(date, BigDecimal.valueOf(45.23), offerEntries);
			controller4.createOffer(date, BigDecimal.valueOf(23.15), offerEntries2);

			List<Product> products = new ArrayList<>();
			products.add(new Product("Pen", "Cool", BigDecimal.valueOf(23.56), 50));
			products.add(new Product("Pants", "Jeans", BigDecimal.valueOf(50.26), 20));
			products.add(new Product("Metal", "Heavy", BigDecimal.valueOf(2.32), 9865));

			returnValue = controller.createSaleOrder("Misfits", date, date, 1, BigDecimal.valueOf(5951.35), products);
			controller.createSaleOrder("Something", date, date, 2, BigDecimal.valueOf(4914.59), products);
		} catch (Exception e) {
			throw new RuntimeException("Could not reset the database", e);
		}
	}

	@Test
	public void testInsert() {
		assertNotNull(returnValue);
		assertEquals(1, returnValue.getSaleOrderNumber());
		assertEquals(BigDecimal.valueOf(5951.35), returnValue.getTotalPrice());
		assertEquals(0, returnValue.getProducts().size());
	}

	@Test
	public void testFindByNumber() {
		try {
			SaleOrder saleOrder = controller.findSaleOrderByNumber(1);
			assertEquals(BigDecimal.valueOf(5951.35), saleOrder.getTotalPrice());
			assertEquals(3, saleOrder.getProducts().size());
			saleOrder = controller.findSaleOrderByNumber(2);
			assertEquals(BigDecimal.valueOf(4914.59), saleOrder.getTotalPrice());
			assertEquals(3, saleOrder.getProducts().size());
			assertEquals(Date.valueOf("2012-05-03"), saleOrder.getDueDate());
		} catch (DataAccessException e) {
			e.printStackTrace();
			fail("Error: DataAccess exception");
		}
	}

	@Test
	public void testGetAll() {
		try {
			List<SaleOrder> saleOrders = controller.getAllSaleOrders();
			assertTrue(saleOrders.size() == 2);
			assertEquals(BigDecimal.valueOf(5951.35), saleOrders.get(0).getTotalPrice());
			assertEquals("Jeans", saleOrders.get(0).getProducts().get(1).getDescription());

			assertEquals(BigDecimal.valueOf(4914.59), saleOrders.get(1).getTotalPrice());
			assertEquals("Something", saleOrders.get(1).getCustomerName());
			assertEquals(Date.valueOf("2012-05-03"), saleOrders.get(1).getDueDate());
		} catch (DataAccessException e) {
			e.printStackTrace();
			fail("Error: DataAccess exception");
		}
	}

	@Test
	public void testUpdate() {
		try {
			returnValue.setTotalPrice(BigDecimal.valueOf(789.23));
			returnValue.setDueDate(Date.valueOf("2012-07-03"));
			returnValue.setCustomerName("Something");

			assertTrue(controller.updateSaleOrder(returnValue));
			returnValue = controller.findSaleOrderByNumber(1);
			assertEquals(BigDecimal.valueOf(789.23), returnValue.getTotalPrice());
			assertEquals(Date.valueOf("2012-07-03"), returnValue.getDueDate());
			assertEquals("Something", returnValue.getCustomerName());
		} catch (DataAccessException e) {
			e.printStackTrace();
			fail("Error: DataAccess exception");
		}
	}

	@Test
	public void testDelete() {
		try {
			List<SaleOrder> saleOrders = controller.getAllSaleOrders();
			assertTrue(saleOrders.size() == 2);
			assertTrue(controller.deleteSaleOrder(1));
			saleOrders = controller.getAllSaleOrders();
			assertTrue(saleOrders.size() == 1);
			assertTrue(controller.findSaleOrderByNumber(1) == null);
			assertTrue(controller.findSaleOrderByNumber(2).getCustomerName().equals("Something"));
		} catch (DataAccessException e) {
			e.printStackTrace();
			fail("Error: DataAccess exception");
		}

	}

}
