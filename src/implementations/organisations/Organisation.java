package implementations.organisations;

import main_package.GameSimulation.Strategy;
import abstracts_interfaces.ObservedSubjectAbstract;

public class Organisation extends ObservedSubjectAbstract{
	
	public String operatingMode;
	public Organisation Parent;
	
	public String getOperatingMode() {
		return operatingMode;
	}

	public void setOperatingMode(Strategy strategy) {
		this.operatingMode = strategy.toString();
		notifyObservers();
	}
	
	
	public Organisation getParent() {
		return Parent;
	}
	
	public void setParent(Organisation parent) {
		Parent = parent;
	}

}
