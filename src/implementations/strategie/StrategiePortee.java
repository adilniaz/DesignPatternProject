package implementations.strategie;

import implementations.controlleur.Chapitre;
import implementations.controlleur.Combat;
import implementations.personnage.Personnage;
import implementations.plateauJeu.Case;
import implementations.swing.Vues;

import java.util.List;

import abstracts_interfaces.CharacterAbstract;
import abstracts_interfaces.factories.gameplatforms.AccessAbstract;
import abstracts_interfaces.factories.gameplatforms.ZoneAbstract;

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
	        					 chapitre.setPersoAttaquer(perso);
		        				 chapitre.deplacePerso(personnage, ((Case)acce.getZoneB()).getPosition());
		        				 Combat combat = new Combat(personnage, perso, chapitre);
		                		 Vues.createVue(combat, chapitre.getFenetre());
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
