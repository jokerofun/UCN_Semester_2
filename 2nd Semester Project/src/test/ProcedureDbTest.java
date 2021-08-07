package test;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Db.DBReset;
import controller.DataAccessException;
import controller.ProcedureController;
import model.Procedure;

public class ProcedureDbTest {

	private ProcedureController controller;
	Procedure returnValue;

	public ProcedureDbTest() throws DataAccessException {
		controller = new ProcedureController();
	}

	@BeforeEach
	public void setUp() {
		try {
			DBReset.resetDB();

			returnValue = controller.createProcedure("Cute", BigDecimal.valueOf(23.56), "Boss");
			controller.createProcedure("Hammer", BigDecimal.valueOf(5.23), "Manager");
			controller.createProcedure("Break", BigDecimal.valueOf(89.12), "Worker");
		} catch (Exception e) {
			throw new RuntimeException("Could not reset the database", e);
		}
	}

	@Test
	public void testInsert() {
		assertNotNull(returnValue);
		assertEquals("Cute", returnValue.getProcedureName());
		assertEquals(BigDecimal.valueOf(23.56), returnValue.getPricePerHour());
		assertEquals("Boss", returnValue.getEmployeeTypeRequired());
	}

	@Test
	public void testFindByName() {
		try {
			List<Procedure> procedures = controller.findProcedureByName("e");
			assertTrue(procedures.size() == 3);
			assertEquals("Boss", procedures.get(0).getEmployeeTypeRequired());
			procedures = controller.findProcedureByName("Hammer");
			assertTrue(procedures.size() == 1);
			assertEquals(BigDecimal.valueOf(5.23), procedures.get(0).getPricePerHour());
			procedures = controller.findProcedureByName("Break");
			assertTrue(procedures.size() == 1);
			assertEquals("Break", procedures.get(0).getProcedureName());
		} catch (DataAccessException e) {
			e.printStackTrace();
			fail("Error: DataAccess exception");
		}
	}

	@Test
	public void testFindByCode() {
		try {
			Procedure procedure = controller.findProcedureByCode(1);
			assertEquals("Boss", procedure.getEmployeeTypeRequired());
			procedure = controller.findProcedureByCode(2);
			assertEquals("Hammer", procedure.getProcedureName());
			procedure = controller.findProcedureByCode(3);
			assertEquals(BigDecimal.valueOf(89.12), procedure.getPricePerHour());
			procedure = controller.findProcedureByCode(4);
			assertNull(procedure);
		} catch (DataAccessException e) {
			e.printStackTrace();
			fail("Error: DataAccess exception");
		}
	}

	@Test
	public void testGetAll() {
		try {
			List<Procedure> procedures = controller.getAllProcedures();
			assertTrue(procedures.size() == 3);
		} catch (DataAccessException e) {
			e.printStackTrace();
			fail("Error: DataAccess exception");
		}
	}

	@Test
	public void testUpdate() {
		try {
			assertTrue(controller.updateProcedure("Cut", BigDecimal.valueOf(99.99), 1, "Sleeper"));
			List<Procedure> procedures = controller.findProcedureByName("c");
			assertTrue(procedures.size() == 1);
			assertEquals(BigDecimal.valueOf(99.99), procedures.get(0).getPricePerHour());
			assertEquals("Sleeper", procedures.get(0).getEmployeeTypeRequired());
		} catch (DataAccessException e) {
			e.printStackTrace();
			fail("Error: DataAccess exception");
		}
	}

	@Test
	public void testDelete() {
		try {
			List<Procedure> procedures = controller.getAllProcedures();
			assertTrue(procedures.size() == 3);
			assertTrue(controller.deleteProcedure(1));
			procedures = controller.getAllProcedures();
			assertTrue(procedures.size() == 2);
			assertTrue(controller.findProcedureByName("Cute").size() == 0);
			assertTrue((controller.findProcedureByName("Hammer").get(0).getPricePerHour())
					.equals(BigDecimal.valueOf(5.23)));
			assertTrue(controller.findProcedureByCode(2).getEmployeeTypeRequired().equals("Manager"));
		} catch (DataAccessException e) {
			e.printStackTrace();
			fail("Error: DataAccess exception");
		}
	}

}
