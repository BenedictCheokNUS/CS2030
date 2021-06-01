/**
 * CS2030 2020/21 ST 1
 * Lab 4: Box
 * File Name: BooleanCondition.java
 * Description: BooleanCondition is an interface with a boolean method test()
 *
 * @author Benedict Cheok Wei En (B03), A0199433U
 */


interface BooleanCondition<T> {
  public abstract boolean test(T testThat);
}
