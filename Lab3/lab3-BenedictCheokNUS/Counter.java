/**
 * CS2030 2020/21 ST1
 * Lab 3: Simulation 3
 * File: Counter.java
 * Description: This file contains the class Counter for the service counters in the shop
 * Attributes: counterID, avail, counterQsize counterQueue
 * Methods: Counter (Constructor), getCounterID, getAvail, getQueue, getCounterQsize, 
 * setAvail, toString, compareTo
 * counterQueueJoin, isQueueEmpty, isQueueFull, counterNextCustomer
 * @author Benedict Cheok Wei En (B03), A0199433U
 */

public class Counter implements Comparable<Counter> {
  
  /**ATTRIBUTES*/
  
  private int counterID;
  private boolean avail;
  private int counterQsize;
  private Queue<Customer> counterQueue;

  /**CONSTRUCTOR METHOD*/
  
  public Counter(int counterID, boolean avail, int queueLen) {
    this.counterID = counterID;
    this.avail = avail;
    this.counterQsize = queueLen; //Stores the max length of the queue
    this.counterQueue = new Queue<Customer>(queueLen);
  }

  /**ACCESSOR METHODS*/
  
  public int getCounterID() {
    return this.counterID;
  }

  public boolean getAvail() {
    return this.avail;
  }

  public Queue<Customer> getQueue() {
    return this.counterQueue;
  }

  public int getCounterQsize() {
    return this.counterQsize;
  } 

  /**MODIFIER METHODS*/

  public void setAvail(boolean availStatus) {
    //availStatus is the new status to set for the attribute avail
    this.avail = availStatus;
  }
  
  /**toString METHOD*/
  @Override
  public String toString() {
    String str = "S" + this.counterID + " " + this.counterQueue;
    return str;
  }

  /**compareTo METHOD*/
  @Override
  public int compareTo(Counter thatCounter) {
    //return the difference in the length of counter queue.
    //this counterQueue length - that counter queue length =  difference
    int difference = this.counterQueue.length() - thatCounter.getQueue().length();
    if (difference != 0) { //means counter queues have diff length
      return difference; //compare by queue length
    } else { //means counter queue same, then compare via counterID
      int idDiff = this.counterID - thatCounter.getCounterID();
      return idDiff;
    }
  }

  /**OTHER METHODS*/
  public void counterQueueJoin(Customer newCust) { 
    //this methods enq the customer into the counter queue
    this.counterQueue.<Customer>enq(newCust);
  }

  public boolean isQueueEmpty() {
    return this.counterQueue.isEmpty();
  }

  public boolean isQueueFull() {
    return this.counterQueue.isFull();
  }

  public Customer counterNextCustomer() {
    return (Customer) this.counterQueue.deq();
  }
}
