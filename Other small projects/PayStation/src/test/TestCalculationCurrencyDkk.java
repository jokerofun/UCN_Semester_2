package test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;

import controllayer.ControlPayStation;
import controllayer.IllegalCoinException;
import modellayer.Currency;

/**
 * Inspired by the book: Flexible, Reliable Software Henrik Bærbak Christensen:
 * Flexible, Reliable Software. Taylor and Francis Group, LLC 2010
 */

public class TestCalculationCurrencyDkk {

	ControlPayStation ps;

	/** Fixture for pay station testing. */
	@Before
	public void setUp() {
		ps = new ControlPayStation();
	}

	/**
	 * Entering 50 øre should make the display report 3 minutes parking time.
	 */
	@Test
	public void shouldDisplay3MinFor50Ore() throws IllegalCoinException {

		// Arrange
		int expectedParkingTime = 3; // In minutes
		int coinValue = 50;
		Currency.ValidCurrency coinCurrency = Currency.ValidCurrency.DKK;
		Currency.ValidCoinType coinType = Currency.ValidCoinType.FRACTION;

		// Act
		ps.addPayment(coinValue, coinCurrency, coinType);

		// Assert
		assertEquals("Should display 3 min for 50 øre", expectedParkingTime, ps.readDisplay());
	}

	/**
	 * Entering 50ore, Entering 2dkk, Entering 5dkk; should make the display report
	 * 40 min;
	 */
	@Test
	public void shouldDisplay40MinFor7dkk50ore() throws IllegalCoinException {

		// Arrange
		int expectedParkingTime = 40; // In minutes
		int coin1Value = 50;
		int coin2Value = 2;
		int coin3Value = 5;
		Currency.ValidCurrency coinCurrency = Currency.ValidCurrency.DKK;
		Currency.ValidCoinType coin1Type = Currency.ValidCoinType.FRACTION;
		Currency.ValidCoinType coin2Type = Currency.ValidCoinType.INTEGER;
		Currency.ValidCoinType coin3Type = Currency.ValidCoinType.INTEGER;

		// Act
		ps.addPayment(coin1Value, coinCurrency, coin1Type);
		ps.addPayment(coin2Value, coinCurrency, coin2Type);
		ps.addPayment(coin3Value, coinCurrency, coin3Type);

		// Assert
		assertEquals("Should display 40 min for 7 DKK 50 Ore", expectedParkingTime, ps.readDisplay());
	}

	/** Fixture for pay station testing. */
	@AfterEach
	public void cleanUp() {
		ps.setReady();
	}

}
