/**
 * CS2030 2020/21 ST 1
 * Lab 5: Maybe
 * File Name: BooleanCondition.java
 * Description: BooleanCondition is an interface with a boolean method test()
 *
 * @author Benedict Cheok Wei En (B03), A0199433U
 */

package cs2030.fp;

public interface BooleanCondition<T> {
  boolean test(T testThat);
}
