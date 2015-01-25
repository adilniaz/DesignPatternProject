package simulationlauncher;

import implementations.GameSimulation;
import abstracts_interfaces.SimulationAbstract;

public class SimulationFactory {
	
	public SimulationAbstract createSimulation (SimulationType type) {
		switch (type) {
			case fire_emblem :
				return new GameSimulation();
			default :
				return null;
		}
		
		
	}

}
