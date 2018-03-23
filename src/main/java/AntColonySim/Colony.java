//Filename: Colony.java
//Written By: Jake Thousand
//Written On: 2017-12-09
//Description: Colony class for Ant Colony Simulation

import java.util.*;

public class Colony {
  //attributes
  //reference to the Queen Ant in the simulation
  private QueenAnt queen;
  //reference to View for Colony
  private ColonyView cv;
  //2D Array to hold the 27 x 27 grid of ColonyNodes
  private ColonyNode[][] nodeList;
  //LinkedList to hold all Ants for the Simulation starting with Queen
  private LinkedList antList;
  //queenHatching is a boolean attribute used to turn off hatching Ants for some tests
  private boolean queenHatching;
  //balaHatching is a boolean attribute used to turn off hatching Ants for some tests
  private boolean balaHatching;
  //Random static class for creating random numbers
  private static Random r;
  //constructor
  public Colony(ColonyView cv) {
    //reference to colonyview
    this.cv = cv;
    //initialize the ColonyNode array
    nodeList = new ColonyNode[27][27];
    //initilize the LinkedList of Ants
    antList = new LinkedList();
    //set queenHatching to true for normal simulations
    queenHatching = true;
    //set balaHatching to true for normal simulation
    balaHatching = true;
    //initialize Random class for random numbers
    r = new Random();
  }
  //< - - - - - - queenAlive Method checks if queen is alive (otherwise sim ends) - - - - - - >
  public boolean queenAlive() {
    //check to see if queen is still alive, return true if alive, false if dead
    return queen.checkAlive();
  }
  //< - - - - - - createColonyGrid Method fills Array of ColonyNodes - - - - - - >
  public void createColonyGrid() {
    //iterate through the 27 by 27 grid creating and adding ColonyNodes to the Colony
    //remove all ColonyNodeViews before re-rendering grid
    cv.removeAll();
    for(int x = 0; x < nodeList.length; ++x) {
      for(int y = 0; y < nodeList[0].length; ++y) {
        //call addColonyNode method to add a new ColonyNode to the Colony at int x, int y location
        addColonyNode(x, y);
      }
    }
    //repaint and revalidate the view after adding new elements
    cv.repaint();
    cv.revalidate();
  }
  //< - - - - - - addColonyNode Method - - - - - >
  public void addColonyNode(int x, int y) {
    //addColonyNode creates a new ColonyNodeView and adds that ColonyNodeView to the ColonyView, then creates a new ColonyNode and adds that Node to the Colony
    ColonyNodeView cnv = new ColonyNodeView();
    cv.addColonyNodeView(cnv, x, y);
    //create a new ColonyNode at location int x, int y with reference to the newly created ColonyNodeView
    ColonyNode cn = new ColonyNode(cnv, x, y);
    //add the newly created node to the nodeList at location int x, int y
    nodeList[x][y] = cn;
  }
  //< - - - - - - Initialize Normal Setup - - - - - - >
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
    //make 9 middle nodes visible
    for(int a=12; a<15; ++a) {
      for(int b=12; b<15; ++b) {
        nodeList[a][b].showNode();
        nodeList[a][b].setFoodLevel(0);
      }
    }
    //set Food Amount of Queen Node to 1000 units
    nodeList[13][13].setFoodLevel(1000);
    //do not allow queen to hatch ants in this test
    queenHatching = true;
    //do not allow balas to be generated in this test
    balaHatching = true;
  }
  //< - - - - - - Initialize Queen Test Setup - - - - - - >
  public void queenTestInit() {
    //logic to init colony for Queen Test
    createColonyGrid();
    //create Queen Ant
    createQueen();
    //make Queen Node visible
    nodeList[13][13].showNode();
    //set Food Amount of Queen Node to 1000 units
    nodeList[13][13].setFoodLevel(1000);
     //do not allow queen to hatch ants in this test
    queenHatching = true;
    //do not allow balas to be generated in this test
    balaHatching = false;
 }
  //< - - - - - - Initialize Scout Test Setup - - - - - - >
  public void scoutTestInit() {
    //logic to init colony for Scout Test
    createColonyGrid();
    //Create Queen Ant
    createQueen();
    //Create Scout Ant
    createScout();
    //make Queen Node visible
    nodeList[13][13].showNode();
    //set Food Amount of Queen Node to 1000 unit
    nodeList[13][13].setFoodLevel(1000);
    //do not allow queen to hatch ants in this test
    queenHatching = false;
    //do not allow balas to be generated in this test
    balaHatching = false;
  }
  //< - - - - - - Initialize Forager Test Setup - - - - - - >
  public void foragerTestInit() {
    //logic to init colony for Forager Test
    createColonyGrid();
    //create Queen Ant
    createQueen();
    //create 1 Forager Ant
    createForager();
    //set Queen Node food level and visibility
    nodeList[13][13].setFoodLevel(1000);
    nodeList[13][13].showNode();
    //set adjacent nodes food level and visibility
    nodeList[13][14].setFoodLevel(0);
    nodeList[13][14].showNode();
    nodeList[13][15].setFoodLevel(0);
    nodeList[13][15].showNode();
    nodeList[13][16].setFoodLevel(0);
    nodeList[13][16].showNode();
    nodeList[13][17].setFoodLevel(0);
    nodeList[13][17].showNode();
    nodeList[13][18].setFoodLevel(1000);
    nodeList[13][18].showNode();
    //do not allow queen to hatch ants in this test
    queenHatching = false;
    //do not allow balas to be generated in this test
    balaHatching = false;
  }
  //< - - - - - - Initialize Soldier Test Setup - - - - - - >
  public void soldierTestInit() {
    //logic to init colony for Soldier Test
    createColonyGrid();
    //create Queen Ant
    createQueen();
    //create 1 Soldier Ant
    createSoldier();
    //create 1 Bala Ant
    createBala();
     //make 9 middle nodes visible
    for(int a=3; a<23; ++a) {
      for(int b=3; b<23; ++b) {
        nodeList[a][b].showNode();
        nodeList[a][b].setFoodLevel(0);
      }
    }
    //set Food Amount of Queen Node to 1000 units
    nodeList[13][13].setFoodLevel(1000);
    //do not allow queen to hatch ants in this test
    queenHatching = false;
    //do not allow balas to be generated in this test
    balaHatching = false;
  }
  //< - - - - - - Clear Colony Setup / Sim Results - - - - - - >
  public void clearColony() {
    //logic for reseting colony
    antList.clear();
  }
  //< - - - - - - Update the ColonyNodes and Ants Each Turn - - - - - - >
  public void updateColony(int turnCount) {
    //ArrayList antsForRemoval keeps track of the dead ants during the update,
    //and then removes them from the antList after done iterating through antList
    ArrayList<Ant> antsForRemoval = new ArrayList<Ant>();
    if(balaHatching) {
      //balaChance is a random int between 0 - 99
      int balaChance = r.nextInt(100);
      //if balaChance 0 - 2 (3%), create a Bala
      if(balaChance < 3) {
        createBala();
      }
    }
    //if turnCount % 10 == 0, it is a new day in the simulation
    //on the first turn of the new day, the queen hatches a new ant
    if(turnCount % 10 == 0 && queenHatching) {
      Ant newAnt = queen.hatchAnt(antList.size(), nodeList);
      antList.add(newAnt);
    }
    //if turnCount % 10 == 0, it is a new day in the simulation
    //on the first turn of the new day, the pheromone in each Node is halfed
    if(turnCount % 10 == 0) {
      //iterate through each Node in the grid
      for(int x = 0; x < 27; ++x) {
        for(int y = 0; y < 27; ++y) {
          int halfP = nodeList[x][y].getPheromoneLevel() / 2;
          //set pheromoneLevel to 50% of what it was prior to update
          nodeList[x][y].setPheromoneLevel(halfP);
        }
      }
    }
    //Each Ant in the Sim must be updated each turn
    //This for loop iterates through the Ants in antList and calls the update method on each
    for(int x = 0; x < antList.size(); ++x) {
      boolean antUpdated = ((Ant)antList.get(x)).update(turnCount, nodeList, antList);
      //if the update returns FALSE, ant is dead and needs to be added to antsForRemoval ArrayList
      if(!antUpdated) {
        antsForRemoval.add(((Ant)antList.get(x)));
      }
    }
    //After the Ant update iteration is completed, the Removal list must be cleared
    //this foor loops iteratres through the Ants in antsForRemoval
    for(int y = 0; y < antsForRemoval.size(); ++y) {
      //set temp xCoordinate int
      int xCoordinate = ((Ant)antsForRemoval.get(y)).getXCoordinate();
      //set temp yCoordinate int
      int yCoordinate = ((Ant)antsForRemoval.get(y)).getYCoordinate();
      //based on the type of Ant, remove from ColonyNode and ColonyNodeView
      if(((Ant)antsForRemoval.get(y)).getAntType() == 0) {
        nodeList[xCoordinate][yCoordinate].setQueenPresent(false);
      } else if(((Ant)antsForRemoval.get(y)).getAntType() == 1) {
        nodeList[xCoordinate][yCoordinate].removeForager();
      } else if(((Ant)antsForRemoval.get(y)).getAntType() == 2) {
        nodeList[xCoordinate][yCoordinate].removeScout();
      } else if(((Ant)antsForRemoval.get(y)).getAntType() == 3) {
        nodeList[xCoordinate][yCoordinate].removeSoldier();
      } else if(((Ant)antsForRemoval.get(y)).getAntType() == 4) {
        nodeList[xCoordinate][yCoordinate].removeBala();
      }
      //finally remove the Ant from the antList
      antList.remove(antsForRemoval.get(y));
    }
    //after the iteration is completed, the antsForRemoval ArrayList is cleared
    antsForRemoval.clear();
  }
  //< - - - - - - Create Queen Ant to Initialize the Sim - - - - - >
  public void createQueen() {
    queen = new QueenAnt();
    antList.add(queen);
    nodeList[13][13].setQueenPresent(true);
  }
  //< - - - - - - Create Forager Ant to Initialize the Sim - - - - - >
  public void createForager() {
    int fAntID = antList.size();
    Ant fAnt = new ForagerAnt(fAntID);
    antList.add(fAnt);
    nodeList[13][13].addForager();
  }
  //< - - - - - - Create Scout Ant to Initialize the Sim - - - - - >
  public void createScout() {
    int scAntID = antList.size();
    ScoutAnt scAnt = new ScoutAnt(scAntID);
    antList.add(scAnt);
    nodeList[13][13].addScout();
  }
  //< - - - - - - Create Soldier Ant to Initialize the Sim - - - - - >
  public void createSoldier() {
    int soAntID = antList.size();
    SoldierAnt soAnt = new SoldierAnt(soAntID);
    antList.add(soAnt);
    nodeList[13][13].addSoldier();
  }
  //< - - - - - - Create Bala Ant to Initialize the Sim - - - - - >
  public void createBala() {
    int bAntID = antList.size();
    BalaAnt bAnt = new BalaAnt(bAntID, nodeList);
    antList.add(bAnt);
  }
}
