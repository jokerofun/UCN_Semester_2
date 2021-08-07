package db;

import java.math.BigDecimal;
import java.util.ArrayList;

import control.DataAccessException;
import control.NullPointerException;
import model.Date;
import model.SaleOrder;
import model.SalesLine;

public interface SaleOrderDbIF {

	SaleOrder create(int customerId,boolean deliveryStatus, BigDecimal totalPrice, ArrayList<SalesLine> salesLines,Date date,Date deliveryDate) throws DataAccessException, NullPointerException;

	//boolean update() throws DataAccessException;

	boolean delete(int id) throws DataAccessException;

	SaleOrder findById(int id) throws DataAccessException, NullPointerException;
}
