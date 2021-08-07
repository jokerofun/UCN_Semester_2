package model;

/**
 * Write a description of class Student here.
 *
 * @author Gianna
 * @version Jan.2018
 */
public class Student {
	private int id;
	private String name;
	private Mentor mentor;

	public Mentor getMentor() {
		return mentor;
	}

	public void setMentor(Mentor mentor) {
		this.mentor = mentor;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Constructor for objects of class Student
	 */
	// needed for association course-student

	public Student() {
	}
	
	
	
	public Student(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public Student(int id, String name, Mentor mentor) {
		this.id = id;
		this.name = name;
		this.mentor = mentor;
	}

}
