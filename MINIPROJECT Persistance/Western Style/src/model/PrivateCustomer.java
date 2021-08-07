package model;

public class PrivateCustomer extends Customer {

	public PrivateCustomer(String customerName, String phoneNumber, Address address) {
		super(customerName, phoneNumber, address);
	}

	/**
	 * @return the customerName
	 */
	public String getPrivateCustomerName() {
		return getCustomerName();
	}

	/**
	 * @param customerName the customerName to set
	 */
	public void setPrivateCustomerName(String customerName) {
		setCustomerName(customerName);
	}

	/**
	 * @return the phoneNumber
	 */
	public String getPrivatePhoneNumber() {
		return getPhoneNumber();
	}

	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPrivatePhoneNumber(String phoneNumber) {
		setPhoneNumber(phoneNumber);
	}

	/**
	 * @return the address
	 */
	public Address getAddress() {
		return getAddress();
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(Address address) {
		setAddress(address);
	}

}
