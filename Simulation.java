//Filename: Simulation.java
//Written By: Jake Thousand
//Written OnL 2017-12-09
//Description: Simulation class for Ant Colony Simulation.  At a high level this class controls the Sim and communicates with the view.

import java.util.EventListener;
import java.awt.*;
import java.awt.event.*;
import javax.swing.Timer;

public class Simulation implements ActionListener, SimulationEventListener {
  //attributes
	private int turnCount;
  private Timer newTimer;
  private AntSimGUI asGUI;
  private Colony simColony;
  //constructor
  public Simulation(AntSimGUI asGUI) {
    //turnCount is an int that keeps track of the turns in the simulation
    turnCount = 0;
    //Timer class controls ActionEvents that are fired at regular intervals
    //Simulation class is an ActionListener that updates the sim and synchronizes with the event dispatch thread
    newTimer = new Timer(10, this);
    //asGUI is a reference to the AntSimGUI class which handles the view elements for the sim
    this.asGUI = asGUI;
    //Simulation class is a SimulationEventListener as well and moves the sim forward based on button clicks
    asGUI.addSimulationEventListener(this);
    //reference to the ColonyView, 27x27 grid
    ColonyView colonyView = new ColonyView(27, 27);
    asGUI.initGUI(colonyView);
    //reference to the Colony, passing in a reference to the view for updates
    simColony = new Colony(colonyView);
  }
  //< - - - - - - Start Method Handles the Timer Functionality - - - - - - >
  public void start() {
    //timer thread allows synchronized updates between model and view
    newTimer.start();
  }
  //< - - - - - - Step Method Updates Sim Each Turn - - - - - ->
  public void step() {
    turnCount++;
    //update time label in the view 
    asGUI.setTime(turnsToString());
    //update ColonyNodes and Ants
    simColony.updateColony(turnCount);
  }
  //< - - - - - - turnsToString Method Converts turnsCount to a String for view - - - - - - >
  public String turnsToString() {
    String updatedTimer = "Day: "+turnCount/10+", Turn: "+turnCount%10;
    return updatedTimer;
  }
  //< - - - - - - ActionPerformed Method Implemented from ActionListener - - - - - - >
  public void actionPerformed(ActionEvent e) {
    //if queen is dead, implement Timer.stop method, otherwise jump into the step function
    if(simColony.queenAlive()) {
      step();
    } else {
      newTimer.stop();
      System.out.println(" - - Queen is Dead, Simulation Over !!! - - ");
    }
  }
  //< - - - - - - SimulationEventOccured Implemented from SimulationEvent Listener - - - - - - >
  public void simulationEventOccurred(SimulationEvent simEvent) {
    //set-up or run the simulation based on the simEvent fired
    if(simEvent.getEventType() == SimulationEvent.NORMAL_SETUP_EVENT) {
      //set up simulation for normal operation
      if(newTimer.isRunning()) {
        newTimer.stop();
      }
      turnCount = 0;
      asGUI.setTime(turnsToString());
      simColony.clearColony();
      simColony.normInit();
    } else if(simEvent.getEventType() == SimulationEvent.QUEEN_TEST_EVENT) {
      //set up simulation for testing the queen ant
      if(newTimer.isRunning()) {
        newTimer.stop();
      }
      turnCount = 0;
      asGUI.setTime(turnsToString());
      simColony.clearColony();
      simColony.queenTestInit();
    } else if(simEvent.getEventType() == SimulationEvent.SCOUT_TEST_EVENT) {
      //set up simulation for testing the scout ant
      if(newTimer.isRunning()) {
        newTimer.stop();
      } 
      turnCount = 0;
      asGUI.setTime(turnsToString());
      simColony.clearColony();
      simColony.scoutTestInit();
    } else if(simEvent.getEventType() == SimulationEvent.FORAGER_TEST_EVENT) {
      //set up simulation for testing the forager ant
      if(newTimer.isRunning()) {
        newTimer.stop();
      } 
      turnCount = 0;
      asGUI.setTime(turnsToString());
      simColony.clearColony();
      simColony.foragerTestInit();
    } else if(simEvent.getEventType() == SimulationEvent.SOLDIER_TEST_EVENT) {
      //set up simulation for testing the soldier ant
      if(newTimer.isRunning()) {
        newTimer.stop();
      }
      turnCount = 0;
      asGUI.setTime(turnsToString());
      simColony.clearColony();
      simColony.soldierTestInit();
    } else if(simEvent.getEventType() == SimulationEvent.RUN_EVENT) {
      //set up simulation to run continuously
      if(newTimer.isRunning()) {
        newTimer.stop();
      } else {
        start();
      }
    } else if(simEvent.getEventType() == SimulationEvent.STEP_EVENT) {
      //run the next turn of the simulation
      if(newTimer.isRunning()) {
        newTimer.stop();
      }
      if(simColony.queenAlive()) {
        step();
      } else {
        System.out.println(" - - Queen is Dead, Simulation Over !!! - - ");
      }
    }else {
      //invalid event occurred - probably will never happen
      System.out.println(" - - Invalid Event Fired !!! - - ");
    }
  }
}
