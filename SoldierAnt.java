//Filename: SoldierAnt.java
//Written By: Jake Thousand
//Written On: 2017-12-09
//Description: SoldierAnt class for Ant Colony Simulator

import java.util.*;

public class SoldierAnt implements Ant {
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
  public SoldierAnt(int antID) {
    //assign an antID based on hatching order
    this.antID = antID;
     //A different antType for each Ant Type
    //Queen = 0, Forager = 1, Scout = 2, Soldier = 3, Bala = 4  
    antType = 3;
    //starting age is 1 turn
    currentAge = 1;
    //max age is 1 year = 365 days = 3650 turns
    maxAge = 3650;
    //All Soldiers start in the middle of the Grid, Node 13,13
    xCoordinate = 13;
    yCoordinate = 13;
    //Soldier is initially alive
    isAlive = true;
  }
  //< - - - - - Get and Set Methods for the antID - - - - - - >
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
  //< - - - - - - Get, Set, Increment Methods for the currentAge attribute - - - - - - >
  public int getCurrentAge() {
    return currentAge;
  }
  public void setCurrentAge(int currentAge) {
    this.currentAge = currentAge;
  }
  public void incrementCurrentAge() {
    currentAge++;
  }
  //< - - - - - - Get and Set Methods for maxAge attribute - - - - - - >
  public int getMaxAge() {
    return maxAge;
  }
  public void setMaxAge(int maxAge) {
    this.maxAge = maxAge;
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
  //< - - - - - - Method to check if Soldier is still alive - - - - - - >
  public boolean checkAlive() {
    return isAlive;
  }
  //< - - - - - - Method to kill Soldier Ant if succesful Bala attack - - - - - - >
  public void killAnt() {
    isAlive = false;
  }
  //< - - - - - - Method for the Attack Mode Behavior - - - - - - >
  public void attackAction(ColonyNode[][] nodeList, LinkedList antList) {
    //create a random integer between 0 and 99
    int attackSwing = r.nextInt(100);
    //if random integer is from 50 - 99 (50%), attack hits
    if(attackSwing >= 50) {
      for(int x = 0; x < antList.size(); ++x) {
        //iterate through LinkedList of Ants to find Bala in Node
        if(((Ant)antList.get(x)).getAntType() == 4) {
          //checks antTypes for each Ant, type 4 is BalaAnt
          BalaAnt enemy = (BalaAnt)antList.get(x);;
          //cast the Ant as a BalaAnt and get X and Y Coordinates
          int locX = enemy.getXCoordinate();
          int locY = enemy.getYCoordinate();
          if(locX == xCoordinate && locY == yCoordinate) {
            //If BalaAnt X and Y Coordinates match ColonyNode of Soldier, kill Bala
            enemy.killAnt();
          }
        }
      }
    }
  }
  //< - - - - - - Method for the Move Action Behvaior - - - - - - >
  public void moveAction(ColonyNode[][] nodeList, LinkedList antList) {
    //boolean balasPresent will hold false unless there are Balas within move range 
    boolean balasPresent = false;
    //possibleMoves ArrayList holds CoordinateObjects for possible move actions
    ArrayList<CoordinateObject> possibleMoves = new ArrayList<CoordinateObject>();
    //iterate through all potential moves
    for(int x = -1; x < 2; ++x) {
      for(int y = -1; y < 2; ++y) {
        //set new xCoodinate
        int newX = xCoordinate + x;
        //set new yCoordinate
        int newY = yCoordinate + y;
        if(newX >= 0 && newX <= 26 && newY >= 0 && newY <= 26) {
          //check to make sure new x and y coordinates are within bounds of the 27x27 grid
          if(nodeList[newX][newY].getNodeVisibility()) {
            //check to make sure new ColonyNode is visible
            if(!(newX == xCoordinate && newY == yCoordinate)) {
              //check to make sure new ColonyNode is not the current ColonyNode
              if(nodeList[newX][newY].getBalaCount() > 0) {
                //if ColonyNode meets requirements, and contains Balas, set balasPresent to true
                balasPresent = true;
              }
            }
          }
        }
      }
    }
    //iterate through all potential moves
    for(int x = -1; x < 2; ++x) {
      for(int y = -1; y < 2; ++y) {
        //set new xCoordinate
        int newX = xCoordinate + x;
        //set new yCoordinate
        int newY = yCoordinate + y;
        if(newX >= 0 && newX <= 26 && newY >= 0 && newY <= 26) {
          //check to make sure new x and y coordinates are within bounds of 27x27 grid
          if(nodeList[newX][newY].getNodeVisibility()) {
            //check to make sure new ColonyNode is visible
            if(!(newX == xCoordinate && newY == yCoordinate)) {
              //check to make sure new ColonyNode is not the current ColonyNode
              if(balasPresent) {
                if(nodeList[newX][newY].getBalaCount() > 0) {
                  //if Bala is present in ColonyNode, add new CoordinateObject with new Coordinates into the possibleMoves ArrayList
                  CoordinateObject newCoords = new CoordinateObject(newX, newY);
                  possibleMoves.add(newCoords);
                }
              } else {
                //if no Balas are present add all ColonyNodes meeting the other requiremnets to the ArrayList
                CoordinateObject newCoords = new CoordinateObject(newX, newY);
                possibleMoves.add(newCoords);
              }
            }
          }
        }
      }
    }
    if(possibleMoves.size() > 0) {
      //remove Soldier from ColonyNode
      nodeList[xCoordinate][yCoordinate].removeSoldier();
      //create random integer representing one of the Objects in the ArrayList
      int randomChoice = r.nextInt(possibleMoves.size());
      //create a CoordinateObject from the chosen Object
      CoordinateObject newCO = possibleMoves.get(randomChoice);
      //set xCoordinate to new xCoordinate
      xCoordinate = newCO.getXCoordinate();
      //set yCoordinate to new yCoordinate
      yCoordinate = newCO.getYCoordinate();
      //add Soldier to new ColonyNode
      nodeList[xCoordinate][yCoordinate].addSoldier();
      //if BalaAnt is present, call attack action
      if(nodeList[xCoordinate][yCoordinate].getBalaCount() > 0) {
        attackAction(nodeList, antList);
      }
    }
  }
  //< - - - - - - Update Methods covers all Soldier actions for each turn update - - - - - - >
  public boolean update(int turnsCount, ColonyNode[][] nodeList, LinkedList antList) {
    if(!isAlive) {
      //if Soldier is dead from attack, return false, take no action
      return false;
    } else if(turnsCount % 10 == 0 && currentAge >= maxAge) {
      //at the start of the new day, check to see if ant has died of old age
      //if ant has died, return false, and take no action
      return false;
    } else {
      //if ant is not dead, perform actions, and return true
      if(nodeList[xCoordinate][yCoordinate].getBalaCount() > 0) {
        //if there are Balas in the ColonyNode, attack
        attackAction(nodeList, antList);
      } else {
        //if there are no Balas in the ColonyNode, move
        moveAction(nodeList, antList);
      }
      incrementCurrentAge();
      return true;
    }
  }
}
