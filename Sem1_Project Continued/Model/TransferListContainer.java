package Model;
import java.util.*;

/**
 * TransferListContainer contaning transferLists.
 *
 * @author Alexandru Krausz, Martin Benda, Sebastian Labuda, Simeon Kolev
 * @version 2019.12.10
 */
public class TransferListContainer{
    // instance variables
    private static TransferListContainer instance;
    private ArrayList<TransferList> transferLists;

    /**
     * Constructor for TransferListContainer
     */
    private TransferListContainer()
    {
        transferLists = new ArrayList<>();
    }

    /**
     * Creates and returns an instance of TransferListContainer
     * @return TransferListContainer instance
     */
    public static TransferListContainer getTransferListContainer()
    {
        if(instance == null){
            instance = new TransferListContainer();
        }
        return instance;
    }

    /**
     * Used to add a transferList to the array of transferLists
     * @param transferList TransferList
     * @return boolean
     */
    public boolean addTransferList(TransferList transferList)
    {
        return transferLists.add(transferList);
    }

    /**
     * Used to remove a transferList from the array of transferLists
     * @param transferList TransferList
     */
    public void removeTransferList(TransferList transferList)
    {
        transferLists.remove(transferList);
    }

    /**
     * Used to get an arrayList containing all transferList for a given Warehouse
     * Done by creating a temporary arrayList, putting all found transferList into it and then returns it
     * @param toWarehouse Warehouse
     * @return ArrayList<TransferList>
     */
    public ArrayList getTransferLists(String toWarehouse)
    {
        ArrayList<TransferList> temp = new ArrayList<>();
        for(int i = 0; i < transferLists.size(); i++)
        {
            if(transferLists.get(i).getToWarehouse().getName().equalsIgnoreCase(toWarehouse))
            {
                temp.add(transferLists.get(i));
            }
        }
        return temp;
    }

    /**
     * Used to remove a transferList from the arrayList container
     * @param input int
     * @return boolean
     */
    public boolean deleteTransferList(int input)
    {
        return transferLists.remove(transferLists.get(input));
    }

    /**
     * Used to get the index of a transferList in the arrayList container
     * @param trans TransferList
     * @return int
     */
    public int getIndexTransferList(TransferList trans)
    {
        return transferLists.indexOf(trans);
    }
}
