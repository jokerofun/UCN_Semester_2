package TUI;
import java.util.*;


/**
 * MenuWriter managing the printing of the main menu and its options.
 *
 * @author Alexandru Krausz, Martin Benda, Sebastian Labuda, Simeon Kolev
 * @version 2019.12.10
 */
public class MenuWriter {
   private static ArrayList<String> menu = new ArrayList<>();

   /**
    * Prints out the options in the main menu
    */
   private static void writeMenu()
   {
       int options = 0;
       Iterator iterate = menu.iterator();
       while(iterate.hasNext())
       {
           iterate.next();
           options++;
       }
       System.out.println("[0] Exit");
       for(int i = 1;i <= options;i++)
       {
           System.out.println("[" + i + "]" + menu.get(i-1));
       }
   }

   //Write different menu

   public static void writeMainMenu()
   {
       menu.clear();
       addAllItems();
       writeMenu();
   }

   public static void writeWarehouseMenu()
   {
       menu.clear();
       addWarehouseMenuItems();
       writeMenu();
   }
   
   //Add options to menu
   private static void addAllItems()
   {
       menu.add("Manage inventory");
   }

   private static void addWarehouseMenuItems()
   {
       menu.add("Move product");
       menu.add("Receive delivery");
       menu.add("Add new product");
       menu.add("Search product");
   }
   
}
