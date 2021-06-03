/**
 *  CS2030 2020/21 ST1
 *  Lab 5: Maybe
 *  File Name: Transformer.java
 *  Description: This interface Transformer has a method called transform()
 *  It takes in an argument of generic type T and returns generic type U.
 *
 *  @author Benedict Cheok Wei En (B03), A0199433U
 */

package cs2030.fp;

public interface Transformer<T, U> {
  U transform(T arg);
}

