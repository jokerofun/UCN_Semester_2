/*
 * Project.java
 *
 * Created on 5. oktober 2006, 12:51
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package model;

/**
 *
 * @author kbh, knol
 * @version _Date, 2018-08-30
 */
public class Project {

	private String pname;
	private int pnumber;
	private String plocation; // it has to change to an object if dept-location is implementet
	private Department department;

	/** Creates a new instance of Project */
	public Project() {
	}

	public Project(int pnumber) {
		this.pnumber = pnumber;
	}

	public void setName(String pname) {
		this.pname = pname;
	}

	public void setPnumber(int pnumber) {
		this.pnumber = pnumber;
	}

	public void setPlocation(String plocation) {
		this.plocation = plocation;
	}

	public void setDepartment(Department dept) {
		this.department = dept;
	}

	public String getName() {
		return pname;
	}

	public int getPnumber() {
		return pnumber;
	}

	public String getLocation() {
		return plocation;
	}

	public Department getDepartment() {
		return department;
	}
}
