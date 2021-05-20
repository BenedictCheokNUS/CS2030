/**
 * CS2030 2020/21 ST1
 * Lab 1: Simulation 1
 * File: Customer.java
 * Description: This file contains the Customer class
 * Attributes of Customer class: custID, custTime, arrTime, servTime
 * Methods of Customer class: Customer (Constructor), getCustID, getArrTime, getServTime
 * @author Benedict Cheok Wei En (B03), A0199433U
 */




public class Customer {
  
  //ATTRIBUTES
  private int custID;
  private double[] custTime;
  private double currTime = 0;

  //CONSTRUCTOR METHOD
  //Constructor will take in the Customer ID (called custID) and the array {Arrival Time, Service Time} (called custTime)
  public Customer(int custID, double[] custTime) {
    this.custID = custID;
    this.custTime = custTime;
    this.currTime = custTime[0]; //Initialise current time as the arrival time.
  }

  //ACCESSOR METHODS
  public int getCustID() {
    return this.custID;
  }

  public double getArrTime() { 
    return this.custTime[0];
  }

  public double getServTime() {
    return this.custTime[1];
  }

  public double getCurrTime() {
    return this.currTime;
  }

  //Modifier Methods
  public void setCurrTime(double newTime) {
    this.currTime = newTime;
  }

}
