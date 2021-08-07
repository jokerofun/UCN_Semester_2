package Model;

import java.util.HashMap;

/**
 * Warehouse with name, address, capacity, itemAmmount and two hashMaps
 * containing rentables and sellables.
 *
 * @author Alexandru Krausz, Martin Benda, Sebastian Labuda, Simeon Kolev
 * @version 2019.12.4
 */
public class Warehouse {
	private String name;
	private String address;
	private String capacity;
	private HashMap<String, RentableProduct> rentableProducts;
	private HashMap<String, SellableProduct> sellableProducts;

	/**
	 * Constructor for Warehouse
	 * 
	 * @param rentableProducts containing RentableProducts
	 * @param sellableProducts containing SellableProducts
	 * @param name             of the Warehouse
	 * @param address          of the Warehouse
	 * @param itemAmmount      of the Warehouse
	 */
	public Warehouse(String name, String address, String capacity) {
		rentableProducts = new HashMap<>();
		sellableProducts = new HashMap<>();
		this.name = name;
		this.address = address;
		this.capacity = capacity;
	}

	/**
	 * Set the name of this Warehouse
	 * 
	 * @param name String
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Set the address of this Warehouse
	 * 
	 * @param address String
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Set the capacity of this Warehouse
	 * 
	 * @param capacity String
	 */
	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}

	/**
	 * @return name of this Warehouse
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return address of this Warehouse
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @return capacity of this Warehouse
	 */
	public String getCapacity() {
		return capacity;
	}

	/**
	 * Used to add a sellableProduct to the list of sellables
	 * 
	 * @param product SellableProduct
	 */
	public void addSellableProduct(SellableProduct product) {
		sellableProducts.put(product.getBarcodeSellableProduct(), product);
	}

	/**
	 * Used to add a rentableProduct to the list of rentables
	 * 
	 * @param product RentableProduct
	 * @return boolean
	 */
	public boolean addRentableProduct(RentableProduct product) {
		if (rentableProducts.put(product.getBarcodeRentableProduct(), product) != null) {
			return true;
		} else {
			return false;
		}
		// return product;
	}

	/**
	 * Used to find and return a SellableProduct by barcode
	 * 
	 * @param barcode String
	 * @return SellableProduct
	 */
	public SellableProduct findSellableProduct(String barcode) {
		return sellableProducts.get(barcode);
	}

	/**
	 * Used to find and return a RentableProduct by barcode
	 * 
	 * @param barcode String
	 * @return RentableProduct
	 */
	public RentableProduct findRentableProduct(String barcode) {
		return rentableProducts.get(barcode);
	}
}
