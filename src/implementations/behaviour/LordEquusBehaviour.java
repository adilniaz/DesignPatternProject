package implementations.behaviour;


public class LordEquusBehaviour extends CharacterBehaviour {
    
    public LordEquusBehaviour () {
    	
    	this.name = "Lord Equus";
    	
        this.pvBase = 22;
        this.puissanceBase = 7;
        this.capaciteBase = 5;
        this.vitesseBase = 8;
        this.chanceBase = 0;
        this.defBase = 6;
        this.resistanceBase = 3;
        this.constitutionBase = 9;

        this.pvMax = 60;
        this.puissanceMax = 27;
        this.capaciteMax = 26;
        this.vitesseMax = 24;
        this.chanceMax = 30;
        this.defMax = 23;
        this.resistanceMax = 25;
        this.constitutionMax = 25;
        
        this.promoted = true;
        this.power = 3;
        this.classBonusA = 20;
        this.classBonusB = 60;
    }
}