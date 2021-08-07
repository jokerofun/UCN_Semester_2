import java.io.Console;
import java.util.Scanner;

/** 
 * Testing integer scanner
 * 
 * @author (FEN)
 * @version (2015-03-15)
 * 2017 modified GIBE
 */

class Main
{
    public static void main(String[] args)
    {
        
    	System.out.println("*** This machine recognizes integer ***");
    	System.out.println("Type your input: ");

    	Scanner s = new Scanner(System.in);
        String input = s.nextLine();
        s.close();
    	System.out.println("Input String: " + input);       
    	StateMachine intScan = new StateMachine();
    	System.out.println("Result: " + intScan.scan(input));
    	

    }
}
