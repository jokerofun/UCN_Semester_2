package model;

public class ClubCustomer extends Customer {

	private String CVR;

	public ClubCustomer(String customerName, String phoneNumber, String CVR, Address address) {
		super(customerName, phoneNumber, address);
		this.CVR = CVR;
	}

	/**
	 * @return the customerName
	 */
	public String getClubCustomerName() {
		return getCustomerName();
	}

	/**
	 * @param customerName the customerName to set
	 */
	public void setClubCustomerName(String customerName) {
		setCustomerName(customerName);
	}

	/**
	 * @return the phoneNumber
	 */
	public String getClubPhoneNumber() {
		return getPhoneNumber();
	}

	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setClubPhoneNumber(String phoneNumber) {
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

	/**
	 * @return the CVR
	 */
	public String getClubCustomerCVR() {
		return CVR;
	}

	/**
	 * @param ClubCustomerCVR the ClubCustomerCVR to set
	 */
	public void setClubCustomerCVR(String CVR) {
		this.CVR = CVR;
	}
}
