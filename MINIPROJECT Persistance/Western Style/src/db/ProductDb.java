package db;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import control.DataAccessException;
import model.Product;

public class ProductDb implements ProductDbIF {

	@Override
	public Product create(String productName, BigDecimal purchasePrice, BigDecimal salesPrice, BigDecimal rentPrice,
			String countryOfOrigin, int minStock, int inStock) throws DataAccessException {
		Product product = null;
		String sql = String.format(
				"insert into dbo.Product(productName, purchasePrice, salesPrice, rentPrice, countryOfOrigin, minStock, inStock) values('%s','%.2f','%.2f','%.2f','%s','%d','%d')",
				productName, purchasePrice, salesPrice, rentPrice, countryOfOrigin, minStock, inStock);
		// int id = DBConnection.getInstance().executeInsert(sql);
		// product = findById(id);
		return product;
	}

	@Override
	public boolean update(int productId, String productName, BigDecimal purchasePrice, BigDecimal salesPrice,
			BigDecimal rentPrice, String countryOfOrigin, int minStock, int inStock) throws DataAccessException {
		String sql = "update dbo.Product set ";
		int res;
		if (productName != null) {
			sql += " productName='" + productName + "'";
		}
		if (purchasePrice != null) {
			sql += " purchasePrice='" + purchasePrice + "'";
		}
		if (salesPrice != null) {
			sql += " salesPrice='" + salesPrice + "'";
		}
		if (rentPrice != null) {
			sql += "rentPrice='" + rentPrice + "'";
		}
		if (countryOfOrigin != null) {
			sql += "countryOfOrigin='" + countryOfOrigin + "'";
		}
		if (productName == null && purchasePrice == null && salesPrice == null && rentPrice == null
				&& countryOfOrigin == null) {
			return false;
		} else {
			res = DBConnection.getInstance().executeUpdate(sql);
		}

		return res > 0;
	}

	@Override
	public boolean delete(int id) throws DataAccessException {
		String sql = "delete from dbo.Product where productId = " + id;
		// int res = DBConnection.getInstance().executeUpdate(sql);
		// return res > 0;
		return true;
	}

	@Override
	public Product findById(int id) throws DataAccessException {
		Product res = null;
		String sql = "select * from dbo.Product where productId = " + id;
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

	private Product buildObject(ResultSet rs) throws DataAccessException {
		Product product;
		try {
			product = new Product(rs.getInt("productId"), rs.getString("productName"), rs.getBigDecimal("purchasePrice"), rs.getBigDecimal("salesPrice"),rs.getBigDecimal("rentPrice"),rs.getString("countryOfOrigin"),rs.getInt("minStock"),rs.getInt("inStock"));
		} catch (SQLException e) {
			throw new DataAccessException("Could not read result set - single object", e);
		}
		return product;
	}

}
