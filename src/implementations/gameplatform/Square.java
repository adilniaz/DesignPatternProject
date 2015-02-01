package implementations.gameplatform;

import implementations.Position;
import abstracts_interfaces.factories.gameplatforms.ZoneAbstract;
import implementations.character.Character;

public abstract class Square extends ZoneAbstract {
    
    protected int def;
    protected int esq;
    protected Position position;
    protected SquareTypes type;
    
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

    public SquareTypes getType() {
        return type;
    }
    
    public abstract void effect (Character character);
    
}
