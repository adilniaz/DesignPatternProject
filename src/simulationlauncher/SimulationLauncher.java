package simulationlauncher;

import implementations.GameSimulation;

public class SimulationLauncher {
	public static void main(String[] args) {
		GameSimulation gameSimulation = new GameSimulation();
        gameSimulation.runSimulation();
	}
}
