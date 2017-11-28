//Filename: Simulation.java
//Written By: Jake Thousand
//Written OnL 11-22-2017
//Description:

import java.util.EventListener;
import java.awt.*;
import java.awt.event.*;
import javax.swing.Timer;

public class Simulation implements ActionListener, SimulationEventListener {
	private Timer newTimer;
  public AntSimGUI asGUI;
  
  public Simulation(AntSimGUI asGUI) {
    this.asGUI = asGUI;
    asGUI.addSimulationEventListener(this);
    start();
  }
  public void start() {
    //timer functionality for continuous simulation
    newTimer = new Timer(1000, this);
    newTimer.start();
    ColonyView colonyView = new ColonyView(27, 27);
    asGUI.initGUI(colonyView);
    Colony simColony = new Colony(colonyView);
  }
  public void step() {
    //timer functionality for turn by turn simulation
    System.out.println(" - - Turn Event Fired !!! - - ");
  }
  public void actionPerformed(ActionEvent e) {
    //if queen is dead, implement Timer.stop method, otherwise jump into the step function
    step();
  }
  public void simulationEventOccurred(SimulationEvent simEvent) {
    if(simEvent.getEventType() == SimulationEvent.NORMAL_SETUP_EVENT) {
      //set up simulation for normal operation
      System.out.println(" - - Normal Operation Event Fired !!! - - ");
      start();
    }
    else if(simEvent.getEventType() == SimulationEvent.QUEEN_TEST_EVENT) {
      //set up simulation for testing the queen ant
      System.out.println(" - - Queen Test Event Fired !!! - - ");
    } else if(simEvent.getEventType() == SimulationEvent.SCOUT_TEST_EVENT) {
      //set up simulation for testing the scout ant
      System.out.println(" - - Scout Test Event Fired !!! - - ");
    } else if(simEvent.getEventType() == SimulationEvent.FORAGER_TEST_EVENT) {
      //set up simulation for testing the forager ant
      System.out.println(" - - Forager Test Event Fired !!! - - ");
    } else if(simEvent.getEventType() == SimulationEvent.SOLDIER_TEST_EVENT) {
      //set up simulation for testing the soldier ant
      System.out.println(" - - Soldier Test Event Fired !!! - - ");
    } else if(simEvent.getEventType() == SimulationEvent.RUN_EVENT) {
      //set up simulation to run continuously
      System.out.println(" - - Run Event Fired !!! - - ");
    } else if(simEvent.getEventType() == SimulationEvent.STEP_EVENT) {
      //run the next turn of the simulation
      System.out.println(" - - Step Event Fired !!! - - ");
    }else {
      //invalid event occurred - probably will never happen
      System.out.println(" - - Invalid Event Fired !!! - - ");
    }
  }
}
