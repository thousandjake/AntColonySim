//Filename: BalaAnt.java
//Written By: Jake Thousand
//Written On: 2017-12-09
//Description: BalaAnt class for Ant Colony Simulator

import java.util.*;

public class BalaAnt implements Ant {
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
  public BalaAnt(int antID, ColonyNode[][] nodeList) {
    //assign an antID based on hatching order 
    this.antID = antID;
    //A different antType for each Ant Type
    //Queen = 0, Forager = 1, Scout = 2, Soldier = 3, Bala = 4  
    antType = 4;
    //starting age is 1 turn
    currentAge = 1;
    //max age is 1 year = 365 days = 3650 turns
    maxAge = 3650;
    //set default x and y coordinates to top left corner of grid
    xCoordinate = 0;
    yCoordinate = 0;
    //run generateLocation method to determine random entrance for Bala into Colony
    generateLocation(nodeList);
    //Bala is initially alive
    isAlive = true;
  }
  //< - - - - - - Get and Set Methods for the antID attribute - - - - - - >
  public int getAntID() {
    return antID;
  }
  public void setAntID(int antID) {
    this.antID = antID;
  }
  //< - - - - - - Get Method for the antType attribute - - - - - - >
  public int getAntType() {
    return antType;
  }
  //< - - - - - - Method to Generate Random Entrance Node - - - - - - >
  public void generateLocation(ColonyNode[][] nodeList) {
    //ArrayList possibleLocations stores CoordinateObjects containing Coordinates for ColonyNodes
    ArrayList<CoordinateObject> possibleLocations = new ArrayList<CoordinateObject>();
    //iterate through each of the 106 Nodes on the edge of the Colony, adding them to ArrayList
    for(int x = 0; x < 26; ++x) {
      CoordinateObject oneCO = new CoordinateObject(x, 0);
      possibleLocations.add(oneCO);
      CoordinateObject twoCO = new CoordinateObject(x, 26);
      possibleLocations.add(twoCO);
      if(x != 0) {
        CoordinateObject threeCO = new CoordinateObject(0, x);
        possibleLocations.add(threeCO);
      }
      if(x != 26) {
        CoordinateObject fourCO = new CoordinateObject(26, x);
        possibleLocations.add(fourCO);
      }
    }
    //generate a random int between 0 and 105
    int randomChoice = r.nextInt(possibleLocations.size());
    //select the CoordinateObject based on random int
    CoordinateObject balaSquare =  possibleLocations.get(randomChoice);
    //set new xCoordinate
    xCoordinate = balaSquare.getXCoordinate();
    //set new yCoordinate
    yCoordinate = balaSquare.getYCoordinate();
    //add BalaAnt to ColonyNode
    nodeList[xCoordinate][yCoordinate].addBala();
  }
  //< - - - - - - Get, Set, and Increment Methods for the currnetAge attribute - - - - - ->
  public int getCurrentAge() {
    return currentAge;
  }
  public void setCurrentAge(int currentAge) {
    this.currentAge = currentAge;
  }
  public void incrementCurrentAge() {
    currentAge++;
  }
  //< - - - - - - Get and Set Methods for the max age attribute - - - - - - >
  public int getMaxAge() {
    return maxAge;
  }
  public void setMaxAge(int maxAge) {
    this.maxAge = maxAge;
  }
  //< - - - - - - Get and Set Methods for xCoordinate and yCoordinate - - - - - - >
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
  //< - - - - - - Method to kill Bala Ant if successful Soldier attack - - - - - - >
  public void killAnt() {
    isAlive = false;
  }
  //< - - - - - - Method to check adjacent Nodes for enemy ants - - - - - - >
  public boolean checkForAnts(ColonyNode[][] nodeList) {
    //method returns true if any enemys in current ColonyNode
    ColonyNode cn = nodeList[xCoordinate][yCoordinate];
    if(cn.getQueenPresent()) {
      return true;
    } else if(cn.getForagerCount() > 0) {
      return true;
    } else if(cn.getScoutCount() > 0) {
      return true;
    } else if(cn.getSoldierCount() > 0) {
      return true;
    } else {
      return false;
    }
  }
  //< - - - - - - Method for Attack Action Behavior - - - - - - >
  public void attackAction(ColonyNode[][] nodeList, LinkedList antList) {
    //create a random integer between 0 and 99
    int attackSwing = r.nextInt(100);
    //if random integer is from 50 - 99 (50%), attack hits
    if(attackSwing >= 50) {
      for(int x = 0; x < antList.size(); ++x) {
        //iterate through LinkList of Ants to find Bala in Node
        if(((Ant)antList.get(x)).getAntType() != 4) {
          //checks antTypes for each Ant, type 4 is BalaAnt
          Ant enemy = ((Ant)antList.get(x));
          //cast the Object as an Ant and get X and Y Coordinates
          int locX = enemy.getXCoordinate();
          int locY = enemy.getYCoordinate();
          if(locX == xCoordinate && locY == yCoordinate) {
            //if enemy ant X and Y Coordinates match ColonyNode of Bala, kill enemy Ant
            enemy.killAnt();
          }
        }
      }
    }
  }
  //< - - - - - - Method for the Move Action Behavior - - - - - - >
  public void moveAction(ColonyNode[][] nodeList, LinkedList antList) {
    //boolean enemiesPresent will hold false unless there are Enemy Ants within move range
    boolean enemiesPresent = false;
    //possibleMoves ArrayList holds CoordinateObjects for possible move actions
    ArrayList<CoordinateObject> possibleMoves = new ArrayList<CoordinateObject>();
    //iterate through all potential moves
    for(int x = -1; x < 2; ++x) {
      for(int y = -1; y < 2; ++y) {
        //set new xCoordinate
        int newX = xCoordinate + x;
        //set new yCoordinate
        int newY = yCoordinate + y;
        if(newX >= 0 && newX <= 26 && newY >= 0 && newY <= 26) {
          //check to make sure new x and y coordinates are within the bounds of the 27x27 grid
            if(!(newX == xCoordinate && newY == yCoordinate)) {
              //check to make sure new ColonyNode is not the current ColonyNode
              if(checkForAnts(nodeList)) {
                //if ColonyNode meets requirements, and contains Enemy ants, set enemiesPresent to true
                enemiesPresent = true;
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
          //check to make sure x and y coordinates are within bounds of 27x27 grid
            if(!(newX == xCoordinate && newY == yCoordinate)) {
              //check to make sure new ColonyNode is not current ColonyNode
              if(enemiesPresent) {
                if(checkForAnts(nodeList)) {
                  //if Enemy ant is present in ColonyNode, add new Coordinate Object to the ArrayList
                  CoordinateObject newCoords = new CoordinateObject(newX, newY);
                  possibleMoves.add(newCoords);
                }
              } else {
                //if no Enemy ant present add all ColonyNodes meeting the other requirements to the ArrayList
                CoordinateObject newCoords = new CoordinateObject(newX, newY);
                possibleMoves.add(newCoords);
              }
            }
        }
      }
    }
    //remove BalaAnt from ColonyNode
    nodeList[xCoordinate][yCoordinate].removeBala();
    //create random integer representing one of the Objects in the ArrayList
    int randomChoice = r.nextInt(possibleMoves.size());
    //create a CoordinateObject from the chosen Object
    CoordinateObject newCO = possibleMoves.get(randomChoice);
    //set xCoordinate to new xCoordinate
    xCoordinate = newCO.getXCoordinate();
    //set yCoordinate to new yCoordinate
    yCoordinate = newCO.getYCoordinate();
    //add Bala to the new ColonyNode;
    nodeList[xCoordinate][yCoordinate].addBala();
    //if Enemy Ant is present, call attack action
    if(checkForAnts(nodeList)) {
      attackAction(nodeList, antList);
    }
 
  }
  //< - - - - - - Update Methods covers all Bala actions for eaach turn update - - - - - - >
  public boolean update(int turnsCount, ColonyNode[][] nodeList, LinkedList antList) {
    if(!isAlive) {
      //if Bala is dead from attack, return false, take no action
      return false;
    } else if(turnsCount % 10 == 0 && currentAge >= maxAge) {
      //at the start of the new day, check to see if ant has died of old age
      //if ant has died, return false, and take no acton
      return false;
    } else {
      //if ant is not dead, perform actions, increment age, return true
      if(checkForAnts(nodeList)) {
        //if there are enemies present, attack action
        attackAction(nodeList, antList);
      } else {
        //if no enemies present, movement action
        moveAction(nodeList, antList);
      }
      incrementCurrentAge();
      return true;
    }
  }
}
