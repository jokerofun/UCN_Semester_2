package model;

public class ProcedureEntry {

	private int hours;
	private Procedure procedure;

	public ProcedureEntry(int hours, Procedure procedure) {
		this.hours = hours;
		this.procedure = procedure;
	}

	/**
	 * @return the hours
	 */
	public int getHours() {
		return hours;
	}

	/**
	 * @param hours the hours to set
	 */
	public void setHours(int hours) {
		this.hours = hours;
	}

	/**
	 * @return the procedure
	 */
	public Procedure getProcedure() {
		return procedure;
	}

	/**
	 * @param procedure the procedure to set
	 */
	public void setProcedure(Procedure procedure) {
		this.procedure = procedure;
	}
}
