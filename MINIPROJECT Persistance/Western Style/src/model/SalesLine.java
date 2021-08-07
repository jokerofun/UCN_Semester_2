package model;

import java.math.BigDecimal;

public class SalesLine {
	private int productId;
	private int quantity;
	private boolean isRent;
	private BigDecimal totalPrice;
	
	public SalesLine(int productId, int quantity,boolean isRent,BigDecimal price) {
		this.productId = productId;
		this.quantity = quantity;
		this.isRent = isRent;
		this.totalPrice = BigDecimal.valueOf(quantity).multiply(price);
	}
	
	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public boolean isRent() {
		return isRent;
	}

	public void setRent(boolean isRent) {
		this.isRent = isRent;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

}
