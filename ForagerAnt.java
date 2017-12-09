//Filename: ForagerAnt.java
//Written By: Jake Thousand
//Written On: 2017-11-27
//Description:

import java.util.*;

public class ForagerAnt implements Ant {
  //attributes
  private int antID;
  private int antType;
  private int currentAge;
  private int maxAge;
  private int xCoordinate;
  private int yCoordinate;
  private int prevXCoord;
  private int prevYCoord;
  private boolean isAlive;
  private boolean isCarryingFood;
  private Stack<CoordinateObject> moveHistory;
  //static random generator
  private static Random r = new Random();
  //constructor
  public ForagerAnt(int antID) {
    this.antID = antID;
    antType = 1;
    currentAge = 1;
    maxAge = 3650;
    xCoordinate = 13;
    yCoordinate = 13;
    isAlive = true;
    prevXCoord = 13;
    prevYCoord = 13;
    isCarryingFood = false;
    moveHistory = new Stack<CoordinateObject>();
    CoordinateObject cO = new CoordinateObject(13,13);
    moveHistory.push(cO);
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
  public boolean getIsCarryingFood() {
    return isCarryingFood;
  }
  public void setIsCarryingFood(boolean isCarryingFood) {
    this.isCarryingFood = isCarryingFood;
  }
  public void pickUpFood(ColonyNode[][] nodeList) {
    nodeList[xCoordinate][yCoordinate].removeFoodUnit();
    setIsCarryingFood(true);
    moveHistory.pop();
  }
  public void depositFood(ColonyNode[][] nodeList) {
    nodeList[xCoordinate][yCoordinate].addFoodUnit();
    setIsCarryingFood(false);
  }
  public void depositPheromone(ColonyNode[][] nodeList) {
    int p = nodeList[xCoordinate][yCoordinate].getPheromoneLevel()+10;
    nodeList[xCoordinate][yCoordinate].setPheromoneLevel(p);
  }
  public void clearMoveHistory() {
    while(!moveHistory.empty()) {
      moveHistory.pop();
    }
    CoordinateObject cO = new CoordinateObject(xCoordinate, yCoordinate);
    moveHistory.push(cO);
  }
  public void forageMoveAction(ColonyNode[][] nodeList) {
    //need moveAction logic
    int maxPheromone = 0;
    ArrayList<CoordinateObject> coordList = new ArrayList<CoordinateObject>();
    for(int x = -1; x < 2; ++x) {
      for(int y = -1; y < 2; ++y) {
        int newX = xCoordinate + x;
        int newY = yCoordinate + y;
        if(newX >= 0 && newX <= 26 && newY >= 0 && newY <= 26) {
          if(nodeList[newX][newY].getNodeVisibility()) {
            if(nodeList[newX][newY].getPheromoneLevel() > maxPheromone) {
              if(!(prevXCoord == newX && prevYCoord == newY)) {
                //do nothing
                if(!(xCoordinate == newX && yCoordinate == newY)) {
                  maxPheromone = nodeList[newX][newY].getPheromoneLevel();
                }
              }
            }
          }
        }
      }
    }
    System.out.println(" - - - maxPheromone: "+maxPheromone+" - - - ");
    for(int a = -1; a < 2; ++a) {
      for(int b = -1; b < 2; ++b) {
        int newX = xCoordinate + a;
        int newY = yCoordinate + b;
        if(newX >= 0 && newX <= 26 && newY >= 0 && newY <= 26) {
          if(nodeList[newX][newY].getNodeVisibility()) {
            if(nodeList[newX][newY].getPheromoneLevel() >= maxPheromone) {
              if(!(prevXCoord == newX && prevYCoord == newY)) {
                //do nothing
                if(!(xCoordinate == newX && yCoordinate == newY)) {
                  CoordinateObject newCoords = new CoordinateObject(newX, newY);
                  coordList.add(newCoords);
                }
              }
            }
          }
        }
      }
    }
    System.out.println(" - - - ArrayLength: "+coordList.size()+" - - - ");
    prevXCoord = xCoordinate;
    prevYCoord = yCoordinate;
    if(coordList.size() > 0) {
      nodeList[xCoordinate][yCoordinate].removeForager();
      int randomChoice = r.nextInt(coordList.size());
      CoordinateObject newCO = coordList.get(randomChoice);
      xCoordinate = newCO.getXCoordinate();
      yCoordinate = newCO.getYCoordinate();
      nodeList[xCoordinate][yCoordinate].addForager();
      moveHistory.push(newCO);
    }
  }
  public void returnMoveAction(ColonyNode[][] nodeList) {
    //need moveAction logic
    CoordinateObject cO = moveHistory.pop();
    nodeList[xCoordinate][yCoordinate].removeForager();
    xCoordinate = cO.getXCoordinate();
    yCoordinate = cO.getYCoordinate();
    nodeList[xCoordinate][yCoordinate].addForager();
  }
  public boolean update(int turnsCount, ColonyNode[][] nodeList, LinkedList antList) {
    //need update logic
    System.out.println(" - - Forager Update Triggered !!! - - ");
    if(!isAlive) {
      return false;
    } else {
      if(isCarryingFood) {
        System.out.println(" - - - Return-to-movement mode !!! - - - ");
        if(nodeList[xCoordinate][yCoordinate].getPheromoneLevel() < 1000) {
          System.out.println(" - - - Depositing Pheromone !!! - - - ");
          depositPheromone(nodeList);
        }
        returnMoveAction(nodeList);
        if(xCoordinate == 13 && yCoordinate == 13) {
          System.out.println(" - - - Depositing Food / Clear Move History !!! - - - ");
          depositFood(nodeList);
          clearMoveHistory();
        }
      } else {
        System.out.println(" - - - Forager movement !!! - - - ");
        forageMoveAction(nodeList);
        if(nodeList[xCoordinate][yCoordinate].getFoodLevel() > 0) {
          if(xCoordinate == 13 && yCoordinate == 13) {
            //do nothing
          } else {
            System.out.println(" - - - Picking Up Food !!! - - - ");
            pickUpFood(nodeList);
          }
        }
      }
      return true;
    }
  }
}
