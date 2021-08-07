/**
 * class Start - Start state
 * 
 * @author (FEN)
 * @version (2015-03-14)
 * 2017 modified by GIBE
 */
public class Start extends State {

	
	@Override
	public State transition(char c) {
		
        if (c == '+' || c == '-')
            return transTable.get(StateMachine.Inputs.sign);
        else if ('0' <= c && c <= '9')
            return transTable.get(StateMachine.Inputs.digit);
        else
            return transTable.get(StateMachine.Inputs.other);
	}
	
	@Override
	public void action()
    {
        System.out.println("State Start");
    }
}
