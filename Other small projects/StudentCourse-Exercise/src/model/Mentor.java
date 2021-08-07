package model;

public class Mentor {

	private int mentorId;
    private String mentorName;
	
	public Mentor(int mentorId) {
		this.mentorId = mentorId;
	}
	
	public Mentor(int mentorId, String mentorName) {
		this.mentorId = mentorId;
		this.mentorName= mentorName;
	}
       
    public int getMentorId() {
		return mentorId;
	}

	public void setMentorId(int mentorId) {
		this.mentorId = mentorId;
	}
	public String getMentorName() {
		return mentorName;
	}
	public void setName(String mentorName) {
		this.mentorName = mentorName;
	}

}
