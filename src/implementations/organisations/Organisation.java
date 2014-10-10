package implementations.organisations;

import abstracts_interfaces.SujetObserveAbstrait;

public class Organisation extends SujetObserveAbstrait{
	
	public enum modeFonctionnement{
		MODE_GUERRE, MODE_PAIX, MODE_NON_DEFINI, MODE_REPLI
	}
	
	public String modeFonctionnement;
	
	public String getModeFonctionnement() {
		return modeFonctionnement;
	}

	public void setModeFonctionnement(String modeFonctionnement) {
		this.modeFonctionnement = modeFonctionnement;
		Notify();
	}

	public Organisation Parent;
	
	public Organisation getParent() {
		return Parent;
	}
	
	public void setParent(Organisation parent) {
		Parent = parent;
	}
	
}
