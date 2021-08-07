
package model;

/**
 * WorksOn.java
 * 
 * @author kbh, knol
 * @version 20. oktober 2006, 2018-08-30
 */
public class WorksOn {
	private Employee emp;
	private Project project;
	private double hours;

	/** Creates a new instance of WorksOn */
	public WorksOn() {
	}

	public void setEmp(Employee emp) {
		this.emp = emp;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public void setHours(double hours) {
		this.hours = hours;
	}

	public Employee getEmployee() {
		return emp;
	}

	public Project getProject() {
		return project;
	}

	public double getHours() {
		return hours;
	}
}
