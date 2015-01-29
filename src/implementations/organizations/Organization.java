package implementations.organizations;

import implementations.controller.Chapter.Ordre;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import abstracts_interfaces.ObservedSubjectAbstract;


public class Organization extends ObservedSubjectAbstract {
	
	public String operatingMode;
	public Organization parent;
	protected PropertyChangeSupport pcsControlleurVue;
	
	public Organization () {
		this.pcsControlleurVue = new PropertyChangeSupport(this);
	}
	
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
    
    public void addListener(PropertyChangeListener ecouteur) {
        this.pcsControlleurVue.addPropertyChangeListener(ecouteur);
    }
    
    public void setOrder (Ordre order) {
    	this.pcsControlleurVue.firePropertyChange("ordre", order, null);
    }

}
