package implementations.organizations;

import abstracts_interfaces.ObservedSubjectAbstract;


public class Organization extends ObservedSubjectAbstract {
	
	public String operatingMode;
	public Organization parent;
	
	public String getOperatingMode() {
		return operatingMode;
	}
	public void setOperatingMode(String operatingMode) {
		this.operatingMode = operatingMode;
		notifyObservers();
	}
	public Organization getParent() {
		return parent;
	}
	public void setParent(Organization parent) {
		this.parent = parent;
	}
	
	

}
