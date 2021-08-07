package Model;

/**
 * PurchaseOrder with list of sellable products and order info from Order superclass.
 *
 * @author Alexandru Krausz, Martin Benda, Sebastian Labuda, Simeon Kolev
 * @version 2019.12.4
 */
public class PurchaseOrder extends Order{
    // instance variables
    //TODO sellable items

    /**
     * Constructor for PurchaseOrder
     * @param date from Order superclass
     * @param registrationNumber from Order superclass
     * @param price from Order superclass
     * @param employeeCode from Order superclass
     * @param state from Order superclass
     */
    public PurchaseOrder()
    {
        super("10.21.23", 54, 46, "1a2ef","Not done","Yes");
    }
    
    /**
     * Set the date of this PurchaseOrder
     * @param date
     */
    public void setDate(String date)
    {
        setDateOrder(date);
    }
    
    /**
     * Set the registrationNumber of this PurchaseOrder
     * @param registrationNumber
     */
    public void setRegistrationNumber(int registrationNumber)
    {
        setRegistrationNumberOrder(registrationNumber);
    }
    
    /**
     * Set the price of this PurchaseOrder
     * @param price
     */
    public void setPrice(double price)
    {
        setPriceOrder(price);
    }
    
    /**
     * Set the employeeCode of this PurchaseOrder
     * @param employeeCode
     */
    public void setEmployeeCode(String employeeCode)
    {
        setEmployeeCodeOrder(employeeCode);
    }
    
    /**
     * Updates the state of this PurchaseOrder
     * @param state
     */
    public void updateState(String state)
    {
        updateStateOrder(state);
    }
    
    /**
     * Updates the description of the state of this PurchaseOrder
     * @param description
     */
    public void updateDescription(String description)
    {
        updateDescriptionOrder(description);
    }
    
    /**
     * @return date of this PurchaseOrder
     */
    public String getDate()
    {
        return getDateOrder();
    }
    
    /**
     * @return registrationNumber of this PurchaseOrder
     */
    public int getRegistrationNumber()
    {
        return getRegistrationNumberOrder();
    }
    
    /**
     * @return price of this PurchaseOrder
     */
    public double getPrice()
    {
        return getPriceOrder();
    }
    
    /**
     * @return employeeCode of this PurchaseOrder
     */
    public String getEmployeeCode()
    {
        return getEmployeeCodeOrder();
    }
    
    /**
     * @return state of this PurchaseOrder
     */
    public String getState()
    {
        return getStateOrder();
    }
    
    /**
     * @return description of the state of this PurchaseOrder
     */
    public String getDescription()
    {
        return getDescriptionOrder();
    }
}
