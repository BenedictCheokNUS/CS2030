/**
 * This class encapsulates JoinCounterQEvent in the shop
 * simulation. JoinCounterQEvent puts the customer into the 
 * counter queue.
 * Your task is to replace this class with new classes, 
 * following proper OOP principles.
 *
 * @author Benedict Cheok Wei En (B03), A0199433U
 * @version CS2030 AY20/21 ST1
 */
class JoinCounterQEvent extends Event {
  
  /**ATTRIBUTES*/

  //The customer to be added into the queue. 
  private Customer cust;
  //The counter which the customer is going to queue for.
  private Counter qAtCounter;
  
  /**CONSTRUCTOR METHOD*/
  /**
   * Constructor for a shop event.
   *
   * @param time        The time this event occurs.
   * @param cust        The customer associated with this
   *                    event.
   * @param qAtCounter  The counter which the customer would
   *                    queue up for
   */
  public JoinCounterQEvent(Customer cust, Counter qAtCounter) {
    super(cust.getCurrTime());
    this.cust = cust;
    this.qAtCounter = qAtCounter; //type Counter
  }
  
  /**METHODS*/  
  /**
   * Returns the string representation of the event,
   * depending on the type of event.
   *
   * @return A string representing the event.
   */
  @Override
  public String toString() {
    String str = "";
    str = String.format(": %s joined counter queue (at %s)", this.cust, this.qAtCounter); 
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
    this.qAtCounter.counterQueueJoin(this.cust); //simply, this.cust enters queue.
    return new Event[] {};
  }
}
