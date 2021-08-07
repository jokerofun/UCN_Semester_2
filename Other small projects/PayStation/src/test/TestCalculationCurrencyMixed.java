package test;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controllayer.ControlPayStation;
import controllayer.IllegalCoinException;
import modellayer.Currency;

/**
 * Inspired by the book: Flexible, Reliable Software Henrik Bćrbak Christensen:
 * Flexible, Reliable Software. Taylor and Francis Group, LLC 2010
 */

public class TestCalculationCurrencyMixed {

	ControlPayStation ps;

	/** Fixture for pay station testing. */
	@BeforeEach
	public void setUp() {
		ps = new ControlPayStation();
	}

	/**
	 * Entering 1 euro, 10 kroner and 5 kroner should make the display report 120
	 * minutes parking time.
	 */
	@Test
	public void shouldDisplay120MinFor1EuroAnd15Kroner() throws IllegalCoinException {
		// Arrange
		int expectedParkingTime = 120; // In minutes
		int euroValue = 1;
		int firstKronerValue = 10;
		int secondKronerValue = 5;
		Currency.ValidCurrency euroCurrency = Currency.ValidCurrency.EURO;
		Currency.ValidCoinType euroType = Currency.ValidCoinType.INTEGER;
		Currency.ValidCurrency KronerCurrency = Currency.ValidCurrency.DKK;
		Currency.ValidCoinType KronerType = Currency.ValidCoinType.INTEGER;

		// Act
		ps.addPayment(euroValue, euroCurrency, euroType);
		ps.addPayment(firstKronerValue, KronerCurrency, KronerType);
		ps.addPayment(secondKronerValue, KronerCurrency, KronerType);
		// Assert
		assertEquals("Should display 120 min for 1 euro and 15 kroner", expectedParkingTime, ps.readDisplay());
	}

	/** Fixture for pay station testing. */
	@AfterEach
	public void cleanUp() {
		ps.setReady();
	}

}
