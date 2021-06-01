/** 
 *  CS2030 2020/21 ST1
 *  Lab 4: Box
 *  File Name: LongerThan.java
 *  Description: Class LongerThan implements BooleanCondition<String>
 *  Checks if length of string greater than limit
 *  if string length greater than limit, return true
 *  else return false
 *
 *  @author Benedict Cheok Wei En (B03), A0199433U
 *
 */

class LongerThan implements BooleanCondition<String> {
  
  /**ATTRIBUTES*/

  private Integer strLimit;

  /**CONSTRUCTOR METHOD*/

  public LongerThan(Integer newLength) {
    this.strLimit = newLength;
  }
  
  /**METHOD*/

  @Override 
  public boolean test(String testThis) {
    return ((Integer) testThis.length() > this.strLimit);
  }
}
