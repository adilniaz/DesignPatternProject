package survivalPotager.modele.mangeables;

import survivalPotager.configuration.Parametres;


public class Radis extends Mangeable {

	@Override
	public int getBesoinIrrigation() {
		 
		return Parametres.besoinEauRadis;
	}

	@Override
	public int getCycleDeCroissance() {
		 
		return Parametres.croissanceRadis;
	}

	@Override
	public int getRendement() {
		 
		return Parametres.rendementRadis;
	}

	public Radis() {
		  this.toursAvantCueillette = 2;
	}
}
