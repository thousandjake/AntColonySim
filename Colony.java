//Filename: Colony.java
//Written By: Jake Thousand
//Written On: 2017-11-22
//Description:

import dataStructures.*;

public class Colony {
  private QueenAnt queen; 
  private ColonyView cv;
  private ColonyNode[][] nodeList = new ColonyNode[27][27];
  private ArrayList antList = new ArrayList();
  public Colony(ColonyView cv) {
    //constructor method
    this.cv = cv;
  }
  public boolean queenAlive() {
    //check to see if queen is still alive, return true if alive, false if dead
    if(queen.getCurrentAge() == queen.getMaxAge()) {
      return false;
    } else {
      return true;
    }
  }
  public void normInit() {
    //iterate through the 27 by 27 grid creating and adding ColonyNodes to the Colony
    for(int x = 0; x < nodeList.length; ++x) {
      for(int y = 0; y < nodeList[0].length; ++y) {
        //call addColonyNode method to add a new ColonyNode to the Colony at int x, int y location
        addColonyNode(x, y);
      }
    }
  }
  public void addColonyNode(int x, int y) {
    //addColonyNode creates a new ColonyNodeView and adds that ColonyNodeView to the ColonyView, then creates a new ColonyNode and adds that Node to the Colony
    ColonyNodeView cnv = new ColonyNodeView();
    cv.addColonyNodeView(cnv, x, y);
    //showNodes for now, so I can see progress while testing
    cnv.showNode();
    //create a new ColonyNode at location int x, int y with reference to the newly created ColonyNodeView
    ColonyNode cn = new ColonyNode(cnv, x, y);
    //add the newly created node to the nodeList at location int x, int y
    nodeList[x][y] = cn;
    if(x == 13 && y == 13) {
      queen =  new QueenAnt();
      antList.add(queen);
      cn.setQueenPresent(true);
    }
  }
  public void queenTestInit() {
    //logic to init colony for Queen Test
  }
  public void scoutTestInit() {
    //logic to init colony for Scout Test
  }
  public void foragerTestInit() {
    //logic to init colony for Forager Test
  }
  public void soldierTestInit() {
    //logic to init colony for Soldier Test
  }
  public void clearColony() {
    //logic for reseting colony
  }
  public void updateColony(int turnCount) {
    //logic for updating colony
  }
}
