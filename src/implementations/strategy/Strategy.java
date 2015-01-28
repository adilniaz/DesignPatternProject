package implementations.strategy;

import implementations.character.Character;
import implementations.controller.Chapter;

public abstract class Strategy {
	
	protected Character personnage;
	
	public Strategy (Character personnage) {
		this.personnage = personnage;
	}
	
	public abstract void run (Chapter chapitre);
	
	public abstract String name ();

}
