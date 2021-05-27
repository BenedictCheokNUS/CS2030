/**
 * CS2030 2020/21 ST1
 * Lab 2: Simulation 2
 * File: Counter.java
 * Description: This file contains the class Counter for the service counters in the shop
 * Attributes: Counter ID, Availability
 * Methods: Counter (Constructor), getCounterID, getAvail, setAvail
 * @author Benedict Cheok Wei En (B03), A0199433U
 */

public class Counter {
  
  /**ATTRIBUTES*/
  
  private int counterID;
  private boolean avail;

  /**CONSTRUCTOR METHOD*/
  
  public Counter(int counterID, boolean avail) {
    this.counterID = counterID;
    this.avail = avail;
  }

  /**ACCESSOR METHODS*/
  
  public int getCounterID() {
    return this.counterID;
  }

  public boolean getAvail() {
    return this.avail;
  }

  /**MODIFIER METHODS*/

  public void setAvail(boolean availStatus) {
    //availStatus is the new status to set for the attribute avail
    this.avail = availStatus;
  }
  
  /**toString METHOD*/
  @Override
  public String toString() {
    String str = "S" + this.counterID;
    return str;
  }
}
