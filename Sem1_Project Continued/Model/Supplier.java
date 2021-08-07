package Model;


/**
 * Supplier with contact information.
 *
 * @author Alexandru Krausz, Martin Benda, Sebastian Labuda, Simeon Kolev
 * @version 2019.12.5
 */
public class Supplier{
    // instance variables
    private String name;
    private String address;
    private String CVR;
    //TODO add more ?

    /**
     * Constructor for Supplier
     * @param name of the Supplier
     * @param address of the Supplier
     * @param CVR of the Supplier
     */
    public Supplier(String name, String address, String CVR)
    {
        this.name = name;
        this.address = address;
        this.CVR = CVR;
    }
    
    /**
     * Set the name of this Supplier
     * @param name
     */
    public void setNameSupplier(String name)
    {
        this.name = name;
    }
    
    /**
     * Set the address of this Supplier
     * @param address
     */
    public void setAddressSupplier(String address)
    {
        this.address = address;
    }
    
    /**
     * Set the CVR of this Supplier
     * @param CVR
     */
    public void setCVRSupplier(String CVR)
    {
        this.CVR = CVR;
    }
    
    /**
     * @return name of this Supplier
     */
    public String getNameSupplier()
    {
        return name;
    }
    
    /**
     * @return address of this Supplier
     */
    public String getAddressSupplier()
    {
        return address;
    }
    
    /**
     * @return CVR of this Supplier
     */
    public String getCVRSupplier()
    {
        return CVR;
    }
}
