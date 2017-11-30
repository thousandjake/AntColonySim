//Filename: ForagerAnt.java
//Written By: Jake Thousand
//Written On: 2017-11-27
//Description:

public class ForagerAnt implements Ant {
  //attributes
  private int antID;
  private int currentAge;
  private int maxAge;
  private int xCoordinate;
  private int yCoordinate;
  //constructor
  public ForagerAnt() {

  }
  //get and set methods
  public int getAntID() {
    return antID;
  }
  public void setAntID(int antID) {
    this.antID = antID;
  }
  public int getCurrentAge() {
    return currentAge;
  }
  public void setCurrentAge(int currentAge) {
    this.currentAge = currentAge;
  }
  public int getMaxAge() {
    return maxAge;
  }
  public void setMaxAge(int maxAge) {
    this.maxAge = maxAge;
  }
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
  public void moveAction(int xCoord, int yCoord) {
    //need moveAction logic
  }
}
