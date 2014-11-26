package fireemblem.strategie;

import fireemblem.controlleur.Chapitre;
import fireemblem.personnage.Personnage;

public abstract class Strategie {
	
	protected Personnage personnage;
	
	public Strategie (Personnage personnage) {
		this.personnage = personnage;
	}
	
	public abstract void run (Chapitre chapitre);

}
