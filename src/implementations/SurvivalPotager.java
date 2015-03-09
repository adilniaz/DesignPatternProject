package implementations;

import survivalPotager.vue.StartScreen;
import implementations.views.Window;
import abstracts_interfaces.SimulationAbstract;

public class SurvivalPotager implements SimulationAbstract{

	
	@Override
	public void run(Window window) {
		new StartScreen();
		//window.dispose();
	}

}
