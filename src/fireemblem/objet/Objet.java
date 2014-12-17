package fireemblem.objet;

public class Objet {
    
    private String name;
    private TypeObjet typeObjet;
    private int utilisation;
    private int utilisationMax;
    private int prix;
    
    public Objet (String nom, int utilisation, int prix, TypeObjet typeObjet) {
        this.name = nom;
        this.utilisation = utilisation;
        this.prix = prix;
        this.typeObjet = typeObjet;
    }

    public String getName() {
        return name;
    }

    public TypeObjet getType() {
        return typeObjet;
    }
    
    public void setUtilisation (int utilisation) {
    	this.utilisation = utilisation;
    }

    public int getUtilisation() {
        return utilisation;
    }

    public int getUtilisationMax() {
        return utilisationMax;
    }

    public int getPrix() {
        return prix;
    }
    
}
