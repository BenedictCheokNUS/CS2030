/**
 * This class encapsulates Departure event in the shop
 * simulation.  Your task is to replace this
 * class with new classes, following proper OOP principles.
 *
 * @author Benedict Cheok Wei En (B03), A0199433U
 * @version CS2030 AY20/21 ST1
 */
class DepartureEvent extends Event {
  
  /**ATTRIBUTES*/

  private Shop shop;
  private Customer cust;
  private Queue shopQueue;
  
  /**CONSTRUCTOR METHOD*/
  /**
   * Constructor for DepartureEvent.
   *
   * @param time       The time this event occurs.
   * @param shop       The shop
   * @param cust       The customer associated with this
   *                   event.
   * @param shopQueue  The queue for the shop.
   */
  public DepartureEvent(Customer cust, Shop shop) {
    super(cust.getCurrTime());
    this.shop = shop;
    this.cust = cust;
    this.shopQueue = this.shop.getQueue();
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
    str = String.format(": %s departed", this.cust);
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
    if (this.cust.getAssCounter() > -1) { //Customer has been served by one of the counters
      return this.nextCustomer();  
    } else { //Customer departs due to a full queue. not served at all
      return new Event[] {};
    }
  }
  
  /**
   * @return Event[] with appropriate Event depending on if queue is empty
   * 
   */
  private Event[] nextCustomer() {  
    int availCounterID = this.cust.getAssCounter(); //counter now empty as the customer just left.
    if (shopQueue.isEmpty() == false) { //there are customers waiting to be served...
      Customer nextCustomer = (Customer) this.shopQueue.deq();
      nextCustomer.setCurrTime(this.getTime());
      Counter[] counterList = this.shop.getCounterList();
      Event serveCust = new ServiceBeginEvent(nextCustomer, counterList[availCounterID], this.shop);
      return new Event[] {serveCust};
    } else { //queue is empty le. no more customers to serve.
      return new Event[] {};
    }
  }
}
