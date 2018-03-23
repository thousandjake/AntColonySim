//Filename: QueenAnt.java
//Written By: Jake Thousand
//Written On: 2017-12-09
//Description:  This is the Queen Ant class for the Ant Simulation Project

import java.util.*;

public class QueenAnt implements Ant {
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
  public QueenAnt() {
    //The Queen antID 0, other Ant IDs will be assigned in ascending order as hatched
    antID = 0;
    //A different antType for each Ant Type
    //Queen = 0, Forager = 1, Scout = 2, Soldier = 3, Bala = 4
    antType = 0;
    //start age at 1 turn
    currentAge = 1;
    //max age is 20 years = 7300 days = 73000 turns
    maxAge = 73000;
    //Queen Node is middle of the Grid, Node 13,13
    xCoordinate = 13;
    yCoordinate = 13;
    //Queen is initially Alive
    isAlive = true;
  }
  //< - - - - - - Get and Set Methods for AntID attribute - - - - - - >
  public int getAntID() {
    return antID;
  }
  public void setAntID(int antID) {
    this.antID = antID;
  }
  //< - - - - - - Get Method for AntType attribute - - - - - - >
  public int getAntType() {
    return antType;
  }
  //< - - - - - - Get, Set, and Increment Methods for CurrentAge attribute - - - - - - >
  public int getCurrentAge() {
    return currentAge;
  }
  public void setCurrentAge(int currentAge) {
    this.currentAge = currentAge;
    if(currentAge == maxAge) {
      isAlive = false;
    }
  }
  public void incrementCurrentAge() {
    currentAge++;
  }
  //< - - - - - - Get and Set Methods for MaxAge attribute - - - - - - >
  public int getMaxAge() {
    return maxAge;
  }
  public void setMaxAge(int maxAge) {
    this.maxAge = maxAge;
    if(currentAge == maxAge) {
      isAlive = false;
    }
  }
  //< - - - - - - Get and Set Methods for xCoordinate and yCoordinate attributes - - - - - - >
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
  //< - - - - - - Method check whether Queen Ant is still Alive - - - - - - >
  public boolean checkAlive() {
    return isAlive;
  }
  //< - - - - - - Method to kill the Ant if attacked - - - - - - >
  public void killAnt() {
    isAlive = false;
  }
  //< - - - - - - Method to Consume 1 unit of food - - - - - - >
  public void consumeFood(ColonyNode[][] nodeList) {
    if(nodeList[xCoordinate][yCoordinate].getFoodLevel() > 0) {
      //check to see if the Node the Ant is in contains units of Food
      //if food is available, the quantity in the node will be reduced by one
      nodeList[xCoordinate][yCoordinate].removeFoodUnit();
    } else {
      //if there is no food availabile in the node, the isAlive attribute is set to false (queen starved)
      isAlive = false;
    }
  }
  //< - - - - - - Method to update / take actions once per turn - - - - - - >
  public boolean update(int turnsCount, ColonyNode[][] nodeList, LinkedList antList) {
    if(!isAlive) {
      //if queen is dead from starvation or attack, return false and do not take an action
      return false;
    } else if(turnsCount%10 == 0 && currentAge >= maxAge) {
      //at the start of the new day, check to see if queen has died from old age
      //if queen has died, return false and do not take an action
      return false;
    } else {
      //if queen is not dead, increase current age by 1 turn and consume 1 unit of food
      //return true once the actions are completed
      incrementCurrentAge();
      consumeFood(nodeList);
      return true;
    }
  }
  //< - - - - - - Method to create(hatch) new Ants - - - - - ->
  public Ant hatchAnt(int antCount, ColonyNode[][] nodeList) {
    //the Colony class will class this method once per day, prior to update the rest of the Colony
    int randNum = r.nextInt(100);
    //generate a random number between 0 and 99
    if(randNum < 50) {
      //if randNum is between 0 and 49 (50% chance) generate a new ForagerAnt in Queen Node 13,13
      ForagerAnt fAnt = new ForagerAnt(antCount);
      nodeList[13][13].addForager();
      //return the new Ant to the Colony class to be added to the LinkedList of Ants
      return fAnt;
    } else if(randNum >= 50 && randNum < 75) {
      //if randNum is between 50 and 74 (25% chance) generate a new ScoutAnt in Queen Node 13,13
      ScoutAnt scAnt = new ScoutAnt(antCount);
      nodeList[13][13].addScout();
      //return the new Ant to the Colony class to be added to the LinkedList of Ants
      return scAnt;
    } else {
      //if randNum is between 75 and 99 generate a new SoldierAnt in Queen Node 13,13
      SoldierAnt soAnt = new SoldierAnt(antCount);
      nodeList[13][13].addSoldier();
      //return the new Ant to the Colony class to be added to the LinkedList of Ants
      return soAnt;
    }
  }
}
