package implementations.character;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import implementations.Position;
import implementations.behaviour.CharacterBehaviour;
import implementations.controller.Chapter.Ordre;
import implementations.object.Weapon;
import implementations.object.Objet;
import implementations.organizations.Organization;
import implementations.strategy.AttackNearestStrategy;
import implementations.strategy.NoActionStrategy;
import implementations.strategy.NoMovementStrategy;
import implementations.strategy.RangeStrategy;
import implementations.strategy.Strategy;
import abstracts_interfaces.CharacterAbstract;

public class Character extends CharacterAbstract {
    
    private int niv;
    private int pv;
    private int pvGagne;
    private int puissanceGagne;
    private int capaciteGagne;
    private int vitesseGagne;
    private int chanceGagne;
    private int defGagne;
    private int resistanceGagne;
    private int constitutionGagne;
    private int experience;
    
    protected int pourcentagePv;
    protected int pourcentagePuissance;
    protected int pourcentageCapacite;
    protected int pourcentageVitesse;
    protected int pourcentageChance;
    protected int pourcentageDefense;
    protected int pourcentageResistance;
    
    private Position position;
    
    private CharacterBehaviour comportementPersonnage;
    private Strategy strategie;
    
    private final Objet[] objets;
    
    private boolean aJouer;
    
    private Status status;
    private Etat etat;
    
    public enum Status {
    	normal, boss;
    }
    
    public enum Etat {
    	normal, attendre;
    }
    
    public Character (String nom, Organization organisation) {
        super(organisation, nom);
        this.experience = 0;
        this.objets = new Objet[5];
        this.aJouer = false;
        this.niv = 1;
        this.status = Status.normal;
        this.etat = Etat.normal;
        if (organisation != null) {
	        organisation.addListener(new PropertyChangeListener() {
	            @Override
	            public void propertyChange(PropertyChangeEvent evt) {
	                if (evt.getPropertyName().equals("ordre")) {
	                	ordre((Ordre)evt.getOldValue());
	                }
	            }
	        });
        }
    }
    
    public Character (Character perso) {
    	super(perso.getSubject(), perso.getName());
    	this.niv = perso.niv;
    	this.pv = perso.pv;
    	this.pvGagne = perso.pvGagne;
    	this.puissanceGagne = perso.puissanceGagne;
    	this.capaciteGagne = perso.capaciteGagne;
    	this.vitesseGagne = perso.vitesseGagne;
    	this.chanceGagne = perso.chanceGagne;
    	this.defGagne = perso.defGagne;
    	this.resistanceGagne = perso.resistanceGagne;
    	this.constitutionGagne = perso.constitutionGagne;
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
    	this.behaviour = perso.behaviour;
    	this.move = perso.move;
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
    	} else if (pv > this.getPvMax()) {
    		this.pv = this.getPvMax();
    	} else {
    		this.pv = pv;
    	}
    }
    
    public void changeNiveau (int niv) {
    	while (this.niv < niv) {
    		this.niveauSuivant();
    	}
    	this.pv = this.getPvMax();
    }
    
    public boolean estKo () {
    	return this.pv == 0;
    }

    public int getPvMax() {
        return this.pvGagne + this.comportementPersonnage.getPvBase();
    }

    public void setPvGagne(int pv) {
        this.pvGagne = pv;
    }
    
    public int getPvGagne () {
    	return this.pvGagne;
    }

    public int getPuissance() {
        return this.comportementPersonnage.getPuissanceBase() + this.puissanceGagne;
    }

    public void setPuissanceGagne(int puissance) {
        this.puissanceGagne = puissance;
    }
    
    public int getPuissanceGagne () {
    	return this.puissanceGagne;
    }

    public int getCapacite() {
        return this.comportementPersonnage.getCapaciteBase() + this.capaciteGagne;
    }

    public void setCapaciteGagne(int capacite) {
        this.capaciteGagne = capacite;
    }
    
    public int getCapaciteGagne () {
    	return this.capaciteGagne;
    }

    public int getVitesse() {
        return this.comportementPersonnage.getVitesseBase() + this.vitesseGagne;
    }

    public void setVitesseGagne(int vitesse) {
        this.vitesseGagne = vitesse;
    }
    
    public int getVitesseGagne () {
    	return this.vitesseGagne;
    }

    public int getChance() {
        return this.comportementPersonnage.getChanceBase() + this.chanceGagne;
    }

    public void setChanceGagne(int chance) {
        this.chanceGagne = chance;
    }
    
    public int getChanceGagner () {
    	return this.chanceGagne;
    }

    public int getDef() {
        return this.comportementPersonnage.getDefBase() + this.defGagne;
    }

    public void setDefGagne(int def) {
        this.defGagne = def;
    }
    
    public int getDefGagne () {
    	return this.defGagne;
    }

    public int getResistance() {
        return this.comportementPersonnage.getResistanceBase() + this.resistanceGagne;
    }

    public void setResistanceGagne(int resistance) {
        this.resistanceGagne = resistance;
    }
    
    public int getResistanceGagne () {
    	return this.resistanceGagne;
    }

    public int getConstitution() {
        return this.comportementPersonnage.getConstitutionBase() + this.constitutionGagne;
    }

    public void setConstitutionGagne(int constitution) {
        this.constitutionGagne = constitution;
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
    
    public void soin () {
    	this.pv = this.getPvMax();
    }
    
    public void niveauSuivant () {
    	if (this.niv < 19) {
    		this.niv++;
    		if (this.getPvMax() < this.comportementPersonnage.getPvMax()) {
    			int alea = (int) (Math.random() *100 +1);
    			if (alea < this.pourcentagePv) {
    				this.pvGagne++;
    			}
    		}
    		if (this.getPuissance() < this.comportementPersonnage.getPuissanceMax()) {
    			int alea = (int) (Math.random() *100 +1);
    			if (alea < this.pourcentagePuissance) {
    				this.puissanceGagne++;
    			}
    		}
    		if (this.getCapacite() < this.comportementPersonnage.getCapaciteMax()) {
    			int alea = (int) (Math.random() *100 +1);
    			if (alea < this.pourcentageCapacite) {
    				this.capaciteGagne++;
    			}
    		}
    		if (this.getVitesse() < this.comportementPersonnage.getVitesseMax()) {
    			int alea = (int) (Math.random() *100 +1);
    			if (alea < this.pourcentageVitesse) {
    				this.vitesseGagne++;
    			}
    		}
    		if (this.getChance() < this.comportementPersonnage.getChanceMax()) {
    			int alea = (int) (Math.random() *100 +1);
    			if (alea < this.pourcentageChance) {
    				this.chanceGagne++;
    			}
    		}
    		if (this.getDef() < this.comportementPersonnage.getDefMax()) {
    			int alea = (int) (Math.random() *100 +1);
    			if (alea < this.pourcentageDefense) {
    				this.defGagne++;
    			}
    		}
    		if (this.getResistance() < this.comportementPersonnage.getResistanceMax()) {
    			int alea = (int) (Math.random() *100 +1);
    			if (alea < this.pourcentageResistance) {
    				this.resistanceGagne++;
    			}
    		}
    	} else if (this.niv == 19 && this.comportementPersonnage.hasPromotedClass()) {
    		FireEmblemCharacterFactory characterFactory = new FireEmblemCharacterFactory();
    		Character character = (Character)characterFactory.createCharacter(this.getName(), this.subject, this.comportementPersonnage.getPromotedClass());
    		this.behaviour = character.behaviour;
    		this.move = character.move;
    		this.comportementPersonnage = character.comportementPersonnage;
    		this.niv = 1;
    		this.experience = 0;
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

    public CharacterBehaviour getComportementPersonnage() {
        return comportementPersonnage;
    }

    public void setComportementPersonnage(CharacterBehaviour comportementPersonnage) {
        this.comportementPersonnage = comportementPersonnage;
        this.pv = this.getPvMax();
    }

    public Strategy getStrategie() {
		return strategie;
	}

	public void setStrategie(Strategy strategie) {
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
    	for (int i = 0 ; i < this.objets.length ; i++) {
    		if (this.objets[i] == null) {
    			this.objets[i] = obj;
    			break;
    		}
    	}
    }

    public Objet[] getObjets() {
        return objets;
    }
    
    public Weapon getArme() {
        return (Weapon) this.objets[0];
    }
    
    public void setArme (int indice) {
    	Objet obj = this.objets[0];
    	this.objets[0] = this.objets[indice];
    	this.objets[indice] = obj;
    }
    
    public int getNbCombat () {
    	return 0;
    }
    
    public int getNbVictoire () {
    	return 0;
    }
    
    public int getNbDefaite () {
    	return 0;
    }
    
    public void setStatus (Status status) {
    	this.status = status;
    }
    
    public Status getStatus () {
    	return this.status;
    }
    
    public void setEtat (Etat etat) {
    	this.etat = etat;
    }
    
    public Etat getEtat () {
    	return this.etat;
    }
    
    public void ordre (Ordre ordre) {
    	System.out.println(this.getName() + " : changement ordre => " + ordre.toString());
    	switch (ordre) {
    		case immobile :
    			this.setStrategie(new NoMovementStrategy(this));
    			break;
    		case plusProche :
    			this.setStrategie(new AttackNearestStrategy(this));
    			break;
    		case portee :
    			this.setStrategie(new RangeStrategy(this));
    			break;
    		case rien :
    			this.setStrategie(new NoActionStrategy(this));
    			break;
    	}
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
