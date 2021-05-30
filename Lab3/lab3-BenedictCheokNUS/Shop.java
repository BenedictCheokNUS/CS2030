/**
 * CS2030 2020/21 ST1
 * Lab 3: Simulation 3
 * File Name: Shop.java
 * Description: This file contains the class Shop
 * Attributes: noOfCustomers, noOfCounters, counterQueueLen, queueLen, custTimeL, 
 * customerList, counterList, shopQueue
 * Methods: Shop (Constructor), getCounterList, getCustomerList, getAvailCounterID,
 * getThatCounter, getQueue, isQueueEmpty, isQueueFull, nextCustomerPls, custInQueue, queueAtCounter
 * @author Benedict Cheok Wei En (B03), A0199433U
 * 
 */

public class Shop {
  
  /**ATTRIBUTES*/

  private int noOfCustomers;
  private int noOfCounters;
  private int counterQueueLen; //Length of Counter Queues
  private int queueLen;
  //2D array. List of list of customer arrival and service times e.g. [[arr0, serv0], [arr1, serv1]]
  private double[][] custTimeL;
  private Customer[] customerList;
  private Array<Counter> counterList;
  //very impt to define Queue with parameter type <Customer>
  //Else, java will just Queue with parameter type <T> (RAW TYPE)
  private Queue<Customer> shopQueue; 

  /**METHODS*/

  //createCustomers Method to initialise all the customers.
  //createCustomers take in noOfCustomers, and custTime, init all customers and add to customerList
  private Customer[] createCustomers(int noOfCustomers, double [][] custTimeL) {
    //Initialise a temp list of length noOfCustomers to store Customer objects
    Customer[] tempCustomerList = new Customer[noOfCustomers]; 
    for (int custID = 0; custID < noOfCustomers; custID++) {
      double[] custTime = new double[custTimeL[custID].length];
      custTime = custTimeL[custID];
      Customer newCustomer = new Customer(custID, custTime);
      tempCustomerList[custID] = newCustomer;
    }
    return tempCustomerList;
  }

 
  //createCounters Method to Initialise all the counters. 
  //createCoutners take in noOfCounters, init all counters and add to counterList
  private Array<Counter> createCounters(int noOfCounters, int counterQueueLen) {
    //Initialise a temp list of length noOfCounters to store Counter objects
    Array<Counter> tempCounterList = new Array<Counter>(noOfCounters); 
    boolean avail = true; //sets all the availability of the counters to TRUE
    for (int counterID = 0; counterID < noOfCounters; counterID++) {
      //Call constructor method of counter
      Counter newCounter = new Counter(counterID, avail, counterQueueLen);  
      tempCounterList.set(counterID, newCounter); //adds new counter to the list oc counters
      //tempCounterList[counterID] = newCounter; //adds new counter to the list of counters.
    }
    return tempCounterList;
  }

  /**MAIN CONSTRUCTOR METHOD*/

  public Shop(int noOfCustomers, int noOfCounters, double[][] custTimeL, 
      int queueLen, int counterQueueLen) {
    
    this.noOfCustomers = noOfCustomers;
    this.noOfCounters = noOfCounters;
    this.queueLen = queueLen;
    this.counterQueueLen = counterQueueLen;
    this.custTimeL = custTimeL;
    this.shopQueue = new Queue<Customer>(queueLen); //Okay. Parameterised Type Queue
    
    this.customerList = createCustomers(this.noOfCustomers, this.custTimeL); 
    this.counterList = createCounters(this.noOfCounters, this.counterQueueLen);
   
  }

  /**ACCESSOR METHODS*/

  public Customer[] getCustomerList() {
    return this.customerList;
  }
  
  public Array<Counter> getCounterList() {
    return this.counterList;
  }

  public int getAvailCounterID() {
    int counterNo = -1;
    //for loop to find and avail counter for the customer. 
    for (int i = 0; i < this.noOfCounters; i++) {
      if (this.counterList.get(i).getAvail()) {
        counterNo = i;
        break;
      }
    }
    return counterNo;
  }

  public Counter getThatCounter(int counterID) {
    //Input is the counter ID
    //It then returns the Counter object with that counterID
    return this.counterList.get(counterID);
  }

  public Queue<Customer> getQueue() {
    return this.shopQueue;
  }

  /**OTHER METHODS*/

  public boolean isQueueEmpty() {
    return this.shopQueue.isEmpty();
  } 

  public boolean isQueueFull() {
    return this.shopQueue.isFull();
  }

  public Customer nextCustomerPls() {
    return (Customer) this.shopQueue.deq();
  }

  public void custInQueue(Customer newCust) {
    this.shopQueue.<Customer>enq(newCust); 
  }

  public int queueAtCounter() {
    int counterQNo = -1;
    Counter queueThisCounter = this.counterList.min(); //counter returned may have a full queue
    if (queueThisCounter.getQueue().isFull() == false) { //the queue has slots avail
      counterQNo = queueThisCounter.getCounterID();
    }
    //else, counter queue is full...
    //if counter queue is full, 
    //  then means that all other counter queues must be same length or longer than this
    //  but all counter queues has same max length
    //  therefore, all counter queues are full.
    return counterQNo;
  }
}
