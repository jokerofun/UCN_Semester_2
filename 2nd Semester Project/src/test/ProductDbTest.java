package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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
import controller.ProductController;
import controller.SaleOrderController;
import model.OfferEntry;
import model.Procedure;
import model.Product;

public class ProductDbTest {

	private ProductController controller;
	private SaleOrderController controller2;
	private OfferController controller3;
	private CustomerController controller4;
	private ProcedureController controller5;

	public ProductDbTest() throws DataAccessException {
		controller = new ProductController();
		controller2 = new SaleOrderController();
		controller3 = new OfferController();
		controller4 = new CustomerController();
		controller5 = new ProcedureController();
	}

	@BeforeEach
	public void setUp() {
		try {
			DBReset.resetDB();

			controller4.createCustomer("Misfits", "Danmarksgade 66", "9000", "Aalborg", "65894R", "86549856",
					"misfits@ucn.dk");

			List<OfferEntry> offerEntries = new ArrayList<>();
			OfferEntry offerEntry1 = new OfferEntry("Machine", 5);
			Procedure procedure1 = controller5.createProcedure("Cut", BigDecimal.valueOf(23.56), "a lot");
			offerEntry1.addProcedureAndHour(procedure1, 4);
			offerEntries.add(offerEntry1);
			Date date = Date.valueOf("2012-05-03");

			controller3.createOffer(date, BigDecimal.valueOf(45.23), offerEntries);

			List<Product> products = new ArrayList<>();

			controller2.createSaleOrder("Misfits", date, date, 1, BigDecimal.valueOf(516.43), products);
		} catch (Exception e) {
			throw new RuntimeException("Could not reset the database", e);
		}
	}

	@Test
	public void testInsert() {
		try {
			Product returnValue = controller.createProductWithSaleOrder("Pencil", "Really cool",
					BigDecimal.valueOf(0.23), 30, 1);
			assertNotNull(returnValue);
			assertEquals("Pencil", returnValue.getProductName());
			assertEquals("Really cool", returnValue.getDescription());
			assertEquals(BigDecimal.valueOf(0.23), returnValue.getPrice());
			assertEquals(30, returnValue.getQuantity());
		} catch (DataAccessException e) {
			e.printStackTrace();
			fail("Error: SQL exception");
		}
	}

	@Test
	public void testFindById() {
		try {
			controller.createProductWithSaleOrder("Pencil", "Really cool", BigDecimal.valueOf(0.23), 30, 1);
			controller.createProductWithSaleOrder("Hammer", "Claw head", BigDecimal.valueOf(1.56), 20, 1);
			controller.createProductWithSaleOrder("Car part", "For something", BigDecimal.valueOf(65.23), 3, 1);
			Product product = controller.findProductById(1);
			assertEquals("Pencil", product.getProductName());
			product = controller.findProductById(2);
			assertEquals(BigDecimal.valueOf(1.56), product.getPrice());
			product = controller.findProductById(3);
			assertEquals(3, product.getQuantity());
		} catch (DataAccessException e) {
			e.printStackTrace();
			fail("Error: DataAccess exception");
		}
	}

	@Test
	public void testFindByName() {
		try {
			controller.createProductWithSaleOrder("Pencil", "Really cool", BigDecimal.valueOf(0.23), 30, 1);
			controller.createProductWithSaleOrder("Hammer", "Claw head", BigDecimal.valueOf(1.56), 20, 1);
			controller.createProductWithSaleOrder("Car part", "For something", BigDecimal.valueOf(65.23), 3, 1);
			Product product = controller.findProductByName("Pencil");
			assertEquals("Pencil", product.getProductName());
			product = controller.findProductByName("Hammer");
			assertEquals(BigDecimal.valueOf(1.56), product.getPrice());
			product = controller.findProductByName("Car part");
			assertEquals(3, product.getQuantity());
		} catch (DataAccessException e) {
			e.printStackTrace();
			fail("Error: DataAccess exception");
		}
	}

	@Test
	public void testGetAll() {
		try {
			controller.createProductWithSaleOrder("Pencil", "Really cool", BigDecimal.valueOf(0.23), 30, 1);
			controller.createProductWithSaleOrder("Hammer", "Claw head", BigDecimal.valueOf(1.56), 20, 1);
			controller.createProductWithSaleOrder("Car part", "For something", BigDecimal.valueOf(65.23), 3, 1);
			List<Product> products = controller.getAllProducts();
			assertTrue(products.size() == 3);
			assertNotNull(products);
		} catch (DataAccessException e) {
			e.printStackTrace();
			fail("Error: DataAccess exception");
		}
	}

	@Test
	public void testUpdate() {
		try {
			Product product = controller.createProductWithSaleOrder("Pencil", "Really cool", BigDecimal.valueOf(0.23),
					30, 1);
			product.setProductName("Pen");
			product.setPrice(BigDecimal.valueOf(12.56));
			product.setQuantity(78);
			assertTrue(controller.updateProduct(product));
			product = controller.findProductById(1);
			assertEquals("Pen", product.getProductName());
			assertEquals(BigDecimal.valueOf(12.56), product.getPrice());
			assertEquals(78, product.getQuantity());
		} catch (DataAccessException e) {
			e.printStackTrace();
			fail("Error: DataAccess exception");
		}
	}

	@Test
	public void testDelete() {
		try {
			controller.createProductWithSaleOrder("Pencil", "Really cool", BigDecimal.valueOf(0.23), 30, 1);
			controller.createProductWithSaleOrder("Hammer", "Claw head", BigDecimal.valueOf(1.56), 20, 1);
			List<Product> products = controller.getAllProducts();
			assertTrue(products.size() == 2);
			assertTrue(controller.deleteProduct(1));
			assertFalse(controller.deleteProduct(3));
			products = controller.getAllProducts();
			assertTrue(products.size() == 1);
			assertTrue(controller.findProductById(1) == null);
			assertTrue(controller.findProductById(2).getPrice().equals(BigDecimal.valueOf(1.56)));
		} catch (DataAccessException e) {
			e.printStackTrace();
			fail("Error: DataAccess exception");
		}
	}

}
