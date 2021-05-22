import java.util.Scanner;

/**
 * This class implements a shop simulation.
 *
 * @author Benedict Cheok Wei En (B03), A0199433U
 * @version CS2030 AY20/21 ST1
 */
class ShopSimulation extends Simulation {
  
  /**ATTRIBUTES*/

  /** 
   * The list of customer arrival events to populate
   * the simulation with.
   */
  private Event[] initEvents;
  // The number of customers
  private int numOfCustomers = 0;
  // The number of counters
  private int numOfCounters = 0;
  // 2D Array to store customer arrival and service times.
  private double[][] custTimeL;
  
  /**CONSTRUCTOR METHOD*/
  /** 
   * Constructor for a shop simulation. 
   *
   * @param sc A scanner to read the parameters from.  The first
   *           integer scanned is the number of customers; followed
   *           by the number of service counters.  Next is a 
   *           sequence of (arrival time, service time) pair, each
   *           pair represents a customer.
   */
  public ShopSimulation(Scanner sc) {
    this.numOfCustomers = sc.nextInt();
    this.numOfCounters = sc.nextInt();
    //Create customer timings
    this.custTimeL = createCustTimeL(sc, this.numOfCustomers);  
    //Initialise new shop
    Shop newShop = new Shop(this.numOfCustomers, this.numOfCounters, this.custTimeL);
    //Initialise initial events
    this.initEvents = createEvents(newShop);
  }

  /**METHODS*/

  public double[][] createCustTimeL(Scanner sc, int numOfCustomers) {
    double[][] tempTimeL = new double[numOfCustomers][2];
    
    //Preparing customer data for shop initialisation
    for (int id = 0; id < this.numOfCustomers; id++) {
      double arrivalTime = sc.nextDouble();
      double serviceTime = sc.nextDouble();
      tempTimeL[id][0] = arrivalTime;
      tempTimeL[id][1] = serviceTime;
    }
    return tempTimeL;
  }

  public Event[] createEvents(Shop newShop) {
    //This method assembles/creates the list of events for the simulation by initialising them.
    //Get list of customers
    //For each customer in customer list, get arrival time, customer ID, service time
    Customer[] custList = newShop.getCustomerList();
    Event[] tempinitEvents = new Event[custList.length];
    for (Customer cust : custList) {
      int custID = cust.getCustID();
      tempinitEvents[custID] = new ArrivalEvent(cust, newShop); //Handover customer to arrival
    }
    return tempinitEvents;  
  }

  /**ACCESSOR METHODS*/
  /**
   * Retrieve an array of events to populate the 
   * simulator with.
   *
   * @return An array of events for the simulator.
   */
  @Override
  public Event[] getInitialEvents() {
    return this.initEvents;
  }
}
