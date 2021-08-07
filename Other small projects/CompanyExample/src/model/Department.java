/*
 * Department.java
 *
 * Created on 5. oktober 2006, 12:50
 *
 * 
 */

package model;

/**
 *
 * @author kbh, knol
 * @version _Date, 2018-08-30
 */
public class Department {
	private String dname;
	private int dnumber;
	private Employee manager;
	private String mgrStartDate;

	/** Creates a new instance of Department */
	public Department() {
	}

	public Department(int dnumber) {
		this.dnumber = dnumber;
	}

	public Department(String dname, int dnumber, Employee manager, String mgrStartDate) {
		this.dname = dname;
		this.dnumber = dnumber;
		this.manager = manager;
		this.mgrStartDate = mgrStartDate;
	}

	// set methods
	public void setDname(String dname) {
		this.dname = dname;
	}

	public void setDnumber(int dnumber) {
		this.dnumber = dnumber;
	}

	public void setManager(Employee manager) {
		this.manager = manager;

	}

	public void setStartDate(String startdate) {
		this.mgrStartDate = startdate;
	}

	// get methods
	public String getDname() {
		return dname;
	}

	public int getDnumber() {
		return dnumber;

	}

	public Employee getManager() {
		return manager;

	}

	public String getStartDate() {
		return mgrStartDate;
	}

	@Override
	public String toString() {
		return "Department [dname=" + dname + ", dnumber=" + dnumber + ", manager=" + manager + ", mgrStartDate="
				+ mgrStartDate + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + dnumber;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Department other = (Department) obj;
		if (dnumber != other.dnumber)
			return false;
		return true;
	}

}
