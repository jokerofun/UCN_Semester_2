/** 
 * @author (FEN)
 * @version (2015-03-15)
 * 2017 modified by GIBE
 */

public class SignedInteger extends State {
    
    
	@Override
	public State transition(char c) {

        if('0'<=c && c<='9')
            return transTable.get(StateMachine.Inputs.digit);
        else
            return transTable.get(StateMachine.Inputs.digit);
	}
	
	@Override
	public void action()
    {
        System.out.println("State SignedInteger");
    }

}
