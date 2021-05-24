/**
 * CS2030 2020/21 ST1
 * Lab 2: Simulation 2
 * File Name: Shop.java
 * Description: This file contains the class Shop
 * Attributes: noOfCustomers, noOfCounters, queueLen, custTimeL, customerList, counterList, 
 * shopQueue
 * Methods: Shop (Constructor), getCounterList, getCustomerList, getQueue
 * @author Benedict Cheok Wei En (B03), A0199433U
 * 
 */

public class Shop {
  
  /**ATTRIBUTES*/

  private int noOfCustomers;
  private int noOfCounters;
  private int queueLen;
  //2D array. List of list of customer arrival and service times e.g. [[arr0, serv0], [arr1, serv1]]
  private double[][] custTimeL;
  private Customer[] customerList;
  private Counter[] counterList;
  private Queue shopQueue;

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
  private Counter[] createCounters(int noOfCounters) {
    //Initialise a temp list of length noOfCounters to store Counter objects
    Counter[] tempCounterList = new Counter[noOfCounters]; 
    boolean avail = true; //sets all the availability of the counters to TRUE
    for (int counterID = 0; counterID < noOfCounters; counterID++) { 
      Counter newCounter = new Counter(counterID, avail); //Calls constructor method of Counter
      tempCounterList[counterID] = newCounter; //adds new counter to the list of counters.
    }
    return tempCounterList;
  }

  /**MAIN CONSTRUCTOR METHOD*/

  public Shop(int noOfCustomers, int noOfCounters, double[][] custTimeL, int queueLen) {
    this.noOfCustomers = noOfCustomers;
    this.noOfCounters = noOfCounters;
    this.queueLen = queueLen;
    this.custTimeL = custTimeL;
    this.shopQueue = new Queue(queueLen);
    
    this.customerList = createCustomers(this.noOfCustomers, this.custTimeL); 
    this.counterList = createCounters(this.noOfCounters);
   
  }

  /**ACCESSOR METHODS*/

  public Customer[] getCustomerList() {
    return this.customerList;
  }
  
  public Counter[] getCounterList() {
    return this.counterList;
  }

  public int getAvailCounterID() {
    int counterNo = -1;
    //for loop to find and avail counter for the customer. 
    for (int i = 0; i < this.noOfCounters; i++) {
      if (this.counterList[i].getAvail()) {
        counterNo = i;
        break;
      }
    }
    return counterNo;
  }

  public Queue getQueue() {
    return this.shopQueue;
  }

}
