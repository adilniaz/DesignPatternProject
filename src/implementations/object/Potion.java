package implementations.object;

import implementations.character.Character;

public class Potion extends Objet {
	
	private int pv;

	public Potion(String nom, int utilisation, int prix, ObjetType typeObjet, int pv) {
		super(nom, utilisation, prix, typeObjet);
		this.pv = pv;
	}
	
	public void use (Character character) {
		character.setPv(character.getPv() + this.pv);
	}

}
