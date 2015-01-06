package implementations;

public class Position {
    
    public enum Orientation {
        face, dos, gauche, droite;
    }
    
    private int positionX;
    private int positionY;
    private Orientation orientation;
    
    public Position() {
        this.positionX = 0;
        this.positionY = 0;
        this.orientation = null;
    }
    
    public Position(int x, int y) {
        this.positionX = x;
        this.positionY = y;
        this.orientation = null;
    }
    
    public Position(int x, int y, Orientation orientation) {
        this.positionX = x;
        this.positionY = y;
        this.orientation = orientation;
    }
    
    public Position (Position p) {
        this.positionX = p.positionX;
        this.positionY = p.positionY;
        this.orientation = p.orientation;
    }

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + this.positionX;
        hash = 67 * hash + this.positionY;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Position other = (Position) obj;
        if (this.positionX != other.positionX) {
            return false;
        }
        if (this.positionY != other.positionY) {
            return false;
        }
        return true;
    }
    
}