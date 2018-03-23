//Filename: CoordinateObject.java
//Written By: Jake Thousand
//Written On: 2017=12-09
//Description: CoordinateObject class used in Ant Colony Simulator.  The class is just used to store grid coordinates for random ant movements

public class CoordinateObject {
  //attributes
  private int xCoordinate;
  private int yCoordinate;
  //constructor
  public CoordinateObject(int xCoordinate, int yCoordinate) {
    this.xCoordinate = xCoordinate;
    this.yCoordinate = yCoordinate;
  }
  //methods
  public int getXCoordinate() {
    return xCoordinate;
  }
  public void setXCoordinate(int xCoordinate) {
    this.xCoordinate = xCoordinate;
  }
  public int getYCoordinate() {
    return yCoordinate;
  }
  public void setYCoordinate(int yCoordinate) {
    this.yCoordinate = yCoordinate;
  }
}
  
