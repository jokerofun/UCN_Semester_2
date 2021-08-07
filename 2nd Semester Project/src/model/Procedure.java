package model;

import java.math.BigDecimal;

public class Procedure {

	private String procedureName;
	private BigDecimal pricePerHour;
	private String employeeTypeRequired;
	private int code;
	// done field int
	// TODO type for auto generated code maybe ???

	public Procedure(String procedureName, BigDecimal pricePerHour, int code, String employeeTypeRequired) {
		this.procedureName = procedureName;
		this.pricePerHour = pricePerHour;
		this.code = code;
		this.employeeTypeRequired = employeeTypeRequired;
	}

	/**
	 * @return the procedureName
	 */
	public String getProcedureName() {
		return procedureName;
	}

	/**
	 * @param procedureName the procedureName to set
	 */
	public void setProcedureName(String procedureName) {
		this.procedureName = procedureName;
	}

	/**
	 * @return the pricePerHour
	 */
	public BigDecimal getPricePerHour() {
		return pricePerHour;
	}

	/**
	 * @param pricePerHour the pricePerHour to set
	 */
	public void setPricePerHour(BigDecimal pricePerHour) {
		this.pricePerHour = pricePerHour;
	}

	/**
	 * @return the employeeTypeRequired
	 */
	public String getEmployeeTypeRequired() {
		return employeeTypeRequired;
	}

	/**
	 * @param employeeTypeRequired the employeeTypeRequired to set
	 */
	public void setEmployeeTypeRequired(String employeeTypeRequired) {
		this.employeeTypeRequired = employeeTypeRequired;
	}

	/**
	 * @return the code
	 */
	public int getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(int code) {
		this.code = code;
	}
}
