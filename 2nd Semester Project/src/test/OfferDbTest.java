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
import controller.DataAccessException;
import controller.OfferController;
import controller.ProcedureController;
import model.Offer;
import model.OfferEntry;
import model.Procedure;

public class OfferDbTest {

	private OfferController controller;
	private ProcedureController controller2;
	Offer returnValue;

	public OfferDbTest() throws DataAccessException {
		controller = new OfferController();
		controller2 = new ProcedureController();
	}

	@BeforeEach
	public void setUp() {
		try {
			DBReset.resetDB();

			List<OfferEntry> offerEntries = new ArrayList<>();
			List<OfferEntry> offerEntries2 = new ArrayList<>();
			OfferEntry offerEntry1 = new OfferEntry("Machine", 5);
			OfferEntry offerEntry2 = new OfferEntry("Gear", 12);
			Procedure procedure1 = controller2.createProcedure("Cut", BigDecimal.valueOf(23.56), "a lot");
			Procedure procedure2 = controller2.createProcedure("Hammer", BigDecimal.valueOf(5.23), "some");
			Procedure procedure3 = controller2.createProcedure("Break", BigDecimal.valueOf(89.12), "idk");
			Procedure procedure4 = controller2.createProcedure("shetr", BigDecimal.valueOf(4.65), "gwe");
			Procedure procedure5 = controller2.createProcedure("hsr", BigDecimal.valueOf(7.89), "gre");
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

			returnValue = controller.createOffer(date, BigDecimal.valueOf(45.23), offerEntries);
			controller.createOffer(date, BigDecimal.valueOf(23.15), offerEntries2);
		} catch (Exception e) {
			throw new RuntimeException("Could not reset the database", e);
		}
	}

	@Test
	public void testInsert() {
		assertNotNull(returnValue);
		assertEquals(1, returnValue.getOfferNumber());
		assertEquals(BigDecimal.valueOf(45.23), returnValue.getMaterialPrice());
		assertEquals(BigDecimal.valueOf(5951.35), returnValue.getTotalPrice());
		assertEquals(6, returnValue.getProcedures().size());
		// doesn't retrieve in same order
		assertEquals("shetr", returnValue.getProcedures().get(2).getProcedureName());
		assertEquals(BigDecimal.valueOf(89.12), returnValue.getProcedures().get(1).getPricePerHour());
		assertEquals("some", returnValue.getProcedures().get(0).getEmployeeTypeRequired());
		assertEquals(Date.valueOf("2012-05-03"), returnValue.getDueDate());
	}

	@Test
	public void testFindByNumber() {
		try {
			Offer offer = controller.findOfferByNumber(1);
			assertEquals(BigDecimal.valueOf(45.23), offer.getMaterialPrice());
			assertEquals(6, offer.getProcedures().size());
			assertEquals(BigDecimal.valueOf(5951.35), offer.getTotalPrice());
			offer = controller.findOfferByNumber(2);
			assertEquals(3, offer.getProcedures().size());
			assertEquals(Date.valueOf("2012-05-03"), offer.getDueDate());
		} catch (DataAccessException e) {
			e.printStackTrace();
			fail("Error: DataAccess exception");
		}
	}

	@Test
	public void testGetAll() {
		try {
			List<Offer> offers = controller.getAllOffers();
			assertTrue(offers.size() == 2);
			assertEquals(BigDecimal.valueOf(5951.35), offers.get(0).getTotalPrice());
			assertEquals("idk", offers.get(0).getProcedures().get(1).getEmployeeTypeRequired());

			assertEquals(BigDecimal.valueOf(23.15), offers.get(1).getMaterialPrice());
			assertEquals("gwe", offers.get(1).getProcedures().get(2).getEmployeeTypeRequired());
			assertEquals(Date.valueOf("2012-05-03"), offers.get(1).getDueDate());
		} catch (DataAccessException e) {
			e.printStackTrace();
			fail("Error: DataAccess exception");
		}
	}

	@Test
	public void testUpdate() {
		try {
			returnValue.setMaterialPrice(BigDecimal.valueOf(789.23));
			returnValue.setDueDate(Date.valueOf("2012-07-03"));

			assertTrue(controller.updateOffer(returnValue));
			returnValue = controller.findOfferByNumber(1);
			assertEquals(BigDecimal.valueOf(789.23), returnValue.getMaterialPrice());
			assertEquals(Date.valueOf("2012-07-03"), returnValue.getDueDate());
		} catch (DataAccessException e) {
			e.printStackTrace();
			fail("Error: DataAccess exception");
		}
	}

	@Test
	public void testDelete() {
		try {
			List<Offer> offers = controller.getAllOffers();
			assertTrue(offers.size() == 2);
			assertTrue(controller.deleteOffer(1));
			offers = controller.getAllOffers();
			assertTrue(offers.size() == 1);
			assertTrue(controller.findOfferByNumber(1) == null);
			assertTrue(controller.findOfferByNumber(2).getProcedures().get(0).getEmployeeTypeRequired().equals("some"));
		} catch (DataAccessException e) {
			e.printStackTrace();
			fail("Error: DataAccess exception");
		}

	}

}
