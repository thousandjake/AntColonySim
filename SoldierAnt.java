//Filename: SoldierAnt.java
//Written By: Jake Thousand
//Written On: 2017-11-27
//Description:

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
    this.antID = antID;
    antType = 3;
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
  public void setAntID(int antID) {
    this.antID = antID;
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
  public void attackAction(ColonyNode[][] nodeList, LinkedList antList) {
    //attack action
    int attackSwing = r.nextInt(100);
    if(attackSwing >= 50) {
      //hit
      for(int x = 0; x < antList.size(); ++x) {
        if(((Ant)antList.get(x)).getAntType() == 4) {
          BalaAnt enemy = (BalaAnt)antList.get(x);;
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
    boolean balasPresent = false;
    ArrayList<CoordinateObject> possibleMoves = new ArrayList<CoordinateObject>();
    for(int x = -1; x < 2; ++x) {
      for(int y = -1; y < 2; ++y) {
        int newX = xCoordinate + x;
        int newY = yCoordinate + y;
        if(newX >= 0 && newX <= 26 && newY >= 0 && newY <= 26) {
          if(nodeList[newX][newY].getNodeVisibility()) {
            if(!(newX == xCoordinate && newY == yCoordinate)) {
              if(nodeList[newX][newY].getBalaCount() > 0) {
                balasPresent = true;
              }
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
          if(nodeList[newX][newY].getNodeVisibility()) {
            if(!(newX == xCoordinate && newY == yCoordinate)) {
              if(balasPresent) {
                if(nodeList[newX][newY].getBalaCount() > 0) {
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
    }
    nodeList[xCoordinate][yCoordinate].removeSoldier();
    int randomChoice = r.nextInt(possibleMoves.size());
    CoordinateObject newCO = possibleMoves.get(randomChoice);
    xCoordinate = newCO.getXCoordinate();
    yCoordinate = newCO.getYCoordinate();
    nodeList[xCoordinate][yCoordinate].addSoldier();
    if(nodeList[xCoordinate][yCoordinate].getBalaCount() > 0) {
      attackAction(nodeList, antList);
    }
  }
  public boolean update(int turnCount, ColonyNode[][] nodeList, LinkedList antList) {
    //need updateAction logic
    System.out.println(" - - Soldier Update Triggered !!! - - ");
    if(!isAlive) {
      return false;
    } else {
      if(nodeList[xCoordinate][yCoordinate].getBalaCount() > 0) {
        attackAction(nodeList, antList);
      } else {
        moveAction(nodeList, antList);
      }
      return true;
    }
  }
}
