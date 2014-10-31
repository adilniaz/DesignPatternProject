package fireemblem.controlleur;

import fireemblem.objet.Arme;
import fireemblem.objet.TypeArme;
import fireemblem.personnage.Personnage;

public class Combat extends Controlleur {
    
    private final Personnage perso1;
    private final Personnage perso2;
    
    public final String SIMULER_COMBAT = "simulerCombat";
    public final String COMBAT = "combat";
    public final String ANNIMATION_ATTAQUE_PERSO1 = "annimation_attaque_perso1";
    public final String ANNIMATION_CRITIQUE_PERSO1 = "annimation_critique_perso1";
    public final String ANNIMATION_DISTANCE_PERSO1 = "annimation_distance_perso1";
    public final String ANNIMATION_DISTANCE_CRITIQUE_PERSO1 = "annimation_distance_critique_perso1";
    public final String ANNIMATION_ESQUIVE_PERSO1 = "annimation_esquive_perso1";
    public final String ANNIMATION_ATTAQUE_PERSO2 = "annimation_attaque_perso2";
    public final String ANNIMATION_CRITIQUE_PERSO2 = "annimation_critique_perso2";
    public final String ANNIMATION_DISTANCE_PERSO2 = "annimation_distance_perso2";
    public final String ANNIMATION_DISTANCE_CRITIQUE_PERSO2 = "annimation_distance_critique_perso2";
    public final String ANNIMATION_ESQUIVE_PERSO2 = "annimation_esquive_perso2";
    public final String MODIFY_PV_PERSO1 = "modify_pv_perso1";
    public final String MODIFY_PV_PERSO2 = "modify_pv_perso2";
    public final String FIN_COMBAT = "finCombat";
    
    public Combat (Personnage perso1, Personnage perso2) {
        this.perso1 = perso1;
        this.perso2 = perso2;
    }

    public Personnage getPerso1() {
        return perso1;
    }

    public Personnage getPerso2() {
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
    
    public int calculeForce (Personnage perso1, Personnage perso2) {
        int weaponTriangleBonus = 0;
        int effectiveCoefficient = 1;
        int supportBonus = 0;
        int force = perso1.getPuissance() + ((perso1.getArme().getPuisance() + weaponTriangleBonus) * effectiveCoefficient) + supportBonus;
        return force;
    }
    
    public int calculeDefense (Personnage perso1, Personnage perso2) {
        int defense;
        int supportBonus = 0;
        int terrainBonus = 0;
        if (perso2.getArme().getTypeArme() == TypeArme.noir) {
            defense = perso1.getResistance() + supportBonus + terrainBonus;
        } else {
            defense = perso1.getDef() + supportBonus + terrainBonus;
        }
        return defense;
    }
    
    public int calculeVitesseAttaque (Personnage perso) {
        return perso.getVitesse() - (perso.getArme().getPoids() - perso.getConstitution());
    }
    
    public int calculeNbAttaque (Personnage perso1, Personnage perso2) {
        int attackSpeedPerso1 = this.calculeVitesseAttaque(perso1);
        int attackSpeedPerso2 = this.calculeVitesseAttaque(perso2);
        return ((attackSpeedPerso1 - attackSpeedPerso2) >= 4) ? 2 : 1;
    }
    
    public int calculePrecision (Personnage perso1, Personnage perso2) {
        int weaponTriangleBonus = 0;
        if (this.weaponIsEffective(perso1.getArme(), perso2.getArme())) {
            weaponTriangleBonus = 15;
        } else if (this.weaponIsWeak(perso1.getArme(), perso2.getArme())) {
            weaponTriangleBonus = -15;
        }
        int supportBonus = 0;
        int sRankBonus = perso1.getArme().getRang() == Arme.Rang.S ? 5 : 0;
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
    
    public int calculeEsquive (Personnage perso) {
        int supportBonus = 0;
        int terrainBonus = 0;
        int tacticianBonus = 0;
        int avoid = (this.calculeVitesseAttaque(perso) * 2) + perso.getChance() + supportBonus + terrainBonus + tacticianBonus;
        return avoid;
    }
    
    public int calculeCritique (Personnage perso1) {
        int supportBonus = 0;
        int criticalBonus = 0;
        int sRankBonus = perso1.getArme().getRang() == Arme.Rang.S ? 5 : 0;
        int critique = perso1.getArme().getCritique() + (perso1.getCapacite() / 2) + supportBonus + criticalBonus + sRankBonus;
        return critique;
    }
    
    public boolean weaponIsEffective (Arme arme1, Arme arme2) {
        switch (arme1.getTypeArme()) {
            case epee:
                switch (arme2.getTypeArme()) {
                    case hache :
                        return true;
                }
        }
        return false;
    }
    
    public boolean weaponIsWeak (Arme arme1, Arme arme2) {
        return this.weaponIsEffective(arme2, arme1);
    }
    
    public void combat () {
        
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
        
        this.pcsControlleurVue.firePropertyChange(COMBAT, statPerso1, statPerso2);
        
        int prec = 0;
        int critique = 0;
        
        this.pcsControlleurVue.firePropertyChange(ANNIMATION_ATTAQUE_PERSO1, null, null);
        
        prec = (int) (Math.random() * 100 + 1);
        critique = (int) (Math.random() * 100 + 1);
        
        if (prec < precPerso1) {
            int pvP2 = this.perso2.getPv();
            if (critique < critiquePerso1) {
                this.perso2.setPv(this.perso2.getPv() - (forcePerso1 * 3));
            } else {
                this.perso2.setPv(this.perso2.getPv() - forcePerso1);
            }
            this.pcsControlleurVue.firePropertyChange(MODIFY_PV_PERSO2, pvP2 - this.perso2.getPv(), null);
        } else {
            this.pcsControlleurVue.firePropertyChange(ANNIMATION_ESQUIVE_PERSO2, null, null);
        }
        
        if (this.perso2.getPv() > 0) {
            prec = (int) (Math.random() * 100 + 1);
            critique = (int) (Math.random() * 100 + 1);
            this.pcsControlleurVue.firePropertyChange(ANNIMATION_ATTAQUE_PERSO2, null, null);
            if (prec < precPerso2) {
                int pvP1 = this.perso1.getPv();
                if (critique < critiquePerso2) {
                    this.perso1.setPv(this.perso1.getPv() - (forcePerso2 * 3));
                } else {
                    this.perso1.setPv(this.perso1.getPv() - forcePerso2);
                }
                this.pcsControlleurVue.firePropertyChange(MODIFY_PV_PERSO1, pvP1 - this.perso1.getPv(), null);
            } else {
                this.pcsControlleurVue.firePropertyChange(ANNIMATION_ESQUIVE_PERSO1, null, null);
            }
        }
        
        if (this.perso1.getPv() > 0 && nbAttaquePerso1 > 1) {
            prec = (int) (Math.random() * 100 + 1);
            critique = (int) (Math.random() * 100 + 1);
            this.pcsControlleurVue.firePropertyChange(ANNIMATION_ATTAQUE_PERSO1, null, null);
            if (prec < precPerso1) {
                int pvP2 = this.perso2.getPv();
                if (critique < critiquePerso1) {
                    this.perso2.setPv(this.perso2.getPv() - (forcePerso1 * 3));
                } else {
                    this.perso2.setPv(this.perso2.getPv() - forcePerso1);
                }
                this.pcsControlleurVue.firePropertyChange(MODIFY_PV_PERSO2, pvP2 - this.perso2.getPv(), null);
            } else {
                this.pcsControlleurVue.firePropertyChange(ANNIMATION_ESQUIVE_PERSO2, null, null);
            }
        }
        
        if (this.perso2.getPv() > 0 && nbAttaquePerso2 > 1) {
            prec = (int) (Math.random() * 100 + 1);
            critique = (int) (Math.random() * 100 + 1);
            this.pcsControlleurVue.firePropertyChange(ANNIMATION_ATTAQUE_PERSO2, null, null);
            if (prec < precPerso2) {
                int pvP1 = this.perso1.getPv();
                if (critique < critiquePerso2) {
                    this.perso1.setPv(this.perso1.getPv() - (forcePerso2 * 3));
                } else {
                    this.perso1.setPv(this.perso1.getPv() - forcePerso2);
                }
                this.pcsControlleurVue.firePropertyChange(MODIFY_PV_PERSO1, pvP1 - this.perso1.getPv(), null);
            } else {
                this.pcsControlleurVue.firePropertyChange(ANNIMATION_ESQUIVE_PERSO1, null, null);
            }
        }
    }
    
    private void attaquePerso1 () {
        
    }    
}
