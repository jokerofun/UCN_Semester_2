

package TUI;
import Controller.*;
import Model.*;

/**
 * Write a description of class Test here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Test {
   private WarehouseContainer warehouseContainer;
   public Test()
   {
       crateTest();
   }
   public static void tryme()
   {
       String result = InputReader.getUserInputString();
       System.out.println("Input = " + result + "\n");
   }

   public static void tryme2()
   {
       int result = InputReader.getUserInputInt();
       System.out.println("Input = " + result + "\n");
   }
   
   public void crateTest()
   {
       warehouseContainer = WarehouseContainer.getWarehouseContainer();
       warehouseContainer.findWarehouseByName("Diy"). addSellableProduct(new SellableProduct("123","OK",12.19,100,20,450,false));
       warehouseContainer.findWarehouseByName("Diy").addSellableProduct(new SellableProduct("1234","no",12.19,50,20,450,false));
       warehouseContainer.findWarehouseByName("Timber").addSellableProduct(new SellableProduct("12345","OK",12.19,100,20,450,false));
   }
}
