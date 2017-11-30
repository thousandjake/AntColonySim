//Filename: Ant.java
//Written On: Jake Thousand
//Written By: 2017-11-27
//Description: 

public interface Ant {
  //get & set AntID attribute
  int getAntID();
  void setAntID(int antID);
  //get & set currentAge attribute
  int getCurrentAge();
  void setCurrentAge(int currentAge);
  //get & set maxLife attrbiute
  int getMaxAge();
  void setMaxAge(int maxAge);
  //get & set xCoordinate attribute
  int getXCoordinate();
  void setXCoordinate(int xCoordinate);
  //get & set yXoordinate attrbiute
  int getYCoordinate();
  void setYCoordinate(int yCoordinate);
  //moveAction to move Ant from Node to Node
  void moveAction(int xCoord, int yCoord);
}


