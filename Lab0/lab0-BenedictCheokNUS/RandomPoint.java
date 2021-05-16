import java.util.Random;

/**
 * CS2020 Lab0: RandomPoint.java
 * Special Term 1, 2020/21
 * @author Benedict Cheok Wei En (B03), A0199433U
 * Updated as of: 16 May 2021, 2020HRS
 */

//java.util.Random is a class
//First you instantiate an object of Random class:
//Random rng = new Random(1) <-- default seed here is 1. 
//To get a double number between 0 and 1 (e.g. 0.34...):
//double no = rng.nextDouble() <-- where the nextDouble method returns a double of value between 0 and 1
//To change the seed, use modifier method of .setSeed():
//rng.setSeed()

class RandomPoint extends Point {
  
  //Attributes/fields
  private static Random rng = new Random(1);
  //private Random rngy = new Random(1);
  private double minX;
  private double maxX;
  private double minY;
  private double maxY;

  //Constructor Method
  public RandomPoint(double minX, double maxX, double minY, double maxY){
    super(0, 0); //Initialise a point first with x and y coord of 0 by calling constructor of parent class first
    
    this.minX = minX;
    this.maxX = maxX;
    this.minY = minY;
    this.maxY = maxY;
    
    double noX = rng.nextDouble(); //noX is a value between 0 and 1, which we would use to find a random x coord between minX and maxX
    double noY = rng.nextDouble(); // noY has the same concept as noX, but for the y coord
    
    double newX = noX*(this.maxX - this.minX) + this.minX;
    double newY = noY*(this.maxY - this.minY) + this.minY;
    
    super.setX(newX); //sets the new value of x coord using the parent class modifier method 
    super.setY(newY); //sets the new value of y coord
    
  }
  
  //Set Seed Method
  public static void setSeed(long seed) {
    rng.setSeed(seed);

  }
  
}
