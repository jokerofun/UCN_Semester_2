package TUI;
import java.util.*;

/**
 * InputReader managing different type of inputs.
 *
 * @author Alexandru Krausz, Martin Benda, Sebastian Labuda, Simeon Kolev
 * @version 2019.12.10
 */
public class InputReader {
    public static String getUserInputString()
    {
        Scanner sc = new Scanner(System.in);
        String result = sc.nextLine();
        return result;
    }

    public static String getUserInputIntCheck()
    {
        int result = getUserInputInt();
        if(result == -1)
        {
        return "Wrong Input";
        }
        else
        {
            return result + "";
        }
    }
    
    public static int getUserInputInt()
    {
        Scanner sc = new Scanner(System.in);
        int i;
        while(!sc.hasNextInt())
        {
            System.out.println("Input must be a number!");
            sc.nextLine();
        }
        i = sc.nextInt();
        return i;
    }
}
