package Controller;

import java.util.ArrayList;
import java.util.Vector;

import Model.RentableProduct;
import Model.SellableProduct;
import Model.TransferList;
import Model.TransferListContainer;
import Model.Warehouse;
import Model.WarehouseContainer;

/**
 * WarehouseController managing the movement and storage of products.
 *
 * @author Alexandru Krausz, Martin Benda, Sebastian Labuda, Simeon Kolev
 * @version 2019.12.6
 */
public class WarehouseController {
	// instance variables
	private Warehouse toWarehouse;
	private Warehouse fromWarehouse;
	private WarehouseContainer warehouses;
	private SellableProduct product;
	private ArrayList<String> info;
	private ArrayList<TransferList> listOfTransferLists;
	private TransferListContainer transferListContainer;
	private TransferList transferListRefference;
	private int size;

	/**
	 * Constructor for WarehouseController
	 */
	public WarehouseController() {
		transferListContainer = TransferListContainer.getTransferListContainer();
		warehouses = WarehouseContainer.getWarehouseContainer();
		listOfTransferLists = new ArrayList<>();
	}

	/**
	 * Used to add new sellable products to warehouse
	 * 
	 * @param name        String
	 * @param barcode     String
	 * @param description String
	 * @param price       double
	 * @param amount      int
	 * @param minAmount   int
	 * @param maxAmount   int
	 * @param unique      boolean
	 */
	public void addSellableProduct(String barcode, String description, double price, int amount, int minAmount,
			int maxAmount, boolean unique) {
		SellableProduct newProduct = new SellableProduct(barcode, description, price, amount, minAmount, maxAmount,
				unique);

		toWarehouse.addSellableProduct(newProduct);
	}

	/**
	 * Used to add new rentable products to warehouse
	 * 
	 * @param name                String
	 * @param barcode             String
	 * @param productID           int
	 * @param description         String
	 * @param price               double
	 * @param placement           String
	 * @param physicalDescription String
	 * @return boolean
	 */
	public boolean addRentableProduct(String barcode, int productID, String description, double price, String placement,
			String physicalDescription) {
		RentableProduct newProduct = new RentableProduct(barcode, productID, description, price, placement,
				physicalDescription);

		if (fromWarehouse.addRentableProduct(newProduct)) {
			return true;
		} else {
			return false;
		}
		// return product;
	}

	public void addWarehouse(String name, String address, String capacity) {
		Warehouse w = new Warehouse(name, address, capacity);
		warehouses.addWarehouse(w);
	}

	/**
	 * Used to find a sellableProduct by barcode
	 * 
	 * @param barcode String
	 * @return ArrayList<String>
	 */
	public ArrayList findSellableProduct(String barcode) {
		info = new ArrayList<>();
		product = fromWarehouse.findSellableProduct(barcode);
		getinfo(product);
		return info;
	}

	public SellableProduct existSellableProduct(String barcode) {
		return toWarehouse.findSellableProduct(barcode);
	}

	public SellableProduct findSellableProductDelivery(String barcode) {
		return toWarehouse.findSellableProduct(barcode);
	}

	public SellableProduct findSellableProductFrom(String barcode) {
		return fromWarehouse.findSellableProduct(barcode);
	}

	/**
	 * Used to get all info of a sellableProduct and concatenate it.
	 * 
	 * @param pass SellableProduct
	 */
	private void getinfo(SellableProduct pass) {
		if (pass != null) {
			info.add(pass.getBarcodeSellableProduct());
			info.add(pass.getDescriptionSellableProduct());
			info.add(pass.getPriceSellableProduct() + "");
			info.add(pass.getAmountSellableProduct() + "");
		} else {
			info.add("Product not found!");
		}
	}

	/**
	 * Used to find a rentableProduct by barcode
	 * 
	 * @param barcode String
	 * @return RentableProduct
	 */
	public RentableProduct findRentableProduct(String barcode) {
		return fromWarehouse.findRentableProduct(barcode);
	}

	/**
	 * Used to find a warehouse by name
	 * 
	 * @param name String
	 * @return Warehouse
	 */
	private Warehouse findWarehouseByName(String name) {
		return warehouses.findWarehouseByName(name);
	}

	/**
	 * Used to set toWarehouse field to a Warehouse found by name
	 * 
	 * @param name String
	 * @return boolean
	 */
	public boolean toWarehouse(String name) {
		toWarehouse = findWarehouseByName(name);
		return toWarehouse != null;
	}

	/**
	 * Used to set fromWarehouse field to a Warehouse found by name
	 * 
	 * @param name String
	 * @return boolean
	 */
	public boolean fromWarehouse(String name) {
		fromWarehouse = findWarehouseByName(name);
		return fromWarehouse != null;
	}

	public boolean newTransferList() {
		transferListRefference = new TransferList(fromWarehouse, toWarehouse);
		return transferListContainer.addTransferList(transferListRefference);
	}

	public int getIndexTransferList() {
		transferListContainer = TransferListContainer.getTransferListContainer();
		return transferListContainer.getIndexTransferList(transferListRefference);
	}

	public int getAmount() {
		return product.getAmountSellableProduct();
	}

	public boolean checkProduct(String barcode) {
		if (transferListRefference.checkProduct(barcode)) {
			return true;
		} else {
			return false;
		}
	}

	public void addProductToTransfer(int amount) {
		transferListRefference.addProduct(product.getBarcodeSellableProduct(), amount);
		product.setAmountSellableProduct(product.getAmountSellableProduct() - amount);
	}

	public boolean getListOfTransferLists(String WarehouseName) {
		if (transferListContainer.getTransferLists(WarehouseName).size() != 0) {
			listOfTransferLists = transferListContainer.getTransferLists(WarehouseName);
			return true;
		} else {
			return false;
		}
	}

	public ArrayList getEntryList() {
		info = new ArrayList<>();
		int i = 0;
		for (TransferList a : listOfTransferLists) {
			info.add("[" + i + "]");
			info.addAll(a.getEntryList());
			i++;
		}
		updateSize(i);
		return info;
	}

	private void updateSize(int size) {
		this.size = size;
	}

	public int getListSize() {
		return size;
	}

	public void transfer(int input) {
		SellableProduct tempProduct;
		info = new ArrayList<>();
		TransferList tempTransferlist;
		tempTransferlist = listOfTransferLists.get(input);
		toWarehouse = tempTransferlist.getToWarehouse();
		info = tempTransferlist.getEntryListKey();
		for (int i = 0; i < info.size(); i++) {
			tempProduct = findSellableProductDelivery(info.get(i));
			if (tempProduct != null) {
				tempProduct.setAmountSellableProduct(
						(tempProduct.getAmountSellableProduct() + tempTransferlist.getAmount(info.get(i))));
			} else {
				fromWarehouse = tempTransferlist.getFromWarehouse();
				tempProduct = findSellableProductFrom(info.get(i));
				String barcode = tempProduct.getBarcodeSellableProduct();
				String description = tempProduct.getDescriptionSellableProduct();
				double price = tempProduct.getPriceSellableProduct();
				int amount = tempTransferlist.getAmount(info.get(i));
				int minAmount = tempProduct.getMinAmountSellableProduct();
				int maxAmount = tempProduct.getMaxAmountSellableProduct();
				boolean unique = false;// TO BE IMPLEMENTED
				addSellableProduct(barcode, description, price, amount, minAmount, maxAmount, unique);
			}
		}
		deletTransferList(input);
	}

	public boolean deletTransferList(int input) {
		transferListContainer = TransferListContainer.getTransferListContainer();
		return transferListContainer.deleteTransferList(input);
	}

	public void deleteWarehouse(String name) {
		Warehouse warehouse = warehouses.findWarehouseByName(name);
		warehouses.removeWarehouse(warehouse);
	}

	public Vector<String> getWarehousesNames() {
		return warehouses.listWarehousesNames();
	}
}