package implementations.object;

public class ObjetFactory {
    
    public Objet createObjet (String name, ObjetType typeObjet) {
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
                return new Weapon(name, 46, 460, Weapon.Rang.E, 1, 5, 5, 90, 0, WeaponType.epee, typeObjet);
            case ereshkigal :
                return new Weapon(name, -1, -1, Weapon.Rang.none, 1|2, 12, 20, 95, 0, WeaponType.noir, typeObjet);
			case griffe:
				break;
            case hache_fer :
                return new Weapon(name, 45, 270, Weapon.Rang.E, 1, 10, 8, 75, 0, WeaponType.hache, typeObjet);
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
