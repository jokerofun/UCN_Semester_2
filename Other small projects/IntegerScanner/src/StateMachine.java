/**
 * Checking signed integer
 * 
 * @author (FEN) 
 * @version (2015-03-15)
 * 2017 modified by GIBE
 */

public class StateMachine {
	
    private State currentState;
	
	private State startState;
    private State signState;
    private State integerState;
    private State errorState;
    

    public StateMachine()
    {  	
    	startState = new Start();
    	integerState = new UnsignedInt();
        signState = new SignedInteger();
        errorState = new Error();
        
        startState.addTransition(Inputs.digit, integerState);
        startState.addTransition(Inputs.sign, signState);
        startState.addTransition(Inputs.other, errorState);

        signState.addTransition(Inputs.digit, integerState);
        signState.addTransition(Inputs.other, errorState);

        integerState.addTransition(Inputs.digit, integerState);
        integerState.addTransition(Inputs.other, errorState);

        errorState.addTransition(Inputs.other, errorState);
        errorState.addTransition(Inputs.digit, errorState);
        errorState.addTransition(Inputs.sign, errorState);

    }

    public boolean scan(String input)
    {
        //input.length()>0
        boolean ok = false;
        int i = 0;
        currentState = startState;
        while (i<input.length())
        {               
            char nextChar = input.charAt(i);
            currentState.action();
            currentState = currentState.transition(nextChar);
            i++;
        }
        if (currentState == integerState)
            ok = true;
        return ok;
    }
    
    enum Inputs {sign, digit, other};
}
