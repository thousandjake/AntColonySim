//Filename: SimDriver.java
//Written By: Jake Thousand
//Written On: 2017-11-20
//Description:  This class instantiates the ColonyView class, the AntSimGUI class, and the Simulation class needed to run the Ant Colony Simulation

import javax.swing.*;
import java.io.*;

public class SimDriver {
  public static void main(String[] args) {
    //create the colony grid with height 27 nodes, width 27 nodes
    //ColonyView colonyView = new ColonyView(27, 27);
    //create the AntSimGUI with reference to the newly created ColonyView
    AntSimGUI simView = new AntSimGUI();
    //simView.initGUI(colonyView);
    //create the Simulation class with reference ot the newly created AntSimGUI
    Simulation newSim = new Simulation(simView);
  }
}
