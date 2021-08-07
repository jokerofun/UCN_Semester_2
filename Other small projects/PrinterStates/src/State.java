import java.util.*;
/**
 * Abstract class State - Represents a general state
 * 
 * @author (FEN)
 * @version (2015-03-17)
 * 
 * GIBE small changes
 */
public abstract class State
{
   private Map<Events, State> adjacents;

   public State(){
       adjacents = new TreeMap<>();
   }

   public Map<Events, State> getAdjacents(){
       return adjacents; 
   }

   public void addTransition(Events e, State s){
       adjacents.put(e, s);
   }

   public String eventString(){
       StringBuilder bld = new StringBuilder();
       Set<Events> keys= adjacents.keySet();
       Iterator<Events> it= keys.iterator();
       while(it.hasNext()){
           Events currKey= it.next();
           bld.append(currKey.label() + "   ");
       }
       return bld.toString();
   }

   //get next state
	public State transition(Events e) {
       return getAdjacents().get(e);
	}

   //Actions associated with the concrete state
   public abstract void action();
}
