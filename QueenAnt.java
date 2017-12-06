//Filename: QueenAnt.java
//Written By: Jake Thousand
//Written On: 2017-11-27
//Description:

import java.util.*;

public class QueenAnt implements Ant {
  //attributes
  private int antID;
  private int currentAge;
  private int maxAge;
  private int xCoordinate;
  private int yCoordinate;
  private boolean isAlive;
  //static random number generator
  private static Random r = new Random();
  //constructor
  public QueenAnt() {
    antID = 0;
    currentAge = 1;
    maxAge = 73000;
    xCoordinate = 13;
    yCoordinate = 13;
    isAlive = true;
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
    if(currentAge == maxAge) {
      isAlive = false;
    }
  }
  public int getMaxAge() {
    return maxAge;
  }
  public void setMaxAge(int maxAge) {
    this.maxAge = maxAge;
    if(currentAge == maxAge) {
      isAlive = false;
    }
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
  public boolean checkAlive() {
    return isAlive;
  }
  public void consumeFood(ColonyNode[][] nodeList) {
    if(nodeList[xCoordinate][yCoordinate].getFoodLevel() > 0) {
      nodeList[xCoordinate][yCoordinate].removeFoodUnit();
    } else {
      isAlive = false;
    }
  }
  public void update(int turnCount, ColonyNode[][] nodeList) {
    System.out.println(" - - Queen Update Triggered !!! - - ");
    setCurrentAge(turnCount);
    consumeFood(nodeList);
  }
  public Ant hatchAnt(int antCount, ColonyNode[][] nodeList) {
    int randNum = r.nextInt(100) + 1;
    if(randNum < 50) {
      ForagerAnt fAnt = new ForagerAnt(antCount);
      nodeList[13][13].addForager();
      return fAnt;
    } else if(randNum >= 50 && randNum < 75) {
      ScoutAnt scAnt = new ScoutAnt(antCount);
      nodeList[13][13].addScout();
      return scAnt;
    } else {
      SoldierAnt soAnt = new SoldierAnt(antCount);
      nodeList[13][13].addSoldier();
      return soAnt;
    }
  }
}
