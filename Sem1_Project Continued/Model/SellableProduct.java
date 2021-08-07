package Model;
import java.util.*;

/**
 * SellableProduct with barcode, description, price, minAmount, maxAmount, suppliers and unique .
 *
 * @author Alexandru Krausz, Martin Benda, Sebastian Labuda, Simeon Kolev
 * @version 2019.12.4
 */
public class SellableProduct implements Component{
    // instance variables
    private ArrayList<Supplier> suppliers;
    private String barcode;
    private String description;
    private double price;
    private int amount;
    private int minAmount;
    private int maxAmount;
    private boolean unique;

    /**
     * Constructor for SellableProduct
     * @param suppliers containing list of Supplier
     * @param barcode of the SellableProduct
     * @param description of the SellableProduct
     * @param price of the SellableProduct
     * @param amount of the SellableProduct
     * @param minAmount of the SellableProduct
     * @param maxAmount of the SellableProduct
     * @param unique of the SellableProduct
     */
    public SellableProduct(String barcode, String description, double price, int amount, int minAmount, int maxAmount, boolean unique)
    {
        suppliers = new ArrayList<>();
        this.barcode = barcode;
        this.description = description;
        this.price = price;
        this.amount = amount;
        this.minAmount = minAmount;
        this.maxAmount = maxAmount;
        this.unique = unique;
    }
    
    /**
     * Used to add a supplier to the list of suppliers of a SellableProduct
     * @param supplier Supplier
     */
    public void addSupplier(Supplier supplier)
    {
        suppliers.add(supplier);
    }
    
    /**
     * Used to remove a supplier from the list of suppliers of a SellableProduct
     * @param supplier Supplier
     */
    public void removeSupplier(Supplier supplier)
    {
        suppliers.remove(supplier);
    }

    /**
     * Set the barcode of this SellableProduct
     * @param barcode String
     */
    public void setBarcodeSellableProduct(String barcode) 
    {
        this.barcode = barcode;
    }
    
    /**
     * Set the description of this SellableProduct
     * @param description String
     */
    public void setDescriptionSellableProduct(String description) 
    {
        this.description = description;
    }
    
    /**
     * Set the price of this SellableProduct
     * @param price double
     */
    public void setPriceSellableProduct(double price) 
    {
        this.price = price;
    }
    
    /**
     * Set the amount of this SellableProduct
     * @param amount int
     */
    public void setAmountSellableProduct(int amount)
    {
        this.amount = amount;
    }
    
    /**
     * Set the minAmount of this SellableProduct
     * @param minAmount int
     */
    public void setMinAmountSellableProduct(int minAmount) 
    {
        this.minAmount = minAmount;
    }
    
    /**
     * Set the maxAmount of this SellableProduct
     * @param maxAmount int
     */
    public void setMaxAmountSellableProduct(int maxAmount) 
    {
        this.maxAmount = maxAmount;
    }
    
    /**
     * Set the unique of this SellableProduct
     * @param unique boolean
     */
    public void setUniqueSellableProduct(boolean unique) 
    {
        this.unique = unique;
    }
    
    /**
     * @return suppliers of this SellableProduct
     */
    public ArrayList getSuppliersSellableProduct()
    {
        return suppliers;
    }
    
    /**
     * @return barcode of this SellableProduct
     */
    public String getBarcodeSellableProduct()
    {
        return barcode;
    }
    
    /**
     * @return description of this SellableProduct
     */
    public String getDescriptionSellableProduct()
    {
        return description;
    }
    
    /**
     * @return price of this SellableProduct
     */
    public double getPriceSellableProduct()
    {
        return price;
    }
    
    /**
     * @return amount of this SellableProduct
     */
    public int getAmountSellableProduct()
    {
        return amount;
    }
    
    /**
     * @return minAmount of this SellableProduct
     */
    public int getMinAmountSellableProduct()
    {
        return minAmount;
    }
    
    /**
     * @return maxAmount of this SellableProduct
     */
    public int getMaxAmountSellableProduct()
    {
        return maxAmount;
    }
    
    /**
     * @return unique of this SellableProduct
     */
    public boolean getUniqueSellableProduct()
    {
        return unique;
    }
}
