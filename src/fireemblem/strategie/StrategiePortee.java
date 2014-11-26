package fireemblem.strategie;

import fireemblem.controlleur.Chapitre;
import fireemblem.personnage.Personnage;

public class StrategiePortee extends Strategie {
	
	/* le perso se déplace et attaque si un ennemie se trouve dans sa 
	 * portee de déplacement + attaque */
	
	public StrategiePortee (Personnage perso) {
		super(perso);
	}

	@Override
	public void run(Chapitre chapitre) {
		
	}

}
