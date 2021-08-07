package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import control.DataAccessException;
import db.StudentDb;
import db.StudentDbIF;
import model.Student;

class TestStudentDb {

	private StudentDbIF studentDb;

	@BeforeEach
	public void setUp() throws SQLException {
		try {
			DBCleanup.cleanDB();
		} catch (Exception e) {
			throw new RuntimeException("Could not clean up the db");
		}
		try {
			DBCleanup.restoreDB();
		} catch (Exception e) {
			throw new RuntimeException("Could not restore the db");
		}
		studentDb = new StudentDb();
	}

//	@Test
//	public void testInsert() {
//		try {
//			Student returnValue = studentDb.createWithIdentity("Michelle");
//			assertNotNull(returnValue);
//			assertEquals("Michelle", returnValue.getName());
//			assertEquals(103, returnValue.getId());
//		} catch (DataAccessException e) {
//			e.printStackTrace();
//			fail("Error: SQL exception");
//		}
//	}

	@Test
	public void testFindByIdExisting() {
		try {
			Student student = studentDb.findById(103, false);
			assertEquals("Steven", student.getName());
		} catch (DataAccessException e) {
			e.printStackTrace();
			fail("Error: DataAccess exception");
		}
	}
}
