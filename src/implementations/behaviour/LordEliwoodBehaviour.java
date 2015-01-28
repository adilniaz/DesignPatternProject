package implementations.behaviour;

import implementations.character.FireEmblemCharacterType;

public class LordEliwoodBehaviour extends CharacterBehaviour {
    
    public LordEliwoodBehaviour () {
    	
    	this.name = "Lord";
    	this.charactersType = FireEmblemCharacterType.lord_eliwood;
    	
        this.pvBase = 18;
        this.puissanceBase = 5;
        this.capaciteBase = 5;
        this.vitesseBase = 7;
        this.chanceBase = 0;
        this.defBase = 5;
        this.resistanceBase = 0;
        this.constitutionBase = 7;

        this.pvMax = 60;
        this.puissanceMax = 20;
        this.capaciteMax = 20;
        this.vitesseMax = 20;
        this.chanceMax = 30;
        this.defMax = 20;
        this.resistanceMax = 20;
        this.constitutionMax = 20;
        
        this.promoted = false;
        this.power = 3;
        this.classBonusA = 0;
        this.classBonusB = 0;
    }
}