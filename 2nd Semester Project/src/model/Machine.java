package model;

public class Machine extends Equipment{
	private String dimension;
	
	public Machine(String name,String type,int id,String dimension) {
		super(name,type,id);
		this.dimension = dimension;
	}
	public String getDimension() {
		return dimension;
	}
	public void setDimension(String dimension) {
		this.dimension = dimension;
	}
}