import java.lang.Thread;

class Test {

  public static void main(String[] args) {
    System.out.println(Thread.currentThread().getName()); //Blocking
    Thread t1 = new Thread(() -> {
      for (int i = 1; i < 100; i++) {
        System.out.print(Thread.currentThread().getName());
        System.out.print("_");
      }
    }, "T1");

    Thread t2 = new Thread(() -> {
      System.out.println(Thread.currentThread().getName());
      for (int i = 2; i < 100; i++) {
        System.out.print("*");
      }
    }, "T2");

    t1.start(); //Non-Blocking
    t2.start(); //Non-Blocking
  }
} 
