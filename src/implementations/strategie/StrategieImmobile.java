package implementations.strategie;

import implementations.Position;
import implementations.controlleur.Chapitre;
import implementations.controlleur.Combat;
import implementations.personnage.Personnage;
import implementations.swing.Vues;
import abstracts_interfaces.CharacterAbstract;

public class StrategieImmobile extends Strategie {
	
	/* Le personnage ne bouge pas mais attaque si un ennemie se trouve 
	 * a porté d'attaque */
	
	public StrategieImmobile (Personnage perso) {
		super(perso);
	}

	@Override
	public void run(Chapitre chapitre) {
		if (chapitre.getPlateauDeJeu().getEnnemies().contains(personnage)) {
			boolean combat = false;
			Personnage perso;
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
	
	private void combat (Personnage p1, Personnage p2, Chapitre chapitre) {
		chapitre.setPersoAttaquer(p2);
    	Combat combat = new Combat(p1, p2, chapitre);
    	Vues.createVue(combat, chapitre.getFenetre());
        combat.run();
        chapitre.verifMort();
	}

}
