package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Write a description of class Team here.
 *
 * @author Gianna
 * @version (a version number or a date)
 */
public class Team
{
    private int id;
    private String prefix;
    private String description;
    private List<Student> students;

    public Team()
    {
    }
    
    public Team(int id, String prefix, String description)
    {
        this.id = id;
        this.prefix = prefix;
        this.description = description;
    }

    
    public int getId() {
		return id;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		//TO DO check parameter...
		this.prefix = prefix;
	}

	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		//TO DO check parameter...
		this.description = description;
	}
	
	
	public void addStudent(Student student) {
		if (this.students == null ) {
			students = new ArrayList<>();
		}
		// avoid hidden null values - only accept non-null students!
		if(student != null) {
			students.add(student);
		}
	}
	
	public void setListStudent(List<Student> list) {
		students = new ArrayList<>();
		students.addAll(list);
	}
	
	public List<Student> getStudents() {
		return students;
	}
	

}
