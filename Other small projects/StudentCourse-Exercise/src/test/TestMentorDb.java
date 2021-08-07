package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import control.DataAccessException;
import java.sql.SQLException;
import db.MentorDb;
import db.MentorDbIF;
import model.Mentor;

class TestMentorDb {

	private MentorDbIF mentorDb ;
	
	@BeforeEach
	public void setUp() throws SQLException {
		try {
			DBCleanup.cleanDB();
		}
		catch(Exception e) {
			throw new RuntimeException("Could not clean up the db");
		}
		try {
			DBCleanup.restoreDB();
		}
		catch(Exception e) {
			throw new RuntimeException("Could not restore the db");
		}
		mentorDb = new MentorDb();
	}
	


	@Test
	public void testInsert() {
		try {
            Mentor returnValue = mentorDb.createWithIdentity("Michelle");
            assertNotNull(returnValue);
            assertEquals("Michelle", returnValue.getMentorName());
            assertEquals(103, returnValue.getMentorId());
            } catch (DataAccessException e) {
			e.printStackTrace();
			fail("Error: SQL exception");
		}
	}
		
	@Test 
	public void testFindByIdExisting() {
		try {
			Mentor mentor = mentorDb.findById(100);
			assertEquals("Mark", mentor.getMentorName());
		} catch (DataAccessException e) {
			e.printStackTrace();
			fail("Error: DataAccess exception");
		}	
	}
}
