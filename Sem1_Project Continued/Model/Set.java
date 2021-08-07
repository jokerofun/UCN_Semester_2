package Model;
import java.util.*;


/**
 * Write a description of class Set here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Set implements Component{
   private String name;
   private ArrayList<Component> components;

   private Set()
   {
       components = new ArrayList<>();
   }
}
