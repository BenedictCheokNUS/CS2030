/**
 * This class encapsulates Service Begin event in the shop
 * simulation.  Your task is to replace this
 * class with new classes, following proper OOP principles.
 *
 * @author Benedict Cheok Wei En (B03), A0199433U
 * @version CS2030 AY20/21 ST1
 */
class ServiceBeginEvent extends Event {
  
  //ATTRIBUTES
  
  // This is the customer for the service event
  private Customer cust;
  // This is the counter associated with the service
  private Counter counter;

  /**
   * An array to indicate if a service counter is
   * available.  available[i] is true if and only
   * if service counter i is available to serve.
   */
  private boolean[] available;
  
  //CONSTRUCTOR METHOD
  /**
   * Constructor for a ServiceBeginEvent.
   *
   * @param time        The time this event occurs.
   * @param cust        The customer associated with this
   *                    event
   * @param counter     The counter associated with this 
   *                    event.
   * @param available   The indicator of which counter is
   *                    available.
   */
  public ServiceBeginEvent(Customer cust, Counter counter, boolean[] available) {
    super(cust.getCurrTime());
    this.cust = cust; //type Customer
    this.counter = counter; //type Counter
    this.available = available; //type boolean[]
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
    str = String.format(": Customer %d service begin (by Counter %d)", this.cust.getCustID(), this.counter.getCounterID());
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
    //The current event is a service-begin event.
    //Mark the counter as unavailable, then schedule
    //a service-end event at the current time + service time.
    int currCounterID = counter.getCounterID();
    double serviceTime = cust.getServTime();
    this.available[currCounterID] = false;
    this.counter.setAvail(false); //set the availability of the counter to false
    double endTime = this.cust.getCurrTime() + serviceTime;
    this.cust.setCurrTime(endTime);
    Event serveEnd = new ServiceEndEvent(this.cust, this.counter, this.available);
    return new Event[] {serveEnd};

  }
}
