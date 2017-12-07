//Filename: Colony.java
//Written By: Jake Thousand
//Written On: 2017-11-22
//Description:

//import dataStructures.*;
import java.util.*;

public class Colony {
  private QueenAnt queen; 
  private ColonyView cv;
  private ColonyNode[][] nodeList;
  private LinkedList antList;
  public Colony(ColonyView cv) {
    //constructor method
    this.cv = cv;
    nodeList = new ColonyNode[27][27];
    antList = new LinkedList();
  }
  public boolean queenAlive() {
    //check to see if queen is still alive, return true if alive, false if dead
    return queen.checkAlive();
  }
  public void createColonyGrid() {
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
    //cnv.showNode();
    //create a new ColonyNode at location int x, int y with reference to the newly created ColonyNodeView
    ColonyNode cn = new ColonyNode(cnv, x, y);
    //add the newly created node to the nodeList at location int x, int y
    nodeList[x][y] = cn;
  }
  public void normInit() {
    //logic to init colony for Normal Simulation
    //create grid of ColonyNode
    createColonyGrid();
    //create Queen Ant
    createQueen();
    //create 10 Soldier Ants
    for(int x=0; x < 10; ++x) {
      createSoldier();
    }
    //create 50 Forager Ants
    for(int y=0; y < 50; ++y) {
      createForager();
    }
    //create 4 Scout Ants
    for(int z=0; z < 4; ++z) {
      createScout();
    }
    //set Food Amount of Queen Node to 1000 units
    nodeList[13][13].setFoodLevel(1000);
    //make 9 middle nodes visible
    for(int a=12; a<15; ++a) {
      for(int b=12; b<15; ++b) {
        nodeList[a][b].showNode();
      }
    }
  }
  public void queenTestInit() {
    //logic to init colony for Queen Test
    createColonyGrid();
    //create Queen Ant
    createQueen();
    //make Queen Node visible
    nodeList[13][13].showNode();
  }
  public void scoutTestInit() {
    //logic to init colony for Scout Test
    createColonyGrid();
  }
  public void foragerTestInit() {
    //logic to init colony for Forager Test
    createColonyGrid();
  }
  public void soldierTestInit() {
    //logic to init colony for Soldier Test
    createColonyGrid();
  }
  public void clearColony() {
    //logic for reseting colony
    antList.clear();
  }
  public void updateColony(int turnCount) {
    //logic for updating colony
    if(turnCount%10 == 0) {
      Ant newAnt = queen.hatchAnt(antList.size(), nodeList);
      antList.add(newAnt);
    }
    for(Iterator itr = antList.iterator(); itr.hasNext();) {
      ((Ant)itr.next()).update(turnCount, nodeList);
    }
  }
  public void createQueen() {
    queen = new QueenAnt();
    antList.add(queen);
    nodeList[13][13].setQueenPresent(true);
  }
  public void createForager() {
    int fAntID = antList.size();
    ForagerAnt fAnt = new ForagerAnt(fAntID);
    antList.add(fAnt);
    nodeList[13][13].addForager();
  }
  public void createScout() {
    int scAntID = antList.size();
    ScoutAnt scAnt = new ScoutAnt(scAntID);
    antList.add(scAnt);
    nodeList[13][13].addScout();
  }
  public void createSoldier() {
    int soAntID = antList.size();
    SoldierAnt soAnt = new SoldierAnt(soAntID);
    antList.add(soAnt);
    nodeList[13][13].addSoldier();
  }
  public void createBala() {
    int bAntID = antList.size();
    BalaAnt bAnt = new BalaAnt(bAntID, 0, 0);
    antList.add(bAnt);
    //TODO - add location
  }
}
