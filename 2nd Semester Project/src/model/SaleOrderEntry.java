package model;

import java.util.HashMap;
import java.util.List;

public class SaleOrderEntry {

	private SaleOrder saleOrder;
	private HashMap<Product, List<ProcedureEntry>> productAndItsProcedures;

	public SaleOrderEntry(SaleOrder saleOrder) {
		this.saleOrder = saleOrder;
		productAndItsProcedures = new HashMap<Product, List<ProcedureEntry>>();
	}

	/**
	 * @return the saleOrder
	 */
	public SaleOrder getSaleOrder() {
		return saleOrder;
	}

	/**
	 * @param saleOrder the saleOrder to set
	 */
	public void setSaleOrder(SaleOrder saleOrder) {
		this.saleOrder = saleOrder;
	}

	/**
	 * @return the productAndItsProcedures
	 */
	public HashMap<Product, List<ProcedureEntry>> getProductAndItsProcedures() {
		return productAndItsProcedures;
	}

	/**
	 * @param productAndItsProcedures the productAndItsProcedures to set
	 */
	public void setProductAndItsProcedures(HashMap<Product, List<ProcedureEntry>> productAndItsProcedures) {
		this.productAndItsProcedures = productAndItsProcedures;
	}
}
