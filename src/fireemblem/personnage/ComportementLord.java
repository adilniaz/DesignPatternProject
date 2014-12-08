package fireemblem.personnage;

public class ComportementLord extends ComportementPersonnage {
    
    public ComportementLord () {
    	
    	this.name = "Lord";
    	
        this.pvBase = 0;
        this.puissanceBase = 0;
        this.capaciteBase = 0;
        this.vitesseBase = 0;
        this.chanceBase = 0;
        this.defBase = 0;
        this.resistanceBase = 0;

        this.pvMax = 60;
        this.puissanceMax = 20;
        this.capaciteMax = 20;
        this.vitesseMax = 20;
        this.chanceMax = 30;
        this.defMax = 20;
        this.resistanceMax = 20;
        
        this.promoted = false;
        this.power = 3;
        this.classBonusA = 0;
        this.classBonusB = 0;
    }
}
