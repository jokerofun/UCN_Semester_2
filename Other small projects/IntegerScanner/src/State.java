import java.util.HashMap;
import java.util.Map;

/**
 * Abstract class State - Represents a general state
 * 
 * @author (FEN)
 * @version (2015-03-14)
 * 2017 modified by GIBE
 * 
 */
public abstract class State
{
	
	public Map<StateMachine.Inputs,State> transTable;
	
	
    //returns next state
    abstract public State transition(char c);

    //in case there are any actions connected to a concrete state  
    abstract public void action();
    

    public State()
    {
        transTable = new HashMap<StateMachine.Inputs, State>();
    }

    public void addTransition(StateMachine.Inputs inp, State s)
    {
        transTable.put(inp, s);
    }
}
