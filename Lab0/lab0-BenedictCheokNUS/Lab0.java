import java.util.Scanner;

/**
 * CS2030S Lab 0: Estimating Pi with Monte Carlo
 * Special Term 1, 2020/21
 *
 * This program takes in two command line arguments: the 
 * number of points and a random seed.  It runs the
 * Monte Carlo simulation with the given argument and print
 * out the estimated pi value.
 *
 * @author Benedict Cheok Wei En 
 */

class Lab0 {

  // TODO estimatePi(long numOfPoints, int seed) {
  // }
  public static double estimatePi(long numOfPoints, int seed) { //numOfPoints is the number of randomly generated k points.
    double r = 0.5; //Radius of circle
    double minX = 0;
    double maxX = 2 * r;
    double minY = 0;
    double maxY = 2 * r;
    
    //First, create my circle
    //Centre of circle Point c, x coord = ((maxX - minX)/2) + minX
    //Centre of circle Point c, y coord = ((maxY - minY)/2) + minY
    //After creating my circle, time to "drop" the random points
    //create a variable n, which represents the number of points that drop into the circle
    //for each random point in the range of the total numOfPoints:
    //  create random point p
    //  if circle.contains(p) is true:
    //      then n += 1
    //double pi = 4 * n / k
    //return pi
    
    //Create circle
    double cx = ((maxX - minX) / 2) + minX;
    double cy = ((maxY - minY) / 2) + minY;
    Point pc = new Point(cx, cy); //pc is the centre
    Circle c = new Circle(pc, r);
    //Checking if Circle created properly
    //System.out.println("Circle has been created");
    //System.out.println(c.toString());

    double n = 0; //n is a counter that counts the number of points that successfully drop into the circle.
    
    RandomPoint.setSeed(seed); //set the seed first before generating the points
    
    for (int i = 0; i < numOfPoints; i++) {
      //System.out.println("Point No: " + (i + 1));
      Point p = new RandomPoint(minX, maxX, minY, maxY); //random point generated
      //Check if point p is in the circle or not
      if (c.contains(p) == true) {
        n += 1; //n plus 1 more point in the circle
      }
    }
    double piEst = 4 * n / numOfPoints; //Computes the estimated value of pi using 4*n/k
    return piEst; //returns the estimated value of pi
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int numOfPoints = sc.nextInt();
    int seed = sc.nextInt();

    double pi = estimatePi(numOfPoints, seed);

    System.out.println("Estimated Value of Pi = " + pi); //remove the string portion before submission!!!!!!!!!!!
    sc.close();
  }
}
