# Ant Colony Simulator

----
## Setup
Project set-up with [Apache Maven](https://maven.apache.org/).
 You can compile the project using the compile command.

    mvn compile

The Main-Class for the project is SimDriver.  The class is stored in the target/classes folder after compile is completed.  You can run the application using the following command.

    java SimDriver

----
## Project Description
This was my final project submission for my Data Structures and Algorithms course.  The view components (ColonyView, ColonyNodeView, SimulationEvent, and SimulationEventListener) were provided by the professor.  All of the other components were written by me.  

The project simulates the activity of an ant colony in a 27 by 27 grid.  Each Colony Node contains a random amount of food.  The middle Colony Node contains one Queen Ant, any other Ants, and a certain quantity of food.  The simulation ends when the Queen Ant dies from starvation, attack, or old age.  The different types of Ants are shown below.

* **Queen Ant (Yellow)** - The Queen consumes a unit of food each turn and births and new friendly Ant (of random type) once per day (10 turns).  If the Queen runs out of food or is attacked by a Bala Ant, the simulation ends.  If the Queen survives for 20 years, the simulation ends.
* **Bala Ant (Red)** - The Bala Ant is an enemy of the colony and will attack any non-Bala Ants it encounters.  The Balas are generated at random from the edges of the grid and move at random.  The Bala has a max age of 1 year.
* **Forager Ant (Green)** - The Forager Ants search for food and once found, return to the center Node to drop the food off for the Queen.  The Foragers leave pheromone trails so that they can find their way back to Nodes with food.  The Nodes color will change with the pheromone levels but will lose half its pheromone each day.  The Bala has a max age of 1 year.
* **Scout Ant (Blue)** - The Scout Ants reveal the hidden portions of the grid so that Foragers and Soldiers can travel to the revealed Nodes.  The Bala has a max age of 1 year.
* **Soldier Ant (Black)** - The Soldier Ants travel at random through the revealed Nodes looking for Bala Ants.  If the Soldier is directly adjacent to a Bala, they will fight and one of the Ants will die.  The Bala has a max age of 1 year.

----
## Controlling the Simulation
The Application has several buttons to set-up the simulation and then run the simulation either continuously or step-by-step.

* **Normal Setup** - This button sets up the simulation with the middle 9 Nodes revealed.  The center Node contains the Queen, 50 Foragers, 4 Scouts, 10 Soldiers, and 1000 units of food.  This is the "normal" condition of the simulation.
* **Queen Test** - This button sets up the simulation with only the center Node revealed.  The center Node contains the Queen, and 1000 units of food.  The Queen births new Ants each day.  There are no Balas in this mode.  This simulation is meant to test the behavior of the Queen.
* **Scout Test** - This button sets up the simulation with only the center Node revealed.  The center Node contains the Queen, 1 Scout, and 1000 units of food.  The Queen does not birth new Ants.  There are no Balas in this mode.  This simulation is meant to test the behavior of the Scout.
* **Forager Test** - This button sets up the simulation with only the center Node and 5 nearby Nodes revealed.  The center Node contains the Queen, 1 Forager, and 1000 units of food.  The Queen does not birth new Ants.  There are no Balas in this mode.  This simulation is meant to test the behavior of the Forager.
* **Soldier Test** - This button sets up the simulation with many of the Nodes revealed.  The center Node contains the Queen, 1 Soldier, and 1000 units of food.  There is also 1 Bala present in the Colony.  The Queen does not birth new Ants.   This simulation is meant to test the behavior of the Soldier and Bala.
* **Run** - This button will start the simulation and run continuously until the simulation ends or another button is clicked.  The simulation can be paused by clicking the Run button once, and restarted by clicking the Run button again.
* **Step** - This button will complete one turn of the simulation and then stop the simulation.  The simulation can be restarted using the Run button or the Step button.

----
## Changelog
* 23-Mar-2018 initial creation
