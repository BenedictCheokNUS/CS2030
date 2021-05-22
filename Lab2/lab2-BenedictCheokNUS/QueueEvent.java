/**
 * This class encapsulates QueueEvent in the shop
 * simulation. QueueEvent puts the customer into the queue.
 * Your task is to replace this class with new classes, 
 * following proper OOP principles.
 *
 * @author Benedict Cheok Wei En (B03), A0199433U
 * @version CS2030 AY20/21 ST1
 */
class QueueEvent extends Event {
  
  /**ATTRIBUTES*/

  //The Shop
  private Shop shop;
  //The customer to be added into the queue. 
  private Customer cust;
  //The queue of the shop
  private Queue shopQueue;
  
  /**CONSTRUCTOR METHOD*/
  /**
   * Constructor for a shop event.
   *
   * @param time        The time this event occurs.
   * @param cust        The customer associated with this
   *                    event.
   * @param shop        The shop.
   * @param shopQueue   The queue for the shop.
   */
  public QueueEvent(Customer cust, Shop shop) {
    super(cust.getCurrTime());
    this.cust = cust;
    this.shop = shop;
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
    str = String.format(": %s joined queue %s", this.cust, this.shopQueue); 
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
    this.shopQueue.enq(this.cust);
    return new Event[] {};
  }
}
