package test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import Db.DBConnection;

public class TestDBConnection {

	@Test
	public void testGetConnection() {
		try {
			assertNotNull(DBConnection.getInstance().getConnection());
		} catch (Exception e) {
			fail("issues with dbconnection");
		}
	}

}
