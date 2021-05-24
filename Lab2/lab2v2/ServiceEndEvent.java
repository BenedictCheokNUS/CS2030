/**
 * This class encapsulates ServiceEnd event in the shop
 * simulation.  Your task is to replace this
 * class with new classes, following proper OOP principles.
 *
 * @author Benedict Cheok Wei En (B03), A0199433U
 * @version CS2030 AY20/21 ST1
 */
class ServiceEndEvent extends Event {
  
  /**ATTRIBUTES*/
  private Shop shop;
  private Customer cust;
  private Counter counter; 
  
  /**CONSTRUCTOR METHOD*/
  /**
   * Constructor for ServiceEnd event.
   *
   * @param time        The time this event occurs.
   * @param shop        The shop
   * @param cust        The customer associated with this
   *                    event.
   * @param counter     The counter associated with
   *                    this event.
   *
   */
  public ServiceEndEvent(Customer cust, Counter counter, Shop shop) {
    super(cust.getCurrTime());
    this.shop = shop;
    this.cust = cust;
    this.counter = counter;
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
    str = String.format(": %s service done (by %s)", this.cust, this.counter);
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
    // The current event is a service-end event.
    // Mark the counter as available, then schedule
    // a departure event at the current time.
    int currCounterID = this.counter.getCounterID();
    this.counter.setAvail(true);
    Event depart = new DepartureEvent(this.cust, this.shop);
    return new Event[] {depart};
  }
}
