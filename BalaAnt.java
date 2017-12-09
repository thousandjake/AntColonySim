//Filename: BalaAnt.java
//Written By: Jake Thousand
//Written On: 2017-11-27
//Description:

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
    this.antID = antID;
    antType = 4;
    currentAge = 1;
    maxAge = 3650;
    xCoordinate = 0;
    yCoordinate = 0;
    generateLocation(nodeList);
    isAlive = true;
  }
  //get and set methods
  public int getAntID() {
    return antID;
  }
  public void setAntID(int antID) {
    this.antID = antID;
  }
  public void generateLocation(ColonyNode[][] nodeList) {
    ArrayList<CoordinateObject> possibleLocations = new ArrayList<CoordinateObject>();
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
    int randomChoice = r.nextInt(possibleLocations.size());
    CoordinateObject balaSquare =  possibleLocations.get(randomChoice);
    xCoordinate = balaSquare.getXCoordinate();
    yCoordinate = balaSquare.getYCoordinate();
    nodeList[xCoordinate][yCoordinate].addBala();
  }
  public int getAntType() {
    return antType;
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
  public boolean checkAlive() {
    return isAlive;
  }
  public void killAnt() {
    isAlive = false;
  }
  public boolean checkForAnts(ColonyNode[][] nodeList) {
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
  public void attackAction(ColonyNode[][] nodeList, LinkedList antList) {
    //attack logic
    int attackSwing = r.nextInt(100);
    if(attackSwing >= 50) {
      //hit
      for(int x = 0; x < antList.size(); ++x) {
        if(((Ant)antList.get(x)).getAntType() != 4) {
          Ant enemy = ((Ant)antList.get(x));
          int locX = enemy.getXCoordinate();
          int locY = enemy.getYCoordinate();
          if(locX == xCoordinate && locY == yCoordinate) {
            enemy.killAnt();
          }
        }
      }
    }
  }
  public void moveAction(ColonyNode[][] nodeList, LinkedList antList) {
    //need moveAction logic
    boolean enemiesPresent = false;
    ArrayList<CoordinateObject> possibleMoves = new ArrayList<CoordinateObject>();
    for(int x = -1; x < 2; ++x) {
      for(int y = -1; y < 2; ++y) {
        int newX = xCoordinate + x;
        int newY = yCoordinate + y;
        if(newX >= 0 && newX <= 26 && newY >= 0 && newY <= 26) {
            if(!(newX == xCoordinate && newY == yCoordinate)) {
              if(checkForAnts(nodeList)) {
                enemiesPresent = true;
              }
            }
        }
      }
    }
    for(int x = -1; x < 2; ++x) {
      for(int y = -1; y < 2; ++y) {
        int newX = xCoordinate + x;
        int newY = yCoordinate + y;
        if(newX >= 0 && newX <= 26 && newY >= 0 && newY <= 26) {
            if(!(newX == xCoordinate && newY == yCoordinate)) {
              if(enemiesPresent) {
                if(checkForAnts(nodeList)) {
                  CoordinateObject newCoords = new CoordinateObject(newX, newY);
                  possibleMoves.add(newCoords);
                }
              } else {
                CoordinateObject newCoords = new CoordinateObject(newX, newY);
                possibleMoves.add(newCoords);
              }
            }
        }
      }
    }
    nodeList[xCoordinate][yCoordinate].removeBala();
    int randomChoice = r.nextInt(possibleMoves.size());
    CoordinateObject newCO = possibleMoves.get(randomChoice);
    xCoordinate = newCO.getXCoordinate();
    yCoordinate = newCO.getYCoordinate();
    nodeList[xCoordinate][yCoordinate].addBala();
    if(checkForAnts(nodeList)) {
      attackAction(nodeList, antList);
    }
 
  }
  public boolean update(int turnCount, ColonyNode[][] nodeList, LinkedList antList) {
    //need updateAction logic
    System.out.println(" - - Bala Update Triggered !!! - - ");
    if(!isAlive) {
      return false;
    } else {
      if(checkForAnts(nodeList)) {
        attackAction(nodeList, antList);
      } else {
        moveAction(nodeList, antList);
      }
      return true;
    }
  }
}
