package model;
/**
 * Write a description of class Customer here.
 *
 * @author Gianna
 * @version Mar.2018
 */
public class Customer
{
    private int id;
    private String name;
    
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
     * Constructor for objects of class Customer
     */

    public Customer()
    {
    	
    }
    
    public Customer(int id, String name)
    {
        this.id = id;
        this.name = name;
    }

}
