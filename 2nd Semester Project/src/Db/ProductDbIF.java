package Db;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import controller.DataAccessException;
import model.Product;

public interface ProductDbIF {

//	Product create(String productName, String productDescription, BigDecimal price, int quantity)
//			throws DataAccessException;

	Product createWithSaleOrder(String productName, String productDescription, BigDecimal price, int quantity,
			int saleOrderNumber) throws DataAccessException;

	Product findById(int productId) throws DataAccessException;

	Product findByName(String productName) throws DataAccessException;

	boolean update(Product product) throws DataAccessException;

	boolean delete(int productId) throws DataAccessException;

	List<Product> getAll() throws DataAccessException;

	HashMap<String, Integer> getAllProductAndQuantity(int offerNumber) throws DataAccessException;
}
