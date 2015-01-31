package simulationlauncher;

import implementations.AgeOfWar;
import implementations.GameSimulation;
import abstracts_interfaces.SimulationAbstract;

public class SimulationFactory {
	public SimulationAbstract createSimulation (SimulationType type) {
		switch (type) {
			case fire_emblem :
				return new GameSimulation();
			case ageOfWar :
				return new AgeOfWar();
			default :
				return null;
		}
	}
}
