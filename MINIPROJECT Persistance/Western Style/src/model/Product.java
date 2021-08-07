package model;
import java.math.BigDecimal;

public class Product {
    // instance variables
    private int productId;
    private String productName;
    private BigDecimal purchasePrice;
    private BigDecimal salesPrice;
    private BigDecimal rentPrice;
    private String countryOfOrigin;
    private int minStock;
    private int inStock;

    /**
     * Constructor for Product
     * @param productId of the Product
     * @param productName of the Product
     * @param purchasePrice of the Product
     * @param salesPrice of the Product
     * @param rentPrice of the Product
     * @param countryOfOrigin of the Product
     * @param minStock of the Product
     * @param inStock of the Product
     */
    public Product(int productId, String productName, BigDecimal purchasePrice, BigDecimal salesPrice, BigDecimal rentPrice, String countryOfOrigin, int minStock, int inStock)
    {
    	this.productId = productId;
		this.productName = productName;
        this.purchasePrice = purchasePrice;
        this.salesPrice = salesPrice;
        this.rentPrice = rentPrice;
        this.countryOfOrigin = countryOfOrigin;
        this.minStock = minStock;
        this.inStock = inStock;
    }

    /**
     * Set the ID of this Product
     * @param productId int
     */
    public void setProductId(int productId) 
    {
        this.productId = productId;
    }
    
    /**
     * Set the name of this Product
     * @param productName String
     */
    public void setProductName(String productName) 
    {
        this.productName = productName;
    }
    
    /**
     * Set the purchasePrice of this Product
     * @param purchasePrice bigDecimal
     */
    public void setPurchasePrice(BigDecimal purchasePrice) 
    {
        this.purchasePrice = purchasePrice;
    }
    
    /**
     * Set the salesPrice of this Product
     * @param salesPrice bigDecimal
     */
    public void setSalesPrice(BigDecimal salesPrice)
    {
        this.salesPrice = salesPrice;
    }
    
    /**
     * Set the rentPrice of this Product
     * @param rentPrice bigDecimal
     */
    public void setRentPrice(BigDecimal rentPrice) 
    {
        this.rentPrice = rentPrice;
    }
    
    /**
     * Set the countryOfOrigin of this Product
     * @param countryOfOrigin String
     */
    public void setCountryOfOrigin(String countryOfOrigin) 
    {
        this.countryOfOrigin = countryOfOrigin;
    }
    
    /**
     * Set the minStock of this Product
     * @param minStock int
     */
    public void setMinStock(int minStock) 
    {
        this.minStock = minStock;
    }

    /**
     * Set the inStock of this Product
     * @param inStock int
     */
    public void setInStock(int inStock) 
    {
        this.inStock = inStock;
    }
    
    /**
     * @return ID of this Product
     */
    public int getProductId()
    {
        return productId;
    }
    
    /**
     * @return Name of this Product
     */
    public String getProductName()
    {
        return productName;
    }
    
    /**
     * @return purchasePrice of this Product
     */
    public BigDecimal getPurchasePrice()
    {
        return purchasePrice;
    }
    
    /**
     * @return salesPrice of this Product
     */
    public BigDecimal getSalesPrice()
    {
        return salesPrice;
    }
    
    /**
     * @return rentPrice of this Product
     */
    public BigDecimal getRentPrice()
    {
        return rentPrice;
    }
    
    /**
     * @return countryOfOrigin of this Product
     */
    public String getCountryOfOrigin()
    {
        return countryOfOrigin;
    }
    
    /**
     * @return minStock of this Product
     */
    public int getMinStock()
    {
        return minStock;
    }

    /**
     * @return inStock of this Product
     */
    public int getInStock()
    {
        return inStock;
    }
}