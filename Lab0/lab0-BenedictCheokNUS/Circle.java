/**
 * CS2030S Lab 0: Circle.java
 * Special Term 1, 2020/21
 *
 * The Circle class represents a circle with a center 
 * and a radius.
 *
 * @author Benedict Cheok Wei En 
 */

import java.lang.Math;

class Circle {
  /** The center of the circle. */
  private Point c;

  /** The radius of the circle (assume positive). */
  private double r;

  /**
   * Constructor for a circle.  Takes in a center c and a 
   * radius r (assume to be positive). 
   *
   * @param c The center of the new circle.
   * @param r The radius of the new circle.
   */
  public Circle(Point c, double r) {
    this.c = c;
    this.r = r;
  }

  /**
   * Checks if a given point p is contained within the circle.
   *
   * @param p The point to test.
   * @return true if p is within this circle; false otherwise.
   */
  public boolean contains(Point p) {
    //r^2 = dx^2 + dy^2 (Pythagoras Theorem)
    //dx = px - cx, where px is the x coord of P, and cx is x coord of C
    //dy = py - cy, where py is y coord of P and cy is y coord of C
    double cx = c.getX();
    double cy = c.getY();
    double px = p.getX();
    double py = p.getY();
    double dx = px - cx;
    double dy = py - cy;

    //If in circle, return true, else return false.
    if (Math.pow(dx, 2) + Math.pow(dy, 2) <= Math.pow(this.r, 2)) { //dx^2 + dy^2 <= r^2, means inside circle
      return true;
    } else { //dx^2 + dy^2 > r^2, means outside of circle
      return false;
    } 
  }

  /**
   * Return the string representation of this circle.
   *
   * @return The string representing of this circle.
   */
  public String toString() {
    return "{ center: " + this.c + ", radius: " + this.r + " }";
  }
}
