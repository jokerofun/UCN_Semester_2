
package model;

import java.time.LocalDate;

/**
 *
 * @author kbh
 * @version 2006.10.04
 */
public class Employee {

	// instance variables
	private String fname;
	private String minit;
	private String lname;
	private String ssn;
	private LocalDate bdate;
	private String address;
	private String sex;
	private double salary;
	private Employee supervisor;
	private Department dep;

	public Employee() {

	}

	public Employee(String ssn) {
		this.ssn = ssn;
	}

	public Employee(String fname, String minit, String lname, String ssn, LocalDate bdate, String address, String sex,
			double salary, Employee supervisor, Department dep) {
		this.fname = fname;
		this.minit = minit;
		this.lname = lname;
		this.ssn = ssn;
		this.bdate = bdate;
		this.address = address;
		this.sex = sex;
		this.salary = salary;
		this.supervisor = supervisor;
		this.dep = dep;
	}

	// set methods
	public void setFname(String fname) {
		this.fname = fname;
	}

	public void setMinit(String minit) {
		this.minit = minit;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public void setBdate(LocalDate bdate) {
		this.bdate = bdate;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public void setSupervisor(Employee emp) {
		this.supervisor = emp;
	}

	public void setDepartment(Department dep) {
		this.dep = dep;
	}

	// get method
	public String getFname() {
		return fname;
	}

	public String getMinit() {
		return minit;
	}

	public String getLname() {
		return lname;
	}

	public String getSsn() {
		return ssn;
	}

	public LocalDate getBdate() {
		return bdate;
	}

	public String getAddress() {
		return address;
	}

	public String getSex() {
		return sex;
	}

	public double getSalary() {
		return salary;
	}

	public Employee getSupervisor() {
		return supervisor;
	}

	public Department getDept() {
		return dep;
	}

	@Override
	public String toString() {
		return "Employee [fname=" + fname + ", minit=" + minit + ", lname=" + lname + ", ssn=" + ssn + ", bdate="
				+ bdate + ", address=" + address + ", sex=" + sex + ", salary=" + salary + ", supervisor=" + supervisor
				+ ", dep=" + dep + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ssn == null) ? 0 : ssn.hashCode());
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
		Employee other = (Employee) obj;
		if (ssn == null) {
			if (other.ssn != null)
				return false;
		} else if (!ssn.equals(other.ssn))
			return false;
		else if (ssn.equals(other.ssn) && (ssn.equals("") || ssn.equals("0"))) // unassigned ssn
			return false;
		return true;
	}

}
