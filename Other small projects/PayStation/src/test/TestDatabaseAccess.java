package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Statement;
import java.time.LocalDate;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controllayer.ControlPrice;
//import controllayer.ControlPayStation;
//import controllayer.Currency;
//import controllayer.IPayStation;
//import controllayer.IReceipt;
//import controllayer.IllegalCoinException;
import databaselayer.DBConnection;
import databaselayer.DatabaseLayerException;
import databaselayer.DatabasePBuy;
import databaselayer.DatabasePPrice;
import modellayer.PBuy;
import modellayer.PPayStation;
import modellayer.PPrice;

//import static org.junit.Assert.*;

/**
 * Inspired by the book: Flexible, Reliable Software Henrik Bærbak Christensen:
 * Flexible, Reliable Software. Taylor and Francis Group, LLC 2010
 */

public class TestDatabaseAccess {

	DBConnection con = null;
	static PBuy tempPBuy;

	/** Fixture for pay station testing. */
	@BeforeEach
	public void setUp() {
		con = DBConnection.getInstance();
	}

	@Test
	public void wasConnected() {
		assertNotNull("Connected - connection cannot be null", con);

		DBConnection.closeConnection();
		boolean wasNullified = DBConnection.instanceIsNull();
		assertTrue("Disconnected - instance set to null", wasNullified);

		con = DBConnection.getInstance();
		assertNotNull("Connected - connection cannot be null", con);
	}

	@Test
	public void wasInsertedBuy() throws DatabaseLayerException {

		// Arrange
		LocalDate timeNow = java.time.LocalDate.now();
		double payedCentAmount = 100;

		tempPBuy = new PBuy();

		PPayStation pStat = new PPayStation(1, "P-423E");
		pStat.setAmount(payedCentAmount);
		tempPBuy.setAssociatedPaystation(pStat);
		tempPBuy.setBuyTime(timeNow);

		DatabasePBuy dbPbuy = new DatabasePBuy();

		// Act
		int key = dbPbuy.insertParkingBuy(tempPBuy);

		// Assert
		assertEquals(1, key);

	}

	@Test
	public void wasRetrievedPriceDatabaselayer() throws DatabaseLayerException {
		// Arrange
		PPrice foundPrice = null;
		int pZoneId = 2;
		DatabasePPrice dbPrice = new DatabasePPrice();

		// Act
		foundPrice = dbPrice.getPriceByZoneId(pZoneId);

		// Assert
		assertEquals("Dummy", 24, foundPrice.getParkingPrice());

	}

	@Test
	public void wasRetrievedPriceControllayer() throws DatabaseLayerException {

		// Arrange
		PPrice foundPrice = null;
		int pZoneId = 2;
		ControlPrice priceController = new ControlPrice();

		// Act
		foundPrice = priceController.getPriceRemote(pZoneId);

		// Assert
		assertEquals("Dummy", 24, foundPrice.getParkingPrice());

	}

	@AfterAll
	public static void cleanUpWhenFinish() {
		//
		// Arrange
		DatabasePBuy dbPbuy = new DatabasePBuy();
		int numDeleted = 0;

		// Act
		try {
			numDeleted = dbPbuy.deleteParkingBuy(tempPBuy);
			Statement stmt = DBConnection.getInstance().getDBcon().createStatement();
			stmt.executeUpdate("drop table if exists dbo.PBuy");
			stmt.executeUpdate("CREATE TABLE dbo.PBuy(\r\n" + "		id int IDENTITY(1,1) NOT NULL,\r\n"
					+ "		buyTime datetime NOT NULL,\r\n" + "		duration int NOT NULL,\r\n"
					+ "		payedAmount float NOT NULL,\r\n" + "		pPaystation_id int NOT NULL,\r\n"
					+ "		CONSTRAINT PK_PBuy PRIMARY KEY CLUSTERED (id),\r\n"
					+ "		CONSTRAINT FK_PBuy_PPaystation FOREIGN KEY(pPaystation_id) REFERENCES PPaystation(id)\r\n"
					+ "	)");
		} catch (Exception ex) {
			System.out.println("Error: " + ex.getMessage());
		} finally {
			DBConnection.closeConnection();
		}

		// Assert
		// assertEquals("One row deleted", 1, numDeleted );
	}

}
