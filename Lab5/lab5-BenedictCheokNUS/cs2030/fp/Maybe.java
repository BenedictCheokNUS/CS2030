/**
 *  CS2030 2020/21 ST1
 *  Lab 5: Maybe
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
 *  @author Benedict Cheok Wei En (B03), A0199433U
 */

package cs2030.fp;

import java.util.NoSuchElementException;

public abstract class Maybe<T> {

  /**ATTRIBUTES*/
  //private final T content;
  private static final Maybe<Object> NONE = new None();
 
  /**NESTED CLASSES*/
  private static final class Some<T> extends Maybe<T> {
    //ATTRIBUTES
    private T content;

    //CONSTRUCTOR METHOD for class Some
    public Some(T t) {
      this.content = t;
    }

    //toString METHOD for class Some
    @Override
    public String toString() {
      String str = "";
      str = String.format("[%s]", this.content);
      return str;
    }
    
    //equals METHOD for class Some
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
    @Override
    protected T get() {
      return this.content;
    }

    @Override
    public Maybe<T> filter(BooleanCondition<? super T> testCond) {
      if ((this.get() != null) && (testCond.test(this.get()) == false)) {
        return none();
      } else { //content == null OR testCond.test(this.content) TRUE by De Morgan's Law
        return this;
      }
    }

    @Override
    public <U> Maybe<U> map(Transformer<? super T, ? extends U> trans) {
      return some(trans.transform(this.get()));
    }

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
    
    @Override
    public <U extends T> T orElse(U value) {
      return this.content;
    }

    @Override 
    public <U extends T> T orElseGet(Producer<U> producer) {
      return this.content;
    }
  }

  private static final class None extends Maybe<Object> {
    //ATTRIBUTES for class None
    private Object content;

    //CONSTRUCTOR METHOD for class None
    public None() {
      this.content = null;
    }

    //toString METHOD for class None
    @Override
    public String toString() {
      return "[]";
    }
    
    //equals METHOD for class None
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
    @Override
    protected Exception get() throws NoSuchElementException {
      throw new NoSuchElementException();
    }
    
    @Override
    public Maybe<Object> filter(BooleanCondition<Object> testCond) {
      return this;
    }

    @Override
    public <U> Maybe<U> map(Transformer<Object, ? extends U> trans) {
      return none(); //returns back None object
    }

    @Override
    public <U> Maybe<U> flatMap(Transformer<Object, ? extends Maybe<? extends U>> trans) {
      //flatMap should only be used for wordToMaybeInt kind of transformation
      return none();
    }

    @Override
    public <U> U orElse(U value) {
      return value;
    }

    @Override
    public <U> U orElseGet(Producer<U> producer) {
      return producer.produce();
    }
  }
 
  /**FACTORY METHODS*/
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

  public static <I> Maybe<I> some(I itemInput) {
    Some<I> someObj = new Some<I>(itemInput);
    return someObj;
  }

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

  /**OTHER METHODS*/
  protected abstract T get();  
  
  public abstract Maybe<T> filter(BooleanCondition<? super T> testCond);

  public abstract <U> Maybe<U> map(Transformer<? super T, ? extends U> trans);
  
  public abstract <U> Maybe<U> flatMap(Transformer<? super T, ? extends Maybe<? extends U>> trans);
  
  public abstract <U extends T> T orElse(U valuElse);

  public abstract <U extends T> T orElseGet(Producer<U> producer);
}
