package fireemblem.objet;

public class FactoryObjet {
    
    public Objet createObjet (String name, TypeObjet typeObjet) {
        switch (typeObjet) {
            case potion :
                return new Objet(name, 3, 300);
            case epee_fer :
                return new Arme(name, 46, 460, Arme.Rang.E, 1, 5, 5, 90, 0, TypeArme.epee);
            case hache_fer :
                return new Arme(name, 45, 270, Arme.Rang.E, 1, 10, 8, 75, 0, TypeArme.hache);
            case ereshkigal :
                return new Arme(name, -1, -1, Arme.Rang.none, 1|2, 12, 20, 95, 0, TypeArme.noir);
        }
        return null;
    }
    
}
