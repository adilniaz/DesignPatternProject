package fireemblem.strategie;

import abstracts_interfaces.CharacterAbstract;
import fireemblem.Position;
import fireemblem.controlleur.Chapitre;
import fireemblem.controlleur.Combat;
import fireemblem.personnage.Personnage;
import fireemblem.swing.Vues;

public class StrategieImmobile extends Strategie {
	
	/* Le personnage ne bouge pas mais attaque si un ennemie se trouve 
	 * a porté d'attaque */
	
	public StrategieImmobile (Personnage perso) {
		super(perso);
	}

	@Override
	public void run(Chapitre chapitre) {
		if (chapitre.getPlateauDeJeu().getEnnemies().contains(personnage)) {
			Personnage perso;
			Position p1 = new Position(personnage.getPosition().getPositionX()+1, personnage.getPosition().getPositionY());
            perso = this.getPersonnage(p1, chapitre);
            if (perso != null) {
            	Combat combat = new Combat(personnage, perso);
            	Vues.createVue(combat, chapitre.getFenetre());
                combat.combat();
            }
			Position p2 = new Position(personnage.getPosition().getPositionX()-1, personnage.getPosition().getPositionY());
			perso = this.getPersonnage(p2, chapitre);
            if (perso != null) {
            	Combat combat = new Combat(personnage, perso);
            	Vues.createVue(combat, chapitre.getFenetre());
                combat.combat();
            }
			Position p3 = new Position(personnage.getPosition().getPositionX(), personnage.getPosition().getPositionY()+1);
			perso = this.getPersonnage(p3, chapitre);
            if (perso != null) {
            	Combat combat = new Combat(personnage, perso);
            	Vues.createVue(combat, chapitre.getFenetre());
                combat.combat();
            }
			Position p4 = new Position(personnage.getPosition().getPositionX(), personnage.getPosition().getPositionY()-1);
			perso = this.getPersonnage(p4, chapitre);
            if (perso != null) {
            	Combat combat = new Combat(personnage, perso);
            	Vues.createVue(combat, chapitre.getFenetre());
                combat.combat();
            }
		}
	}
	
	public Personnage getPersonnage (Position p, Chapitre chapitre) {
		for (CharacterAbstract c : chapitre.getPlateauDeJeu().getAnnexes()) {
			Personnage perso = (Personnage) c;
			if (perso.getPosition().equals(p)) {
				return perso;
			}
		}
		for (CharacterAbstract c : chapitre.getPlateauDeJeu().getPersonnages()) {
			Personnage perso = (Personnage) c;
			if (perso.getPosition().equals(p)) {
				return perso;
			}
		}
		return null;
	}

}
