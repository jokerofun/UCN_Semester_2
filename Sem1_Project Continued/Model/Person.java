package Model;

/**
 * Person with contact information
 * @author Alexandru Krausz, Martin Benda, Sebastian Labuda, Simeon Kolev
 * @version 2019.12.3
 */

public class Person{
    // instance variables
    private String name;
    private String email;
    private String address;
    private String phoneNumber;

    /**
     * Constructor for Person
     * @param name of the Person
     * @param email of the Person
     * @param address of the Person
     * @param phoneNumber of the Person
     */
    public Person(String name, String email, String address, String phoneNumber)
    {
        this.name = name;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    /**
     * Set the name of the Person
     * @param name
     */
    public void setNamePerson(String name) 
    {
        this.name = name;
    }
    
    /**
     * Set the email of the Person
     * @param email
     */
    public void setEmailPerson(String email)
    {
         this.email = email;
    }
    
    /**
     * Set the address of the Person
     * @param address
     */
    public void setAddressPerson(String address) 
    {
        this.address = address;
    }
    
    /**
     * Set the phoneNumber of the Person
     * @param phoneNumber
     */
    public void setPhoneNumberPerson(String phoneNumber) 
    {
        this.phoneNumber = phoneNumber;
    }
    
    /**
     * @return name of the Person
     */
    public String getNamePerson() 
    {
        return name;
    }
    
    /**
     * @return email of the Person
     */
    public String getEmailPerson()
    {
        return email;
    }
    
    /**
     * @return address of the Person
     */
    public String getAddressPerson() 
    {
        return address;
    }
    
    /**
     * @return phoneNumber of the Person
     */
    public String getPhoneNumberPerson() 
    {
        return phoneNumber;
    }
}

