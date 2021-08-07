package model;
import java.math.*;
import java.util.*;
import control.NullPointerException;

import model.Date;

public class SaleOrder {
	private int customerId;
	private Date date;
	private boolean deliveryStatus = false;
	private Date deliveryDate;
	private BigDecimal totalPrice;
	private ArrayList<SalesLine> salesLines = new ArrayList<>();
	
	public SaleOrder() {
	}
	
	public void addSalesLine(SalesLine salesLine) throws NullPointerException{
		try {
		salesLines.add(salesLine);
		}
		catch(Exception e) {
			throw new NullPointerException("SalesLine not created",e);
		}
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public boolean isDeliveryStatus() {
		return deliveryStatus;
	}

	public void setDeliveryStatus(boolean deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void addCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public BigDecimal calculateTotalPirce() {
		BigDecimal price;
		price = new BigDecimal("0.00");
		MathContext mc = new MathContext(4);
		for(SalesLine s: salesLines) {
			price = price.add(s.getTotalPrice(),mc);
		}
		return price;
	}
}
