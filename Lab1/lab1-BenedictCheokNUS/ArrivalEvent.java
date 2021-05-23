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
  
  //This is the shop.
  private Shop shop;
  //This is the customer for the arrival event
  private Customer cust;
  //This is the list of counters in the shop  
  private Counter[] counters; //The list of counters in the shop for the SERVICE_...portion.

  /**CONSTRUCTOR METHOD*/
  /**
   * Constructor for Arrival event.
   *
   * @param time        The time associated with this event (the arrival time of customer).
   * @param shop        The shop.
   * @param cust        The customer object associated with this event.
   * @param counters    The array of counter objects in the shop.
   *
   */

  public ArrivalEvent(Customer cust, Shop shop) {
    super(cust.getArrTime());
    this.shop = shop; //type Shop
    this.cust = cust; //type Customer
    this.counters = this.shop.getCounterList(); //type Counter array
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
    str = String.format(": %s arrives", this.cust); //get the customer's ID and return it.   
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
 
    int counterNo = -1;
    //for loop to find an available counter for the customer.
    for (int i = 0; i < this.counters.length; i++) {
      if (this.counters[i].getAvail()) {
        counterNo = i;
        break;
      }
    }

    if (counterNo == -1) { //no free counters
      Event custDepart = new DepartureEvent(this.cust, this.shop); //Handover to departure
      return new Event[] {custDepart};
    } else { //got counter.
      Event serveCust = new ServiceBeginEvent(this.cust, this.counters[counterNo], this.shop);
      return new Event[] {serveCust};
    }
  }   
}
