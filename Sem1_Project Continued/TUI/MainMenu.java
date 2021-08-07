package TUI;

/**
 * MainMenu managing the main menu and its options.
 *
 * @author Alexandru Krausz, Martin Benda, Sebastian Labuda, Simeon Kolev
 * @version 2019.12.10
 */
public class MainMenu {

    public MainMenu()
    {
        start();
    }

    /**
     * Prints out the main menu
     */
    private void printmenu()
    {
        MenuWriter.writeMainMenu();
    }

    /**
     * Handles the calls of the different menus
     */
    private void start()
    {
        int input = -1;
        while(input != 0)
        {
            printmenu();
            System.out.println("Enter key: ");
            input = InputReader.getUserInputInt();
            switch (input)
            {
                case 1:
                {
                    new WarehouseMenu();
                    break;
                }
            }
        }
    }
}
