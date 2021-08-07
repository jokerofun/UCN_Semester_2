/** 
 * @author (FEN)
 * @version (2015-03-15)
 * 2017 modified by GIBE
 */

public class Error extends State{
	
	
	@Override
	public State transition(char c) {
		
            return transTable.get(StateMachine.Inputs.other);
	}
	
	@Override
	public void action()
    {
        System.out.println("State Error");
    }
}

