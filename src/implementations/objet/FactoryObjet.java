package implementations.objet;

public class FactoryObjet {
    
    public Objet createObjet (String name, TypeObjet typeObjet) {
        switch (typeObjet) {
        	/* Objet */
            case potion :
                return new Objet(name, 3, 300, typeObjet);
            /* Arme */
			case alondite:
				break;
			case bec:
				break;
            case epee_fer :
                return new Arme(name, 46, 460, Arme.Rang.E, 1, 5, 5, 90, 0, TypeArme.epee, typeObjet);
            case ereshkigal :
                return new Arme(name, -1, -1, Arme.Rang.none, 1|2, 12, 20, 95, 0, TypeArme.noir, typeObjet);
			case griffe:
				break;
            case hache_fer :
                return new Arme(name, 45, 270, Arme.Rang.E, 1, 10, 8, 75, 0, TypeArme.hache, typeObjet);
			case lance_fer:
				break;
			case souffle:
				break;
			default:
				break;
        }
        return null;
    }
    
}
