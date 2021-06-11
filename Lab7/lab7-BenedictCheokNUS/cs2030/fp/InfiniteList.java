package cs2030.fp;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/** 
 * CS2030 2020/21 ST1
 * Lab 7: Infinite List
 * File Name: InfiniteList.java
 * Description: 
 *
 * Attributes:
 * head, tail, EMPTY_LIST 
 *
 * Methods:
 * InfiniteList(), generate(), iterate(), InfiniteList(), InfiniteList(), 
 * head(), tail(), map(), filter(), empty(), limit(), takeWhile(), isEmpty(), 
 * reduce(), count(), toList(), toString()
 *
 * @author Benedict Cheok Wei En (B03), A0199433U
 * @version CS2030 2020/21 ST1
 */
public class InfiniteList<T> {
  
  /**
   * ATTRIBUTE: head
   * This attribute stores the value of the head, which is a Maybe object,
   * wrapped in a Lazy object. This means that it is only evaluated when 
   * needed, and evaluated once. 
   */
  private final Lazy<Maybe<T>> head;
  
  /**
   * ATTRIBUTE: tail
   * This attribute stores the infinite list that is behind the head wrapped
   * in a Lazy object. This means it is only evaluated when needed, and 
   * evaluated once.
   */
  private final Lazy<InfiniteList<T>> tail;

  /**
   * ATTRIBUTE: EMPTY_LIST
   * This attribute is an empty list, that can be used to indicate that 
   * the list is empty, or to mark the end of an finite list. Needed for 
   * truncating the list, or creating an empty list.
   */
  private static InfiniteList<?> EMPTY_LIST = new EmptyList();
  
  /**
   * STATIC NESTED CLASS: EmptyList
   * This is a list that inherits the InfiniteList, to handle special cases of
   * the list being empty and to mark the end of the list. 
   *
   */
  private static class EmptyList extends InfiniteList<Object> {

    /**
     * NESTED CLASS CONSTRUCTOR METHOD: EmptyList()
     * This method instantiates an empty list that inherits from InfiniteList.
     */
    EmptyList() {
      super(); //just calls the InfiniteList() constructor method 1
    }
    
    /**
     * NESTED CLASS METHOD: head()
     * This method will throw a NoSuchElementException when head() is called as 
     * the empty list has no head.
     *
     * @throws NoSuchElementException There is no head in EmptyList.
     */
    @Override
    public Object head() {
      throw new NoSuchElementException();
    }
    
    /**
     * NESTED CLASS METHOD: tail()
     * This method will throw a NoSuchElementException when tail() is called as
     * the empty list has no tail. 
     *
     * @throws NoSuchElementException There is no tail in EmptyList.
     */
    @Override
    public InfiniteList<Object> tail() {
      throw new NoSuchElementException();
    }
    
    /**
     * NESTED CLASS METHOD: map()
     * This method maps the empty list using a mapper. However, the empty list is 
     * empty and has no elements to map. Therefore it returns an empty list
     *
     * @return InfiniteList   The method returns this EmptyList, which is a 
     *                        subtype of InfiniteList
     */
    @Override
    public <R> InfiniteList<R> map(Transformer<Object, ? extends R> mapper) {
      return empty(); //returns back this empty list.      
    }

    /**
     * NESTED CLASS METHOD: filter()
     * This method filters the elements of the list. However the empty list
     * has no elements, and therefore the method returns an empty list.
     *
     * @return InfiniteList   The method returns this EmptyList, which is a 
     *                        subtype of InfiniteList.
     */
    @Override
    public InfiniteList<Object> filter(BooleanCondition<Object> predicate) {
      return this; //There is nth to filter in an empty list.
    }
    
    /**
     * NESTED CLASS METHOD: isEmpty()
     * This method returns true as EmptyList is empty. 
     *
     * @return boolean        This method would return true as the empty list
     *                        is empty.
     */
    @Override
    public boolean isEmpty() {
      return true;
    }
    
    /**
     * NESTED CLASS METHOD: limit(n)
     * The method returns the empty list as the empty list has no length.
     *
     * @return InfiniteList   This method returns an empty list, which is a 
     *                        a subtype of infinite list.
     */
    @Override
    public InfiniteList<Object> limit(long n) {
      return this;
    }

    /**
     * NESTED CLASS METHOD: toList()
     * This method returns a type of List object of the infinite list.
     *
     * @return List           The method returns a newly instantiated ArrayList
     *                        with no elements.
     */
    @Override
    public List<Object> toList() {
      List<Object> empList = new ArrayList<Object>();
      return empList;
    }
    
    /**
     * NESTED CLASS METHOD: takeWhile(predicate)
     * This method returns the empty list as there is no element in the empty list
     * to check against the predicate.
     *
     * @param predicate       The test to check if the element of the list fulfils
     *                        it, else truncate list.
     * @return InfiniteList   The method returns an empty list which is subtype of
     *                        infinite list.
     */
    @Override
    public InfiniteList<Object> takeWhile(BooleanCondition<Object> predicate) {
      return this; //empty list got nothing to take.
    }
    
    /**
     * NESTED CLASS METHOD: reduce(identity, accumulator)
     * This method will always return the identity argument of the reduce method.
     *
     * @return U              The method returns the identity argument of type U.
     */ 
    @Override
    public <U> U reduce(U identity, Combiner<U, Object, U> accumulator) {
      return identity;
    }

    /**
     * NESTED CLASS METHOD: count()
     * This method counts the number of elements in the list. 
     * An empty list has 0 elements.
     *
     * @return long           The method will always return 0 for empty list.
     */
    @Override
    public long count() {
      return 0;
    }
  }

  /**
   * CONSTRUCTOR METHOD 1: InfiniteList()
   * This is the generic constructor method provided of InfiniteList
   */
  InfiniteList() { 
    head = null; 
    tail = null;
  }
  
  /**
   * FACTORY METHOD: generate
   * This factory method produces an infinite list of a specified value 
   * e.g. [[1] [[1] [[1] [[1] ...]]]]
   * 
   * @param <T>               The type variable of the infinite list defined.
   * @param producer          The producer required to populate the elements
   *                          of the Infinite List.
   * 
   * @return InfiniteList     Returns an InfiniteList.
   */
  public static <T> InfiniteList<T> generate(Producer<T> producer) {
    //OLD: return new InfiniteList<T>(producer, () -> InfiniteList.generate(producer));
    return new InfiniteList<T>(Lazy.of(() -> Maybe.some(producer.produce())), 
        Lazy.of(() -> InfiniteList.<T>generate(producer)));
  }

  /**
   * FACTORY METHOD: iterate
   * This factory method produces an infinite list with values conforming to 
   * an equation defined by the transformer "next"
   * e.g. [[1] [[2] [[3] ...]]] where transformer is x + 1.
   *
   * @param <T>               The type variable of teh infinite list defined.
   * @param seed              The starting value of the infinite list.
   * @param next              The transformer that transform the first value,
   *                          into the next value required for the next 
   *                          element of the infinite list.
   * @return InfiniteList     Returns an InfiniteList
   *
   */
  public static <T> InfiniteList<T> iterate(T seed, Transformer<T, T> next) {
    //OLD: return new InfiniteList<T>(() -> init, 
    //    () -> InfiniteList.iterate(next.transform(init), next))
    return new InfiniteList<T>(seed, () -> InfiniteList.iterate(next.transform(seed), next));
  }
  
  /**
   * CONSTRUCTOR METHOD 2: InfiniteList()
   * This method is for taking in the head, a parameter of type T 
   * and a producer tail, of type InfiniteList of type T and 
   * initialising an instance of InfiniteList.
   * Usually called by the factory method iterate().
   *
   * @param head              The starting value of the infinite list.
   * @param tail              The producer required to populate the remaining
   *                          elements of the infinite list.
   */
  private InfiniteList(T head, Producer<InfiniteList<T>> tail) {
    this.head = Lazy.of(Maybe.some(head));
    this.tail = Lazy.of(tail);
  }

  /**
   * CONSTRUCTOR METHOD 3: InfiniteList()
   * This method is for taking in the head, an instance of Lazy of Maybe of type
   * parameter T, and a tail, an instance of Lazy of InfiniteList of type 
   * parameter T, and initialising an instance of InfiniteList.
   * Usually called by the factory method generate()
   *
   * @param head              The starting value of infinite list wrapped in 
   *                          a Lazy, that wraps a Maybe object of type T. 
   *                          This is so that the infinite list can exhibit
   *                          laziness.
   * @param tail              The tail is a producer that produces an infinite
   *                          list, which is wrapped in a Lazy object, so that
   *                          the tail would be produced only when called upon
   *                          and produced only once.
   */
  private InfiniteList(Lazy<Maybe<T>> head, Lazy<InfiniteList<T>> tail) {
    this.head = head;
    this.tail = tail;
  }

  /**
   * METHOD: head()
   * This method returns the value of the head of the infinite list.
   *
   * @return T                It returns a value of type T.
   *
   */
  public T head() {
    return this.head.get().orElseGet(() -> this.tail.get().head());
  }

  /**
   * METHOD: tail()
   * This method returns the subsequent infinite list behind the head of 
   * the current infinite list. 
   *
   * @return InfiniteList     It returns an infinite list that is behind the head
   */
  public InfiniteList<T> tail() {    
    return (this.head.get() == Maybe.none()) ? (this.tail.get().tail()) : (this.tail.get());
  }
  
  /**
   * METHOD: map()
   * This method maps the elements x of the infinite list to a function
   * (Transformer) f, and returns a new infinite list with the elements 
   * which are f(x).
   *
   * @param <R>               The new type variable (codomain) of the 
   *                          infinite list that is mapped to the 
   *                          function provided.
   * @param mapper            The function that modifies the element of the
   *                          infinite list based on a defined function.
   * @return InfiniteList     The method returns an infinite list of type R,
   *                          which is the Codomain of the function.
   *
   */ 
  public <R> InfiniteList<R> map(Transformer<? super T, ? extends R> mapper) {
    Lazy<Maybe<R>> newHead = Lazy.of(() -> Maybe.some(this.head()).map(mapper));
    Lazy<InfiniteList<R>> newTail = Lazy.of(() -> this.tail().map(mapper));
    return new InfiniteList<R>(newHead, newTail);
  }

  /**
   * METHOD: filter()
   * This method filters the elements that fulfil the condition provided by the
   * predicate, which is a type of BooleanCondition. Elements that do not fulfil
   * the predicate would be returned as a Maybe.none() object.
   *
   * @param predicate         The conditions that the elements must fulfil.
   *
   * @return InfiniteList     The method returns and InfiniteList with all the 
   *                          filtered elements.
   */
  public InfiniteList<T> filter(BooleanCondition<? super T> predicate) {
    //OLD METHOD:
    //Producer<T> newHead  = () -> cond.test(this.head()) ? this.head() : null;
    //return new InfiniteList<>(newHead, () -> this.tail().filter(cond));
    
    //NEW METHOD:
    Lazy<Maybe<T>> newHead = Lazy.of(() -> this.head.get().filter(predicate));
    Lazy<InfiniteList<T>> newTail = Lazy.of(() -> this.tail.get().filter(predicate));
    return new InfiniteList<T>(newHead, newTail);
  }
  
  /**
   * METHOD: empty()
   * This method would return an empty list. 
   * 
   * @param <T>               The type variable of the infinite list.
   * @return InfiniteList     The method returns an EmptyList which is a subtype
   *                          of the infinite list. 
   */
  public static <T> InfiniteList<T> empty() {
    //EMPTY_LIST is of type EmptyList, which inherits from the parent class
    //InfiniteList. As such, EMPTY_LIST is a subtype of InfiniteList.
    //Therefore, it is safe to cast EMPTY_LIST to InfiniteList<T>
    @SuppressWarnings("unchecked")
    InfiniteList<T> outEmptyList = (InfiniteList<T>) EMPTY_LIST;
    return outEmptyList;
  }

  /**
   * METHOD: limit(n)
   * This method would truncate the infinite list after a specified n elements.
   * Elements that are of Maybe.None type are not counted, and looked over. 
   *
   * @param n                 The specified length of the list to be truncated
   * @return InfiniteList     It returns a truncated finite list, of the type
   *                          InfiniteList with an EmptyList at the end of it. 
   */
  public InfiniteList<T> limit(long n) {
    if (n <= 0) {
      return empty();  
    } else {
      Lazy<Maybe<T>> newHead = Lazy.of(() -> Maybe.some(this.head()));
      Lazy<InfiniteList<T>> newTail = Lazy.of(() -> this.tail().limit(n - 1));
      return new InfiniteList<T>(newHead, newTail);
    }
    //return new InfiniteList<>();
  }
  
  /**
   * METHOD: takeWhile(predicate)
   * This method would run through the list and check if each element fulfils
   * the predicate. If it fails the test, it would truncate the list.
   *
   * @param predicate         The testing condition that the element of the list
   *                          needs to fulfil.
   * @return InfiniteList     The method returns an finite list truncated at the
   *                          first instance of an element failing the predicate.
   *                          The type of the finite list is of InfiniteList type.
   */
  public InfiniteList<T> takeWhile(BooleanCondition<? super T> predicate) {
    Lazy<Boolean> passFail = Lazy.of(() -> predicate.test(this.head()));
    Lazy<Maybe<T>> newHead = Lazy.of(() -> passFail.get() ? Maybe.some(this.head()) : Maybe.none());
    Lazy<InfiniteList<T>> newTail = Lazy.of(() -> passFail.get() ? 
        this.tail().takeWhile(predicate) : empty());
    return new InfiniteList<T>(newHead, newTail);
  }
  
  /**
   * METHOD: isEmpty()
   * This method returns false for a normal infinite list as there are things
   * inside the list. However, this method is overrided in the nested child
   * class EmptyList, and there it would return true.
   *
   * @return boolean          The method returns false for a normal infinite list.
   */
  public boolean isEmpty() {
    return false;
  }
  
  /**
   * METHOD: reduce(identity, accumulator)
   * This method applies the lambda function accumulator repeatedly on the elements
   * of the InfiniteList (finite) until it reduces the list to a single value.
   * It is a terminal operation.
   *
   * @param <U>               The type variable of the output value
   * @param identity          The variable to be combined with the element of 
   *                          the list. It is updated by the result of the 
   *                          combination of a current element with the identity.
   * @param accumulator       The function to how to combine the identity with 
   *                          with the current element.
   * @return U                The method returns a single value. 
   */
  public <U> U reduce(U identity, Combiner<U, ? super T, U> accumulator) {
    return this.tail().reduce(accumulator.combine(identity, this.head()), accumulator);
  }

  /**
   * METHOD: count()
   * This method counts the number of elements in the list
   * It is a terminal operation
   *
   * @return long             The method returns a long, indicating the number of
   *                          elements in the list.
   */
  public long count() {
    if (this.head.get() == Maybe.none()) {
      return this.tail.get().count(); //skip this one
    } else {
      return this.tail.get().count() + 1L;
    } 
  }
  
  /**
   * METHOD: toList()
   * This method converts the InfiniteList type into a List object.
   *
   * @return List             The method returns a List with all the elements.
   */
  public List<T> toList() {
    List<T> newList = new ArrayList<>();
    if (this.head.get() != Maybe.none()) {
      newList.add(this.head());
    }
    newList.addAll(this.tail.get().toList());
    return newList;
  }
  
  /**
   * METHOD: toString()
   * This method returns a string representation of the InfiniteList object. 
   *
   * @return String           The string representation of the object.
   */
  @Override
  public String toString() {
    return "[" + this.head + " " + this.tail + "]";
  }
}
