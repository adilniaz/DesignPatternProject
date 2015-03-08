package survivalPotager.modele.mangeables;

public class Tomate extends Mangeable {


	@Override
	public int getBesoinIrrigation() {
		return 2;
	}

	@Override
	public int getCycleDeCroissance() {
		return 3;
	}

	@Override
	public int getRendement() {
		return 3;
	}

	public Tomate() { 
		 this.toursAvantCueillette = 3;
	}
}
