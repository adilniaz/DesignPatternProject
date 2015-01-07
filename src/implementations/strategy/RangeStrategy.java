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

public class RangeStrategy extends Strategy {
	
	/* le perso se déplace et attaque si un ennemie se trouve dans sa 
	 * portee de déplacement + attaque */
	
	public RangeStrategy (Character perso) {
		super(perso);
	}

	@Override
	public void run(Chapter chapitre) {
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
	
	public boolean verifCombat (List<ZoneAbstract> zones, List<CharacterAbstract> personnages, Chapter chapitre) {
		for (ZoneAbstract zone : zones) {
			Square c = (Square) zone;
			for (CharacterAbstract character : personnages) {
				Character perso = (Character) character;
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
