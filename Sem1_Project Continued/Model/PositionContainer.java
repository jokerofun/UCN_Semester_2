package Model;
import java.util.*;


/**
 * PositionContainer containing a list of Position.
 *
 * @author Alexandru Krausz, Martin Benda, Sebastian Labuda, Simeon Kolev
 * @version 2019.12.5
 */
public class PositionContainer{
    // instance variables
    private static PositionContainer instance;
    private ArrayList<Position> positions;

    /**
     * Constructor for PositionContainer
     * @param positions containing positions
     */
    private PositionContainer()
    {
        positions = new ArrayList<>();
    }
    
    /**
     * Creates and returns an instance of PositionContainer
     * @return positionContainer instance
     */
    public static PositionContainer getPositionContainer()
    {
        
        if(instance == null){
            instance = new PositionContainer();
        }
        return instance;
    }
    
    /**
     * Used to add a position to the list of positions
     * @param position object
     */
    public void addPosition(Position position)
    {
        positions.add(position);
    }
    
    /**
    * Used to remove a position from the list of positions
    * @param position object
    */
    public void removePosition(Position position)
    {
        positions.remove(position);
    }
}
