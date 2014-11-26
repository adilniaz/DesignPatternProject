package fireemblem.strategie;

import fireemblem.controlleur.Chapitre;
import fireemblem.personnage.Personnage;

public class StrategieAucuneAction extends Strategie {

	/* Le personnage ne fait rien */
	
	public StrategieAucuneAction (Personnage perso) {
		super(perso);
	}
	
	@Override
	public void run(Chapitre chapitre) {
		
	}
	
	
	
}
