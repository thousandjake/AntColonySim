//Filename: ScoutAnt.java
//Written By: Jake Thousand
//Written On: 2017-11-27
//Description:

import java.util.*;

public class ScoutAnt implements Ant {
  //attributes
  private int antID;
  private int currentAge;
  private int maxAge;
  private int xCoordinate;
  private int yCoordinate;
  //static random number generator
  private static Random r = new Random();
  //constructor
  public ScoutAnt(int antID) {
    this.antID = antID;
    currentAge = 1;
    maxAge = 3650;
    xCoordinate = 13;
    yCoordinate = 13;
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
  public void moveAction(ColonyNode[][] nodeList) {
    //need moveAction logic
    int randX = 0, randY = 0;
    while(randX == 0 && randY == 0) {
      randX = r.nextInt(3)-1;
      randY = r.nextInt(3)-1;
    }
    nodeList[xCoordinate][yCoordinate].removeScout();
    if(xCoordinate > 0 && xCoordinate < 26) {
      xCoordinate = xCoordinate + randX;
    }
    if(yCoordinate > 0 && yCoordinate < 26) {
      yCoordinate = yCoordinate + randY;
    }
    nodeList[xCoordinate][yCoordinate].showNode();
    nodeList[xCoordinate][yCoordinate].addScout();
  }
  public void update(int turnCount, ColonyNode[][] nodeList) {
    //need updateAction logic
    System.out.println(" - - Scout Update Triggered !!! - - ");
    moveAction(nodeList);
  }
}
