package fireemblem.personnage;

import implementations.organisations.Organisation;
import fireemblem.Position;
import fireemblem.objet.Arme;
import fireemblem.objet.Objet;
import fireemblem.strategie.Strategie;
import abstracts_interfaces.CharacterAbstract;

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
        this.objets = new Objet[5];
        this.aJouer = false;
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
        this.pv = pv;
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
