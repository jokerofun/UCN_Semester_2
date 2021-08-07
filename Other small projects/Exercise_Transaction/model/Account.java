package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Write a description of class Customer here.
 *
 * @author Gianna
 * @version Mar.2018
 */
public class Account
{
    private int id;
    private String name;
    private Customer customer;
    private List<Posting> postings;
    
	/**
     * Constructor for objects of class Customer
     */

    public Account()
    {
    	postings = new ArrayList<>();
    }
    
    public Account(String name, Customer customer)
    {
        this();
    	this.id = 0;
        this.name = name;
        this.customer = customer;
    }
    
    public Account(int id, String name, Customer customer)
    {
        this.id = id;
        this.name = name;
        this.customer = customer;
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

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public void addPosting(Posting posting) {
    	if (posting!=null)
    		postings.add(posting);
    }
    
}
