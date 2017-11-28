//Filename: Colony.java
//Written By: Jake Thousand
//Written On: 2017-11-22
//Description:


public class Colony {
  public ColonyView cv;
  public ColonyNode[][] nodeList = new ColonyNode[27][27];
  //public ArrayList antList = new ArrayList();
  public Colony(ColonyView cv) {
    //constructor method
    this.cv = cv;
    //initialize the Colony by calling the initColony method
    initColony();
  }
  public void initColony() {
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
  }
}
