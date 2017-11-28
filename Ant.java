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
  void setCurrentAge(int currentAntAge);
  //get & set maxLife attrbiute
  int getMaxLife();
  void setMaxLife(int maxAntAge);
  //get & set xCoordinate attribute
  int getCoordinateX();
  void setCoordinateX(int xCoord);
  //get & set yXoordinate attrbiute
  int getCoordinateY();
  void setCoordinateY(int yCoord);
  //moveAction to move Ant from Node to Node
  void moveAction(int xCoord, int yCoord);
}


