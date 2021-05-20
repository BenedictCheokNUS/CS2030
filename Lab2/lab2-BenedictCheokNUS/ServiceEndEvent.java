/**
 * This class encapsulates ServiceEnd event in the shop
 * simulation.  Your task is to replace this
 * class with new classes, following proper OOP principles.
 *
 * @author Benedict Cheok Wei En (B03), A0199433U
 * @version CS2030 AY20/21 ST1
 */
class ServiceEndEvent extends Event {
  
  //ATTRIBUTES
   
  private Customer cust;
  private Counter counter; 
  /**
   * An array to indicate if a service counter is
   * available.  available[i] is true if and only
   * if service counter i is available to serve.
   */
  private boolean[] available;

  //CONSTRUCTOR METHOD
  /**
   * Constructor for ServiceEnd event.
   *
   * @param time        The time this event occurs.
   * @param cust        The customer associated with this
   *                    event.
   * @param counter     The counter associated with
   *                    this event.
   * @param available   The indicator of which counter is
   *                    available.
   */
  public ServiceEndEvent(Customer cust, Counter counter, boolean[] available) {
    super(cust.getCurrTime());
    this.cust = cust;
    this.counter = counter;
    this.available = available;
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
    str = String.format(": Customer %d service done (by Counter %d)", this.cust.getCustID(), this.counter.getCounterID());
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
    this.available[currCounterID] = true;
    this.counter.setAvail(true);
    Event depart = new DepartureEvent(this.cust);
    return new Event[] {depart};

  }
}
