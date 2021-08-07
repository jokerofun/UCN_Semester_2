package model;
import java.math.*;
import java.util.*;
import control.NullPointerException;

import model.Date;

public class SalesOrder {
	private Date date;
	private boolean deliveryStatus;
	private Date deliveryDate;
	private BigDecimal totalPrice;
	private ArrayList<SalesLine> SalesLines = new ArrayList<>();
	
	public SalesOrder() {
	}
	
	public void addSalesLine(SalesLine salesLine) throws NullPointerException{
		try {
		SalesLines.add(salesLine);
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
}
