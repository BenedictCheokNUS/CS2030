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
  
  /**CONSTRUCTOR METHOD*/
  /**
   * Constructor for DepartureEvent.
   *
   * @param time       The time this event occurs.
   * @param shop       The shop
   * @param cust       The customer associated with this
   *                   event.
   */
  public DepartureEvent(Customer cust, Shop shop) {
    super(cust.getCurrTime());
    this.shop = shop;
    this.cust = cust;
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
    } else { //Customer departs due to a full shop queue. not served at all
      return new Event[] {};
    }
  }
  
  /**
   * @return Event[] with appropriate Event depending on if queue is empty
   * 
   */
  private Event[] nextCustomer() {
    int availCounterID = this.cust.getAssCounter(); //counter now empty as customer just left.
    Counter availCounter = this.shop.getThatCounter(availCounterID);
    
    if (availCounter.getCounterQsize() == 0) { //counter no queue
      if (this.shop.isQueueEmpty() == false) { //Entrance queue got ppl
        Customer nextCustomer = this.shop.nextCustomerPls();
        nextCustomer.setCurrTime(this.getTime());
        Event serveCust = new ServiceBeginEvent(nextCustomer, availCounter, this.shop);
        return new Event[] {serveCust};
      } else { //Shop entrance queue is empty. no more customer
        return new Event[] {};
      }
    } else { //counter queue max length not equals 0
      if (availCounter.isQueueEmpty() == false) { //there is someone waiting at counter queue
        Customer nextCounterCust = availCounter.counterNextCustomer(); 
        nextCounterCust.setCurrTime(this.getTime());
        Event serveCounterCust = new ServiceBeginEvent(nextCounterCust, availCounter, this.shop);
        if (this.shop.isQueueEmpty() == false) { //shop entrance queue got people.
          Customer nextToCounterQ = this.shop.nextCustomerPls();
          nextToCounterQ.setCurrTime(this.getTime());
          Event joinCounterQ = new JoinCounterQEvent(nextToCounterQ, availCounter);
          return new Event[] {joinCounterQ, serveCounterCust};
        } else { //shop entrance queue is empty
          return new Event[] {serveCounterCust};
        }
      } else { //Counter queue empty. No more customers
        return new Event[] {};
      }
    }
  }
}
