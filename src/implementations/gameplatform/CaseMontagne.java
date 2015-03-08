package implementations.gameplatform;

import implementations.Position;
import implementations.character.Character;

public class CaseMontagne extends Square {
    
    public CaseMontagne () {
        this.def = 1;
        this.esq = 30;
        this.type = SquareTypes.montagne;
    }
    
    public CaseMontagne (Position p) {
        this.def = 1;
        this.esq = 30;
        this.position = p;
        this.type = SquareTypes.montagne;
    }

    @Override
    public String getNom() {
        return "Montagne";
    }
    
    @Override
    public void effect (Character character) {
    	
    }

}