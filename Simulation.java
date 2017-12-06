//Filename: Simulation.java
//Written By: Jake Thousand
//Written OnL 11-22-2017
//Description:

import java.util.EventListener;
import java.awt.*;
import java.awt.event.*;
import javax.swing.Timer;

public class Simulation implements ActionListener, SimulationEventListener {
	private int turnCount;
  private Timer newTimer;
  private AntSimGUI asGUI;
  private Colony simColony;
  
  public Simulation(AntSimGUI asGUI) {
    turnCount = 0;
    newTimer = new Timer(1000, this);
    this.asGUI = asGUI;
    asGUI.addSimulationEventListener(this);
    ColonyView colonyView = new ColonyView(27, 27);
    asGUI.initGUI(colonyView);
    simColony = new Colony(colonyView);
  }
  public void start() {
    //timer functionality for continuous simulation
    newTimer.start();
  }
  public void step() {
    //timer functionality for turn by turn simulation
    turnCount++;
    asGUI.setTime(turnsToString());
    simColony.updateColony(turnCount);
  }
  public String turnsToString() {
    String updatedTimer = "Day: "+turnCount/10+", Turn: "+turnCount%10;
    return updatedTimer;
  }
  public void actionPerformed(ActionEvent e) {
    //if queen is dead, implement Timer.stop method, otherwise jump into the step function
    if(simColony.queenAlive()) {
      step();
    } else {
      newTimer.stop();
      System.out.println(" - - Queen is Dead, Simulation Over !!! - - ");
    }
  }
  public void simulationEventOccurred(SimulationEvent simEvent) {
    if(simEvent.getEventType() == SimulationEvent.NORMAL_SETUP_EVENT) {
      //set up simulation for normal operation
      System.out.println(" - - Normal Operation Event Fired !!! - - ");
      if(newTimer.isRunning()) {
        System.out.println(" - - Sim Already in Process !!! - - ");
      } else {
        turnCount = 0;
        asGUI.setTime(turnsToString());
        simColony.clearColony();
        simColony.normInit();
      }
    } else if(simEvent.getEventType() == SimulationEvent.QUEEN_TEST_EVENT) {
      //set up simulation for testing the queen ant
      System.out.println(" - - Queen Test Event Fired !!! - - ");
      if(newTimer.isRunning()) {
        System.out.println(" - - Sim Already in Process !!! - - ");
      } else {
        turnCount = 0;
        asGUI.setTime(turnsToString());
        simColony.clearColony();
        simColony.queenTestInit();
      }
    } else if(simEvent.getEventType() == SimulationEvent.SCOUT_TEST_EVENT) {
      //set up simulation for testing the scout ant
      System.out.println(" - - Scout Test Event Fired !!! - - ");
      if(newTimer.isRunning()) {
        System.out.println(" - - Sim Already in Process !!! - - ");
      } else {
        turnCount = 0;
        asGUI.setTime(turnsToString());
        simColony.clearColony();
        simColony.scoutTestInit();
      }
    } else if(simEvent.getEventType() == SimulationEvent.FORAGER_TEST_EVENT) {
      //set up simulation for testing the forager ant
      System.out.println(" - - Forager Test Event Fired !!! - - ");
      if(newTimer.isRunning()) {
        System.out.println(" - - Sim Already in Process !!! - - ");
      } else {
        turnCount = 0;
        asGUI.setTime(turnsToString());
        simColony.clearColony();
        simColony.foragerTestInit();
      }
    } else if(simEvent.getEventType() == SimulationEvent.SOLDIER_TEST_EVENT) {
      //set up simulation for testing the soldier ant
      System.out.println(" - - Soldier Test Event Fired !!! - - ");
      if(newTimer.isRunning()) {
        System.out.println(" - - Sim Already in Process !!! - - ");
      } else {
        turnCount = 0;
        asGUI.setTime(turnsToString());
        simColony.clearColony();
        simColony.soldierTestInit();
      }
    } else if(simEvent.getEventType() == SimulationEvent.RUN_EVENT) {
      //set up simulation to run continuously
      System.out.println(" - - Run Event Fired !!! - - ");
      if(newTimer.isRunning()) {
        System.out.println(" - - Sim Already in Process !!! - - ");
      } else {
        start();
      }
    } else if(simEvent.getEventType() == SimulationEvent.STEP_EVENT) {
      //run the next turn of the simulation
      System.out.println(" - - Step Event Fired !!! - - ");
      if(newTimer.isRunning()) {
        System.out.println(" - - Sim Already in Process !!! - - ");
      } else {
        if(simColony.queenAlive()) {
          step();
        } else {
          System.out.println(" - - Queen is Dead, Simulation Over !!! - - ");
        }
      }
    }else {
      //invalid event occurred - probably will never happen
      System.out.println(" - - Invalid Event Fired !!! - - ");
    }
  }
}
