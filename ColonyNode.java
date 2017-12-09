//Filename: ColonyNode.java
//Written By: Jake Thousand
//Written On: 2017-12-09
//Description: ColonyNode class represents each Node in the Ant Colony Simulator

import java.util.*;

public class ColonyNode {
  //reference to view for ColonyNode
  public ColonyNodeView cnv;
  //static random number generator
  private static Random r = new Random();
  //attributes
  public int xCoord;
  public int yCoord;
  private String nodeID;
  private boolean nodeVisible;
  private int foodLevel;
  private int pheromoneLevel;
  private boolean queenPresent;
  private int foragerCount;
  private int scoutCount;
  private int soldierCount;
  private int balaCount;
  //constructor method
  public ColonyNode(ColonyNodeView cnv, int x, int y) {
    //reference to view for ColonyNode
    this.cnv = cnv; 
    //set x and y coordinates in Colony Grid (Range 0  - 26)
    this.xCoord = x;
    this.yCoord = y;
    //set nodeID based on position in Colony Grid
    setNodeID(x,y);
    //ColonyNodes start hidden for scouting in Simulation
    hideNode();
    //set foodLevel in Node based on random chance
    randomFoodLevel();
    //set pheromoneLevel to 0 to start
    setPheromoneLevel(0);
    //set queenPresent to false, middle Node will have queenPresent
    setQueenPresent(false);
    //all Ant Counts start at 0
    setForagerCount(0);
    setScoutCount(0);
    setSoldierCount(0);
    setBalaCount(0);
  }
  // - - - - - - Get & Set NodeID !!! - - - - - - 
  public String getNodeID() {
    return nodeID;
  }
  public void setNodeID(int x, int y) {
    String newNodeID = ""+x+", "+y;
    nodeID = newNodeID;
    //set nodeID in the view
    cnv.setID(newNodeID);
  }
  // - - - - - - Get & Set Node Visible !!! - - - - - - 
  public boolean getNodeVisibility() {
    return nodeVisible;
  }
  public void showNode() {
    //set attribute to true and make Node visible in view
    nodeVisible = true;
    cnv.showNode();
  }
  public void hideNode() {
    //set attribute to false and make Node hidden in view
    nodeVisible = false;
    cnv.hideNode();
  }
  // - - - - - - Manipulate Food Level !!! - - - - - - 
  public int getFoodLevel() {
    return foodLevel;
  }
  public void setFoodLevel(int foodAmount) {
    //set attribute amount and update quantity in view
    foodLevel = foodAmount;
    cnv.setFoodAmount(foodAmount);
  }
   public void addFoodUnit() {
     //add 1 unit of food and update quantity in view 
    foodLevel++;
    cnv.setFoodAmount(foodLevel);
  }
  public void removeFoodUnit() {
    //remove 1 unit of food and update quantity in view
    foodLevel--;
    cnv.setFoodAmount(foodLevel);
  }
  public void randomFoodLevel() {
    //generate random int between 0 - 99 
    int foodChance = r.nextInt(100);
    //generate random int between 500 - 1000
    int foodAmount = r.nextInt(500)+500;
    //if random int foodChance is between 0 - 25 (25%), Node has food
    if(foodChance < 25) {
      //set foodLevel to random int foodAmount and update  view
      foodLevel = foodAmount;
      cnv.setFoodAmount(foodLevel);
    } 
  }
  // - - - - - - Manipulate Pheromone Level !!! - - - - - - 
  public int getPheromoneLevel() {
    return pheromoneLevel;
  }
  public void setPheromoneLevel(int pheromoneAmount) {
    //set pheromoneLevel and update view
    pheromoneLevel = pheromoneAmount;
    cnv.setPheromoneLevel(pheromoneAmount);
  }
  public void addPheromoneLevel() {
    //add one unit of pheromone and update view
    pheromoneLevel++;
    cnv.setPheromoneLevel(pheromoneLevel);
  }
  // - - - - - - Get & Set QueenPresent !!! - - - - - - 
  public boolean getQueenPresent() {
    return queenPresent;
  }
  public void setQueenPresent(boolean queenStatus) {
    //set queenPresent to true or false and update view / icon accordingly
    queenPresent = queenStatus;
    cnv.setQueen(queenStatus);
    if(queenPresent) {
      cnv.showQueenIcon();
    }
  }
  // - - - - - - FORAGER ANTS !!! - - - - - - 
  public int getForagerCount() {
    return foragerCount;
  }
  public void setForagerCount(int count) {
    if(count < 0) {
      System.out.println(" - - Error Forager Count < 0 !!! - - "); 
    } else if(count == 0) {
      foragerCount = count;
      cnv.setForagerCount(count);
      cnv.hideForagerIcon();
    } else {
      foragerCount = count;
      cnv.setForagerCount(count);
      cnv.showForagerIcon();
    }
  }
  public void addForager() {
    if(foragerCount < 0) {
      System.out.println(" - - Error Forager Count < 0 !!! - - ");
    } else if(foragerCount == 0) {
      ++foragerCount;
      cnv.setForagerCount(foragerCount);
      cnv.showForagerIcon();
    } else {
      ++foragerCount;
      cnv.setForagerCount(foragerCount);
    }
  }
  public void removeForager() {
   if(foragerCount < 1) {
     System.out.println(" - - Error Forager Count < 0 !!! - - ");
   } else if(foragerCount == 1) {
     --foragerCount;
     cnv.setForagerCount(foragerCount);
     cnv.hideForagerIcon();
   } else {
     --foragerCount;
     cnv.setForagerCount(foragerCount);
   }
  }
  // - - - - - - SCOUT ANTS !!! - - - - - - 
  public int getScoutCount() {
    return scoutCount;
  }
  public void setScoutCount(int count) {
    if(count < 0) {
      System.out.println(" - - Error Scout Count < 0 !!! - - ");
    } else if(count == 0) {
      scoutCount = count;
      cnv.setScoutCount(count);
      cnv.hideScoutIcon();
    } else {
      scoutCount = count;
      cnv.setScoutCount(count);
      cnv.showScoutIcon();
    }
  }
  public void addScout() {
    if(scoutCount < 0) {
      System.out.println(" - - Error Scout Count < 0 !!! - - ");
    } else if(scoutCount == 0) {
      ++scoutCount;
      cnv.setScoutCount(scoutCount);
      cnv.showScoutIcon();
    } else {
      ++scoutCount;
      cnv.setScoutCount(scoutCount);
    }
  }
  public void removeScout() {
   if(scoutCount < 1) {
     System.out.println(" - - Error Scout Count < 0 !!! - - ");
   } else if(scoutCount == 1) {
     --scoutCount;
     cnv.setScoutCount(scoutCount);
     cnv.hideScoutIcon();
   } else {
     --scoutCount;
     cnv.setScoutCount(scoutCount);
   }
  }
 // - - - - - - SOLDIER ANTS !!! - - - - - - 
  public int getSoldierCount() {
    return soldierCount;
  }
  public void setSoldierCount(int count) {
    if(count < 0) {
      System.out.println(" - - Error Soldier Count < 0 !!! - - ");
    } else if(count == 0) {
      soldierCount = count;
      cnv.setSoldierCount(count);
      cnv.hideSoldierIcon();
    } else {
      soldierCount = count;
      cnv.setSoldierCount(count);
      cnv.showSoldierIcon();
    }
  }
  public void addSoldier() {
    if(soldierCount < 0) {
      System.out.println(" - - Error Soldier Count < 0 !!! - - ");
    } else if(soldierCount == 0) {
      ++soldierCount;
      cnv.setSoldierCount(soldierCount);
      cnv.showSoldierIcon();
    } else {
      ++soldierCount;
      cnv.setSoldierCount(soldierCount);
    }
  }
  public void removeSoldier() {
   if(soldierCount < 1) {
     System.out.println(" - - Error Soldier Count < 0 !!! - - ");
   } else if(soldierCount == 1) {
     --soldierCount;
     cnv.setSoldierCount(soldierCount);
     cnv.hideSoldierIcon();
   } else {
     --soldierCount;
     cnv.setSoldierCount(soldierCount);
   }
  }
 // - - - - - - BALA ANTS !!! - - - - - - 
  public int getBalaCount() {
    return balaCount;
  }
  public void setBalaCount(int count) {
    if(count < 0) {
      System.out.println(" - - Error Bala Count < 0 !!! - - ");
    } else if(count == 0) {
      balaCount = count;
      cnv.setBalaCount(count);
      cnv.hideBalaIcon();
    } else {
      balaCount = count;
      cnv.setBalaCount(count);
      cnv.showBalaIcon();
    }
  }
  public void addBala() {
    if(balaCount < 0) {
      System.out.println(" - - Error Bala Count < 0 !!! - - ");
    } else if(balaCount == 0) {
      ++balaCount;
      cnv.setBalaCount(balaCount);
      cnv.showBalaIcon();
    } else {
      ++balaCount;
      cnv.setBalaCount(balaCount);
    }
  }
  public void removeBala() {
   if(balaCount < 1) {
     System.out.println(" - - Error Bala Count < 0 !!! - - ");
   } else if(balaCount == 1) {
     --balaCount;
     cnv.setBalaCount(balaCount);
     cnv.hideBalaIcon();
   } else {
     --balaCount;
     cnv.setBalaCount(balaCount);
   }
  }
}

