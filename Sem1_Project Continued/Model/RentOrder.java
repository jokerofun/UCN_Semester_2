package Model;

/**
 * RentOrder with list of rentableItems and order info from Order superclass.
 *
 * @author Alexandru Krausz, Martin Benda, Sebastian Labuda, Simeon Kolev
 * @version 2019.12.3
 */
public class RentOrder extends Order{
    // instance variables
    //TODO rentable items

    /**
     * Constructor for RentOrder
     * @param date from Order superclass
     * @param registrationNumber from Order superclass
     * @param price from Order superclass
     * @param employeeCode from Order superclass
     * @param state from Order superclass
     */
    public RentOrder()
    {
        super("10.21.23", 54, 46, "1a2ef","Not done","Yes");
    }
    
    /**
     * Set the date of this RentOrder
     * @param date
     */
    public void setDate(String date)
    {
        setDateOrder(date);
    }
    
    /**
     * Set the registrationNumber of this RentOrder
     * @param registrationNumber
     */
    public void setRegistrationNumber(int registrationNumber)
    {
        setRegistrationNumberOrder(registrationNumber);
    }
    
    /**
     * Set the price of this RentOrder
     * @param price
     */
    public void setPrice(double price)
    {
        setPriceOrder(price);
    }
    
    /**
     * Set the employeeCode of this RentOrder
     * @param employeeCode
     */
    public void setEmployeeCode(String employeeCode)
    {
        setEmployeeCodeOrder(employeeCode);
    }
    
    /**
     * Updates the state of this RentOrder
     * @param state
     */
    public void updateState(String state)
    {
        updateStateOrder(state);
    }
    
    /**
     * Updates the description of the state of this RentOrder
     * @param description
     */
    public void updateDescription(String description)
    {
        updateDescriptionOrder(description);
    }
    
    /**
     * @return date of this RentOrder
     */
    public String getDate()
    {
        return getDateOrder();
    }
    
    /**
     * @return registrationNumber of this RentOrder
     */
    public int getRegistrationNumber()
    {
        return getRegistrationNumberOrder();
    }
    
    /**
     * @return price of this RentOrder
     */
    public double getPrice()
    {
        return getPriceOrder();
    }
    
    /**
     * @return employeeCode of this RentOrder
     */
    public String getEmployeeCode()
    {
        return getEmployeeCodeOrder();
    }
    
    /**
     * @return state of this RentOrder
     */
    public String getState()
    {
        return getStateOrder();
    }
    
    /**
     * @return description of the state of this RentOrder
     */
    public String getDescription()
    {
        return getDescriptionOrder();
    }
}
