package implementations.gameplatform;

import implementations.Position;
import implementations.character.Character;

public class CaseFort extends Square {
    
    public CaseFort () {
        this.def = 2;
        this.esq = 20;
        this.type = SquareTypes.fort;
    }
    
    public CaseFort (Position p) {
        this.def = 2;
        this.esq = 20;
        this.position = p;
        this.type = SquareTypes.fort;
    }

    @Override
    public String getNom() {
        return "Fort";
    }
    
    @Override
    public void effect (Character character) {
    	if (character != null) {
    		character.setPv(character.getPv() + ((character.getPvMax() * 20)/100));
    	}
    }

}
