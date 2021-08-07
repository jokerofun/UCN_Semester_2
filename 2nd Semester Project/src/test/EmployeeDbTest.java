package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.DataAccessException;
import controller.EmployeeController;
import controller.PositionController;
import model.Employee;

public class EmployeeDbTest {

	private EmployeeController controller;
	private PositionController controller2;
	Employee returnValue;

	public EmployeeDbTest() throws DataAccessException {
		controller = new EmployeeController();
		controller2 = new PositionController();
	}

	@BeforeEach
	public void setUp() {
		try {
			// DBReset.resetDB();

			controller2.createPosition("Manager", "High");
			controller2.createPosition("Boss", "High");
			controller2.createPosition("Worker", "Low");
			returnValue = controller.createEmployee("Simoen", "Plamenov", "Kolev", "Danmarksgade 66", "9000", "Aalborg",
					"86549856", "misfits@ucn.dk", BigDecimal.valueOf(654.23), "Manager");
			controller.createEmployee("Martin", "Avc", "Aytd", "Adgre 66", "9001", "Aalborg", "436432", "fawets@ucn.dk",
					BigDecimal.valueOf(65.25), "Boss");
			controller.createEmployee("Afwe", "Dod", "Sikew", "Fherh 66", "9200", "Aalborg", "235242", "jytre@ucn.dk",
					BigDecimal.valueOf(10.5), "Worker");
		} catch (Exception e) {
			throw new RuntimeException("Could not reset the database", e);
		}
	}

	@Test
	public void testInsert() {
		assertNotNull(returnValue);
		assertEquals("Simoen", returnValue.getFirstName());
		assertEquals("Plamenov", returnValue.getMiddleName());
		assertEquals("Kolev", returnValue.getLastName());
		assertEquals("Danmarksgade 66", returnValue.getStreet());
		assertEquals("9000", returnValue.getZipcode());
		assertEquals("Aalborg", returnValue.getCity());
		assertEquals("86549856", returnValue.getPhoneNumber());
		assertEquals("misfits@ucn.dk", returnValue.getEmail());
		assertEquals(BigDecimal.valueOf(654.23), returnValue.getSalaryPerHour());
		assertEquals("Manager", returnValue.getPosition().getPositionName());
	}

	@Test
	public void testFindById() {
		try {
			Employee employee = controller.findEmployeeById(1);
			assertEquals("Simoen", employee.getFirstName());
			employee = controller.findEmployeeById(2);
			assertEquals("Boss", employee.getPosition().getPositionName());
			employee = controller.findEmployeeById(3);
			assertEquals("Aalborg", employee.getCity());
		} catch (DataAccessException e) {
			e.printStackTrace();
			fail("Error: DataAccess exception");
		}
	}

	@Test
	public void testFindByName() {
		try {
			Employee employee = controller.findEmployeeByName("jtdsr", "Plamenov", "gre");
			assertEquals("Simoen", employee.getFirstName());
			employee = controller.findEmployeeByName("Martin", "ythdt", "uyt");
			assertEquals("9001", employee.getZipcode());
			employee = controller.findEmployeeByName("sgrt", "hgfhg", "Sikew");
			assertEquals("Worker", employee.getPosition().getPositionName());
		} catch (DataAccessException e) {
			e.printStackTrace();
			fail("Error: DataAccess exception");
		}
	}

	@Test
	public void testGetAll() {
		try {
			List<Employee> employees = controller.getAllEmployees();
			assertTrue(employees.size() == 3);
		} catch (DataAccessException e) {
			e.printStackTrace();
			fail("Error: DataAccess exception");
		}
	}

	@Test
	public void testUpdate() {
		try {
			returnValue.setMiddleName("testingUE");
			returnValue.setCity("testingUA");
			returnValue.getPosition().setPositionName("Worker");

			assertTrue(controller.updateEmployee(returnValue));
			returnValue = controller.findEmployeeById(1);
			assertEquals("testingUE", returnValue.getMiddleName());
			assertEquals("testingUA", returnValue.getCity());
			assertEquals("Worker", returnValue.getPosition().getPositionName());
		} catch (DataAccessException e) {
			e.printStackTrace();
			fail("Error: DataAccess exception");
		}
	}

	@Test
	public void testDelete() {
		try {
			List<Employee> employees = controller.getAllEmployees();
			assertTrue(employees.size() == 3);
			assertTrue(controller.deleteEmployee(1));
			employees = controller.getAllEmployees();
			assertTrue(employees.size() == 2);
			assertTrue(controller.findEmployeeById(1) == null);
			assertTrue(controller.findEmployeeById(2).getFirstName().equals("Martin"));
		} catch (DataAccessException e) {
			e.printStackTrace();
			fail("Error: DataAccess exception");
		}
	}
}
