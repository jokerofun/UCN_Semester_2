package db;

import java.math.BigDecimal;

import control.DataAccessException;
import model.Product;

public interface ProductDbIF {

	Product create(String productName, BigDecimal purchasePrice, BigDecimal salesPrice, BigDecimal rentPrice, String countryOfOrigin, int minStock, int inStock) throws DataAccessException;

	boolean update(int productId, String productName, BigDecimal purchasePrice, BigDecimal salesPrice, BigDecimal rentPrice, String countryOfOrigin, int minStock, int inStock) throws DataAccessException;

	boolean delete(int id) throws DataAccessException;

	Product findById(int id) throws DataAccessException;
}
