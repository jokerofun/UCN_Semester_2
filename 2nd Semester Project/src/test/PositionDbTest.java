package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Db.DBReset;
import controller.DataAccessException;
import controller.PositionController;
import model.Position;

public class PositionDbTest {

	private PositionController controller;
	Position returnValue;

	public PositionDbTest() throws DataAccessException {
		controller = new PositionController();
	}

	@BeforeEach
	public void setUp() {
		try {
			DBReset.resetDB();

			returnValue = controller.createPosition("Manager", "High");
			controller.createPosition("Boss", "High");
			controller.createPosition("Worker", "Low");
		} catch (Exception e) {
			throw new RuntimeException("Could not reset the database", e);
		}
	}

	@Test
	public void testInsert() {
		assertNotNull(returnValue);
		assertEquals("Manager", returnValue.getPositionName());
		assertEquals("High", returnValue.getClearance());
	}

	@Test
	public void testFindByName() {
		try {
			Position position = controller.findPositionByName("Manager");
			assertEquals("High", position.getClearance());
			position = controller.findPositionByName("Boss");
			assertEquals("High", position.getClearance());
			position = controller.findPositionByName("Worker");
			assertEquals("Low", position.getClearance());
		} catch (DataAccessException e) {
			e.printStackTrace();
			fail("Error: DataAccess exception");
		}
	}

	@Test
	public void testGetAll() {
		try {
			List<Position> positions = controller.getAllPositions();
			assertTrue(positions.size() == 3);
		} catch (DataAccessException e) {
			e.printStackTrace();
			fail("Error: DataAccess exception");
		}
	}

	@Test
	public void testUpdate() {
		try {
			assertTrue(controller.updatePosition("Manager", "Medium"));
			Position position = controller.findPositionByName("Manager");
			assertEquals("Manager", position.getPositionName());
			assertEquals("Medium", position.getClearance());
		} catch (DataAccessException e) {
			e.printStackTrace();
			fail("Error: DataAccess exception");
		}
	}

	@Test
	public void testDelete() {
		try {
			List<Position> positions = controller.getAllPositions();
			assertTrue(positions.size() == 3);
			assertTrue(controller.deletePosition("Manager"));
			positions = controller.getAllPositions();
			assertTrue(positions.size() == 2);
			assertTrue(controller.findPositionByName("Manager") == null);
			assertTrue(controller.findPositionByName("Boss").getClearance().equals("High"));
		} catch (DataAccessException e) {
			e.printStackTrace();
			fail("Error: DataAccess exception");
		}
	}

}
