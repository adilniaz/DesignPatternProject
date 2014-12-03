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

public class StrategiePortee extends Strategie {
	
	/* le perso se déplace et attaque si un ennemie se trouve dans sa 
	 * portee de déplacement + attaque */
	
	public StrategiePortee (Personnage perso) {
		super(perso);
	}

	@Override
	public void run(Chapitre chapitre) {
		List<ZoneAbstract> zones = this.personnage.getMove().getCaseAvailable(chapitre.getPlateauDeJeu(), this.personnage);
        List<ZoneAbstract> zonesAtk = chapitre.getPorteAttaque(zones, chapitre.getPlateauDeJeu().getZones());
        boolean deplacement = verifCombat(zones, chapitre.getPlateauDeJeu().getPersonnages(), chapitre);
        if (!deplacement) {
        	deplacement = verifCombat(zones, chapitre.getPlateauDeJeu().getAnnexes(), chapitre);
        }
        if (!deplacement) {
        	deplacement = verifCombat(zonesAtk, chapitre.getPlateauDeJeu().getPersonnages(), chapitre);
        }
        if (!deplacement) {
        	deplacement = verifCombat(zonesAtk, chapitre.getPlateauDeJeu().getAnnexes(), chapitre);
        }
	}
	
	public boolean verifCombat (List<ZoneAbstract> zones, List<CharacterAbstract> personnages, Chapitre chapitre) {
		for (ZoneAbstract zone : zones) {
			Case c = (Case) zone;
			for (CharacterAbstract character : personnages) {
				Personnage perso = (Personnage) character;
       		 	if (c.getPosition().equals(perso.getPosition())) {
	        		 for (AccessAbstract acce : chapitre.getPlateauDeJeu().getAcces()) {
	        			 if (acce.getZoneA().equals(c)) {
	        				 if (zones.contains(acce.getZoneB())) {
		        				 chapitre.deplacePerso(personnage, ((Case)acce.getZoneB()).getPosition());
		        				 Combat combat = new Combat(personnage, perso);
		                		 Vues.createVue(combat, chapitre.getFenetre());
		                         combat.run();
		                         return true;
	        				 }
	        			 }
	        		 }
	        	 }
       	 	}
		}
		return false;
	}

}
