package implementations.strategy;

import implementations.Position;
import implementations.character.Character;
import implementations.controller.Chapter;
import implementations.controller.Combat;
import implementations.views.View;
import abstracts_interfaces.CharacterAbstract;

public class NoMovementStrategy extends Strategy {
	
	/* Le personnage ne bouge pas mais attaque si un ennemie se trouve 
	 * a porté d'attaque */
	
	public NoMovementStrategy (Character perso) {
		super(perso);
	}
	
	public String name () {
		return "immobile";
	}

	@Override
	public void run(Chapter chapitre) {
		if (chapitre.getPlateauDeJeu().getEnnemies().contains(personnage)) {
			boolean combat = false;
			Character perso;
			Position p1 = new Position(personnage.getPosition().getPositionX()+1, personnage.getPosition().getPositionY());
            perso = this.getPersonnage(p1, chapitre);
            if (perso != null && !combat) {
            	this.combat(this.personnage, perso, chapitre);
            }
			Position p2 = new Position(personnage.getPosition().getPositionX()-1, personnage.getPosition().getPositionY());
			perso = this.getPersonnage(p2, chapitre);
			if (perso != null && !combat) {
            	this.combat(this.personnage, perso, chapitre);
            }
			Position p3 = new Position(personnage.getPosition().getPositionX(), personnage.getPosition().getPositionY()+1);
			perso = this.getPersonnage(p3, chapitre);
			if (perso != null && !combat) {
            	this.combat(this.personnage, perso, chapitre);
            }
			Position p4 = new Position(personnage.getPosition().getPositionX(), personnage.getPosition().getPositionY()-1);
			perso = this.getPersonnage(p4, chapitre);
			if (perso != null && !combat) {
            	this.combat(this.personnage, perso, chapitre);
            }
		}
	}
	
	public Character getPersonnage (Position p, Chapter chapitre) {
		for (CharacterAbstract c : chapitre.getPlateauDeJeu().getAnnexes()) {
			Character perso = (Character) c;
			if (perso.getPosition().equals(p)) {
				return perso;
			}
		}
		for (CharacterAbstract c : chapitre.getPlateauDeJeu().getPersonnages()) {
			Character perso = (Character) c;
			if (perso.getPosition().equals(p)) {
				return perso;
			}
		}
		return null;
	}
	
	private void combat (Character p1, Character p2, Chapter chapitre) {
		chapitre.setPersoAttaquer(p2);
    	Combat combat = new Combat(p1, p2, chapitre);
    	View.createVue(combat, chapitre.getFenetre());
        combat.run();
        chapitre.verifMort();
	}

}
