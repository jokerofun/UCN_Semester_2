package Model;
import java.util.*;


/**
 * GroupContainer singleton containing Groups.
 *
 * @author Alexandru Krausz, Martin Benda, Sebastian Labuda, Simeon Kolev
 * @version 2019.12.4
 */
public class GroupContainer{
    private static GroupContainer instance;
    // instance variables
    private ArrayList<Group> groups;

    /**
     * Constructor for GroupContainer
     * @param groups list of groups
     */
    private GroupContainer()
    {
        groups = new ArrayList<>();
    }
    
    /**
     * Creates and returns an instance of GroupContainer
     * @return groupContainer instance
     */
    public static GroupContainer getGroupContainer()
    {
        
        if(instance == null){
            instance = new GroupContainer();
        }
        return instance;
    }
    
    /**
     * Used to add a group to the list of groups
     * @param group object
     */
    public void addGroup(Group group)
    {
        groups.add(group);
    }
    
    /**
     * Used to remove a group from the list of groups
     * @param group object
     */
    public void removeGroup(Group group)
    {
        groups.remove(group);
    }
}
