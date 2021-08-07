package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import db.DBConnection;


class TestDBConnection {

	@Test
	public void testGetConnection() {

		try {
			assertNotNull(DBConnection.getInstance().getConnection());
		} catch (Exception e) {
			fail("issues with dbconnection");
		}
	}

}
