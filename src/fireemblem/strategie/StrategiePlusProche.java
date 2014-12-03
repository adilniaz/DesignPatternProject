package fireemblem.strategie;

import java.util.List;

import abstracts_interfaces.CharacterAbstract;
import abstracts_interfaces.factories.gameplatforms.AccessAbstract;
import abstracts_interfaces.factories.gameplatforms.ZoneAbstract;
import fireemblem.controlleur.Chapitre;
import fireemblem.controlleur.Combat;
import fireemblem.personnage.Personnage;
import fireemblem.plateauJeu.Case;
import fireemblem.swing.Vues;

public class StrategiePlusProche extends Strategie {
	
	/* se dirige et attaque le perso le plus proche */
	
	public StrategiePlusProche (Personnage perso) {
		super(perso);
	}

	@Override
	public void run(Chapitre chapitre) {
		System.out.println("StrategiePlusProche");
		Personnage perso = this.getPlusProchePerso(chapitre);
		List<ZoneAbstract> zones = this.personnage.getMove().getCaseAvailable(chapitre.getPlateauDeJeu(), this.personnage);
        List<ZoneAbstract> zonesAtk = chapitre.getPorteAttaque(zones, chapitre.getPlateauDeJeu().getZones());
        boolean deplacement = false;
        for (ZoneAbstract zone : zones) {
        	 Case c = (Case) zone;
        	 if (c.getPosition().equals(perso.getPosition())) {
        		 for (AccessAbstract acce : chapitre.getPlateauDeJeu().getAcces()) {
        			 if (acce.getZoneA().equals(c)) {
        				 if (zones.contains(acce.getZoneB())) {
	        				 chapitre.deplacePerso(personnage, ((Case)acce.getZoneB()).getPosition());
	        				 Combat combat = new Combat(personnage, perso);
	                		 Vues.createVue(combat, chapitre.getFenetre());
	                         combat.run();
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
	        	Case c = (Case) zone;
	       	 	if (c.getPosition().equals(perso.getPosition())) {
		       	 	for (AccessAbstract acce : chapitre.getPlateauDeJeu().getAcces()) {
		       	 		if (acce.getZoneA().equals(c)) {
		       	 			if (zonesAtk.contains(acce.getZoneB())) {
			       	 			chapitre.deplacePerso(personnage, ((Case)acce.getZoneB()).getPosition());
			       	 			Combat combat = new Combat(personnage, perso);
			       	 			Vues.createVue(combat, chapitre.getFenetre());
			                    combat.run();
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
        	Case caseProche = null;
        	for (ZoneAbstract zone : zones) {
        		Case c = (Case) zone;
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
	
	public Personnage getPlusProchePerso (Chapitre chapitre) {
		double distance = 100;
		double tmpDistance;
		Personnage p = null;
		for (CharacterAbstract c : chapitre.getPlateauDeJeu().getAnnexes()) {
			Personnage perso = (Personnage) c;
			tmpDistance = this.distanceEuclidienne(this.personnage.getPosition().getPositionX(), this.personnage.getPosition().getPositionY(), perso.getPosition().getPositionX(), perso.getPosition().getPositionY());
			if (tmpDistance < distance) {
				distance = tmpDistance;
				p = perso;
			}
		}
		for (CharacterAbstract c : chapitre.getPlateauDeJeu().getPersonnages()) {
			Personnage perso = (Personnage) c;
			tmpDistance = this.distanceEuclidienne(this.personnage.getPosition().getPositionX(), this.personnage.getPosition().getPositionY(), perso.getPosition().getPositionX(), perso.getPosition().getPositionY());
			if (tmpDistance < distance) {
				distance = tmpDistance;
				p = perso;
			}
		}
		return p;
	}

}
