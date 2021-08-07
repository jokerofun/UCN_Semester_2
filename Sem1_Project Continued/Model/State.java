package Model;


/**
 * State with current state and description of it.
 *
 * @author Alexandru Krausz, Martin Benda, Sebastian Labuda, Simeon Kolev
 * @version 2019.12.8
 */
public class State {
   private String state;//TO CHECK
   private String description;

   //TO CHECK
   /**
     * Constructor for State
     * @param state of the State
     * @param description of the State
     */
   public State(String state,String description)
   {
       this.state = state;
       this.description = description;
   }

   /**
     * Updates the state of this State
     * @param state
     */
   public void updateState(String state)
   {
       this.state = state;
   }

   /**
     * Updates the description of this RentOrder
     * @param description
     */
   public void updateDescription(String description)
   {
       this.description = description;
   }

   /**
     * @return state of this State
     */
   public String getState()
   {
       return state;
   }

   /**
     * @return description of this State
     */
   public String getDescription()
   {
       return description;
   }
}
