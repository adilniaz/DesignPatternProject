package implementations.strategy;

import implementations.character.Character;
import implementations.controller.Chapter;

public class NoActionStrategy extends Strategy {

	/* Le personnage ne fait rien */
	
	public NoActionStrategy (Character perso) {
		super(perso);
	}
	
	@Override
	public void run(Chapter chapitre) {
		
	}
	
	
	
}
