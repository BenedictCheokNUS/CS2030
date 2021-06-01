/** 
 *  CS2030 2020/21 ST1
 *  Lab 4: Box
 *  File Name: LastDigitsOfHashCode.java
 *  Description: Class LastDigitsOfHashCode implements Transformer interface
 *  It will override the method transform()
 *  It will transform the content of the Box, into a Box<Integer>
 *  LastDigitsOfHashCode will take in a value k, 
 *  which would determine the last k digits of the hashcode of the content of the box
 *  to return
 *  Essentially, the content of the Box is transformed into a hash code
 *  Then the last k digits of the hash code is returned
 *  Use hashCode() which is defined in the class Object to get the hash code
 *
 *  @author Benedict Cheok Wei En (B03), A0199433U
 *
 */

class LastDigitsOfHashCode implements Transformer<Object, Integer> {
  
  /**ATTRIBUTES*/
  private Integer k;

  /**CONSTRUCTOR METHOD*/

  public LastDigitsOfHashCode(Integer lastKdigits) {
    this.k = lastKdigits;
  }

  /**METHODS*/

  @Override
  public Integer transform(Object content) {
    Integer newHashCode = (Integer) content.hashCode(); //Create hash code
    String hashCodeStr = newHashCode.toString(); //Convert to string
    int startIndex = hashCodeStr.length() - this.k; //cut away front portion
    String lastKdigitsStr = hashCodeStr.substring(startIndex); //Using substring() method
    Integer lastKdigitsOut = Integer.parseInt(lastKdigitsStr); //Convert back to Integer
    return lastKdigitsOut;
  }

}
