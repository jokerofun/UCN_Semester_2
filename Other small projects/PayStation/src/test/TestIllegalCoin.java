package test;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controllayer.ControlPayStation;
import controllayer.IllegalCoinException;
import modellayer.Currency;

/**
 * Inspired by the book: Flexible, Reliable Software Henrik Bærbak Christensen:
 * Flexible, Reliable Software. Taylor and Francis Group, LLC 2010
 */

public class TestIllegalCoin {

	ControlPayStation ps;

	/** Fixture for pay station testing. */
	@BeforeEach
	public void setUp() {
		ps = new ControlPayStation();
	}

	/**
	 * Verify that illegal coins are rejected.
	 */

	// Norwegian coin
	@Test()
	public void shouldRejectIllegalCurrencyNokCoin() throws IllegalCoinException {

		// Arrange
		int coinValue = 50;
		Currency.ValidCurrency coinCurrency = Currency.ValidCurrency.NOK;
		Currency.ValidCoinType coinType = Currency.ValidCoinType.INTEGER;

		// Act
		assertThrows(IllegalCoinException.class, () -> ps.addPayment(coinValue, coinCurrency, coinType),
				"Coin type not valid");
	}

	// unknown Euro coin value
	@Test()
	public void shouldRejectIllegalEuroCoin() throws IllegalCoinException {
		// Arrange
		int coinValue = 15;
		Currency.ValidCurrency coinCurrency = Currency.ValidCurrency.EURO;
		Currency.ValidCoinType coinType = Currency.ValidCoinType.INTEGER;

		// Act
		assertThrows(IllegalCoinException.class, () -> ps.addPayment(coinValue, coinCurrency, coinType),
				"Coin type not valid");
	}

}
