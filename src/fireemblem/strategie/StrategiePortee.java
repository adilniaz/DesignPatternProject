package fireemblem.strategie;

import fireemblem.controlleur.Chapitre;
import fireemblem.personnage.Personnage;

public class StrategiePortee extends Strategie {
	
	/* le perso se d�place et attaque si un ennemie se trouve dans sa 
	 * portee de d�placement + attaque */
	
	public StrategiePortee (Personnage perso) {
		super(perso);
	}

	@Override
	public void run(Chapitre chapitre) {
		
	}

}
