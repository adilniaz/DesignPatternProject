package simulationlauncher;

import implementations.AgeOfWar;
import implementations.GameSimulation;
import implementations.SurvivalPotager;
import abstracts_interfaces.SimulationAbstract;

public class SimulationFactory {
	public SimulationAbstract createSimulation (SimulationType type) {
		switch (type) {
			case fire_emblem :
				return new GameSimulation();
			case ageOfWar :
				return new AgeOfWar();
			case Survival_Potager :
				return new SurvivalPotager();
			default :
				return null;
		}
	}
}
