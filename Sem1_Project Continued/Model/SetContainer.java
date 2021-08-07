package Model;
import java.util.*;


/**
 * Write a description of class SetContainer here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class SetContainer {
   private static SetContainer instance;
   private ArrayList<Set> sets;
   
   /**
    * Constructor for SetContainer
    * @param sets containing warehouses 
    */
   private SetContainer()
   {
       sets = new ArrayList<>();
   }
    
   /**
    * Creates and returns an instance of SetContainer
    * @return setContainer instance
    */
   public static SetContainer getSetContainer()
   {
       if(instance == null){
           instance = new SetContainer();
       }
       return instance;
   }
    
   /**
    * Used to add a set to the list of sets
    * @param set object
    */
   public void addSet(Set set)
   {
       sets.add(set);
   }
    
   /**
    * Used to remove a warehouse from the list of warehouses
    * @param warehouse object
    */
   public void removeSet(Set set)
   {
        sets.remove(set);
   }
    
}
