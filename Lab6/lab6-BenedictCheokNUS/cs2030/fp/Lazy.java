/** 
 * CS2030 2020/21 ST1
 * Lab 6: Lazy
 *
 * File Name: Lazy.java
 * Description: 
 *
 * Attributes:
 * @param <T>       Type parameter T of Lazy class.
 * @param producer  Producer type, that stores a function
 *                  produce() to be activated when needed 
 *                  only
 * @param value     Maybe type, that wraps around a value
 *                  whether or not it is null. 
 *
 * Methods:
 *
 * @author Benedict Cheok Wei En (B03), A0199433U
 *
 */

package cs2030.fp;

public class Lazy<T> {
  
  //ATTRIBUTES
  
  private Producer<T> producer;
  private Maybe<T> value = Maybe.none();

  //CONSTRUCTOR METHOD
  
  private Lazy(T value) {
    //Only for the value
    this.value = Maybe.some(value);
  }

  private Lazy(Producer<T> s) {
    //for the producer
    //This method only takes in Producer type arguements
    //Therefore safe to cast to Producer<T> tyoe
    
    //@SuppressWarnings("unchecked")
    this.producer = s;
  }

  //FACTORY METHODS
  
  public static <T> Lazy of(T v) {
    return new Lazy<T>(v);
  }

  public static <T> Lazy of(Producer<T> s) {
    return new Lazy<T>(s);
  }

  //METHODS
  
  public T get() {
    this.value = Maybe.of(this.value.<T>orElseGet(this.producer));
    return this.value.orElse(null);
  }
  
  @Override
  public String toString() {    
    return this.value.map(String::valueOf).orElse("?");
  }

  public <U> Lazy<U> map(Transformer<? super T, ? extends U> trans) {
    Producer<U> newProduce = () -> trans.transform(this.get());
    return Lazy.<U>of(newProduce);
  }
}
