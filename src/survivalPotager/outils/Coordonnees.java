package survivalPotager.outils;

public class Coordonnees {

	private int x;
        private int y;

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}
	
	public Coordonnees(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public String toString() {
		return "(" + (this.getX()+1) + ", " + (this.getY()+1) + ")";
	}

	public boolean equals(Coordonnees c) {
		if (this.getX() == c.getX() && this.getY() == c.getY())
			return true;
		else
			return false;
	}
}
