/**
 * CS2030 2020/21 ST1
 * Lab 4: Box
 * File Name: Box.java
 * Description: This is the generic wrapper class Box 
 * that can take a parameter type T
 * Attributes: T content,
 * Methods: Box (private constructor),
 *
 * @author Benedict Cheok Wei En (B03), A0199433U
 *
 */

class Box<T> {
  
  /**ATTRIBUTES*/
  
  private final T content;
  //Box<?> EMPTY_BOX = new Box<>(null) --> if ? not defined as any type parameter
  //then by type erasure and inference
  //Box<Object> EMPTY_BOX = new Box<Object>(null)
  //Type would be Object type (this is the most specific type able to be inferred
  private static final Box<?> EMPTY_BOX = new Box<>(null);

  /**CONSTRUCTOR METHODS*/
  
  private Box(T newContent) {
    this.content = newContent;
  }
  
  /**FACTORY METHOD*/
  
  //This is the factory method of, that takes in an argument of type I
  //Returns the Box with parameter type I
  //If argument is null, then return null. 
  public static <I> Box<I> of(I itemPutInB) {
    if (itemPutInB == null) {
      return null;
    } else {
      return new Box<I>(itemPutInB);
    }
  }
  
  //This is the factory method ofNullable() that takes in an argument of type I
  //Returns a Box with paramter type I
  //If argument is null, return empty box
  public static <I> Box<I> ofNullable(I itemPutInB) {
    //It is like the .of() method,
    //but this time instead of returning null if itemPutInB is null
    //this factory method would return an empty box instead.
    if (itemPutInB == null) {
      return empty();
    } else { //itemPutInB not null at all
      Box<I> newBox = of(itemPutInB);
      return newBox;
    }
  }

  /**METHODS*/
  
  public static <E> Box<E> empty() {
    //to call the function...
    //.<type of box e.g. Integer..>empty()
    //We know that EMPTY_BOX is a Box by definition
    //And EMPTY_BOX has a wildcard for parameter type
    //Therefore, it is safe to cast EMPTY_BOX to a Box with any specified type para
    @SuppressWarnings("unchecked")
    Box<E> emptyBox = (Box<E>) EMPTY_BOX;
    return emptyBox;
  }

  public boolean isPresent() {
    //returns true if THIS box contains smth
    //returns false if this box contains nth (empty box)
    Box<T> seeBox = new Box<T>(this.content);
    if (seeBox.equals(this.EMPTY_BOX)) {
      return false;
    } else {
      //box contains smth
      return true;
    }
  }
  
  //filter() will take in a BooleanCondition type that has a method test(),
  //if true, return the box untouched
  //else, return empty box
  public Box<T> filter(BooleanCondition<? super T> condition) {
    
    if (this.isPresent() == true) { //Box is not empty
      if (condition.test(this.content)) { //test returns true
        return this;
      } else { //test returns false
        return empty();
      }
    } else { //box is empty
      return empty();
    }
  }

  //map() takes in a Transformer type
  //Using the Transformer type, it transform the box and values inside
  //Ito another box of type Box<U>
  //So Box<T> --> Box<U>
  //If empty box, return empty box
  public <U> Box<U> map(Transformer<? super T, ? extends U> transformer) {
    //Transformer method transform() accepts variable content in type T
    //transform() produces variable of type U
    if (this.isPresent() == true) { //Not empty box
      return (Box<U>) ofNullable(transformer.transform(this.content));  
    } else { //empty box
      return empty();
    }
  }


  /**OVERRIDING METHODS*/

  @Override
  public boolean equals(Object b) {
    //Function takes in type Object first 
    //as we do not know if the object is really a Box or not
    //checks the content of both boxes
    //if content of both boxes are the same, return true.
    //else return false
    
    if (b instanceof Box) {  
      //We have checked that b is of type Box
      //Therefore, it is safe to case b into an object Box with type parameter T
      @SuppressWarnings("unchecked")
      Box<T> temp = (Box<T>) b; //Cast to Box<T> type
      if (this.content == null) {
        //We must check if content is null first.
        //If null, null.equals() will throw null pointer exception
        return this.content == temp.content;
      } else { //not null. safe to use .equals()
        return this.content.equals(temp.content);
      }
    } else { //b is not instanceof Box --> not equal
      return false;
    }
  }

  @Override
  public String toString() {
    String str = "";
    if (this.content != null) {
      str = this.content.toString();
    }
    return String.format("[%s]", str);
  }
}
