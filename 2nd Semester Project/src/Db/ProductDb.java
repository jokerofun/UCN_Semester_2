package Db;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import controller.DataAccessException;
import model.Product;

public class ProductDb implements ProductDbIF {

	private DBConnection dbConnection;

	private static final String FIND_PRODUCT = "select * from dbo.Product";
	private PreparedStatement findProduct;

	private static final String FIND_BY_ID = FIND_PRODUCT + " where productId = ?";
	private PreparedStatement findById;

	private static final String FIND_BY_NAME = FIND_PRODUCT + " where productName = ?";
	private PreparedStatement findByName;

	private static final String FIND_VERSION = "select version from dbo.Product where productId = ?";
	private PreparedStatement findVersion;

	private static final String INSERT_PRODUCT = "insert into " + "dbo.Product "
			+ "(productName, productDescription, productPrice, productQuantity, saleOrderNumber, version) values (?,?,?,?,?,?)";
	private PreparedStatement insertProduct;

//	private static final String INSERT_SALEORDERPRODUCT = "insert into " + "dbo.SaleOrderProduct "
//			+ "(saleOrderNumber, productId, version) values (?,?,?)";
//	private PreparedStatement insertSaleOrderProduct;

	private static final String UPDATE_PRODUCT = "update " + "dbo.Product "
			+ "set productName = ?, productDescription = ?, productPrice = ?, productQuantity = ?, version = ? where productId = ? and version = ?";
	private PreparedStatement updateProduct;

	private static final String DELETE_PRODUCT = "delete from dbo.Product where productId = ?";
	private PreparedStatement deleteProduct;

	private static final String GET_PRODUCTANDQUANTITY = "select productName, quantity from dbo.OfferProcedures where offerNumber = ?";
	private PreparedStatement getProductAndQuantity;

	public ProductDb() throws DataAccessException {
		dbConnection = DBConnection.getInstance();
		init();
	}

	private void init() throws DataAccessException {
		Connection con = DBConnection.getInstance().getConnection();
		try {
			findProduct = con.prepareStatement(FIND_PRODUCT);
			findById = con.prepareStatement(FIND_BY_ID);
			findByName = con.prepareStatement(FIND_BY_NAME);
			findVersion = con.prepareStatement(FIND_VERSION);
			insertProduct = con.prepareStatement(INSERT_PRODUCT, Statement.RETURN_GENERATED_KEYS);
			// insertSaleOrderProduct = con.prepareStatement(INSERT_SALEORDERPRODUCT);
			updateProduct = con.prepareStatement(UPDATE_PRODUCT);
			deleteProduct = con.prepareStatement(DELETE_PRODUCT);
			getProductAndQuantity = con.prepareStatement(GET_PRODUCTANDQUANTITY);

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new DataAccessException("Could not prepare statements", e);
		}
	}

//	@Override
//	public Product create(String productName, String productDescription, BigDecimal price, int quantity)
//			throws DataAccessException {
//		Product product = null;
//		ResultSet rs;
//		int id = -1;
//		for (int i = 0; i <= 5; i++) {
//			try {
//				dbConnection.startTransaction();
//
//				insertProduct.setString(1, productName);
//				insertProduct.setString(2, productDescription);
//				insertProduct.setBigDecimal(3, price);
//				insertProduct.setInt(4, quantity);
//				insertProduct.setInt(5, 0);
//
//				insertProduct.executeUpdate();
//				rs = insertProduct.getGeneratedKeys();
//				if (rs.next()) {
//					id = rs.getInt(1);
//				}
//
//				dbConnection.commitTransaction();
//				i = 5;
//			} catch (SQLException e) {
//				if (dbConnection.getConnection() != null) {
//					System.err.print("Transaction is being rolled back");
//					dbConnection.rollbackTransaction();
//				}
//			}
//		}
//		product = findById(id);
//
//		return product;
//	}

	@Override
	public Product createWithSaleOrder(String productName, String productDescription, BigDecimal price, int quantity,
			int saleOrderNumber) throws DataAccessException {
		Product product = null;
		ResultSet rs;
		int id = -1;
		for (int i = 0; i <= 5; i++) {
			try {
				dbConnection.startTransaction();

				insertProduct.setString(1, productName);
				insertProduct.setString(2, productDescription);
				insertProduct.setBigDecimal(3, price);
				insertProduct.setInt(4, quantity);
				insertProduct.setInt(5, saleOrderNumber);
				insertProduct.setInt(6, 0);

				insertProduct.executeUpdate();
				rs = insertProduct.getGeneratedKeys();
				if (rs.next()) {
					id = rs.getInt(1);
				}

//				insertSaleOrderProduct.setInt(1, saleOrderNumber);
//				insertSaleOrderProduct.setInt(2, id);
//				insertSaleOrderProduct.setInt(3, 0);
//
//				insertSaleOrderProduct.executeUpdate();

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
		product = findById(id);

		return product;
	}

	@Override
	public Product findById(int productId) throws DataAccessException {
		Product res = null;
		try {
			findById.setInt(1, productId);
			ResultSet rs = findById.executeQuery();
			if (rs.next()) {
				res = buildObject(rs);
			}
		} catch (SQLException e) {
			throw new DataAccessException("Could not read result set", e);
		}
		return res;
	}

	@Override
	public Product findByName(String productName) throws DataAccessException {
		Product res = null;
		try {
			findByName.setString(1, productName);
			ResultSet rs = findByName.executeQuery();
			if (rs.next()) {
				res = buildObject(rs);
			}
		} catch (SQLException e) {
			throw new DataAccessException("Could not read result set", e);
		}
		return res;
	}

	private int findVersion(int id) throws DataAccessException {
		int version = -1;
		try {
			findVersion.setInt(1, id);
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
	public boolean update(Product product) throws DataAccessException {
		int res = 0;
		for (int i = 0; i <= 5; i++) {
			try {
				dbConnection.startTransaction();
				int version = findVersion(product.getProductId());

				updateProduct.setString(1, product.getProductName());
				updateProduct.setString(2, product.getDescription());
				updateProduct.setBigDecimal(3, product.getPrice());
				updateProduct.setInt(4, product.getQuantity());
				updateProduct.setInt(5, version + 1);
				updateProduct.setInt(6, product.getProductId());
				updateProduct.setInt(7, version);

				res += updateProduct.executeUpdate();

				dbConnection.commitTransaction();
				i = 5;
			} catch (SQLException e) {
				if (dbConnection.getConnection() != null) {
					System.err.print("Transaction is being rolled back");
					dbConnection.rollbackTransaction();
				}
			}
		}
		return res > 0;
	}

	@Override
	public boolean delete(int productId) throws DataAccessException {
		try {
			deleteProduct.setInt(1, productId);
			return deleteProduct.executeUpdate() > 0;
		} catch (SQLException e) {
			throw new DataAccessException("Could not delete from database", e);
		}
	}

	@Override
	public List<Product> getAll() throws DataAccessException {
		ResultSet rs;
		List<Product> l;
		try {
			rs = findProduct.executeQuery();
			l = buildObjects(rs);
		} catch (SQLException e) {
			throw new DataAccessException("Could not execute query", e);
		}
		return l;
	}

	private Product buildObject(ResultSet rs) throws DataAccessException {
		Product product;
		try {
			int id = rs.getInt("productId");
			String name = rs.getString("productName");
			String description = rs.getString("productDescription");
			BigDecimal price = rs.getBigDecimal("productPrice");
			int quantity = rs.getInt("productQuantity");

			product = new Product(id, name, description, price, quantity);
		} catch (SQLException e) {
			throw new DataAccessException("Could not read result set - single object", e);
		}
		return product;
	}

	private List<Product> buildObjects(ResultSet rs) throws DataAccessException {
		ArrayList<Product> list = new ArrayList<>();
		try {
			while (rs.next()) {
				Product p = buildObject(rs);
				list.add(p);
			}
		} catch (SQLException e) {
			throw new DataAccessException("Could not read result set - next()", e);
		} catch (DataAccessException e) {
			throw new DataAccessException("Could not build object", e);
		}
		return list;
	}

	@Override
	public HashMap<String, Integer> getAllProductAndQuantity(int offerNumber) throws DataAccessException {
		ResultSet rs;
		HashMap<String, Integer> hash;
		try {
			getProductAndQuantity.setInt(1, offerNumber);
			rs = getProductAndQuantity.executeQuery();
			hash = buildHashMap(rs);
		} catch (SQLException e) {
			throw new DataAccessException("Could not execute query", e);
		}
		return hash;
	}

	private HashMap<String, Integer> buildHashMap(ResultSet rs) throws DataAccessException {
		HashMap<String, Integer> hash = new HashMap<>();
		try {
			while (rs.next()) {
				hash.put(rs.getString("productName"), rs.getInt("quantity"));
			}
		} catch (SQLException e) {
			throw new DataAccessException("Could not read result set - next()", e);
		}
		return hash;
	}

}
