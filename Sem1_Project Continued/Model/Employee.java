package Model;


/**
 * Employee with an id(employeeCode), orderNumber and contact info from Person superclass.
 *
 * @author Alexandru Krausz, Martin Benda, Sebastian Labuda, Simeon Kolev
 * @version 2019.12.3
 */
public class Employee extends Person{
    // instance variables
    private String employeeCode;
    private int orderNumber;
    private Position position;

    /**
     * Constructor for Employee
     * @param employeeCode of the Employee
     * @param orderNumber //TODO
     * @param name from Person superclass
     * @param email from Person superclass
     * @param address from Person superclass
     * @param phoneNumber from Person superclass
     * @param position associated with Employee
     */
    public Employee(Position position)
    { 
        super("test", "test", "test", "test");
        this.position = position;
    }
    
    /**
    * Returns the name of the Position, the Employee is associated to
    * @return name of the Position
    */
    public String getPositionName()
    {
        return position.getNamePosition();
    }
    
    /**
    * Returns the clearance of the Position, the Employee is associated to
    * @return clearance of the Position
    */
    public String getPositionClearance()
    {
        return position.getClearancePosition();
    }
    
    /**
     * Set the employeeCode of this Employee
     * @param employeeCode
     */
    public void setEmployeeCode(String employeeCode)
    {
        this.employeeCode = employeeCode;
    }
    
    /**
     * Set the orderNumber of this Employee
     * @param orderNumber
     */
    public void setOrderNumber(int orderNumber)
    {
        this.orderNumber = orderNumber;
    }
    
    /**
     * @return employeeCode of this Employee
     */
    public String getEmployeeCode()
    {
        return employeeCode;
    }
    
    /**
     * @return orderNumber of this Employee
     */
    public int getOrderNumber() 
    {
        return orderNumber;
    }
    
    /**
     * Set the name of this Employee
     * @param name
     */
    public void setName(String name)
    {
        setNamePerson(name);
    }
    
    /**
     * Set the email of this Employee
     * @param email
     */
    public void setEmail(String email)
    {
        setEmailPerson(email);
    }
    
    /**
     * Set the address of this Employee
     * @param address
     */
    public void setAddress(String address)
    {
        setAddressPerson(address);
    }
    
    /**
     * Set the phoneNumber of this Employee
     * @param phoneNumber
     */
    public void setPhoneNumber(String phoneNumber)
    {
        setPhoneNumberPerson(phoneNumber);
    }
    
    /**
     * @return name of this Employee
     */
    public String getName()
    {
        return getNamePerson();
    }
    
    /**
     * @return email of this Employee
     */
    public String getEmail() 
    {
        return getEmailPerson();
    }
    
    /**
     * @return address of this Employee
     */
    public String getAddress()
    {
        return getAddressPerson();
    }
    
    /**
     * @return phoneNumber of this Employee
     */
    public String getPhoneNumber() 
    {
        return getPhoneNumberPerson();
    }
}
