/** 
 * CS2030 2020/21 ST1
 * Lab 6: Lazy
 *
 * File Name: Lazy.java
 * Description: Lazy class wraps a Maybe object that contains
 * something or nothing
 * It would only evaluate the value 
 * when needed, and evaluate it once only
 *
 * @param <T>       Type parameter T of Lazy class
 * Attributes:.
 * @param producer  Producer type, that stores a function
 *                  produce() to be activated when needed 
 *                  only
 * @param value     Maybe type, that wraps around a value
 *                  whether or not it is null. 
 *
 * Methods:
 *
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
  /**
   * Constructor Method 1: Lazy(T value)
   * Access Level: private
   * This private constructor method that
   * initialises a Lazy object with a value
   * of type Maybe
   *
   * @param value   The value to be initialised in
   *                a Maybe object.
   *
   */
  private Lazy(T value) {
    //Only for the value
    this.value = Maybe.some(value);
  }

  /**
   * Constructor Method 2: Lazy(Producer s)
   * Access Level: private
   * This private constructor method initialises
   * a Lazy object with a producer that would only
   * produce when called.
   *
   * @param s     The argument of type producer to
   *              be initialised with
   *
   */
  private Lazy(Producer<T> s) {
    //for the producer
    this.producer = s;
  }

  //FACTORY METHODS
  
  /**
   * Factory Method 1: of(T v)
   * Access Level: public static
   * This public factory method would call upon
   * the private Lazy(T v) constructor method 
   * to initialise a Lazy object with value v
   *
   * @param <T>     The type variable of the object
   * @param v       The value to be initialised in the 
   *                Lazy object
   * @return Lazy   It returns a Lazy object with the 
   *                value stored in a Maybe object.
   *
   */ 
  public static <T> Lazy<T> of(T v) {
    return new Lazy<T>(v);
  }

  /**
   * Factory Method 2: of(Producer s)
   * Access Level: public static
   * This public factory method would call upon the
   * private Lazy(Producer s) constructor to 
   * initialise a Lazy object with a producer
   *
   * @param <T>     The type variable of the object.
   * @param s       The producer to be initialised 
   *                in the Lazy object
   * @return Lazy   It returns a Lazy object with
   *                the producer stored in it.
   *
   */
  public static <T> Lazy<T> of(Producer<T> s) {
    return new Lazy<T>(s);
  }

  //METHODS
  
  /**
   * Method: get()
   * Access Level: public
   * This method returns the value of the Lazy object
   * It checks first if the value is cached, else it 
   * executes the producer and caches the value.
   * 
   * @return        Value stored in the object of type T
   *
   */ 
  public T get() {
    this.value = Maybe.<T>of(this.value.<T>orElseGet(this.producer));
    return this.value.orElse(null);
  }

  /**
   * Method: toString()
   * Access Level: public
   * This method returns a string representation of the
   * Lazy object.
   * It would first check if there is any value cached in
   * the Lazy object and return that value as a string. Else
   * it would return "?"
   *
   * @return        String representation of Lazy
   *
   */ 
  @Override
  public String toString() {    
    return this.value.map(String::valueOf).orElse("?");
  }

  /**
   * Method: map(Transformer trans)
   * Access Level: public
   * This method maps the current value to a new value
   * through the transformer.
   * However, the new value would be stored as a producer
   * and only evaluated later if the get() method is 
   * invoked.
   * 
   * @param <U>       The new type variable of the new value.
   * @param trans     The transformation formula to transform
   *                  from the old to the new value.
   * @return Lazy     It returns a Lazy object with the new
   *                  type variable U.
   *
   */ 
  public <U> Lazy<U> map(Transformer<? super T, ? extends U> trans) {
    Producer<U> newProduce = () -> trans.transform(this.get());
    return Lazy.<U>of(newProduce);
  }

  /**
   * Method: flatMap(Transformer trans)
   * Access Level: public
   * This method maps the current value to the new value 
   * through the use of the transformer.
   * The new value would be stored as a producer and only
   * evaluated later when the get() method is invoked.
   * However, this flatMap method only wraps the producer
   * once only.
   *
   * @param <U>       The type variable of the new value
   *                  and new Lazy object
   * @param trans     The transformation formula to transform
   *                  from the old to the new value
   * @return Lazy     It returns a Lazy object with the new type
   *                  variable U.
   *
   */ 
  public <U> Lazy<U> flatMap(Transformer<? super T, ? extends Lazy<? extends U>> trans) {
    //@SuppressWarnings("unchecked")
    Producer<U> newProduce = () -> trans.transform(this.get()).get();
    return Lazy.<U>of(newProduce);
  }

  /**
   * Method: filter(BooleanCondition testCond)
   * Access Level: public
   * This method filters the contents of the Lazy object
   * It stores the unfinished evaluation into a Lazy object
   * of type variable Boolean, which would be executed
   * when invoked.
   *
   * @param testCond    The criteria to which the vontent of the
   *                    Lazy object is evaluated.
   * @return Lazy       It returns a Lazy object of type Boolean.
   *
   */ 
  public Lazy<Boolean> filter(BooleanCondition<? super T> testCond) {
    Producer<Boolean> newProduceBool = () -> testCond.test(this.get());
    return Lazy.<Boolean>of(newProduceBool);
  }

  /**
   * Method: equals(Object n)
   * Access Level: public
   * This method compares the current Lazy object's values to
   * the Object n to be compared to and see if they are the 
   * same.
   * 
   * @param n           The object which the Lazy object is
   *                    being compared to.
   * @return boolean    It returns true if the contents are 
   *                    equal to the argument to be compared to   *
   *
   */ 
  
  @Override
  public boolean equals(Object n) {
    if (n instanceof Lazy) {
      //Type of n is checked and verified to be of type Lazy
      //Therefore it is safe to cast
      @SuppressWarnings("unchecked")
      Lazy<Object> compareWithat = (Lazy<Object>) n;
      return this.get().equals(compareWithat.get());
    } else {
      return false;
    }
  }

  /**
   * Method: combine(Lazy s, Combiner f)
   * Access Level: public
   * This method combines the current instance of the Lazy
   * object with the other Lazy object passed into its 
   * arguments based on a function defined by the Combiner f.
   * It stores this combining operation as a producer into a 
   * Lazy object of type R, which would be returned.
   *
   * @param <S>         The type variable of the Lazy object 
   *                    to be combined with this instance of
   *                    the Lazy object
   * @param <R>         The type variable of the combined Lazy object
   * @param s           The Lazy object ot be combined with the current
   *                    instance of the Lazy object
   * @param f           The Combiner function to be used to combine
   *                    the Lazy object s and the current Lazy object
   *                    instance.
   * @return Lazy       It returns a Lazy object that has been combined
   *                    by the Combiner f
   *
   */
  public <S, R> Lazy<R> combine(Lazy<? extends S> s, 
      Combiner<? super T, ? super S, ? extends R> f) {
    Producer<R> newCombiProd = () -> f.combine(this.get(), s.get());
    return Lazy.<R>of(newCombiProd);
  }
}
