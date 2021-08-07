package Model;


/**
 * Group with name and discount.
 *
 * @author Alexandru Krausz, Martin Benda, Sebastian Labuda, Simeon Kolev
 * @version 2019.12.4
 */
public class Group{
    // instance variables
    private String name;
    private int discount;

    /**
     * Constructor for Group
     * @param name of the Group
     * @param discount for the Group
     */
    public Group(String name, int discount)
    {
        this.name = name;
        this.discount = discount;
    }

    /**
     * Set the name of this Group
     * @param name
     */
    public void setNameGroup(String name)
    {
        this.name = name;
    }
    
    /**
     * Set the discount for this Group
     * @param discount
     */
    public void setDiscountGroup(int discount)
    {
        this.discount = discount;
    }
    
    /**
     * @return name of this Group
     */
    public String getNameGroup()
    {
        return name;
    }
    
    /**
     * @return discount for this Group
     */
    public int getDiscountGroup()
    {
        return discount;
    }
}
