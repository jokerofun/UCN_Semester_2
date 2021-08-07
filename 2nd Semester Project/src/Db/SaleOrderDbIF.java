package Db;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import controller.DataAccessException;
import model.Product;
import model.SaleOrder;

public interface SaleOrderDbIF {

	SaleOrder create(String customerName, Date orderDate, Date dueDate, int offerNumber, BigDecimal totalPrice,
			List<Product> products) throws DataAccessException;

	SaleOrder findByNumber(int saleOrderNumber) throws DataAccessException;

	boolean update(SaleOrder saleOrder) throws DataAccessException;

	boolean delete(int saleOrderNumber) throws DataAccessException;

	List<SaleOrder> getAll() throws DataAccessException;
}
