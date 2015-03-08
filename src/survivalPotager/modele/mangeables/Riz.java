package survivalPotager.modele.mangeables;

public class Riz extends Mangeable {

	@Override
	public int getBesoinIrrigation() {
		return 4;
	}

	@Override
	public int getCycleDeCroissance() {
		return 5;
	}

	@Override
	public int getRendement() {
		return 4;
	}

	public Riz() {
		 this.toursAvantCueillette = 5;
	}
}
