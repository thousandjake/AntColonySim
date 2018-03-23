//Filename: Ant.java
//Written On: Jake Thousand
//Written By: 2017-12-09
//Description: Ant Interface for the different Ant Types in the Colony Sim

import java.util.*;

public interface Ant {
  //get & set AntID attribute
  int getAntID();
  void setAntID(int antID);
  //get AntID attribute
  int getAntType();
  //method to kill Ant if attacked
  void killAnt();
  boolean checkAlive();
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
  //update Action on turn change for the Ant
  boolean update(int turnCount, ColonyNode[][] nodeList, LinkedList antList);
}
