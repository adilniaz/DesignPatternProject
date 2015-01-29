package implementations.behaviour;

import implementations.character.FireEmblemCharacterType;

public class LordHappiaBehaviour extends CharacterBehaviour {
    
    public LordHappiaBehaviour () {
    	
    	this.name = "Lord Happia";
    	this.charactersType = FireEmblemCharacterType.lord_happia;
    	
        this.pvBase = 22;
        this.puissanceBase = 7;
        this.capaciteBase = 6;
        this.vitesseBase = 8;
        this.chanceBase = 0;
        this.defBase = 9;
        this.resistanceBase = 5;
        this.constitutionBase = 15;

        this.pvMax = 60;
        this.puissanceMax = 30;
        this.capaciteMax = 24;
        this.vitesseMax = 24;
        this.chanceMax = 30;
        this.defMax = 29;
        this.resistanceMax = 20;
        this.constitutionMax = 25;
        
        this.promoted = true;
        this.power = 3;
        this.classBonusA = 20;
        this.classBonusB = 60;
    }
}