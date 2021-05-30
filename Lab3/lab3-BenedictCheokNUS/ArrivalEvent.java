/**
 * This class encapsulates the arrival event in the shop
 * simulation.  Your task is to replace this
 * class with new classes, following proper OOP principles.
 *
 * @author Benedict Cheok Wei En (B03), A0199433U
 * @version CS2030 AY20/21 ST1
 */
class ArrivalEvent extends Event {

  /**ATTRIBUTES*/
  
  //This is the shop
  private Shop shop;
  //This is the customer for the arrival event
  private Customer cust;

  /**CONSTRUCTOR METHOD*/

  /**
   * Constructor for Arrival event.
   *
   * @param time        The time associated with this event (the arrival time of customer).
   * @param shop        The shop.
   * @param cust        The customer object associated with this event
   *
   */
  public ArrivalEvent(Customer cust, Shop shop) {
    super(cust.getArrTime()); 
    this.shop = shop; //type Shop
    this.cust = cust; //type Customer
  }
  
  /**METHODS*/
  
  /**
   * Returns the string representation of the arrival event,
   * depending on the type of event.
   *
   * @return A string representing the arrival event.
   */
  @Override
  public String toString() {
    String str = "";
    str = String.format(": %s arrived %s", this.cust, this.shop.getQueue());
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
    //NEW SIMULATE STRUCTURE

    int counterNo = this.shop.getAvailCounterID(); //Return a free counter
    if (counterNo == -1) { //No Free Counters
      //return counter no with shortest queue or smallest ID if queues same len
      int counterQNo = this.shop.queueAtCounter(); //Returns the counter with avail q slots
      if (counterQNo == -1) { //Counter queues are full
        return new Event[] {this.qorDepart()};
      } else { //got counter with incomplete queues
        Counter qatCounter = this.shop.getThatCounter(counterQNo);
        Event joinCounterQueue = new JoinCounterQEvent(this.cust, qatCounter);
        return new Event[] {joinCounterQueue};
      }
    //smth feels off about the statements after...
    } else if (this.shop.isQueueEmpty() == false) { 
      //got free counters but there is someone queuing at entrance alr
      return new Event[] {this.qorDepart()};
    //So, there is a free counter, and entrance queue nobody. 
    //But what if counter queue got ppl, and counter is free?
    //This scenario should not happen. DepartureEvent handles this
    //From DepartureEvent: when customer departs, the counter is free for a moment,
    //A customer from the counter queue immediately moves to the counter
    //And the counter is no longer free again. 
    } else { //entrance queue is empty and there is free counter
      Counter freeCounter = this.shop.getThatCounter(counterNo);
      Event serveCust = new ServiceBeginEvent(this.cust, freeCounter, this.shop);
      return new Event[] {serveCust};
    }
  }
  
  //This method is to determine whether the customer should be handed over to queue usher 
  //or to the departure usher, depending on whether the queue is full or not.
  private Event qorDepart() {
    if (this.shop.isQueueFull()) {
      //queue is full
      //then customer deng chu
      Event custDepart = new DepartureEvent(this.cust, this.shop); //Handover customer to departure
      return custDepart;    
    } else { //entrance queue is not full
      //then customer handover to queue usher
      Event queueCust = new QueueEvent(this.cust, this.shop);
      return queueCust; //Customer joins queue if and only if queue is not full. 
    }
  }
}
