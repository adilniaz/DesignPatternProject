package implementations.behaviour;

import implementations.character.FireEmblemCharacterType;

public class CharacterBehaviour {
	
	protected String name;
    
    protected int pvBase;
    protected int puissanceBase;
    protected int capaciteBase;
    protected int vitesseBase;
    protected int chanceBase;
    protected int defBase;
    protected int resistanceBase;
    protected int constitutionBase;
    
    protected int pvMax;
    protected int puissanceMax;
    protected int capaciteMax;
    protected int vitesseMax;
    protected int chanceMax;
    protected int defMax;
    protected int resistanceMax;
    protected int constitutionMax;
    
    protected boolean promoted;
    protected int power;
    protected int classBonusA;
    protected int classBonusB;
    
    private FireEmblemCharacterType promtedClass;
    
    public String getName () {
    	return this.name;
    }

    public int getPvBase() {
        return pvBase;
    }

    public int getPuissanceBase() {
        return puissanceBase;
    }

    public int getCapaciteBase() {
        return capaciteBase;
    }

    public int getVitesseBase() {
        return vitesseBase;
    }

    public int getChanceBase() {
        return chanceBase;
    }

    public int getDefBase() {
        return defBase;
    }

    public int getResistanceBase() {
        return resistanceBase;
    }

    public int getPvMax() {
        return pvMax;
    }

    public int getPuissanceMax() {
        return puissanceMax;
    }

    public int getCapaciteMax() {
        return capaciteMax;
    }

    public int getVitesseMax() {
        return vitesseMax;
    }

    public int getChanceMax() {
        return chanceMax;
    }

    public int getDefMax() {
        return defMax;
    }

    public int getResistanceMax() {
        return resistanceMax;
    }
    
    public boolean isPromoted () {
    	return this.promoted;
    }
    
    public int getPower () {
    	return this.power;
    }
    
    public int getClassBonusA () {
    	return this.classBonusA;
    }
    
    public int getClassBonusB () {
    	return this.classBonusB;
    }
    
    public void setPromotedClass (FireEmblemCharacterType characterType) {
    	this.promtedClass = characterType;
    }
    
    public FireEmblemCharacterType getPromotedClass () {
    	return this.promtedClass;
    }
    
    public boolean hasPromotedClass () {
    	return this.promtedClass != null;
    }
    
}
