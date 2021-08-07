package db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import control.DataAccessException;
import model.Address;
import model.Customer;

public class CustomerDb implements CustomerDbIF {
	private DBConnection dbConnection = DBConnection.getInstance();

	public CustomerDb() throws DataAccessException {
	}

	// TODO fix this method
	@Override
	public Customer create(String customerName, String phoneNumber, String CVR, String street, String city,
			String zipcode) throws DataAccessException {
		Customer customer = null;
		String sql;
		try {
			dbConnection.startTransaction();
			sql = String.format("insert into dbo.Customer(customerName, phoneNumber, CVR) values('%s','%s','%s')",
					customerName, phoneNumber, CVR);
			int id = dbConnection.executeInsertWithIdentity(sql);
			customer = createAddress(id, street, city, zipcode);

			dbConnection.executeUpdate(sql);
			dbConnection.commitTransaction();
		} catch (DataAccessException e) {
			if (dbConnection.getConnection() != null) {
				try {
					dbConnection.rollbackTransaction();
				} catch (DataAccessException ee) {
					throw new DataAccessException("Could not rollback transaction", ee);
				}
			}
			throw new DataAccessException("Could not start/commit transaction", e);
		
		return customer;
	}

	@Override
	public Customer createAddress(int id, String street, String city, String zipcode) throws DataAccessException {
		String sql;
		sql = String.format(
				"insert into dbo.CustomerAddress(street, city, zipcode, customerId) values('%s','%s','%s','%d')",
				street, city, zipcode, id);
		dbConnection.executeUpdate(sql);
		return findById(id);
	}

	@Override
	public boolean update(int customerId, String customerName, String phoneNumber, String CVR, String street,
			String city, String zipcode) throws DataAccessException {
		String sql1 = "update dbo.Customer set ";
		String sql2 = "update dbo.customerAddress set ";
		int res1;
		int res2;
		// sql1
		if (customerName != null) {
			sql1 += "customerName='" + customerName + "'";
		}
		if (phoneNumber != null) {
			sql1 += ", phoneNumber='" + phoneNumber + "'";
		}
		if (CVR != null) {
			sql1 += ", CVR='" + CVR + "'";
		}
		sql1 += " where customerId = " + customerId;
		if (customerName == null && phoneNumber == null && CVR == null) {
			res1 = 0;
		} else {
			System.out.println(sql1);
			res1 = dbConnection.executeUpdate(sql1);
		}

		// sql2
		if (street != null) {
			sql2 += "street='" + street + "'";
		}
		if (city != null) {
			sql2 += ", city='" + city + "'";
		}
		if (zipcode != null) {
			sql2 += ", zipcode='" + zipcode + "'";
		}
		sql2 += " where customerId = " + customerId;
		if (street == null && city == null && zipcode == null) {
			res2 = 0;
		} else {
			System.out.println(sql2);
			res2 = dbConnection.executeUpdate(sql2);
		}

		if (res1 > 0 || res2 > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean delete(int id) throws DataAccessException {
		int res = 0;
		String sql1 = "alter table customerAddress drop constraint fkcustId where customerId = " + id;
		String sql2 = "delete from dbo.customerAddress where customerId = " + id;
		String sql3 = "delete from dbo.Customer where customerId = " + id;
		res += dbConnection.executeUpdate(sql1);
		System.out.println(res);
		res += dbConnection.executeUpdate(sql2);
		System.out.println(res);
		res += dbConnection.executeUpdate(sql3);
		System.out.println(res);
		return res > 2;
	}

	@Override
	public Customer findById(int id) throws DataAccessException {
		Customer res = null;
		String sql = "select c.customerName, c.phoneNumber, c.CVR, a.street, a.city, a.zipcode from dbo.Customer c inner join dbo.customerAddress a on c.customerId=a.customerId where c.customerId= "
				+ id;
		try (Statement s = dbConnection.getConnection().createStatement(); ResultSet rs = s.executeQuery(sql)) {
			if (rs.next()) {
				res = buildObject(rs);
			}
		} catch (SQLException e) {
			throw new DataAccessException("Could not read result set", e);
		}
		return res;
	}

	@Override
	public List<Customer> getAll() throws DataAccessException {
		ResultSet rs;
		List<Customer> l;
		String sql = "select c.customerName, c.phoneNumber, c.CVR, a.street, a.city, a.zipcode from dbo.Customer c inner join dbo.customerAddress a on c.customerId=a.customerId";
		try (Statement s = dbConnection.getConnection().createStatement()) {
			rs = s.executeQuery(sql);
			l = buildObjects(rs);
		} catch (SQLException e) {
			throw new DataAccessException("Could not execute query", e);
		}
		return l;
	}

	private Customer buildObject(ResultSet rs) throws DataAccessException {
		Customer customer;
		try {
			String name = rs.getString(1);
			String phone = rs.getString(2);
			String street = rs.getString(4);
			String city = rs.getString(5);
			String zipcode = rs.getString(6);
			Address address = new Address(street, city, zipcode);
			customer = new Customer(name, phone, address);
		} catch (SQLException e) {
			throw new DataAccessException("Could not read result set - single object", e);
		}
		return customer;
	}

	private List<Customer> buildObjects(ResultSet rs) throws DataAccessException {
		ArrayList<Customer> list = new ArrayList<>();
		try {
			while (rs.next()) {
				Customer b = buildObject(rs);
				System.out.println(b.getAddress().getStreet());
				list.add(b);
			}
		} catch (SQLException e) {
			throw new DataAccessException("Could not read result set - next()", e);
		} catch (DataAccessException e) {
			throw new DataAccessException("Could not build object", e);
		}
		return list;
	}

}
