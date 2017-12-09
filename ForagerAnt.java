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
    //assign an antID based on hatching order
    this.antID = antID;
    //A different antType for each Ant Type
    //Queen = 0, Forager = 1, Scout = 2, Soldier = 3, Bala = 4
    antType = 1;
    //starting age is 1 turn
    currentAge = 1;
    //max age is 1 year = 365 days = 3650 turns
    maxAge = 3650;
    //All Foragers start in the middle of the Grid, Node 13,13
    xCoordinate = 13;
    yCoordinate = 13;
    //Forager is initially Alive
    isAlive = true;
    //Foragers have attributes to track current and previous Nodes to prevent looping
    //prevXCoord is the previous Nodes X Coordinate
    prevXCoord = 13;
    //prevYCoord is the previous Nodes Y Coordinate
    prevYCoord = 13;
    //initially the Forager is foraging and not carrying any food
    isCarryingFood = false;
    //moveHistory is a stack object containing CoordinateObjects
    //CoordinateObject class contains two attributes for xCoordinate and yCoordinate in the colony grid
    moveHistory = new Stack<CoordinateObject>();
    //CoordinateObject initially holds middle Grid coordinates, 13,13
    CoordinateObject cO = new CoordinateObject(13,13);
    //push the initial CoordinateObject into the stack for later use
    moveHistory.push(cO);
  }
  //< - - - - - - Get and Set Methods for the antID - - - - - - >
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
  //< - - - - - - Get, Set, and Increment Methods for the currentAge attribute - - - - - - >
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
  //< - - - - - - Get and Set Methods for the current X and Y Coordinates - - - - - - >
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
  //< - - - - - - Method to check if Forager Ant is still alive - - - - - - >
  public boolean checkAlive() {
    return isAlive;
  }
  //< - - - - - - Method to kill Forager Ant if successful Bala attack - - - - - - >
  public void killAnt() {
    isAlive = false;
  }
  //< - - - - - - Get, Set, Pick-Up, and Deposit Methods for isCarryingFood attribute - - - - - - >
  public boolean getIsCarryingFood() {
    return isCarryingFood;
  }
  public void setIsCarryingFood(boolean isCarryingFood) {
    this.isCarryingFood = isCarryingFood;
  }
  public void pickUpFood(ColonyNode[][] nodeList) {
    //method removes one unit of food from target node
    nodeList[xCoordinate][yCoordinate].removeFoodUnit();
    //isCarryingFood attribute is set to true
    setIsCarryingFood(true);
    //current space is removed (popped) from moveHistory stack
    //this is to prevent the Forager for sitting in Node with food more than 1 turn
    moveHistory.pop();
  }
  public void depositFood(ColonyNode[][] nodeList) {
    //method adds 1 unit of Food to target node
    nodeList[xCoordinate][yCoordinate].addFoodUnit();
    //isCarryingFood attribute is set to false
    setIsCarryingFood(false);
  }
  //< - - - - - - Method to Deposit 10 units of pheromone when in Return-to-Nest Mode - - - - - - >
  public void depositPheromone(ColonyNode[][] nodeList) {
    //p is equal to current node's pheromoneLevel attribute + 10 units
    int p = nodeList[xCoordinate][yCoordinate].getPheromoneLevel()+10;
    //ColonyNode pheromone level set to p
    nodeList[xCoordinate][yCoordinate].setPheromoneLevel(p);
  }
  //< - - - - - - Method to clear moveHistory stack once Return-to-Nest Mode is completed - - - - - - >
  public void clearMoveHistory() {
    //while the stack is not empty, continue to iterate
    while(!moveHistory.empty()) {
      //pop a new CoordinateObject off the stack until stack is empty
      moveHistory.pop();
    }
    CoordinateObject cO = new CoordinateObject(xCoordinate, yCoordinate);
    //create a new initial CoordinateObject and push it onto the stack
    moveHistory.push(cO);
  }
  //< - - - - - - ForageMoveAction method covers Ant movement behavior when in Forage Mode - - - - - - >
  public void forageMoveAction(ColonyNode[][] nodeList) {
    //maxPheromone will hold the highest PheromoneLevel found in the Nodes around the Ant
    int maxPheromone = 0;
    //coordList will hold CoordinateObjects representing potential nodes for the Ant to move to
    ArrayList<CoordinateObject> coordList = new ArrayList<CoordinateObject>();
    //iterate to get all Nodes within movement range
    for(int x = -1; x < 2; ++x) {
      for(int y = -1; y < 2; ++y) {
        //new xCoordinate
        int newX = xCoordinate + x;
        //new yCoordinate
        int newY = yCoordinate + y;
        if(newX >= 0 && newX <= 26 && newY >= 0 && newY <= 26) {
          //check to make sure new Coordinates are not outside the bounds of the 27x27 grid
          if(nodeList[newX][newY].getNodeVisibility()) {
            //check to make sure the new Coordinates represnt a visible ColonyNode
            if(nodeList[newX][newY].getPheromoneLevel() > maxPheromone) {
              //check to the pheromone level of the new ColonyNode against the maxPheromone found
              if(!(prevXCoord == newX && prevYCoord == newY)) {
                //this if statement checks to make sure the new ColonyNode isn't the Node previously moved from
                //this will help prevent looping movements for the Forager Ant
                if(!(xCoordinate == newX && yCoordinate == newY)) {
                  //check to makre sure that the new ColonyNode is not the current ColonyNode the Ant is in
                  //if the ColonyNode meets all of these requirements, replace the maxPheromoneLevel with the pheromoneLevel of the new Node
                  maxPheromone = nodeList[newX][newY].getPheromoneLevel();
                }
              }
            }
          }
        }
      }
    }
    //iterate through the potential nodes again and store CoordinateObject for any Nodes with pheromoneLevels matching the max available
    for(int a = -1; a < 2; ++a) {
      for(int b = -1; b < 2; ++b) {
        //new xCoordinate
        int newX = xCoordinate + a;
        //new yCoordinate
        int newY = yCoordinate + b;
        if(newX >= 0 && newX <= 26 && newY >= 0 && newY <= 26) {
          //check to make suer new Coordinates are not outside the bounds of the 27x27 grid
          if(nodeList[newX][newY].getNodeVisibility()) {
            //check to makre sure the new Coordinates represent a visible ColonyNode
            if(nodeList[newX][newY].getPheromoneLevel() >= maxPheromone) {
              //check to make sure the ColonyNode phermoneLevel matches the max available for movement
              if(!(prevXCoord == newX && prevYCoord == newY)) {
                //this if statement checks to make sure the new ColonyNode isn't the Node previously moved from
                //this will help prevent looping movements for the Forager Ant
                if(!(xCoordinate == newX && yCoordinate == newY)) {
                  //check to make sure the new ColonyNode is not the current ColonyNode the Ant is in
                  //if the ColonyNode meets all these requirements, create a CoordinateObject with the X and Y coordinates and add to ArrayList
                  CoordinateObject newCoords = new CoordinateObject(newX, newY);
                  coordList.add(newCoords);
                }
              }
            }
          }
        }
      }
    }
    //replace the attributes for previous X and Y coordinates with the current coordinates
    prevXCoord = xCoordinate;
    prevYCoord = yCoordinate;
    //if the ArrayList is empty(no moves meeting requirements), then do not move this turn
    if(coordList.size() > 0) {
      //remove forager from current ColonyNode
      nodeList[xCoordinate][yCoordinate].removeForager();
      //generate a random number reprenting the number of move choices
      int randomChoice = r.nextInt(coordList.size());
      //get the CoordinateObject from the ArrayList based on randomChoice
      CoordinateObject newCO = coordList.get(randomChoice);
      //set xCoordinate to new Node xCoordinate
      xCoordinate = newCO.getXCoordinate();
      //set ycoordinate to new Node yCoordinate
      yCoordinate = newCO.getYCoordinate();
      //add Forager to new ColonyNode
      nodeList[xCoordinate][yCoordinate].addForager();
      //push CoorindateObject representing move onto the moveHistory stack
      moveHistory.push(newCO);
    }
  }
  //< - - - - - - ReturnMoveAction method holds behavior for Return-to-Nest Mode movement - - - - - - >
  public void returnMoveAction(ColonyNode[][] nodeList) {
    //pop the last CoordinateObject off the moveHistory stack
    CoordinateObject cO = moveHistory.pop();
    //remove forager from current ColonyNode
    nodeList[xCoordinate][yCoordinate].removeForager();
    //set xCoordinate to new xCoordinate
    xCoordinate = cO.getXCoordinate();
    //set yCoordinate to new yCoordinate
    yCoordinate = cO.getYCoordinate();
    //add forager to new ColonyNode
    nodeList[xCoordinate][yCoordinate].addForager();
  }
  //< - - - - - - Update Method covers all Forager actions for each turn update - - - - - - >
  public boolean update(int turnsCount, ColonyNode[][] nodeList, LinkedList antList) {
    if(!isAlive) {
      //if Forager is dead from attack, return false, take no actions
      return false;
    } else if(turnsCount%10 == 0 && currentAge >= maxAge) {
      //at the start of the new day, check to see if ant has died of old age
      //if ant has died return false, take no action
      return false;
    } else {
      //if forager is not dead, increase age by 1
      incrementCurrentAge();
      if(isCarryingFood) {
        //if forager is carrying food, perform Return-to-Nest actions
        if(nodeList[xCoordinate][yCoordinate].getPheromoneLevel() < 1000) {
          //if ColonyNode pheromoneLevel < 1000 units, deposit pheromone
          depositPheromone(nodeList);
        }
        //perform move action
        returnMoveAction(nodeList);
        if(xCoordinate == 13 && yCoordinate == 13) {
          //Deposit food if forage has moved into Queen Node
          depositFood(nodeList);
          //clear moveHistory stack as return trip is now compelte
          clearMoveHistory();
        }
      } else {
        //if ant is not carrying food, perform Forage actions
        //perform move action
        forageMoveAction(nodeList);
        if(nodeList[xCoordinate][yCoordinate].getFoodLevel() > 0 && !(xCoordinate == 13 && yCoordinate == 13)) {
          //if ColonyNode contains food, and is not Queen Node, pick-up food
          pickUpFood(nodeList);
        }
      }
      //return true as Ant is not dead and update successful
      return true;
    }
  }
}
