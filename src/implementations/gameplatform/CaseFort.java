package implementations.gameplatform;

import implementations.Position;

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
	public String createZone() {
		// TODO Auto-generated method stub
		return null;
	}
}
