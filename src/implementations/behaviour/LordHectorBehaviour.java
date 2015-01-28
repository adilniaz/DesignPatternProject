package implementations.behaviour;

import implementations.character.FireEmblemCharacterType;

public class LordHectorBehaviour extends CharacterBehaviour {
    
    public LordHectorBehaviour () {
    	
    	this.name = "Lord";
    	this.charactersType = FireEmblemCharacterType.lord_hector;
    	
        this.pvBase = 19;
        this.puissanceBase = 7;
        this.capaciteBase = 4;
        this.vitesseBase = 5;
        this.chanceBase = 0;
        this.defBase = 8;
        this.resistanceBase = 0;
        this.constitutionBase = 13;

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