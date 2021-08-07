package Model;


/**
 * Order with date, registrationNumber, price, employeeCode and state.
 *
 * @author Alexandru Krausz, Martin Benda, Sebastian Labuda, Simeon Kolev
 * @version 2019.12.3
 */
public class Order {
    private String date;
    private int registrationNumber;
    private double price;
    private String employeeCode;
    private State state;

    /**
     * Constructor for Order
     * @param date of the Order
     * @param registrationNumber of the Order
     * @param price of the Order
     * @param employeeCode of the Order
     * @param state of the Order
     */
    public Order(String date, int registrationNumber, double price, String employeeCode,String state,String description)
    {
        this.date = date;
        this.registrationNumber = registrationNumber;
        this.price = price;
        this.employeeCode = employeeCode;
        this.state = new State(state,description);
    }

    /**
     * Set the date of this Order
     * @param date
     */
    public void setDateOrder(String date)
    {
        this.date = date;
    }

    /**
     * Set the registrationNumber of this Order
     * @param registrationNumber
     */
    public void setRegistrationNumberOrder(int registrationNumber)
    {
        this.registrationNumber = registrationNumber;
    }

    /**
     * Set the price of this Order
     * @param price
     */
    public void setPriceOrder(double price)
    {
        this.price = price;
    }

    /**
     * Set the employeeCode of this Order
     * @param employeeCode
     */
    public void setEmployeeCodeOrder(String employeeCode)
    {
        this.employeeCode = employeeCode;
    }

    /**
     * Updates the state of this Order
     * @param state
     */
    public void updateStateOrder(String state)
    {
        this.state.updateState(state);
    }

    /**
     * Updates the description of the state of this Order
     * @param description
     */
    public void updateDescriptionOrder(String description)
    {
        this.state.updateDescription(description);
    }
    
    /**
     * @return date of this Order
     */
    public String getDateOrder()
    {
        return date;
    }

    /**
     * @return registrationNumber of this Order
     */
    public int getRegistrationNumberOrder()
    {
        return registrationNumber;
    }

    /**
     * @return price of this Order
     */
    public double getPriceOrder()
    {
        return price;
    }

    /**
     * @return employeeCode of this Order
     */
    public String getEmployeeCodeOrder()
    {
        return employeeCode;
    }

    /**
     * @return state of this Order
     */
    public String getStateOrder()
    {
        return this.state.getState();
    }

    /**
     * @return desciption of the state of this Order
     */
    public String getDescriptionOrder()
    {
        return this.state.getDescription();
    }
}
