package implementations.behaviour;

import implementations.character.FireEmblemCharacterType;


public class LordLynBehaviour extends CharacterBehaviour {
    
    public LordLynBehaviour () {
    	
    	this.name = "Lord";
    	this.charactersType = FireEmblemCharacterType.lord_lyn;
    	
        this.pvBase = 16;
        this.puissanceBase = 4;
        this.capaciteBase = 7;
        this.vitesseBase = 9;
        this.chanceBase = 0;
        this.defBase = 2;
        this.resistanceBase = 0;
        this.constitutionBase = 5;

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
