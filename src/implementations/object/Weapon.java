package implementations.object;

public class Weapon extends Objet {
    
    public enum Rang {
        Prf, S, A, B, C, D, E, none;
    }
    
    private final Rang rang;
    private final int porte;
    private final int poids;
    private final int puisance;
    private final int precision;
    private final int critique;
    private final WeaponType typeArme;
    
    public Weapon (String nom, int utilisation, int prix, Rang rang, int porte, int poids, int puissance, int precision, int critique, WeaponType typeArme, ObjetType typeObjet) {
        super(nom, utilisation, prix, typeObjet);
        this.rang = rang;
        this.porte = porte;
        this.poids = poids;
        this.puisance = puissance;
        this.precision = precision;
        this.critique = critique;
        this.typeArme = typeArme;
    }

    public Rang getRang() {
        return rang;
    }

    public int getPorte() {
        return porte;
    }

    public int getPoids() {
        return poids;
    }

    public int getPuisance() {
        return puisance;
    }

    public int getPrecision() {
        return precision;
    }

    public int getCritique() {
        return critique;
    }

    public WeaponType getTypeArme() {
        return typeArme;
    }
}
