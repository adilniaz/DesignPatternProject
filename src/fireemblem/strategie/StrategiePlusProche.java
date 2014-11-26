package fireemblem.strategie;

import fireemblem.controlleur.Chapitre;
import fireemblem.personnage.Personnage;

public class StrategiePlusProche extends Strategie {
	
	/* se dirige et attaque le perso le plus proche */
	
	public StrategiePlusProche (Personnage perso) {
		super(perso);
	}

	@Override
	public void run(Chapitre chapitre) {
		
	}

}
