package implementations.plateauJeu;

import implementations.Position;

public class CasePlaine extends Case {
    
    public CasePlaine () {
        this.def = 0;
        this.esq = 0;
        this.type = CaseTypes.plaine;
    }
    
    public CasePlaine (Position p) {
        this.def = 0;
        this.esq = 0;
        this.position = p;
        this.type = CaseTypes.plaine;
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
