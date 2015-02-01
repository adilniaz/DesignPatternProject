package implementations.gameplatform;

import implementations.Position;
import implementations.character.Character;

public class CaseChateau extends Square {
    
    public CaseChateau () {
        this.def = 0;
        this.esq = 0;
        this.type = SquareTypes.chateau;
    }
    
    public CaseChateau (Position p) {
        this.def = 0;
        this.esq = 0;
        this.position = p;
        this.type = SquareTypes.chateau;
    }

    @Override
    public String getNom() {
        return "Château";
    }
    
    @Override
    public void effect (Character character) {
    	
    }

	@Override
	public String createZone() {
		// TODO Auto-generated method stub
		return null;
	}
}