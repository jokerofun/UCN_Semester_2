package model;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Write a description of class Customer here.
 *
 * @author Gianna
 * @version Mar.2018
 */
public class Posting
{
	
	private int id;
    private BigDecimal amount;
    private String description;
    private LocalDate date;
    
    private static final int DEFAULT_POSTING_ID_NOT_YET_CREATED = 0;
    

    public Posting()
    {
    	
    }
    
    public Posting(BigDecimal amount, String description, LocalDate date)
    {
    	this.id = DEFAULT_POSTING_ID_NOT_YET_CREATED;
    	this.amount = amount;
    	this.description = description;
    	this.date = date;    	
    }
    
    
    
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}



 

}
