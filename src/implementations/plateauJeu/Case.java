package implementations.plateauJeu;

import implementations.Position;
import abstracts_interfaces.factories.gameplatforms.ZoneAbstract;

public abstract class Case extends ZoneAbstract {
    
    protected int def;
    protected int esq;
    protected Position position;
    protected CaseTypes type;
    
    public Position getPosition() {
        return this.position;
    }
    
    public abstract String getNom ();

    public int getDef() {
        return def;
    }

    public int getEsq() {
        return esq;
    }

    public CaseTypes getType() {
        return type;
    }
    
}
