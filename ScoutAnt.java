//Filename: ScoutAnt.java
//Written By: Jake Thousand
//Written On: 2017-12-09
//Description: ScoutAnt class for Ant Colony Simulator

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
    //assign an antID based on hatching order
    this.antID = antID;
     //A different antType for each Ant Type
    //Queen = 0, Forager = 1, Scout = 2, Soldier = 3, Bala = 4  
    antType = 2;
    //starting age is 1 turn
    currentAge = 1;
    //max age is 1 year = 365 days = 3650 turns
    maxAge = 3650;
    //All Scouts start in the middle of the Grid, Node 13,13
    xCoordinate = 13;
    yCoordinate = 13;
    //Scout is initially alive 
    isAlive = true;
  }
  //< - - - - - - Get and Set Methods for the antID - - - - - - >
  public int getAntID() {
    return antID;
  }
  public void setAntID(int antID) {
    this.antID = antID;
  }
  //< - - - - - - Get Method for antType attribute - - - - - - >
  public int getAntType() {
    return antType;
  }
  //< - - - - - - Get, Set, and Increment Methods for the currentAge attribute - - - - - ->
  public int getCurrentAge() {
    return currentAge;
  }
  public void setCurrentAge(int currentAge) {
    this.currentAge = currentAge;
  }
  public void incrementCurrentAge() {
    currentAge++;
  }
  //< - - - - - - Get and Set Methods for the maxAge attribute - - - - - - >
  public int getMaxAge() {
    return maxAge;
  }
  public void setMaxAge(int maxAge) {
    this.maxAge = maxAge;
  }
  //< - - - - - - Method to check if Scout is still alive - - - - - - >
  public boolean checkAlive() {
    return isAlive;
  }
  //< - - - - - - Method to kill Scout Ant if successful Bala attack - - - - - - >
  public void killAnt() {
    isAlive = false;
  }
  //< - - - - - - Get and Set Methods for X and Y Coordinates - - - - - - >
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
  //< - - - - - - Method for Random Movement Action Behavior - - - - - - >
  public void moveAction(ColonyNode[][] nodeList) {
    //ArrayList to hold CoordinateObjects with coordinates for possible move actions
    ArrayList<CoordinateObject> possibleMoves = new ArrayList<CoordinateObject>();
    //iterate to get all Nodes within movement range
    for(int x=-1; x < 2; ++x) {
      for(int y = -1; y < 2; ++y) {
        //set xCoordinate
        int newX = xCoordinate + x;
        //set yCoordinate
        int newY = yCoordinate + y;
        if( newX >= 0 && newX <= 26 && newY >= 0 && newY <=26) {
          //check to make sure new Coordinates are not outside the bounds of the 27x27 grid
          if(!(newX == xCoordinate && newY == yCoordinate)) {
            //check to make sure new ColonyNode is not the current ColonyNode
            CoordinateObject cO = new CoordinateObject(newX, newY);
            //create a new CoordinateObject with new X and Y coordinates and push intoArrayList
            possibleMoves.add(cO);
          }
        }
      }
    }
    //remove ScoutAnt from current ColonyNode
    nodeList[xCoordinate][yCoordinate].removeScout();
    //create a random number based on the number of moves possibele
    int randomChoice = r.nextInt(possibleMoves.size());
    //set xCoordinate to new Coordinate randomly chosen
    xCoordinate = possibleMoves.get(randomChoice).getXCoordinate();
    //set yCoordinate to new Coordinate randomly chosen
    yCoordinate = possibleMoves.get(randomChoice).getYCoordinate();
    //add ScoutAnt to new ColonyNode
    nodeList[xCoordinate][yCoordinate].addScout();
    //set new ColonyNode to visible
    nodeList[xCoordinate][yCoordinate].showNode();
  }
  //< - - - - - - Update Methods covers all Scout actions for each turn update - - - - - - >
  public boolean update(int turnsCount, ColonyNode[][] nodeList, LinkedList antList) {
    if(!isAlive) {
      //if Scout is dead from attack, return false, take no actions
      return false;
    } else if(turnsCount % 10 == 0 && currentAge >= maxAge) {
      //at the start of the new day, check to see if ant has died of old age
      //if ant has died, return false, and take no action
      return false;
    } else {
      //if ant is not dead, perform moveAction, and return true
      moveAction(nodeList);
      incrementCurrentAge();
      return true;
    }
  }
}
