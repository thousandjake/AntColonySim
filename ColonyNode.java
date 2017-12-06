//Filename: ColonyNode.java
//Written By: Jake Thousand
//Written On: 2017-11-22
//Description: 

import java.util.*;

public class ColonyNode {
  //reference to view for Colony Node
  public ColonyNodeView cnv;
  //static random number generator
  private static Random r = new Random();
  //attributes
  public int xCoord;
  public int yCoord;
  private String nodeID;
  private int foodLevel;
  private int pheromoneLevel;
  private boolean queenPresent;
  private int foragerCount;
  private int scoutCount;
  private int soldierCount;
  private int balaCount;
  //constructor method
  public ColonyNode(ColonyNodeView cnv, int x, int y) {
    this.cnv = cnv;
    this.xCoord = x;
    this.yCoord = y;

    setNodeID(x,y);
    //TODO - generate random food level for ColonyNode
    setFoodLevel(10);
    //TODO - generate random pheromone level for ColonyNode
    setPheromoneLevel(1000);
    setQueenPresent(false);
    setForagerCount(0);
    setScoutCount(0);
    setSoldierCount(0);
    setBalaCount(0);
  }
  public String getNodeID() {
    return nodeID;
  }
  public void setNodeID(int x, int y) {
    String newNodeID = ""+x+", "+y;
    nodeID = newNodeID;
    cnv.setID(newNodeID);
  }
  public int getFoodLevel() {
    return foodLevel;
  }
  public void setFoodLevel(int foodAmount) {
    foodLevel = foodAmount;
    cnv.setFoodAmount(foodAmount);
  }
  public int getPheromoneLevel() {
    return pheromoneLevel;
  }
  public void addFoodUnit() {
    foodLevel++;
    cnv.setFoodAmount(foodLevel);
  }
  public void removeFoodUnit() {
    foodLevel--;
    cnv.setFoodAmount(foodLevel);
  }
  public void setPheromoneLevel(int pheromoneAmount) {
    pheromoneLevel = pheromoneAmount;
    cnv.setPheromoneLevel(pheromoneAmount);
  }
  public boolean getQueenPresent() {
    return queenPresent;
  }
  public void setQueenPresent(boolean queenStatus) {
    queenPresent = queenStatus;
    cnv.setQueen(queenStatus);
    if(queenPresent) {
      cnv.showQueenIcon();
    }
  }
  public void setNodeVisible(boolean visible) {
    if(visible) {
      cnv.showNode();
    } else {
      cnv.hideNode();
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
    //cnv.repaint();
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
    //cnv.repaint();
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
    //cnv.repaint();
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
    //cnv.repaint();
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

