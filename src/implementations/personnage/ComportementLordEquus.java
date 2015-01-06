package implementations.personnage;

public class ComportementLordEquus extends ComportementPersonnage {
    
    public ComportementLordEquus () {
    	
    	this.name = "Lord Equus";
    	
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
        
        this.promoted = true;
        this.power = 3;
        this.classBonusA = 20;
        this.classBonusB = 60;
    }
}