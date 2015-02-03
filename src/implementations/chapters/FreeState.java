package implementations.chapters;

import implementations.character.Character;
import implementations.controller.Chapter;
import abstracts_interfaces.CharacterAbstract;

public class FreeState extends DefaultState {
	
	public FreeState (Chapter chapter) {
		super(chapter);
	}

	@Override
	public void action() {
		this.chapter.freeStateAction();
	}

	@Override
	public void info() {
		/* Si perso, afffiche info du perso */
		for (CharacterAbstract perso : this.chapter.getPlateauDeJeu().getPersonnages()) {
            Character p = (Character) perso;
            if (p.getPosition().equals(this.chapter.getCurrentPosition())) {
                this.chapter.setPersoEnCours(p);
                this.chapter.setState(new CharacterViewState(this.chapter));
                break;
            }
        }
    	for (CharacterAbstract perso : this.chapter.getPlateauDeJeu().getAnnexes()) {
            Character p = (Character) perso;
            if (p.getPosition().equals(this.chapter.getCurrentPosition())) {
            	this.chapter.setPersoEnCours(p);
            	this.chapter.setState(new CharacterViewState(this.chapter));
                break;
            }
        }
    	for (CharacterAbstract perso : this.chapter.getPlateauDeJeu().getEnnemies()) {
            Character p = (Character) perso;
            if (p.getPosition().equals(this.chapter.getCurrentPosition())) {
            	this.chapter.setPersoEnCours(p);
            	this.chapter.setState(new CharacterViewState(this.chapter));
                break;
            }
        }
	}

}
