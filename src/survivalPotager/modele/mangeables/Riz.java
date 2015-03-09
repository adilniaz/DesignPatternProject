package survivalPotager.modele.mangeables;

import survivalPotager.configuration.Parametres;

public class Riz extends Mangeable {

	@Override
	public int getBesoinIrrigation() {
		return Parametres.besoinEauRiz;
	}

	@Override
	public int getCycleDeCroissance() {
		return Parametres.croissanceRiz;
	}

	@Override
	public int getRendement() {
		return Parametres.rendementRiz;
	}

	public Riz() {
		 this.toursAvantCueillette = 5;
	}
}
