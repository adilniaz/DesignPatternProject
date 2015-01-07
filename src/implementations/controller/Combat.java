package implementations.controller;

import implementations.character.Character;
import implementations.object.Weapon;
import implementations.object.WeaponType;

import java.util.ArrayList;
import java.util.List;


public class Combat extends Controller {
    
    private final Character perso1;
    private final Character perso2;
    private final Chapter chapitre;
    
    public final String SIMULER_COMBAT = "simulerCombat";
    public final String COMBAT = "combat";
    public final String ANNIMATION_ATTAQUE_PERSO = "annimation_attaque_perso";
    public final String ANNIMATION_CRITIQUE_PERSO = "annimation_critique_perso";
    public final String ANNIMATION_DISTANCE_PERSO = "annimation_distance_perso";
    public final String ANNIMATION_DISTANCE_CRITIQUE_PERSO = "annimation_distance_critique_perso";
    public final String ANNIMATION_ESQUIVE_PERSO = "annimation_esquive_perso";
    public final String MODIFY_CLASS_PERSO = "modify_class_perso";
    public final String MODIFY_EXP_PERSO = "modify_exp_perso";
    public final String MODIFY_NIV_PERSO = "modify_niv_perso";
    public final String MODIFY_PV_PERSO1 = "modify_pv_perso1";
    public final String MODIFY_PV_PERSO2 = "modify_pv_perso2";
    public final String FIN_COMBAT = "finCombat";
    public final String FIN_SIMULER_COMBAT = "finSimulerCombat";
    
    private int statPerso1[];
    private int statPerso2[];
    
    private List<Integer> pile;
    
    private boolean continuer;
    
    public Combat (Character perso1, Character perso2, Chapter chapitre) {
        this.perso1 = perso1;
        this.perso2 = perso2;
        this.chapitre = chapitre;
    }

    public Character getPerso1() {
        return perso1;
    }

    public Character getPerso2() {
        return perso2;
    }
    
    public void simulerCombat () {
        int pvPerso1 = this.perso1.getPv();
        int pvPerso2 = this.perso2.getPv();
        
        int forcePerso1 = this.calculeForce(this.perso1, this.perso2) - this.calculeDefense(this.perso2, this.perso1);
        if (forcePerso1 < 0) {
            forcePerso1 = 0;
        }
        int forcePerso2 = this.calculeForce(this.perso2, this.perso1) - this.calculeDefense(this.perso1, this.perso2);
        if (forcePerso2 < 0) {
            forcePerso2 = 0;
        }
        
        int nbAttaquePerso1 = this.calculeNbAttaque(this.perso1, this.perso2);
        int nbAttaquePerso2 = this.calculeNbAttaque(this.perso2, this.perso1);
        
        int precPerso1 = this.calculePrecision(this.perso1, this.perso2);
        int precPerso2 = this.calculePrecision(this.perso2, this.perso1);
        
        int critiquePerso1 = this.calculeCritique(this.perso1);
        int critiquePerso2 = this.calculeCritique(this.perso2);
        
        int statPerso1[] = {pvPerso1, forcePerso1, nbAttaquePerso1, precPerso1, critiquePerso1};
        int statPerso2[] = {pvPerso2, forcePerso2, nbAttaquePerso2, precPerso2, critiquePerso2};
        
        this.pcsControlleurVue.firePropertyChange(SIMULER_COMBAT, statPerso1, statPerso2);
        
    }
    
    public void finSimulation () {
    	this.pcsControlleurVue.firePropertyChange(FIN_SIMULER_COMBAT, null, null);
    }
    
    public int calculeForce (Character perso1, Character perso2) {
        int weaponTriangleBonus = 0;
        int effectiveCoefficient = 1;
        int supportBonus = 0;
        int force = perso1.getPuissance() + ((perso1.getArme().getPuisance() + weaponTriangleBonus) * effectiveCoefficient) + supportBonus;
        return force;
    }
    
    public int calculeDefense (Character perso1, Character perso2) {
        int defense;
        int supportBonus = 0;
        int terrainBonus = 0;
        if (perso2.getArme().getTypeArme() == WeaponType.noir) {
            defense = perso1.getResistance() + supportBonus + terrainBonus;
        } else {
            defense = perso1.getDef() + supportBonus + terrainBonus;
        }
        return defense;
    }
    
    public int calculeVitesseAttaque (Character perso) {
        return perso.getVitesse() - (perso.getArme().getPoids() - perso.getConstitution());
    }
    
    public int calculeNbAttaque (Character perso1, Character perso2) {
        int attackSpeedPerso1 = this.calculeVitesseAttaque(perso1);
        int attackSpeedPerso2 = this.calculeVitesseAttaque(perso2);
        return ((attackSpeedPerso1 - attackSpeedPerso2) >= 4) ? 2 : 1;
    }
    
    public int calculePrecision (Character perso1, Character perso2) {
        int weaponTriangleBonus = 0;
        if (this.weaponIsEffective(perso1.getArme(), perso2.getArme())) {
            weaponTriangleBonus = 15;
        } else if (this.weaponIsWeak(perso1.getArme(), perso2.getArme())) {
            weaponTriangleBonus = -15;
        }
        int supportBonus = 0;
        int sRankBonus = perso1.getArme().getRang() == Weapon.Rang.S ? 5 : 0;
        int precision = perso1.getArme().getPrecision() + (perso1.getCapacite() * 2) + (perso1.getChance() /2) + supportBonus + 
                weaponTriangleBonus + sRankBonus;
        if (precision > 100) {
            precision = 100;
        }
        if (precision < 0) {
            precision = 0;
        }
        return precision - this.calculeEsquive(perso2);
    }
    
    public int calculeEsquive (Character perso) {
        int supportBonus = 0;
        int terrainBonus = 0;
        int tacticianBonus = 0;
        int avoid = (this.calculeVitesseAttaque(perso) * 2) + perso.getChance() + supportBonus + terrainBonus + tacticianBonus;
        return avoid;
    }
    
    public int calculeCritique (Character perso1) {
        int supportBonus = 0;
        int criticalBonus = 0;
        int sRankBonus = perso1.getArme().getRang() == Weapon.Rang.S ? 5 : 0;
        int critique = perso1.getArme().getCritique() + (perso1.getCapacite() / 2) + supportBonus + criticalBonus + sRankBonus;
        return critique;
    }
    
    public boolean weaponIsEffective (Weapon arme1, Weapon arme2) {
        switch (arme1.getTypeArme()) {
            case epee:
                switch (arme2.getTypeArme()) {
                    case hache :
                        return true;
					default:
						return false;
                }
			default:
				return false;
        }
    }
    
    public boolean weaponIsWeak (Weapon arme1, Weapon arme2) {
        return this.weaponIsEffective(arme2, arme1);
    }
    
    public void run () {
        
        int forcePerso1 = this.calculeForce(this.perso1, this.perso2) - this.calculeDefense(this.perso2, this.perso1);
        if (forcePerso1 < 0) {
            forcePerso1 = 0;
        }
        int forcePerso2 = this.calculeForce(this.perso2, this.perso1) - this.calculeDefense(this.perso1, this.perso2);
        if (forcePerso2 < 0) {
            forcePerso2 = 0;
        }
        
        int nbAttaquePerso1 = this.calculeNbAttaque(this.perso1, this.perso2);
        int nbAttaquePerso2 = this.calculeNbAttaque(this.perso2, this.perso1);
        
        int precPerso1 = this.calculePrecision(this.perso1, this.perso2);
        int precPerso2 = this.calculePrecision(this.perso2, this.perso1);
        
        int critiquePerso1 = this.calculeCritique(this.perso1);
        int critiquePerso2 = this.calculeCritique(this.perso2);
        
        int statPerso1[] = {forcePerso1, precPerso1, critiquePerso1};
        int statPerso2[] = {forcePerso2, precPerso2, critiquePerso2};
        this.statPerso1 = statPerso1;
        this.statPerso2 = statPerso2;
        
        this.pcsControlleurVue.firePropertyChange(COMBAT, statPerso1, statPerso2);
        
        this.pile = new ArrayList<>();
        pile.add(1);
        pile.add(2);
        if (nbAttaquePerso1 > 1) {
        	pile.add(1);
        } else if (nbAttaquePerso2 > 1) {
        	pile.add(2);
        }
        this.continuer = true;
        while (!pile.isEmpty()) {
        	if (this.continuer) {
	        	this.continuer = false;
	        	this.continuer();
        	}
        	this.attendre(100);
        }
        if (this.chapitre.getPlateauDeJeu().getPersonnages().contains(this.perso1)) {
        	this.gagneExp(this.perso1, this.perso2, true);
        } else if (this.chapitre.getPlateauDeJeu().getPersonnages().contains(this.perso2)) {
        	this.gagneExp(this.perso2, this.perso1, true);
        }
        this.pcsControlleurVue.firePropertyChange(FIN_COMBAT, null, null);
    }
    
    public void doContinue () {
    	this.continuer = true;
    }
    
    protected void continuer () {
    	if (!pile.isEmpty()) {
    		if (!this.perso1.estKo() && !this.perso2.estKo()) {
	    		if (this.pile.get(0) == 1) {
		    		this.attaquePerso(statPerso1, this.perso1, this.perso2);
		    	} else {
		    		this.attaquePerso(statPerso2, this.perso2, this.perso1);
		    	}
    		} else {
    			this.doContinue();
    		}
	    	this.pile.remove(0);
    	}
    }
    
    private int attaquePerso (int stats[], Character attaquant, Character attaquer) {
    	int prec = 0;
        int critique = 0;
        int damage = 0;
        
        this.pcsControlleurVue.firePropertyChange(ANNIMATION_ATTAQUE_PERSO, attaquant, null);
        
        prec = (int) (Math.random() * 100 + 1);
        critique = (int) (Math.random() * 100 + 1);
        
        if (prec < stats[1]) {
            int pv = attaquer.getPv();
            if (critique < stats[2]) {
            	attaquer.setPv(attaquer.getPv() - (stats[0] * 3));
            } else {
            	attaquer.setPv(attaquer.getPv() - stats[0]);
            }
            damage = pv - attaquer.getPv();
            if (attaquant.equals(this.perso1)) {
            	this.pcsControlleurVue.firePropertyChange(MODIFY_PV_PERSO2, damage, null);
            } else {
            	this.pcsControlleurVue.firePropertyChange(MODIFY_PV_PERSO1, damage, null);
            }
        } else {
            this.pcsControlleurVue.firePropertyChange(ANNIMATION_ESQUIVE_PERSO, attaquer, null);
        }
        return damage;
    }
    
    private void gagneExp (Character attaquant, Character attaquer, boolean damage) {
    	int experience = this.experience(attaquant, attaquer, damage);
    	Character tmpPerso = new Character(attaquant);
        attaquant.ajouterExperience(experience);
        if (!tmpPerso.getComportementPersonnage().equals(attaquant.getComportementPersonnage())) {
        	this.pcsControlleurVue.firePropertyChange(MODIFY_EXP_PERSO, experience, tmpPerso);
        	this.pcsControlleurVue.firePropertyChange(MODIFY_CLASS_PERSO, tmpPerso, attaquant);
        } else if (tmpPerso.getNiv() != attaquant.getNiv()) {
        	this.pcsControlleurVue.firePropertyChange(MODIFY_EXP_PERSO, experience, tmpPerso);
        	this.pcsControlleurVue.firePropertyChange(MODIFY_NIV_PERSO, tmpPerso, attaquant);
        } else {
        	this.pcsControlleurVue.firePropertyChange(MODIFY_EXP_PERSO, experience, tmpPerso);
        }
    }
    
    private int experience (Character perso, Character ennemy, boolean damage) {
    	if (damage) {
    		if (ennemy.estKo()) {
    			return (this.experienceFromDoingDamage(perso, ennemy) + 
    			(this.experienceFromDefeating(perso, ennemy) + 20 + 0 + 0)) * 1;
    		} else {
    			return this.experienceFromDoingDamage(perso, ennemy);
    		}
    	} else {
    		return 1;
    	}
    }
    
    private int experienceFromDoingDamage (Character perso, Character ennemy) {
    	return (31 + ((ennemy.getNiv() + (ennemy.getComportementPersonnage().getClassBonusA())) - 
				(perso.getNiv() + (perso.getComportementPersonnage().getClassBonusA())))) / 
				perso.getComportementPersonnage().getPower();
    }
    
    private int experienceFromDefeating (Character perso, Character ennemy) {
    	return ((ennemy.getNiv() * ennemy.getComportementPersonnage().getPower()) + 
    			ennemy.getComportementPersonnage().getClassBonusB()) - (((perso.getNiv() * 
    			perso.getComportementPersonnage().getPower()) + 
    			perso.getComportementPersonnage().getClassBonusB()) / 1);
    }
}
