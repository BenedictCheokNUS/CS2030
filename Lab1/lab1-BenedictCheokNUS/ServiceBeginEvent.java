/**
 * This class encapsulates Service Begin event in the shop
 * simulation.  Your task is to replace this
 * class with new classes, following proper OOP principles.
 *
 * @author Benedict Cheok Wei En (B03), A0199433U
 * @version CS2030 AY20/21 ST1
 */
class ServiceBeginEvent extends Event {
  
  /**ATTRIBUTES*/
  
  // This is the shop
  private Shop shop;
  // This is the customer for the service event
  private Customer cust;
  // This is the counter associated with the service
  private Counter counter;
 
  /**CONSTRUCTOR METHOD*/
  /**
   * Constructor for a ServiceBeginEvent.
   *
   * @param time        The time this event occurs.
   * @param cust        The customer associated with this
   *                    event
   * @param counter     The counter associated with this 
   *                    event.
   *
   */
  public ServiceBeginEvent(Customer cust, Counter counter, Shop shop) {
    super(cust.getCurrTime());
    this.shop = shop; //type Shop
    this.cust = cust; //type Customer
    this.counter = counter; //type Counter
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
    str = String.format(": %s service begin (by %s)", this.cust, this.counter);
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
    this.counter.setAvail(false); //set the availability of the counter to false
    double endTime = this.cust.getCurrTime() + serviceTime;
    this.cust.setCurrTime(endTime);
    this.cust.setAssCounter(currCounterID);
    Event serveEnd = new ServiceEndEvent(this.cust, this.counter, this.shop);
    return new Event[] {serveEnd};
  }
}
