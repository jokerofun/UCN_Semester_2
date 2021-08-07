package control;

import java.math.BigDecimal;

import model.Product;
import model.SaleOrder;
import model.SalesLine;

public class SaleOrderController {
	private SaleOrder saleOrder;
	private Product product;
	
	public SaleOrderController() {
	}
	
	public void createOrder() {
		saleOrder = new SaleOrder();
	}
	
	public boolean addCustomer(int customerId) throws DataAccessException {
		CustomerController c = new CustomerController();
		boolean result = true;
		try {
		c.findCustomerById(customerId);
		}
		catch(Exception e) {
			result = false;
			System.out.println("Error!");
		}
		if(result == true) {
			saleOrder.addCustomerId(customerId);
		}
		return result;
	}
	
	public int findProduct(int productId) {
		ProductController p = new ProductController();
		try{
			product = p.findProduct(productId);
		}
		catch(Exception e) {
			System.out.println("Error");
		}
		if(product == null) {
			return -1;
			//-1 = product not found
		}
		else {
		return product.getInStock();
		}
	}
	
	public BigDecimal addProduct(int quantity, boolean isRent) {
		SalesLine salesLine;
		if(isRent == true)
			salesLine = new SalesLine(product.getProductId(),quantity,isRent,product.getRentPrice());
		else 
			salesLine = new SalesLine(product.getProductId(),quantity,isRent,product.getSalesPrice());
		try{
			saleOrder.addSalesLine(salesLine);
		}
		catch(Exception e) {
			System.out.println("Error");
		}
		return saleOrder.calculateTotalPirce();
	}
	public void Finished() {
		
	}
}