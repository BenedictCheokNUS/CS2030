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
  //This is the list of counters in the shop  
  private Counter[] counters; //The list of counters in the shop for the SERVICE_...portion.
  //This is the shop queue.
  private Queue shopQueue;

  /**CONSTRUCTOR METHOD*/

  /**
   * Constructor for Arrival event.
   *
   * @param time        The time associated with this event (the arrival time of customer).
   * @param shop        The shop.
   * @param cust        The customer object associated with this event.
   * @param counters    The array of counter objects in the shop.
   * @param shopQueue   The queue for the shop. If no counters are avail, 
   *                    put customer in queue to wait first.
   */
  public ArrivalEvent(Customer cust, Shop shop) {
    super(cust.getArrTime()); 
    this.shop = shop; //type Shop
    this.cust = cust; //type Customer
    this.counters = shop.getCounterList(); //type Counter array
    this.shopQueue = shop.getQueue(); //type Queue
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
    str = String.format(": %s arrived %s", this.cust, this.shopQueue);
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
    if (this.shopQueue.isEmpty()) {
      //queue is empty
      //then find for avail counter
      int counterNo = -1;
      //for loop to find an avail counter for the customer.
      for (int i = 0; i < this.counters.length; i++) {
        if (this.counters[i].getAvail()) {
          counterNo = i;
          break;
        }
      }
      if (counterNo == -1) { //if no free counters
        return new Event[] {this.qorDepart()}; 
      } else { //got free counter
        //then customer handover to service counter
        Event serveCust = new ServiceBeginEvent(this.cust, this.counters[counterNo], this.shop);
        return new Event[] {serveCust};
      }
    } else { //queue is not empty... got customer queueing alr
      return new Event[] {this.qorDepart()};
    }
  }
  
  //This method is to determine whether the customer should be handed over to queue usher 
  //or to the departure usher, depending on whether the queue is full or not.
  private Event qorDepart() {
    if (this.shopQueue.isFull()) {
      //queue is full
      //then customer deng chu
      Event custDepart = new DepartureEvent(this.cust, this.shop); //Handover customer to departure
      return custDepart;    
    } else { //queue is not full
      //then customer handover to queue usher
      Event queueCust = new QueueEvent(this.cust, this.shop);
      return queueCust; //Customer joins queue if and only if queue is not full. 
    }
  }
}
