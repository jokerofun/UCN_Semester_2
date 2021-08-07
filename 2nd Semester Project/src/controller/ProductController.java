package controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import Db.ProductDb;
import model.Product;

public class ProductController {

	private ProductDb db;

	public ProductController() throws DataAccessException {
		db = new ProductDb();
	}

//	public Product createProduct(String name, String description, BigDecimal price, int quantity)
//			throws DataAccessException {
//		return db.create(name, description, price, quantity);
//	}

	public Product createProductWithSaleOrder(String name, String description, BigDecimal price, int quantity,
			int saleOrderNumber) throws DataAccessException {
		return db.createWithSaleOrder(name, description, price, quantity, saleOrderNumber);
	}

	public Product findProductById(int productId) throws DataAccessException {
		return db.findById(productId);
	}

	public Product findProductByName(String productName) throws DataAccessException {
		return db.findByName(productName);
	}

	public boolean updateProduct(Product product) throws DataAccessException {
		return db.update(product);
	}

	public boolean deleteProduct(int productId) throws DataAccessException {
		return db.delete(productId);
	}

	public List<Product> getAllProducts() throws DataAccessException {
		return db.getAll();
	}

	public HashMap<String, Integer> getAllProductAndQuantity(int offerNumber) throws DataAccessException {
		return db.getAllProductAndQuantity(offerNumber);
	}
}
