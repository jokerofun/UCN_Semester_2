package Model;


/**
 * Position with name and clearance.
 *
 * @author Alexandru Krausz, Martin Benda, Sebastian Labuda, Simeon Kolev
 * @version 2019.12.5
 */
public class Position{
    // instance variables
    private String name;
    private String clearance;

    /**
     * Constructor for Position
     * @param name of the Position
     * @param clearance of the Position
     */
    public Position(String name, String clearance)
    {
        this.name = name;
        this.clearance = clearance;
    }
    
    /**
     * Set the name of this Position
     * @param name
     */
    public void setNamePosition(String name)
    {
        this.name = name;
    }
    
    /**
     * Set the clearance of this Position
     * @param clearance
     */
    public void setClearancePosition(String clearance)
    {
        this.clearance = clearance;
    }
    
    /**
     * @return name/clearance of this Position
     */
    public String getNamePosition()
    {
        return name;
    }
    
    /**
     * @return clearance of this Position
     */
    public String getClearancePosition()
    {
        return clearance;
    }
}
