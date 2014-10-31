package fireemblem.objet;

public class Objet {
    
    private String name;
    private int utilisation;
    private int prix;
    
    public Objet (String nom, int utilisation, int prix) {
        this.name = nom;
        this.utilisation = utilisation;
        this.prix = prix;
    }

    public String getName() {
        return name;
    }

    public int getUtilisation() {
        return utilisation;
    }

    public int getPrix() {
        return prix;
    }
    
}
