package survivalPotager.modele.mangeables;


public class Radis extends Mangeable {

	@Override
	public int getBesoinIrrigation() {
		 
		return 1;
	}

	@Override
	public int getCycleDeCroissance() {
		 
		return 2;
	}

	@Override
	public int getRendement() {
		 
		return 2;
	}

	public Radis() {
		  this.toursAvantCueillette = 2;
	}
}
