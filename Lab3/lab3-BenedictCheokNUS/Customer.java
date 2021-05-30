/**
 * CS2030 2020/21 ST1
 * Lab 3: Simulation 3
 * File: Customer.java
 * Description: This file contains the Customer class
 * Attributes of Customer class: custID, custTime, currTime, assignedCounterID
 * Methods of Customer class: Customer (Constructor), getCustID, getArrTime, getServTime,
 * getCurrTime, setCurrTime, getAssCounter, setAssCounter, toString
 * @author Benedict Cheok Wei En (B03), A0199433U
 */

public class Customer {
  
  /**ATTRIBUTES*/
  
  private int custID;
  //private double[] custTime;
  private double arrTime = 0;
  private double servTime = 0;
  private double currTime = 0;
  private int assignedCounterID = -1; //Assigned counter remains as -1 if never get served.

  /**CONSTRUCTOR METHOD*/
  
  //Constructor will take in the Customer ID (called custID) 
  //and the array {Arrival Time, Service Time} (called custTime)
  public Customer(int custID, double[] custTime) {
    this.custID = custID;
    this.arrTime = custTime[0];
    this.servTime = custTime[1];
    this.currTime = custTime[0]; //Initialise current time as the arrival time.
  }

  /**ACCESSOR METHODS*/

  public int getCustID() {
    return this.custID;
  }

  public double getArrTime() { 
    return this.arrTime;
  }

  public double getServTime() {
    return this.servTime;
  }

  public double getCurrTime() {
    return this.currTime;
  }
  
  public int getAssCounter() {
    return this.assignedCounterID; 
  }

  /**MODIFIER METHODS*/
  
  public void setCurrTime(double newTime) {
    this.currTime = newTime;
  }

  public void setAssCounter(int newCounterID) {
    this.assignedCounterID = newCounterID;
  }

  /**toString METHOD*/
  @Override
  public String toString() {
    String str = "C" + this.custID;
    return str;
  }
}
