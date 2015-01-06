package implementations.plateauJeu;

import implementations.Position;

public class CaseFort extends Case {
    
    public CaseFort () {
        this.def = 2;
        this.esq = 20;
        this.type = CaseTypes.fort;
    }
    
    public CaseFort (Position p) {
        this.def = 2;
        this.esq = 20;
        this.position = p;
        this.type = CaseTypes.fort;
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
