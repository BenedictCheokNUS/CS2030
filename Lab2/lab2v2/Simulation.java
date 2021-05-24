/**
 * This class is a general abstract class that
 * encapsulates a simulation.  To implement a
 * simulation, inherit from this class and implement
 * the `getInitialEvents` method.  
 *
 * @author Benedict Cheok Wei En (B03), A0199433U
 * @version CS2030 AY20/21 ST1
 */
abstract class Simulation {
  /**
   * An abstract method to return an array of events
   * used to initialize the simulation.
   *
   * @return An array of initial events that the 
   *         simulator can use to kick-start the 
   *         simulation.
   */
  public abstract Event[] getInitialEvents();
}
