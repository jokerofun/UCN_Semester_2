package controller;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import Db.SaleOrderDb;
import model.Product;
import model.SaleOrder;

public class SaleOrderController {

	private SaleOrderDb db;
	private ProductController productController = new ProductController();

	public SaleOrderController() throws DataAccessException {
		db = new SaleOrderDb();
	}

	public SaleOrder createSaleOrder(String customerName, Date orderDate, Date dueDate, int offerNumber,
			BigDecimal totalPrice, List<Product> products) throws DataAccessException {
		SaleOrder saleOrder = null;
		try {
			saleOrder = db.create(customerName, orderDate, dueDate, offerNumber, totalPrice, products);

			if (saleOrder != null) {
				for (Product product : products) {
					try {
						productController.createProductWithSaleOrder(product.getProductName(), product.getDescription(),
								product.getPrice(), product.getQuantity(), saleOrder.getSaleOrderNumber());
					} catch (DataAccessException e) {
						e.printStackTrace();
					}
				}
			}

		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return saleOrder;
	}

	public boolean updateSaleOrder(SaleOrder saleOrder) throws DataAccessException {
		return db.update(saleOrder);
	}

	public boolean deleteSaleOrder(int saleOrderNumber) throws DataAccessException {
		return db.delete(saleOrderNumber);
	}

	public SaleOrder findSaleOrderByNumber(int saleOrderNumber) throws DataAccessException {
		return db.findByNumber(saleOrderNumber);
	}

	public List<SaleOrder> getAllSaleOrders() throws DataAccessException {
		return db.getAll();
	}

}
