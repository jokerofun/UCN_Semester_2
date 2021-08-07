package model;

public class Tool extends Equipment{
	private float length;
	private float diameter;
	private float wear;
	private int inStockQuantity;
	
	public Tool(String name,String type,int id,float length,float diameter,float wear,int inStockQuantity) {
		super(name,type,id);
		this.length = length;
		this.diameter = diameter;
		this.wear = wear;
		this.inStockQuantity = inStockQuantity;
	}
	public float getLength() {
		return length;
	}
	public void setLength(float length) {
		this.length = length;
	}
	public float getDiameter() {
		return diameter;
	}
	public void setDiameter(float diameter) {
		this.diameter = diameter;
	}
	public float getWear() {
		return wear;
	}
	public void setWear(float wear) {
		this.wear = wear;
	}
	public int getInStockQuantity() {
		return inStockQuantity;
	}
	public void setInStockQuantity(int inStockQuantity) {
		this.inStockQuantity = inStockQuantity;
	}
	
}
