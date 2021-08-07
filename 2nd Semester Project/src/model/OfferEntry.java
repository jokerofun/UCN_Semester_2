package model;

import java.math.BigDecimal;
import java.util.HashMap;

public class OfferEntry {

	private String productName;
	private int quantity;
	private HashMap<Procedure, Integer> procedureHour;
	BigDecimal totalPrice = BigDecimal.ZERO;

	public OfferEntry(String productName, int quantity) {
		this.productName = productName;
		this.quantity = quantity;
		this.procedureHour = new HashMap<Procedure, Integer>();
	}

	/**
	 * @return the totalPrice
	 */
	public BigDecimal calculatePricePerHourPerProduct() {
		procedureHour.forEach((k, v) -> {
			totalPrice = totalPrice.add(k.getPricePerHour().multiply(BigDecimal.valueOf(v)));
		});
		return totalPrice.multiply(BigDecimal.valueOf(quantity));
	}

	/**
	 * @return the productName
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * @param productName the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return the procedureHour
	 */
	public HashMap<Procedure, Integer> getProcedureHour() {
		return procedureHour;
	}

	/**
	 * @param procedureHour the procedureHour to set
	 */
	public void setProcedureHour(HashMap<Procedure, Integer> procedureHour) {
		this.procedureHour = procedureHour;
	}

	/**
	 * @param procedure && hours to add
	 */
	public void addProcedureAndHour(Procedure procedure, int hours) {
		this.procedureHour.put(procedure, hours);
	}
}
