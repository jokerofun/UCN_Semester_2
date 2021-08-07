package db;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import control.DataAccessException;
import control.NullPointerException;
import model.Date;
import model.SaleOrder;
import model.SalesLine;

public class SaleOrderDb implements SaleOrderDbIF {
	// Inserting date to sql?
	@Override
	public SaleOrder create(int customerId, boolean deliveryStatus, BigDecimal totalPrice,
			ArrayList<SalesLine> salesLines, Date date, Date deliveryDate)
			throws DataAccessException, NullPointerException {
		SaleOrder saleOrder = null;
		int bit;
		if (deliveryStatus == true) {
			bit = 1;
		} else {
			bit = 0;
		}
		String sql = String.format(
				"insert into dbo.saleOrder(customerId, deliveryStatus, totalPrice) values('%d','%d','%.2f')",
				customerId, bit, totalPrice);
		int id = DBConnection.getInstance().executeInsertWithIdentity(sql);
		for (SalesLine s : salesLines) {
			sql = String.format(
					"insert into dbo.SalesLine(productId, quantity, isRent, totalPrice) values('%d', '%d', '%b', '%.2f')",
					s.getProductId(), s.getQuantity(), s.isRent(), s.getTotalPrice());
			DBConnection.getInstance().executeInsertWithIdentity(sql);
		}
		String dateFormat;
		dateFormat = "'" + date.getYear() + "-" + date.getMonth() + "-" + date.getDay() + "'";
		sql = String.format("insert into dbo.SaleOrder(saleDate) values('%s')", dateFormat);
		DBConnection.getInstance().executeInsertWithIdentity(sql);
		dateFormat = "'" + deliveryDate.getYear() + "-" + deliveryDate.getMonth() + "-" + deliveryDate.getDay() + "'";
		sql = String.format("insert into dbo.SaleOrder(deliveryDate) values('%s')", dateFormat);
		DBConnection.getInstance().executeInsertWithIdentity(sql);
		saleOrder = findById(id);
		return saleOrder;
	}

	/*
	 * @Override public boolean update() throws DataAccessException { String sql =
	 * "update dbo.SaleOrder set "; int res; if ( != null) { sql += " ='" + + "'"; }
	 * if ( == null && == null && == null) { return false; } else { res =
	 * DBConnection.getInstance().executeUpdate(sql); }
	 * 
	 * return res > 0; }
	 */

	@Override
	public boolean delete(int id) throws DataAccessException {
		String sql = "delete from dbo.SaleOrder where saleOrderId = " + id;
		int res = DBConnection.getInstance().executeUpdate(sql);
		return res > 0;
	}

	@Override
	public SaleOrder findById(int id) throws DataAccessException, NullPointerException {
		SaleOrder res = null;
		String sql = "select * from dbo.SaleOrder where SaleOrderId = " + id;
		try (Statement s = DBConnection.getInstance().getConnection().createStatement();
				ResultSet rs = s.executeQuery(sql)) {
			if (rs.next()) {
				res = buildObject(rs);
			}
		} catch (SQLException e) {
			throw new DataAccessException("Could not read result set", e);
		}
		return res;
	}

	private SaleOrder buildObject(ResultSet rs) throws DataAccessException, NullPointerException {
		SaleOrder saleOrder;
		BigDecimal bg1;
		bg1 = new BigDecimal("0.00");
		try {
			saleOrder = new SaleOrder();
			saleOrder.addCustomerId(rs.getInt("customerId"));
			saleOrder.setDeliveryStatus(rs.getBoolean("deliveryStatus"));
			saleOrder.setTotalPrice(rs.getBigDecimal("totalPrice"));
			String sql = "select * from dbo.SalesLine where SaleOrderId = " + rs.getInt("saleOrderId");
			try (Statement s = DBConnection.getInstance().getConnection().createStatement();
					ResultSet rs1 = s.executeQuery(sql)) {
				while (rs1.next()) {
					SalesLine l1 = new SalesLine(rs1.getInt("productId"), rs1.getInt("quantity"),
							rs1.getBoolean("isRent"), bg1);
					l1.setTotalPrice(rs1.getBigDecimal("totalPrice"));
					saleOrder.addSalesLine(l1);
				}
			}
		} catch (SQLException e) {
			throw new DataAccessException("Could not read result set - single object", e);
		}
		return saleOrder;
	}

}