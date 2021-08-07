package TUI;
import Controller.WarehouseController;
import java.util.*;

/**
 * WarehouseMenu managing the warehouse menu and its options.
 *
 * @author Alexandru Krausz, Martin Benda, Sebastian Labuda, Simeon Kolev
 * @version 2019.12.10
 */
public class WarehouseMenu {
    private WarehouseController warehouseController;
    private ArrayList<String> info;
    private String stringInput;
    public WarehouseMenu()
    {
        warehouseController = new WarehouseController();
        start();
    }

    private void printMenu()
    {
        MenuWriter.writeWarehouseMenu();
    }

    private void start()
    {
        int input = -1;
        while(input !=0)
        {
            printMenu();
            System.out.println("Enter key: ");
            input = InputReader.getUserInputInt();
            switch (input)
            {
                case 1:
                {
                    addWarehouses();
                    if(stringInput.equalsIgnoreCase("Exit"))
                    {
                        break;
                    }
                    newTransferList();
                    addProducts();
                    if(stringInput.equalsIgnoreCase("Exit"))
                    {
                        warehouseController.deletTransferList(warehouseController.getIndexTransferList());
                        break;
                    }
                    break;
                }
                case 2:
                {
                    findTransferLists();
                    break;
                }
                case 3:
                {
                    addSellableProduct();
                    break;
                }
                case 4:
                {
                    findSellableProduct();
                    break;
                }
            }
        }
    }

    private void newTransferList()
    {
        if(warehouseController.newTransferList())
        {
            System.out.println("List created successfully");
        }
        else 
        {
            System.out.println("An error has ocurred please try again");
        }
    }

    private void addWarehouses()
    {
        boolean found = false;
        boolean exit = false;
        System.out.println("Enter Warehouse name!");
        System.out.println("From: ");
        while(!found && !exit)
        {
            stringInput = InputReader.getUserInputString();
            if(stringInput.equalsIgnoreCase("Exit"))
            {
                exit = true;
            }
            if(!exit)
            {
                found = warehouseController.fromWarehouse(stringInput);
                if(!found)
                {
                    System.out.println("Warehouse not found!");
                }
            }
        }
        if(!exit)
        {
            System.out.println("Warehouse added");
            found = false;
            System.out.println("To: ");
            while(!found && !exit)
            {
                stringInput = InputReader.getUserInputString();
                if(stringInput.equalsIgnoreCase("Exit"))
                {
                    exit = true;
                }
                if(!exit)
                {
                    found = warehouseController.toWarehouse(stringInput);
                    if(!found)
                    {
                        System.out.println("Warehouse not found!");
                    }
                }
            }
            if(found)
            {
                System.out.println("Warehouse added");
            }
        }
    }

    private void addProducts()
    {
        info = new ArrayList<>();
        int inputamount;
        boolean finished = false;
        boolean exit = false;
        while(!finished && !exit)
        {
            System.out.println("Scan barcode:");
            stringInput = InputReader.getUserInputString();
            if(warehouseController.checkProduct(stringInput))
            {
                if(stringInput.equalsIgnoreCase("Done"))
                {
                    finished = true;
                }
                if(stringInput.equalsIgnoreCase("Exit"))
                {
                    exit = true;
                }
                if(!finished && !exit)
                {
                    info = warehouseController.findSellableProduct(stringInput);
                    for(String a: info)
                    {
                        System.out.println(a);
                    }
                    if(info.size() > 1)
                    {
                        int check = warehouseController.getAmount();
                        System.out.println("[0] Exit");
                        System.out.println("Enter amount to be transfered:");
                        inputamount = InputReader.getUserInputInt();
                        while(inputamount > check || inputamount < 0)
                        {
                            System.out.println("Amount must be between 1 and " + check);
                            inputamount = InputReader.getUserInputInt();
                        }
                        if(inputamount == 0)
                        {
                            System.out.println("Product not added");
                        }
                        else
                        {
                            warehouseController.addProductToTransfer(inputamount);
                        }
                    }
                }
            }
            else
            {
                System.out.println("Product already exist in this order");
            }

        }
    }

    private void findTransferLists()
    {
        int size;
        info = new ArrayList<>();
        boolean found = false;
        boolean exit = false;
        while(!found && !exit)
        {
            System.out.println("Enter delivery warehouse:");
            stringInput = InputReader.getUserInputString();
            if(!stringInput.equalsIgnoreCase("exit"))
            {
                if(warehouseController.getListOfTransferLists(stringInput))
                {
                    System.out.println("Found");
                    found = true;
                }
                else 
                {
                    System.out.println("Something went wrong please try again");
                }
            }
            else 
            {
                exit = true;
            }
        }
        if(!exit)
        {
            info = warehouseController.getEntryList();
            size = warehouseController.getListSize();
            for(int i = 0; i < info.size(); i++)
            {
                System.out.println(info.get(i));
            }

            System.out.println("Enter list number:");
            int input = InputReader.getUserInputInt();

            while((input < 0 || input >= size) && input != -1)
            {
                System.out.println("List not found ty a number from 0 to " + (size-1));
                input = InputReader.getUserInputInt();
            }
            if(input != -1)
            {
                warehouseController.transfer(input);
            }
        }
    }

    private void addSellableProduct()
    {
        //String name;
        String barcode;
        String description;
        double price = 99.9f;
        int amount;
        int minAmount;
        int maxAmount;
        boolean unique = false;//TO BE IMPLEMENTED LATER
        boolean found = false;
        boolean exit = false;
        System.out.println("Enter warehouse name:");
        stringInput = InputReader.getUserInputString();
        found = warehouseController.toWarehouse(stringInput);
        if(stringInput.equalsIgnoreCase("exit"))
        {
            exit = true;
        }
        while(!found && !exit)
        {
            System.out.println("Warehouse not found:");
            stringInput = InputReader.getUserInputString();
            found = warehouseController.toWarehouse(stringInput);
        }
        if(!exit)
        {
            //System.out.println("Enter product name:");
            //stringInput = InputReader.getUserInputString();
            //if(stringInput.equalsIgnoreCase("exit"))
            //{
            //    exit = true;
            // }
            //name = stringInput;
            if(!exit)
            {
                System.out.println("Enter product barcode:");
                stringInput = InputReader.getUserInputString();
                if(stringInput.equalsIgnoreCase("exit"))
                {
                    exit = true;
                }
                barcode = stringInput;
                if(!exit)
                {
                    System.out.println("Enter product description:");
                    stringInput = InputReader.getUserInputString();
                    if(stringInput.equalsIgnoreCase("exit"))
                    {
                        exit = true;
                    }
                    description = stringInput;
                    if(!exit)
                    {
                        //PROBLEM NEED NEW READER
                        //System.out.println("Enter product price:");
                        //price = InputReader.getUserInput();
                        //
                        //TO DO EXIT FOR INT READER
                        System.out.println("Enter product amount:");
                        amount = InputReader.getUserInputInt();
                        if(amount == -1)
                        {
                            exit = true;
                        }
                        if(!exit)
                        {
                            System.out.println("Enter product minimum amount:");
                            minAmount = InputReader.getUserInputInt();
                            if(minAmount == -1)
                            {
                                exit = true;
                            }
                            if(!exit)
                            {
                                System.out.println("Enter product maximum amount:");
                                maxAmount = InputReader.getUserInputInt();
                                if(maxAmount == -1)
                                {
                                    exit = true;
                                }
                                if(!exit)
                                {
                                    warehouseController.addSellableProduct(barcode,description,price,amount,minAmount,maxAmount,unique);
                                    System.out.println("Product added");                            
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void findSellableProduct()
    {
        info = new ArrayList<>();
        boolean exit = false;
        boolean found = false;
        System.out.println("Enter warehouse name:");
        stringInput = InputReader.getUserInputString();
        found = warehouseController.fromWarehouse(stringInput);
        if(stringInput.equalsIgnoreCase("exit"))
        {
            exit = true;
        }
        while(!found && !exit)
        {
            System.out.println("Warehouse not found");
            stringInput = InputReader.getUserInputString();
            found = warehouseController.fromWarehouse(stringInput);
            if(stringInput.equalsIgnoreCase("exit"));
            {
                exit = true;
            }
        }
        if(!exit)
        {
            System.out.println("Scan barcode:");
            stringInput = InputReader.getUserInputString();
            info = warehouseController.findSellableProduct(stringInput);
            for(String a: info)
            {
                System.out.println(a);
            }
        }
    }
}
