package survivalPotager.modele.mangeables;

import survivalPotager.configuration.Parametres;

public class Tomate extends Mangeable {


	@Override
	public int getBesoinIrrigation() {
		return Parametres.besoinEauTomate;
	}

	@Override
	public int getCycleDeCroissance() {
		return Parametres.croissanceTomate;
	}

	@Override
	public int getRendement() {
		return Parametres.rendementTomate;
	}

	public Tomate() { 
		 this.toursAvantCueillette = 3;
	}
}
