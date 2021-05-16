/**
 * CS2030S Lab 0: Point.java
 * Special Term 1, 2020/21
 *
 * The Point class encapsulates a point on a 2D plane.
 *
 * @author Benedict Cheok Wei En, A0199433U
 * Updated as of: 16 May 2021, 2020HRS
 */
class Point {
  //Attributes/Fields
  //x and y coordinates
  private double x;
  private double y;

  //Constructor Method
  public Point(double x, double y) {
    this.x = x;
    this.y = y;
  }

  //Accessor Methods
  public double getX() {
    return this.x;
  }
  public double getY() {
    return this.y;
  }

  //Modfifier Methods
  public void setX(double x) {
    this.x = x;
  }
  public void setY(double y) {
    this.y = y;
  }
  
  //Output string method
  @Override
  public String toString() {
    return "(" + this.x + ", " + this.y + ")";

  }
}
