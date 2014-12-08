package fireemblem.objet;

public class Objet {
    
    private String name;
    private TypeObjet typeObjet;
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

    public TypeObjet getType() {
        return typeObjet;
    }

    public int getUtilisation() {
        return utilisation;
    }

    public int getPrix() {
        return prix;
    }
    
}
