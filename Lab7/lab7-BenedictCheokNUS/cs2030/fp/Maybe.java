
package cs2030.fp;

import java.util.NoSuchElementException;

/**
 *  CS2030 2020/21 ST1
 *  Lab 7: Infinite List
 *  File Name: Maybe.java
 *  Description: Maybe with param T is an option type, which is a wrapper around a value that
 *  might be missing
 *  It represents either some value, or none
 *  Maybe with param T is an abstract class
 *  Attributes: NONE
 *  Nested Classes: None, Some
 *  Factory Methods: none(), some(), of()
 *  Other Methods: protected get(), filter(), map(), flatMap(), orElse(), orElseGet()
 *
 *  @param <T>                The type variable of the class
 *
 *  @author Benedict Cheok Wei En (B03), A0199433U
 *  @version CS2030 2020/21 ST1
 */
public abstract class Maybe<T> {

  /**
   * ATTRIBUTE: NONE
   * This is the none Maybe object
   */
  private static final Maybe<Object> NONE = new None();
 
  /**
   * STATIC FINAL NESTED CLASS: Some
   * This is the static final nested class that inherits Maybe class
   * This child class of Maybe is for a Maybe object with a content inside
   *
   * @param <T>               The type variable of the Some class.
   */
  private static final class Some<T> extends Maybe<T> {
    //ATTRIBUTES
    /**
     * NESTED CLASS ATTRIBUTE: content
     * This attribute stores the value of the Maybe object. 
     */
    private T content;

    //CONSTRUCTOR METHOD for class Some
    /**
     * NESTED CLASS CONSTRUCTOR METHOD: Some()
     * This is the constructor method for the Some class.
     * Returns nothing. It instantiates a Some object.
     *
     * @param t               The value to be instantiated into the Some object.
     */
    public Some(T t) {
      this.content = t;
    }

    //toString METHOD for class Some
    /**
     * NESTED CLASS METHOD: toString()
     * This method returns a string representation of the Some object.
     *
     * @return String         The string representation of the Some object.
     */
    @Override
    public String toString() {
      String str = "";
      str = String.format("[%s]", this.content);
      return str;
    }
    
    //equals METHOD for class Some
    /**
     * NESTED CLASS METHOD: equals(Object)
     * This method returns true if the argument is of the same type as Some, and 
     * contains the same content as this instantiation of some.
     *
     * @param s               This is the object to be compared with.
     * @return boolean        The method returns either true or false.
     */
    @Override
    public boolean equals(Object s) {
      //check if object is instance of Some
      //  Cast the object into type Some
      //  check if this.content == null
      //    return this.content == object.content; 
      //    (null object cannot use .equals() method)
      //  else (this.content != null)
      //    return this.content.equals(object.content)
      //else (object is not instance of Some)
      //  return false

      if (s instanceof Some) {
        //We have checked if s is of type Some
        //Therefore it is safe to cast s into an object Some with param T
        @SuppressWarnings("unchecked")
        Some<T> temp = (Some<T>) s; //Cast to Some<T> type
        if (this.content == null) {
          return this.content == temp.content;
        } else { //this.content != null
          return this.content.equals(temp.content);
        }
      } else { //s is not instance of Some
        return false;
      } 
    }

    //OTHER METHODS for class Some
    /**
     * NESTED CLASS METHOD: get()
     * This method returns the content of the Some object.
     *
     * @return T              The value of the content stored in the Some object
     *                        and is of type variable T.
     */
    @Override
    protected T get() {
      return this.content;
    }
    
    /**
     * NESTED CLASS METHOD: filter(BooleanCondition)
     * This checks if this Some object fulfils the test condition. If it passes, 
     * then the Some object if returned, else a none object is returned.
     *
     * @param testCond        The testing condition to be applied on the Some
     *                        object, and is of type BooleanCondition
     * @return Maybe          The method returns a Maybe object (or its subtypes)
     */
    @Override
    public Maybe<T> filter(BooleanCondition<? super T> testCond) {
      if ((this.get() != null) && (testCond.test(this.get()) == false)) {
        return none();
      } else { //content == null OR testCond.test(this.content) TRUE by De Morgan's Law
        return this;
      }
    }

    /**
     * NESTED CLASS METHOD: map(Transformer)
     * This method maps the value of the Some object to a function.
     *
     * @param <U>             The type variable of the return Maybe object.
     * @param trans           This is the Transformer function to map the content
     *                        of the Some object
     * @return Maybe          The method returns a Maybe object of type U.
     */
    @Override
    public <U> Maybe<U> map(Transformer<? super T, ? extends U> trans) {
      return some(trans.transform(this.get()));
    }

    /**
     * NESTED CLASS METHOD: flatMap(Transformer)
     * This method flat maps the value of the Some object to a function.
     *
     * @param <U>             The type variable of teh return Maybe object
     * @param trans           This is the Transformer function to map the content 
     *                        of the Some object.
     * @return Maybe          The method returns a Maybe object of type U.
     */
    @Override
    public <U> Maybe<U> flatMap(Transformer<? super T, ? extends Maybe<? extends U>> trans) {
      //trans.transform() returns a Maybe<> object for U to Maybe<U> (like wordToMaybeInt)
      //flatMap should only be used for wordToMaybeInt kind of transformation
      //When this flatMap() is called, this is an instance of Some
      //return trans.transform(this.get());
      
      //We know that map() function returns a Maybe<Maybe<U>> object for wordToMaybeInt
      //As such it is safe to suppress warning
      
      @SuppressWarnings("unchecked")
      Maybe<U> transMayb = (Maybe<U>) this.map(trans).get();
      return transMayb;
    }
    
    /**
     * NESTED CLASS METHOD: orElse(value)
     * This method will return the content of the Some object. 
     *
     * @param <U>             The type variable of the value to be returned if 
     *                        there is no content. 
     * @param value           The value to return if it is not a Some object
     *                        (no content)
     * @return T              The method returns the content of type T.
     */
    @Override
    public <U extends T> T orElse(U value) {
      return this.content;
    }

    /**
     * NESTED CLASS METHOD: orElseGet(Producer)
     * This method returns the content of the Some object.
     *
     * @param <U>             The type parameter of the value to be produced by
     *                        the producer if there is no content.
     * @param producer        The producer to be produced if there is no content.
     * @return T              The method returns the content of the Some object
     *                        of type T
     */
    @Override 
    public <U extends T> T orElseGet(Producer<U> producer) {
      return this.content;
    }
  }

  /**
   * STATIC FINAL NESTED CLASS: None
   * This static final nested class None represents a Maybe object that has no
   * value. It inherits from the Maybe class.
   */
  private static final class None extends Maybe<Object> {
    //ATTRIBUTES for class None
    /**
     * NESTED CLASS ATTRIBUTE: content
     * This is the attribute that stores the value of the class of type Object.
     */
    private Object content;

    //CONSTRUCTOR METHOD for class None
    /**
     * NESTED CLASS CONSTRUCTOR METHOD: None()
     * This method initialises the None object, that is a subtype of Maybe class
     */
    public None() {
      this.content = null;
    }

    //toString METHOD for class None
    /**
     * NESTED CLASS METHOD: toString()
     * This method returns a string representation of the None class
     * 
     * @return String         The string representation of the None class.
     */
    @Override
    public String toString() {
      return "[]";
    }
    
    //equals METHOD for class None
    /**
     * NESTED CLASS METHOD: equals(Object)
     * This method returns true if the Object is an instance of None type.
     *
     * @param n               The object to check if this is equal to n.
     * @return boolean        The method returns true or false.
     */
    @Override
    public boolean equals(Object n) {
      //check if object is instance of None
      //  return true
      //else (Not instance of None)
      //  return false
      
      if (n instanceof None) {
        return true;
      } else { //Not None object
        return false;
      }
    }
      
    //OTHER METHODS for class None
    /**
     * NESTED CLASS METHOD: get()
     * This method throws a NoSuchElementException when called upon because 
     * a None object has no content.
     *
     * @return Object         The method is meant to return the value of type Object
     */
    @Override
    protected Object get() {
      throw new NoSuchElementException();
    }
    
    /**
     * NESTED CLASS METHOD: filter(BooleanCondition)
     * This method returns this same None object as a None object would fail
     * the test conditions.
     *
     * @param testCond        The test condition to test the instance of None
     * @return Maybe          The method returns a type of Maybe, or a subtype of
     *                        it.
     */
    @Override
    public Maybe<Object> filter(BooleanCondition<Object> testCond) {
      return this;
    }

    /**
     * NESTED CLASS METHOD: map(Transformer)
     * This method maps the value to the function defined by the Transformer
     * However, a None object has no value, hence this method returns back 
     * a None object
     *
     * @param <U>             The type variable of the value returned after mapping
     *                        the content to the function.
     * @param trans           The function to which the content is to be mapped to
     * @return Maybe          The method is expected to return a Maybe object 
     *                        of type None.
     */
    @Override
    public <U> Maybe<U> map(Transformer<Object, ? extends U> trans) {
      return none(); //returns back None object
    }
    
    /**
     * NESTED CLASS METHOD: flatMap(Transformer)
     * This method maps the value to the function defined by the Transformer, in
     * which the Transformer returns a Maybe object.
     * But, as a None object has no value, hence this method returns back
     * a None object
     *
     * @param <U>             The type variable of the value returned after mapping
     *                        the content to the function.
     * @param trans           The function to which the content is to be mapped to
     * @return Maybe          The method is expected to return a Maybe object
     *                        of type None.
     */
    @Override
    public <U> Maybe<U> flatMap(Transformer<Object, ? extends Maybe<? extends U>> trans) {
      //flatMap should only be used for wordToMaybeInt kind of transformation
      return none();
    }

    /**
     * NESTED CLASS METHOD: orElse(value)
     * This method will always return the argument passed into the method as 
     * the None object has no content to return.
     *
     * @param <U>             The type variable of the value passed in the argument
     * @param value           The value to return if this object has no content
     * @return U              The method is expected to return the argument value
     *                        of type U.
     */
    @Override
    public <U> U orElse(U value) {
      return value;
    }

    /**
     * NESTED CLASS METHOD: orElseGet(Producer)
     * This method will always return the value produced by the producer of the
     * argument as the None object has no content to return.
     *
     * @param <U>             The type variable of the value passed in the argument
     * @param producer        The producer that produces the return value if this 
     *                        object has no content.
     * @return U              The method is expected to return the value produced
     *                        by the producer of type U.
     */
    @Override
    public <U> U orElseGet(Producer<U> producer) {
      return producer.produce();
    }
  }
 
  /**
   * FACTORY METHOD: none()
   * This method of Maybe class calls upon the instantiation of a None object 
   * (a subtype of class Maybe)
   *
   * @param <N>               The type variable of the None object.
   * @return Maybe            The method returns a None object, that is a subtype
   *                          of Maybe class.
   */
  public static <N> Maybe<N> none() {
    //method none() is like Box::empty method. 
    //To call this function:
    //.<type of Maybe, assumed to be Object>none()
    //We know that NONE is a Maybe by definition
    //And NONE has a wildcard for parameter type
    //Therefore it is safe to cast NONE to a Maybe with any specified type param
    
    @SuppressWarnings("unchecked")
    Maybe<N> noneObj = (Maybe<N>) NONE;
    return noneObj; //Just returns the NONE in the attributes.
  }

  /**
   * FACTORY METHOD: some(itemInput)
   * This method of Maybe class calls upon the instantiation of a Some object
   * (a subtype of class Maybe) with the content = itemInput
   *
   * @param <I>               The type variable of the item to be put into the Some
   *                          object.
   * @param itemInput         The value to be instantiated in the Some object. 
   * @return Maybe            The method returns a Some object of type I, that is a
   *                          subtype of the Maybe class.
   */
  public static <I> Maybe<I> some(I itemInput) {
    Some<I> someObj = new Some<I>(itemInput);
    return someObj;
  }

  /**
   * FACTORY METHOD: of(itemInput)
   * This method of Maybe class checks if itemInput is null or not.
   * If there is an input, then it calls on the instantiation of Some object
   * through the method some(). Else it creates a None object through calling none()
   *
   * @param <I>               The type variable of the itemInput
   * @param itemInput         The value to be put into the Maybe object
   * @return Maybe            The method returns a Maybe object of type I.
   */
  public static <I> Maybe<I> of(I itemInput) {
    //if input is != null
    //  return Some
    //else 
    //  return None
    if (itemInput != null) { //Got input
      return some(itemInput);
    } else { //input is null
      return none();
    }
  }

  /**
   * METHOD: get()
   * The method returns the value of the Maybe object.
   * @return T                The value of the Maybe object is returned.
   */
  protected abstract T get();  
  
  /**
   * METHOD: filter(BooleanCondition)
   * The method filters the type of Maybe object based on criteria set by
   * the test condition of type BooleanCondition
   *
   * @param testCond          The testing conditions the Maybe object is 
   *                          subjected to
   * @return Maybe            The method returns a Maybe object of type T.
   */
  public abstract Maybe<T> filter(BooleanCondition<? super T> testCond);

  /**
   * METHOD: map(Transformer)
   * This method maps the content of the Maybe object to the function.
   *
   * @param <U>               The type variable of the output Maybe object with 
   *                          values after mapping through the function
   * @param trans             The function through which the content is passed
   *                          through.
   * @return Maybe            The method returns a Maybe object of type U.
   */
  public abstract <U> Maybe<U> map(Transformer<? super T, ? extends U> trans);
  
  /**
   * METHOD: flatMap(Transformer)
   * This method is like the map() function, but the Transformer itself returns a 
   * Maybe object.
   *
   * @param <U>               The type variable of the output Maybe objecft with
   *                          values after mapping through the function
   * @param trans             The function through which teh content is passed  
   *                          through.
   * @return Maybe            The method returns a Maybe object of type U.
   */
  public abstract <U> Maybe<U> flatMap(Transformer<? super T, ? extends Maybe<? extends U>> trans);
  
  /**
   * METHOD: orElse(valuElse)
   * This method returns the value of the Maybe object, or else, it returns the 
   * argument input to this function.
   *
   * @param <U>               The type variable of the argument.
   * @param valuElse          The value that the function returns if the Maybe
   *                          object is None.
   * @return T                The method returns a value of type T or its subtype.
   */
  public abstract <U extends T> T orElse(U valuElse);

  /**
   * METHOD: orElseGet(Producer)
   * This method returns the value of the Maybe object, or else, it returns the
   * value produced by the producer that was input into the function.
   *
   * @param <U>               The type variable of the producer argument.
   * @param producer          The producer that produces the alternative value
   *                          if the Maybe object is None.
   * @return T                The method returns a value of tyep T or its subtype.
   */
  public abstract <U extends T> T orElseGet(Producer<U> producer);
}
