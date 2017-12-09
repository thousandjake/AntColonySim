//Filename: ScoutAnt.java
//Written By: Jake Thousand
//Written On: 2017-11-27
//Description:

import java.util.*;

public class ScoutAnt implements Ant {
  //attributes
  private int antID;
  private int antType;
  private int currentAge;
  private int maxAge;
  private int xCoordinate;
  private int yCoordinate;
  private boolean isAlive;
  //static random number generator
  private static Random r = new Random();
  //constructor
  public ScoutAnt(int antID) {
    this.antID = antID;
    antType = 2;
    currentAge = 1;
    maxAge = 3650;
    xCoordinate = 13;
    yCoordinate = 13;
    isAlive = true;
  }
  //get and set methods
  public int getAntID() {
    return antID;
  }
  public int getAntType() {
    return antType;
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
  public boolean checkAlive() {
    return isAlive;
  }
  public void killAnt() {
    isAlive = false;
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
    ArrayList<CoordinateObject> possibleMoves = new ArrayList<CoordinateObject>();
    for(int x=-1; x < 2; ++x) {
      for(int y = -1; y < 2; ++y) {
        int newX = xCoordinate + x;
        int newY = yCoordinate + y;
        if( newX >= 0 && newX <= 26 && newY >= 0 && newY <=26) {
          if(!(newX == xCoordinate && newY == yCoordinate)) {
            CoordinateObject cO = new CoordinateObject(newX, newY);
            possibleMoves.add(cO);
          }
        }
      }
    }
    nodeList[xCoordinate][yCoordinate].removeScout();
    int randomChoice = r.nextInt(possibleMoves.size());
    xCoordinate = possibleMoves.get(randomChoice).getXCoordinate();
    yCoordinate = possibleMoves.get(randomChoice).getYCoordinate();
    nodeList[xCoordinate][yCoordinate].addScout();
    nodeList[xCoordinate][yCoordinate].showNode();
  }
  public boolean update(int turnCount, ColonyNode[][] nodeList, LinkedList antList) {
    //need updateAction logic
    System.out.println(" - - Scout Update Triggered !!! - - ");
    if(!isAlive) {
      return false;
    } else {
      moveAction(nodeList);
      return true;
    }
  }
}
