package implementations.gameplatform;

import implementations.Position;

public class CasePlaine extends Square {
    
    public CasePlaine () {
        this.def = 0;
        this.esq = 0;
        this.type = SquareTypes.plaine;
    }
    
    public CasePlaine (Position p) {
        this.def = 0;
        this.esq = 0;
        this.position = p;
        this.type = SquareTypes.plaine;
    }

    @Override
    public String getNom() {
        return "Plaine";
    }

	@Override
	public String createZone() {
		// TODO Auto-generated method stub
		return null;
	}
    
}
