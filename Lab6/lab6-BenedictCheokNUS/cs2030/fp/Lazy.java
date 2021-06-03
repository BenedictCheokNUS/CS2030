package cs2030.fp;

public class Lazy<T> {
  private Producer<T> producer;
  private Maybe<T> value;
}
