/**
 * CS2030 2020/21 ST1
 * Lab 1: Simulation 1
 * File Name: Shop.java
 * Description: This file contains the class Shop
 * Attributes: noOfCustomers, noOfCounters, custTimeL, counterList, customerList
 * Methods: Shop (Constructor), getCounterList, getCustomerList, getAvailCounters
 * @author Benedict Cheok Wei En (B03), A0199433U
 * 
 */

public class Shop {
  
  //ATTRIBUTES
  private int noOfCustomers;
  private int noOfCounters;
  private double[][] custTimeL; //2D Array. List of list of arrival and service time of customer. e.g. [[arr0, serv0], [arr1, serv1],...]
  private Customer[] customerList;
  private Counter[] counterList;
  
  //METHODS

  //createCustomers Method to initialise all the customers.
  //createCustomers take in noOfCustomers, and custTime, init all customers and add to customerList
  private Customer[] createCustomers(int noOfCustomers, double [][] custTimeL) {
    //System.out.println("Creating Customers...");
    Customer[] tempCustomerList = new Customer[noOfCustomers]; //Initialise a temp list of length noOfCustomers to store Customer objects
    for (int custID = 0; custID < noOfCustomers; custID++) {
      double[] custTime = new double[custTimeL[custID].length];
      custTime = custTimeL[custID];
      Customer newCustomer = new Customer(custID, custTime);
      tempCustomerList[custID] = newCustomer;
    }
    //System.out.println("Customers Created!");
    return tempCustomerList;
  }

 
  //createCounters Method to Initialise all the counters. 
  //createCoutners take in noOfCounters, init all counters and add to counterList
  private Counter[] createCounters(int noOfCounters) {
    //System.out.println("Creating Counters...");
    Counter[] tempCounterList = new Counter[noOfCounters]; //Initialise a temp list of length noOfCounters to store Counter objects
    boolean avail = true; //sets all the availability of the counters to TRUE
    for (int counterID = 0; counterID < noOfCounters; counterID++) { 
      Counter newCounter = new Counter(counterID, avail); //Calls constructor method of Counter
      tempCounterList[counterID] = newCounter; //adds new counter to the list of counters in the shop.
    }
    //System.out.println("Counters created!");
    return tempCounterList;
  }

  //MAIN CONSTRUCTOR METHOD
  public Shop(int noOfCustomers, int noOfCounters, double[][] custTimeL) {
    //System.out.println("=".repeat(50));
    //System.out.println("Initialising Shop...");

    this.noOfCustomers = noOfCustomers;
    this.noOfCounters = noOfCounters;
    this.custTimeL = custTimeL;
    
    this.customerList = createCustomers(this.noOfCustomers, this.custTimeL); 
    this.counterList = createCounters(this.noOfCounters);
   
  }

  //ACCESSOR METHODS

  public Customer[] getCustomerList() {
    //System.out.println("Customers: " + this.customerList);
    return this.customerList;
  }
  
  public Counter[] getCounterList() {
    //System.out.println("Counters: " + this.counterList);
    return this.counterList;
  }

  public boolean[] getAvailCounters() { 
    //This method searches all the counters and finds all available counters.
    //It then puts the IDs of all the avail counters into a list and returns it for the customer's choice.
    boolean[] tempAvail = new boolean[this.counterList.length];
    int noOfAvailCounters = 0;
    for (int i = 0; i < this.counterList.length; i++) {
      Counter currCounter = counterList[i];
      tempAvail[i] = currCounter.getAvail();
     
    }
    
    return tempAvail;
  }

}
