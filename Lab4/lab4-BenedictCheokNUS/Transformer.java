/**
 *  CS2030 2020/21 ST1
 *  Lab 4: Box
 *  File Name: Transformer.java
 *  Description: This interface Transformer has a method called transform()
 *  It takes in an argument of generic type T and returns generic type U.
 *
 *  @author Benedict Cheok Wei En (B03), A0199433U
 */

interface Transformer<T, U> {
  public abstract U transform(T arg);
}
