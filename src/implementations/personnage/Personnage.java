package implementations.personnage;

import implementations.Position;
import implementations.objet.Arme;
import implementations.objet.Objet;
import implementations.strategie.Strategie;
import abstracts_interfaces.CharacterAbstract;
import abstracts_interfaces.Organisation;

public class Personnage extends CharacterAbstract {
    
    private int niv;
    private int pv;
    private int pvMax;
    private int puissance;
    private int capacite;
    private int vitesse;
    private int chance;
    private int def;
    private int resistance;
    private int constitution;
    private int aide;
    private int experience;
    
    protected int pourcentagePv;
    protected int pourcentagePuissance;
    protected int pourcentageCapacite;
    protected int pourcentageVitesse;
    protected int pourcentageChance;
    protected int pourcentageDefense;
    protected int pourcentageResistance;
    
    private Position position;
    
    private ComportementPersonnage comportementPersonnage;
    private Strategie strategie;
    
    private final Objet[] objets;
    
    private boolean aJouer;
    
    public Personnage (String nom, Organisation organisation) {
        super(organisation, nom);
        this.experience = 0;
        this.objets = new Objet[5];
        this.aJouer = false;
    }
    
    public Personnage (Personnage perso) {
    	super(perso.getSubject(), perso.getName());
    	this.niv = perso.niv;
    	this.pv = perso.pv;
    	this.pvMax = perso.pvMax;
    	this.puissance = perso.puissance;
    	this.capacite = perso.capacite;
    	this.vitesse = perso.vitesse;
    	this.chance = perso.chance;
    	this.def = perso.def;
    	this.resistance = perso.resistance;
    	this.constitution = perso.constitution;
    	this.aide = perso.aide;
    	this.experience = perso.experience;
    	this.pourcentagePv = perso.pourcentagePv;
    	this.pourcentagePuissance = perso.pourcentagePuissance;
    	this.pourcentageCapacite = perso.pourcentageCapacite;
    	this.pourcentageVitesse = perso.pourcentageVitesse;
    	this.pourcentageChance = perso.pourcentageChance;
    	this.pourcentageDefense = perso.pourcentageDefense;
    	this.pourcentageResistance = perso.pourcentageResistance;
    	this.position = perso.position;
    	this.comportementPersonnage = perso.comportementPersonnage;
    	this.strategie = perso.strategie;
    	this.objets = perso.objets;
    	this.aJouer = perso.aJouer;
    }

    public int getNiv() {
        return niv;
    }

    public void setNiv(int niv) {
        this.niv = niv;
    }

    public int getPv() {
        return pv;
    }

    public void setPv(int pv) {
    	if (pv < 0) {
    		this.pv = 0;
    	} else if (this.pv > this.pvMax) {
    		this.pv = this.pvMax;
    	} else {
    		this.pv = pv;
    	}
    }
    
    public boolean estKo () {
    	return this.pv == 0;
    }

    public int getPvMax() {
        return pvMax;
    }

    public void setPvMax(int pvMax) {
        this.pvMax = pvMax;
    }

    public int getPuissance() {
        return puissance;
    }

    public void setPuissance(int puissance) {
        this.puissance = puissance;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public int getVitesse() {
        return vitesse;
    }

    public void setVitesse(int vitesse) {
        this.vitesse = vitesse;
    }

    public int getChance() {
        return chance;
    }

    public void setChance(int chance) {
        this.chance = chance;
    }

    public int getDef() {
        return def;
    }

    public void setDef(int def) {
        this.def = def;
    }

    public int getResistance() {
        return resistance;
    }

    public void setResistance(int resistance) {
        this.resistance = resistance;
    }

    public int getConstitution() {
        return constitution;
    }

    public void setConstitution(int constitution) {
        this.constitution = constitution;
    }

    public int getAide() {
        return aide;
    }

    public void setAide(int aide) {
        this.aide = aide;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
    	this.experience = experience;
    }
    
    public void ajouterExperience (int experience) {
    	if (this.niv < 20 || (!this.comportementPersonnage.isPromoted() && this.niv == 20)) {
	    	if (this.experience + experience >= 100) {
	    		this.experience = this.experience + experience - 100;
	    		this.niveauSuivant();
	    	} else {
	    		this.experience = this.experience + experience;
	    	}
    	}
    }
    
    public void niveauSuivant () {
    	if (this.niv < 20) {
    		this.niv++;
    		if (this.pvMax < this.comportementPersonnage.getPvMax()) {
    			int alea = (int) (Math.random() *100 +1);
    			if (alea < this.pourcentagePv) {
    				this.pvMax++;
    			}
    		}
    		if (this.puissance < this.comportementPersonnage.getPuissanceMax()) {
    			int alea = (int) (Math.random() *100 +1);
    			if (alea < this.pourcentagePuissance) {
    				this.puissance++;
    			}
    		}
    		if (this.capacite < this.comportementPersonnage.getCapaciteMax()) {
    			int alea = (int) (Math.random() *100 +1);
    			if (alea < this.pourcentageCapacite) {
    				this.capacite++;
    			}
    		}
    		if (this.vitesse < this.comportementPersonnage.getVitesseMax()) {
    			int alea = (int) (Math.random() *100 +1);
    			if (alea < this.pourcentageVitesse) {
    				this.vitesse++;
    			}
    		}
    		if (this.chance < this.comportementPersonnage.getChanceMax()) {
    			int alea = (int) (Math.random() *100 +1);
    			if (alea < this.pourcentageChance) {
    				this.chance++;
    			}
    		}
    		if (this.def < this.comportementPersonnage.getDefMax()) {
    			int alea = (int) (Math.random() *100 +1);
    			if (alea < this.pourcentageDefense) {
    				this.def++;
    			}
    		}
    		if (this.resistance < this.comportementPersonnage.getResistanceMax()) {
    			int alea = (int) (Math.random() *100 +1);
    			if (alea < this.pourcentageResistance) {
    				this.resistance++;
    			}
    		}
    	} else if (this.niv == 20 && this.comportementPersonnage.hasPromotedClass()) {
    		this.comportementPersonnage = this.comportementPersonnage.getPromotedClass();
    		this.niv = 1;
    	}
    }

    public int getPourcentagePv() {
        return pourcentagePv;
    }

    public void setPourcentagePv(int pourcentagePv) {
        this.pourcentagePv = pourcentagePv;
    }

    public int getPourcentagePuissance() {
        return pourcentagePuissance;
    }

    public void setPourcentagePuissance(int pourcentagePuissance) {
        this.pourcentagePuissance = pourcentagePuissance;
    }

    public int getPourcentageCapacite() {
        return pourcentageCapacite;
    }

    public void setPourcentageCapacite(int pourcentageCapacite) {
        this.pourcentageCapacite = pourcentageCapacite;
    }

    public int getPourcentageVitesse() {
        return pourcentageVitesse;
    }

    public void setPourcentageVitesse(int pourcentageVitesse) {
        this.pourcentageVitesse = pourcentageVitesse;
    }

    public int getPourcentageChance() {
        return pourcentageChance;
    }

    public void setPourcentageChance(int pourcentageChance) {
        this.pourcentageChance = pourcentageChance;
    }

    public int getPourcentageDefense() {
        return pourcentageDefense;
    }

    public void setPourcentageDefense(int pourcentageDefense) {
        this.pourcentageDefense = pourcentageDefense;
    }

    public int getPourcentageResistance() {
        return pourcentageResistance;
    }

    public void setPourcentageResistance(int pourcentageResistance) {
        this.pourcentageResistance = pourcentageResistance;
    }

    public ComportementPersonnage getComportementPersonnage() {
        return comportementPersonnage;
    }

    public void setComportementPersonnage(ComportementPersonnage comportementPersonnage) {
        this.comportementPersonnage = comportementPersonnage;
    }

    public Strategie getStrategie() {
		return strategie;
	}

	public void setStrategie(Strategie strategie) {
		this.strategie = strategie;
	}

	public boolean isaJouer() {
		return aJouer;
	}

	public void setaJouer(boolean aJouer) {
		this.aJouer = aJouer;
	}

	public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
    
    public void ajouterObjet (Objet obj) {
        this.objets[0] = obj;
    }

    public Objet[] getObjets() {
        return objets;
    }
    
    public Arme getArme() {
        return (Arme) this.objets[0];
    }

    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

	@Override
	public String display() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String move() {
		// TODO Auto-generated method stub
		return null;
	}
    
}