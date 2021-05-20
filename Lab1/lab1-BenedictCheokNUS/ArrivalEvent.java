/**
 * This class encapsulates the arrival event in the shop
 * simulation.  Your task is to replace this
 * class with new classes, following proper OOP principles.
 *
 * @author Benedict Cheok Wei En (B03), A0199433U
 * @version CS2030 AY20/21 ST1
 */
class ArrivalEvent extends Event {

  //ATTRIBUTES

  //This is the customer for the arrival event
  private Customer cust;

  //This is the list of counters in the shop  
  private Counter[] counters; //The list of counters in the shop for the SERVICE_...portion.

  /**
   * An array to indicate if a service counter is
   * available.  available[i] is true if and only
   * if service counter i is available to serve.
   */
  private boolean[] available;

  //CONSTRUCTOR METHOD

  /**
   * Constructor for Arrival event.
   *
   * @param time        The time associated with this event (the arrival time of customer).
   * @param cust        The customer object associated with this event.
   * @param counters    The array of counter objects in the shop.
   * @param available   The indicator of which counter is
   *                    available.
   * @param arrivalTime The arrival time of the customer obtained from the customer object. This is to be passed to the super class.
   */
  public ArrivalEvent(Customer cust, Counter[] counters, boolean[] available) {
    super(cust.getArrTime());

    this.cust = cust; //type Customer
    this.counters = counters; //type Counter array
    this.available = available; //type boolean
  }
  
  //METHODS
  /**
   * Returns the string representation of the arrival event,
   * depending on the type of event.
   *
   * @return A string representing the arrival event.
   */
  @Override
  public String toString() {
    String str = "";
    str = String.format(": Customer %d arrives", this.cust.getCustID()); //get the customer's ID and return it.   
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
    for (int i = 0; i < this.available.length; i++) {
      if (this.available[i]) {
        counterNo = i;
        break;
      }
    }

    if(counterNo == -1) { //no free counters
      Event custDepart = new DepartureEvent(this.cust); //Handover customer to the departure usher.
      return new Event[] {custDepart};
    } else { //got counter. Start ServiceBeginEvent by handing over the customer to the specific service counter
      Event serveCust = new ServiceBeginEvent(this.cust, this.counters[counterNo], this.available);
      return new Event[] {serveCust};
    }
  }   
}
