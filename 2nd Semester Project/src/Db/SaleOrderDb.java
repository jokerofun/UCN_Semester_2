package Db;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import controller.DataAccessException;
import model.Product;
import model.SaleOrder;

public class SaleOrderDb implements SaleOrderDbIF {

	private DBConnection dbConnection;

	// "select p.*, o.* from Procedures p join OfferProcedures op on op.code =
	// p.code join Offer o on o.offerNumber = op.offerNumber"
	private static final String FIND_SALEORDER = "select * from SaleOrder";
	private PreparedStatement findSaleOrder;

	private static final String FIND_BY_NUMBER = FIND_SALEORDER + " where saleOrderNumber = ?";
	private PreparedStatement findByNumber;

	private static final String FIND_VERSION = "select version from dbo.SaleOrder where saleOrderNumber = ?";
	private PreparedStatement findVersion;

	// old one : select p.* from Product p join SaleOrderProduct sop on
	// sop.productId = p.productId where sop.saleOrderNumber = ?
	private static final String FIND_PRODUCTS = "select * from Product where saleOrderNumber = ?";
	private PreparedStatement findProducts;

	private static final String INSERT_SALEORDER = "insert into " + "dbo.SaleOrder "
			+ "(customerName, orderDate, dueDate, offerNumber, totalPrice, version) values (?,?,?,?,?,?)";
	private PreparedStatement insertSaleOrder;

//	private static final String INSERT_PRODUCT = "insert into " + "dbo.SaleOrderProduct "
//			+ "(saleOrderNumber, productId, version) values (?,?,?)";
//	private PreparedStatement insertProduct;

	private static final String UPDATE_SALEORDER = "update " + "dbo.SaleOrder "
			+ "set customerName = ?, orderDate = ?, dueDate = ?, offerNumber = ?, totalPrice = ?, version = ? where saleOrderNumber = ? and version = ?";
	private PreparedStatement updateSaleOrder;

	private static final String DELETE_SALEORDER = "delete from dbo.SaleOrder where saleOrderNumber = ?";
	private PreparedStatement deleteSaleOrder;

	public SaleOrderDb() throws DataAccessException {
		dbConnection = DBConnection.getInstance();
		init();
	}

	private void init() throws DataAccessException {
		Connection con = DBConnection.getInstance().getConnection();
		try {
			findSaleOrder = con.prepareStatement(FIND_SALEORDER);
			findByNumber = con.prepareStatement(FIND_BY_NUMBER);
			findVersion = con.prepareStatement(FIND_VERSION);
			findProducts = con.prepareStatement(FIND_PRODUCTS);
			insertSaleOrder = con.prepareStatement(INSERT_SALEORDER, Statement.RETURN_GENERATED_KEYS);
			// insertProduct = con.prepareStatement(INSERT_PRODUCT);
			updateSaleOrder = con.prepareStatement(UPDATE_SALEORDER);
			deleteSaleOrder = con.prepareStatement(DELETE_SALEORDER);

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new DataAccessException("Could not prepare statements", e);
		}
	}

	@Override
	public SaleOrder create(String customerName, Date orderDate, Date dueDate, int offerNumber, BigDecimal totalPrice,
			List<Product> products) throws DataAccessException {
		SaleOrder saleOrder = null;
		ResultSet rs;
		int id = -1;
		for (int i = 0; i <= 5; i++) {
			try {
				dbConnection.startTransaction();

				insertSaleOrder.setString(1, customerName);
				insertSaleOrder.setDate(2, orderDate);
				insertSaleOrder.setDate(3, dueDate);
				insertSaleOrder.setInt(4, offerNumber);
				insertSaleOrder.setBigDecimal(5, totalPrice);
				insertSaleOrder.setInt(6, 0);

				insertSaleOrder.executeUpdate();
				rs = insertSaleOrder.getGeneratedKeys();
				if (rs.next()) {
					id = rs.getInt(1);
				}

//				for (Product product : products) {
//					insertProduct.setInt(1, id);
//					insertProduct.setInt(2, product.getProductId());
//					insertProduct.setInt(3, 0);
//
//					insertProduct.executeUpdate();
//				}

				dbConnection.commitTransaction();
				i = 5;
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				if (dbConnection.getConnection() != null) {
					System.err.print("Transaction is being rolled back");
					dbConnection.rollbackTransaction();
				}
			}
		}
		saleOrder = findByNumber(id);

		return saleOrder;
	}

	@Override
	public SaleOrder findByNumber(int saleOrderNumber) throws DataAccessException {
		SaleOrder res = null;
		try {
			findByNumber.setInt(1, saleOrderNumber);
			ResultSet rs = findByNumber.executeQuery();
			if (rs.next()) {
				res = buildObject(rs);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new DataAccessException("Could not read result set", e);
		}
		return res;
	}

	private int findVersion(int offerNumber) throws DataAccessException {
		int version = -1;
		try {
			findVersion.setInt(1, offerNumber);
			ResultSet rs = findVersion.executeQuery();
			if (rs.next()) {
				version = rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new DataAccessException("Could not read result set", e);
		}
		return version;
	}

	@Override
	public boolean update(SaleOrder saleOrder) throws DataAccessException {
		int res = 0;
		for (int i = 0; i <= 5; i++) {
			try {
				dbConnection.startTransaction();

				int version = findVersion(saleOrder.getSaleOrderNumber());
				updateSaleOrder.setString(1, saleOrder.getCustomerName());
				updateSaleOrder.setDate(2, saleOrder.getOrderDate());
				updateSaleOrder.setDate(3, saleOrder.getDueDate());
				updateSaleOrder.setInt(4, saleOrder.getOfferNumber());
				updateSaleOrder.setBigDecimal(5, saleOrder.getTotalPrice());
				updateSaleOrder.setInt(6, version + 1);
				updateSaleOrder.setInt(7, saleOrder.getSaleOrderNumber());
				updateSaleOrder.setInt(8, version);

				res += updateSaleOrder.executeUpdate();

				// TODO maybe products ??
				dbConnection.commitTransaction();
				i = 5;
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				if (dbConnection.getConnection() != null) {
					System.err.print("Transaction is being rolled back");
					dbConnection.rollbackTransaction();
				}
			}
		}
		return res > 0;
	}

	@Override
	public boolean delete(int saleOrderNumber) throws DataAccessException {
		try {
			deleteSaleOrder.setInt(1, saleOrderNumber);
			return deleteSaleOrder.executeUpdate() > 0;
		} catch (SQLException e) {
			throw new DataAccessException("Could not delete from database", e);
		}
	}

	@Override
	public List<SaleOrder> getAll() throws DataAccessException {
		ResultSet rs;
		List<SaleOrder> l;
		try {
			rs = findSaleOrder.executeQuery();
			l = buildObjects(rs);
		} catch (SQLException e) {
			throw new DataAccessException("Could not execute query", e);
		}
		return l;
	}

	private SaleOrder buildObject(ResultSet rs) throws DataAccessException {
		SaleOrder saleOrder;
		List<Product> products = new ArrayList<>();
		ResultSet rs2;
		try {
			int saleOrderNumber = rs.getInt("saleOrderNumber");
			String customerName = rs.getString("customerName");
			Date orderDate = rs.getDate("orderDate");
			Date dueDate = rs.getDate("dueDate");
			int offerNumber = rs.getInt("offerNumber");
			BigDecimal totalPrice = rs.getBigDecimal("totalPrice");

			findProducts.setInt(1, saleOrderNumber);
			rs2 = findProducts.executeQuery();

			while (rs2.next()) {
				int productId = rs2.getInt("productId");
				String productName = rs2.getString("productName");
				String productDescription = rs2.getString("productDescription");
				BigDecimal productPrice = rs2.getBigDecimal("productPrice");
				int productQuantity = rs2.getInt("productQuantity");
				Product product = new Product(productId, productName, productDescription, productPrice,
						productQuantity);
				products.add(product);
			}

			saleOrder = new SaleOrder(saleOrderNumber, customerName, orderDate, dueDate, offerNumber, totalPrice);
			saleOrder.setProducts(products);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new DataAccessException("Could not read result set - single object", e);
		}
		return saleOrder;
	}

	private List<SaleOrder> buildObjects(ResultSet rs) throws DataAccessException {
		ArrayList<SaleOrder> list = new ArrayList<>();
		try {
			while (rs.next()) {
				SaleOrder e = buildObject(rs);
				list.add(e);
			}
		} catch (SQLException e) {
			throw new DataAccessException("Could not read result set - next()", e);
		} catch (DataAccessException e) {
			throw new DataAccessException("Could not build object", e);
		}
		return list;
	}

}
