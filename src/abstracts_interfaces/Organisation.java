package abstracts_interfaces;


public class Organisation extends ObservedSubjectAbstract {
	
	public String operatingMode;
	public Organisation parent;
	
	public String getOperatingMode() {
		return operatingMode;
	}
	public void setOperatingMode(String operatingMode) {
		this.operatingMode = operatingMode;
		notifyObservers();
	}
	public Organisation getParent() {
		return parent;
	}
	public void setParent(Organisation parent) {
		this.parent = parent;
	}
	
	

}
