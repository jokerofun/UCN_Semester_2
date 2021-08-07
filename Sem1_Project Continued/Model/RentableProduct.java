package Model;


/**
 * RentableProduct with barcode, productID, description, price, placement and physicalDescription.
 *
 * @author Alexandru Krausz, Martin Benda, Sebastian Labuda, Simeon Kolev
 * @version 2019.12.4
 */
public class RentableProduct{
    // instance variables
    private String barcode;
    private int productID;
    private String description;
    private double price;
    private String placement;
    private String physicalDescription;

    /**
     * Constructor for RentableProduct
     * @param barcode of the RentableProduct
     * @param productID of the RentableProduct
     * @param description of the RentableProduct
     * @param price of the RentableProduct
     * @param placement of the RentableProduct
     * @param physicalDescription of the RentableProduct
     */
    public RentableProduct(String barcode, int productID, String description, double price, String placement, String physicalDescription)
    {
        this.barcode = barcode;
        this.productID = productID;
        this.description = description;
        this.price = price;
        this.placement = placement;
        this.physicalDescription = physicalDescription;
    }
    
    /**
     * Set the barcode of this RentableProduct
     * @param barcode String
     */
    public void setBarCodeRentableProduct(String barcode) 
    {
        this.barcode = barcode;
    }
    
    /**
     * Set the productID of this RentableProduct
     * @param productID int
     */
    public void setProductIDRentableProduct(int productID) 
    {
        this.productID = productID;
    }
    
    /**
     * Set the description of this RentableProduct
     * @param description String
     */
    public void setDescriptionRentableProduct(String description) 
    {
        this.description = description;
    }
    
    /**
     * Set the price of this RentableProduct
     * @param price double
     */
    public void setPriceRentableProduct(double price) 
    {
        this.price = price;
    }
    
    /**
     * Set the placement of this RentableProduct
     * @param placement String
     */
    public void setPlacementRentableProduct(String placement) 
    {
        this.placement = placement;
    }
    
    /**
     * Set the physicalDescription of this RentableProduct
     * @param physicalDescription String
     */
    public void setPhysicalDescriptionRentableProduct(String physicalDescription) 
    {
        this.physicalDescription = physicalDescription;
    }
    
    /**
     * @return barcode of this RentableProduct
     */
    public String getBarcodeRentableProduct()
    {
        return barcode;
    }
    
    /**
     * @return productID of this RentableProduct
     */
    public int getProductIDRentableProduct()
    {
        return productID;
    }
    
    /**
     * @return description of this RentableProduct
     */
    public String getDescriptionRentableProduct()
    {
        return description;
    }
    
    /**
     * @return price of this RentableProduct
     */
    public double getPriceRentableProduct()
    {
        return price;
    }
    
    /**
     * @return placement of this RentableProduct
     */
    public String getPlacementRentableProduct()
    {
        return placement;
    }
    
    /**
     * @return physicalDescription of this RentableProduct
     */
    public String getPhysicalDescriptionRentableProduct()
    {
        return physicalDescription;
    }
    
}
