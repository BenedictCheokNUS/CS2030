/**
 * CS2030 2020/21 ST1
 * Lab 3: Simulation 3
 * File Name: Array.java
 * Description: This file contains the generic class Array
 * Attributes: T[] array
 * Methods: Array (Constructor), void set(), T get(), T[] getArray()
 * @author Benedict Cheok Wei En (B03), A0199433U
 * 
 */

class Array<T extends Comparable<T>> {
  
  /**ATTRIBUTES*/
  
  private T[] array;

  /**CONSTRUCTOR METHOD*/
  
  Array(int size) {
    //The only way we can put an object into array is through the
    //method set(), which only takes in an argument of type T inside.
    //Therefore, it is safe to case 'Comparable[]' to 'T[]'
    @SuppressWarnings("unchecked")
    T[] a = (T[]) new Comparable[size]; 
    //after type erasure: T[] a = (Comparable) new Comparable[size]
    //Therefore, cannot "new Object[size]" because Object cannot cast to Comparable
    this.array = a;
  }
  
  /**METHODS*/

  public void set(int index, T item) {
    this.array[index] = item;
  }

  public T get(int index) {
    return this.array[index];
  }
  
  public T min() {
    //Method T min() implements compareTo() method from Comparable interface
    //Using the compareTo() method, it searches for the minimum element in the array
    //It then outputs that minimum element from the array
    T minElement = this.array[0];
    for (T element : this.array) {
      if (element.compareTo(minElement) < 0) {
        //found an element smaller than min element
        //replace min element with this element
        minElement = element;
      }
    }
    return minElement;
  }

  public T[] getArray() {
    return this.array;
  }

  public int length() {
    return this.array.length;
  }

}

