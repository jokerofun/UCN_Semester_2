package Model;
import java.util.*;


/**
 * TransferList containing info about from which to which warehouse and a list of items that are being transferred.
 *
 * @author Alexandru Krausz, Martin Benda, Sebastian Labuda, Simeon Kolev
 * @version 2019.12.10
 */
public class TransferList{
    // instance variables
    private Warehouse fromWarehouse;
    private Warehouse toWarehouse;
    private HashMap<String, Integer> list;
    

    /**
     * Constructor for TransferList
     * @param fromWarehouse Warehouse
     * @param toWarehouse Warehouse
     */
    public TransferList(Warehouse fromWarehouse, Warehouse toWarehouse)
    {
        list = new HashMap<>();
        this.fromWarehouse = fromWarehouse;
        this.toWarehouse = toWarehouse;
    }
    
    /**
     * Used to add a product to the list of products to be transferred
     * @param barcode String
     * @param count int
     */
    public void addProduct(String barcode, int count)
    {
       list.put(barcode, count);
    }
    
    /**
     * Used to check if a given product exists in the list of products that are to be transferred
     * @param barcode String
     * @return boolean
     */
    public boolean checkProduct(String barcode)
    {
        return list.containsKey(barcode);
    }
    
    /**
     * Used to remove a product from the list of products that are to be transferred
     * @param barcode String
     */
    public void removeIndividualProduct(String barcode)
    {
        list.remove(barcode);
    }
    
    /**
     * Used to remove all products from the list of products
     * Used after successfully transferring all products to the correct warehouse
     */
    public void clearList()
    {
        for(Map.Entry<String, Integer> entry : list.entrySet()) {
            String key = entry.getKey();

            list.remove(key);
        }
    }

    /**
     * Used to get and return the ProductList of this TransferList
     * @return HashMap<String, Integer>
     */
    public HashMap getProductList()
    {
        return list;
    }
    
    /**
     * Used to get ToWarehouse of this TransferList
     * @return Warehouse
     */
    public Warehouse getToWarehouse()
    {
        return toWarehouse;
    }

    /**
     * Used to get FromWarehouse of this TransferList
     * @return Warehouse
     */
    public Warehouse getFromWarehouse()
    {
        return fromWarehouse;
    }

    /**
     * Used to create and return an arrayList, by creating a temporary arrayList
     * and copying the contents of the hashMap of this TransferList containing products and their amount
     * @return ArrayList<String>
     */
    public ArrayList getEntryList()
    {
        ArrayList<String> temp = new ArrayList<>();
        list.forEach((k,v) -> temp.add("Barcode: " + k + " Amount: " + v));
        return temp;
    }
    
    /**
     * Used to create and return an arrayList, by creating a temporary arrayList
     * and copying the keys of the hashMap of this TransferList containing products and their amount
     * @return ArrayList<String>
     */
    public ArrayList getEntryListKey()
    {
        ArrayList<String> temp = new ArrayList<>();
        list.forEach((k,v) -> temp.add(k));
        return temp;
    }
    
    /**
     * Used to get the amount of a product from the hashMap of this TransferList by searching by key
     * @param key String
     * @return int
     */
    public int getAmount(String key)
    {
        return list.get(key);
    }
}
