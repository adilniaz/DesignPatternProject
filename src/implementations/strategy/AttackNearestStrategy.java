package implementations.strategy;

import implementations.character.Character;
import implementations.controller.Chapter;
import implementations.controller.Combat;
import implementations.gameplatform.Square;
import implementations.views.View;

import java.util.List;

import abstracts_interfaces.CharacterAbstract;
import abstracts_interfaces.factories.gameplatforms.AccessAbstract;
import abstracts_interfaces.factories.gameplatforms.ZoneAbstract;

public class AttackNearestStrategy extends Strategy {
	
	/* se dirige et attaque le perso le plus proche */
	
	public AttackNearestStrategy (Character perso) {
		super(perso);
	}

	@Override
	public void run(Chapter chapitre) {
		Character perso = this.getPlusProchePerso(chapitre);
		List<ZoneAbstract> zones = this.personnage.getMove().getCaseAvailable(chapitre.getPlateauDeJeu(), this.personnage);
        List<ZoneAbstract> zonesAtk = chapitre.getPorteAttaque(zones, chapitre.getPlateauDeJeu().getZones());
        boolean deplacement = false;
        for (ZoneAbstract zone : zones) {
        	 Square c = (Square) zone;
        	 if (c.getPosition().equals(perso.getPosition())) {
        		 for (AccessAbstract acce : chapitre.getPlateauDeJeu().getAcces()) {
        			 if (acce.getZoneA().equals(c)) {
        				 if (zones.contains(acce.getZoneB())) {
        					 chapitre.setPersoAttaquer(perso);
	        				 chapitre.deplacePerso(personnage, ((Square)acce.getZoneB()).getPosition());
	        				 Combat combat = new Combat(personnage, perso, chapitre);
	                		 View.createVue(combat, chapitre.getFenetre());
	                         combat.run();
	                         chapitre.verifMort();
	                         deplacement = true;
	                         break;
        				 }
        			 }
        		 }
        	 }
        	 if (deplacement) {
        		 break;
        	 }
        }
        if (!deplacement) {
	        for (ZoneAbstract zone : zonesAtk) {
	        	Square c = (Square) zone;
	       	 	if (c.getPosition().equals(perso.getPosition())) {
		       	 	for (AccessAbstract acce : chapitre.getPlateauDeJeu().getAcces()) {
		       	 		if (acce.getZoneA().equals(c)) {
		       	 			if (zonesAtk.contains(acce.getZoneB())) {
		       	 				chapitre.setPersoAttaquer(perso);
			       	 			chapitre.deplacePerso(personnage, ((Square)acce.getZoneB()).getPosition());
			       	 			Combat combat = new Combat(personnage, perso, chapitre);
			       	 			View.createVue(combat, chapitre.getFenetre());
			                    combat.run();
			                    chapitre.verifMort();
			                    deplacement = true;
			                    break;
		       	 			}
		       	 		}
		       	 	}
	       	 	}
		       	if (deplacement) {
	        		 break;
	        	}
	        }
        }
        if (!deplacement) {
        	double distance = 100;
        	double tmpDistance;
        	Square caseProche = null;
        	for (ZoneAbstract zone : zones) {
        		Square c = (Square) zone;
	           	tmpDistance = this.distanceEuclidienne(c.getPosition().getPositionX(), c.getPosition().getPositionY(), perso.getPosition().getPositionX(), perso.getPosition().getPositionY());
	           	if (tmpDistance < distance) {
					distance = tmpDistance;
					caseProche = c;
				}
	        }
        	chapitre.deplacePerso(personnage, caseProche.getPosition());
        }
	}
	
	public double distanceEuclidienne (int x1, int y1, int x2, int y2) {
		return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
	}
	
	public Character getPlusProchePerso (Chapter chapitre) {
		double distance = 100;
		double tmpDistance;
		Character p = null;
		for (CharacterAbstract c : chapitre.getPlateauDeJeu().getAnnexes()) {
			Character perso = (Character) c;
			tmpDistance = this.distanceEuclidienne(this.personnage.getPosition().getPositionX(), this.personnage.getPosition().getPositionY(), perso.getPosition().getPositionX(), perso.getPosition().getPositionY());
			if (tmpDistance < distance) {
				distance = tmpDistance;
				p = perso;
			}
		}
		for (CharacterAbstract c : chapitre.getPlateauDeJeu().getPersonnages()) {
			Character perso = (Character) c;
			tmpDistance = this.distanceEuclidienne(this.personnage.getPosition().getPositionX(), this.personnage.getPosition().getPositionY(), perso.getPosition().getPositionX(), perso.getPosition().getPositionY());
			if (tmpDistance < distance) {
				distance = tmpDistance;
				p = perso;
			}
		}
		return p;
	}

}
