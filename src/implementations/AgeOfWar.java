package implementations;

import implementations.views.Window;
import abstracts_interfaces.SimulationAbstract;

public class AgeOfWar implements SimulationAbstract{

	@Override
	public void run(Window window) {
		window.close();
	}

}
