package implementations.strategie;

import implementations.controlleur.Chapitre;
import implementations.personnage.Personnage;

public abstract class Strategie {
	
	protected Personnage personnage;
	
	public Strategie (Personnage personnage) {
		this.personnage = personnage;
	}
	
	public abstract void run (Chapitre chapitre);

}
