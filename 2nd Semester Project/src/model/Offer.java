package model;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Offer {

	private int offerNumber;
	private Date dueDate;
	private BigDecimal materialPrice;
	private BigDecimal totalPrice;
	private List<Procedure> procedures;

	public Offer(int offerNumber, Date dueDate, BigDecimal materialPrice, BigDecimal totalPrice) {
		this.offerNumber = offerNumber;
		this.dueDate = dueDate;
		this.materialPrice = materialPrice;
		this.totalPrice = totalPrice;
		this.procedures = new ArrayList<>();
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
	 * @return the materialPrice
	 */
	public BigDecimal getMaterialPrice() {
		return materialPrice;
	}

	/**
	 * @param materialPrice the materialPrice to set
	 */
	public void setMaterialPrice(BigDecimal materialPrice) {
		this.materialPrice = materialPrice;
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
	 * @return the procedures
	 */
	public List<Procedure> getProcedures() {
		return procedures;
	}

	/**
	 * @param procedures the procedures to set
	 */
	public void setProcedures(List<Procedure> procedures) {
		this.procedures = procedures;
	}

	/**
	 * @param procedure the procedure to add
	 */
	public void addProcedureToList(Procedure procedure) {
		procedures.add(procedure);
	}

	/**
	 * @param procedure the procedure to remove
	 */
	public void removeProcedureFromList(Procedure procedure) {
		procedures.remove(procedure);
	}
}
