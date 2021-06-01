/** 
 *  CS2030 2020/21 ST1
 *  Lab 4: Box
 *  File Name: DivisibleBy.java
 *  Description: Class DivisibleBy implements BooleanCondition<Integer>
 *  It checks if given integer is divisible by another integer
 *  The test() method returns boolean
 *  If divisible, then true
 *  Else (not divisible), then false
 *
 *  @author Benedict Cheok Wei En (B03), A0199433U
 *
 */

class DivisibleBy implements BooleanCondition<Integer> {
  
  /**ATTRIBUTES*/

  private Integer divisor;

  /**CONSTRUCTOR METHOD*/
  
  public DivisibleBy(Integer newInt) {
    this.divisor = newInt;
  }

  /**METHODS*/

  @Override
  public boolean test(Integer testThat) {
    //Check if remainder 0. If remainder 0, then it is divisible
    return ((testThat % this.divisor) == 0);
  }
}
