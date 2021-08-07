package model;

public class EmployeeProcedure {

	private Employee employee;
	private Procedure procedure;

	public EmployeeProcedure(Employee employee, Procedure procedure) {
		this.employee = employee;
		this.procedure = procedure;
	}

	/**
	 * @return the employee
	 */
	public Employee getEmployee() {
		return employee;
	}

	/**
	 * @param employee the employee to set
	 */
	public void setEmployee(Employee employee) {
		this.employee = employee;
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
