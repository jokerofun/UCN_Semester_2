package model;

public class Position {
	private String positionName;
	private String clearance;

	public Position(String positionName, String clearance) {
		this.positionName = positionName;
		this.clearance = clearance;
	}

	/**
	 * @return the positionName
	 */
	public String getPositionName() {
		return positionName;
	}

	/**
	 * @param positionName the positionName to set
	 */
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	/**
	 * @return the clearance
	 */
	public String getClearance() {
		return clearance;
	}

	/**
	 * @param clearance the clearance to set
	 */
	public void setClearance(String clearance) {
		this.clearance = clearance;
	}
}
