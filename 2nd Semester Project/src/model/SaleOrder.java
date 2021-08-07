package model;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class SaleOrder {
	private int saleOrderNumber;
	private String customerName;
	private Date orderDate;
	private Date dueDate;
	private int offerNumber;
	private BigDecimal totalPrice;
	private List<Product> products;

	public SaleOrder(int orderNumber, String customerName, Date orderDate, Date dueDate, int offerNumber,
			BigDecimal totalPrice) {
		this.saleOrderNumber = orderNumber;
		this.customerName = customerName;
		this.orderDate = orderDate;
		this.dueDate = dueDate;
		this.offerNumber = offerNumber;
		this.totalPrice = totalPrice;
		this.products = new ArrayList<>();
	}

	/**
	 * @return the saleOrderNumber
	 */
	public int getSaleOrderNumber() {
		return saleOrderNumber;
	}

	/**
	 * @param saleOrderNumber the saleOrderNumber to set
	 */
	public void setSaleOrderNumber(int orderNumber) {
		this.saleOrderNumber = orderNumber;
	}

	/**
	 * @return the customerName
	 */
	public String getCustomerName() {
		return customerName;
	}

	/**
	 * @param customerName the customerName to set
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	/**
	 * @return the orderDate
	 */
	public Date getOrderDate() {
		return orderDate;
	}

	/**
	 * @param orderDate the orderDate to set
	 */
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	/**
	 * @return the dueDate
	 */
	public Date getDueDate() {
		return dueDate;
	}

	/**
	 * @param dueDate the dueDate to set
	 */
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	/**
	 * @return the offerNumber
	 */
	public int getOfferNumber() {
		return offerNumber;
	}

	/**
	 * @param offerNumber the offerNumber to set
	 */
	public void setOfferNumber(int offerNumber) {
		this.offerNumber = offerNumber;
	}

	/**
	 * @return the totalPrice
	 */
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	/**
	 * @param totalPrice the totalPrice to set
	 */
	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	/**
	 * @return the products
	 */
	public List<Product> getProducts() {
		return products;
	}

	/**
	 * @param products the products to set
	 */
	public void setProducts(List<Product> products) {
		this.products = products;
	}

	/**
	 * @param product the product to add
	 */
	public void addProductToList(Product product) {
		products.add(product);
	}

	/**
	 * @param product the product to remove
	 */
	public void removeProductFromList(Product product) {
		products.remove(product);
	}

}
