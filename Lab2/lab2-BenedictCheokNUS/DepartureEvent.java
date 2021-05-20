/**
 * This class encapsulates Departure event in the shop
 * simulation.  Your task is to replace this
 * class with new classes, following proper OOP principles.
 *
 * @author Benedict Cheok Wei En (B03), A0199433U
 * @version CS2030 AY20/21 ST1
 */
class DepartureEvent extends Event {
  
  //ATTRIBUTES

  private Customer cust;
  
  //CONSTRUCTOR METHOD
  /**
   * Constructor for DepartureEvent.
   *
   * @param time       The time this event occurs.
   * @param cust       The customer associated with this
   *                   event.
   */
  public DepartureEvent(Customer cust) {
    super(cust.getCurrTime());
    this.cust = cust;
  }

  //METHODS
  /**
   * Returns the string representation of the event,
   * depending on the type of event.
   *
   * @return A string representing the event.
   */
  @Override
  public String toString() {
    String str = "";
    str = String.format(": Customer %d departed", this.cust.getCustID());
    return super.toString() + str;
  }

  /**
   * The logic that the simulation should follow when simulating
   * this event.
   *
   * @return An array of new events to be simulated.
   */
  @Override
  public Event[] simulate() {
  return new Event[] {};
  } 
}
