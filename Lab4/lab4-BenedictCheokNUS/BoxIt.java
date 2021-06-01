/** 
 *  CS2030 2020/21 ST1
 *  Lab 4: Box
 *  File Name: BoxIt.java
 *  Description: Class BoxIt implements interface Transformer
 *  It transform an item in the box, into a box containing the item
 *  So from [item] to [[item]]
 *  And so from type T to Box<T>
 *  When invoked with map(), it will result in a box within the box.
 *
 *  @author Benedict Cheok Wei En (B03), A0199433U
 *
 */

class BoxIt<T> implements Transformer<T, Box<T>> {
  
  /**CONSTRUCTOR METHOD*/

  public BoxIt() {
  }

  /**METHOD*/

  @Override
  public Box<T> transform(T content) {
    return Box.ofNullable(content);
  }
}
