package utility;

import modellayer.Coin;
import modellayer.Currency;
import modellayer.PPrice;

public class Calculation {

	public static double calculate(Coin coin, PPrice price) {

		double result = 0;
		Currency.ValidCurrency currency = coin.getCurrency();
		PPrice priceDkk = price;

		if (currency == Currency.ValidCurrency.EURO) {
			result = getEuroCoinValueInCent(coin);
		} else if (currency == Currency.ValidCurrency.DKK) {
			result = getDkkCoinValueInCent(coin, priceDkk);
		}
		return result;
	}

	private static double getEuroCoinValueInCent(Coin coin) {
		double valueInCent = 0;
		double coinValue = coin.getAmount();

		if (coin.getCoinType() == Currency.ValidCoinType.INTEGER) {
			valueInCent = coinValue * 100;
		} else {
			valueInCent = coinValue;
		}

		return valueInCent;
	}

	private static double getDkkCoinValueInCent(Coin coin, PPrice price) {
		double valueInCent = 0;
		Currency.ValidCoinType coinType = coin.getCoinType();
		double coinValue = coin.getAmount();

		if (coinType == Currency.ValidCoinType.INTEGER) {
			valueInCent = ((coinValue * 100) / price.getExchangeEuroDkk());
		} else {
			valueInCent = (coinValue / price.getExchangeEuroDkk());
		}

		return valueInCent;
	}
}
