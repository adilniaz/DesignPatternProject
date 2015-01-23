package implementations.object;

public class ObjetFactory {
    
    public Objet createObjet (String name, ObjetType typeObjet) {
        switch (typeObjet) {
        	/* Objet */
            case potion :
                return new Objet(name, 3, 300, typeObjet);
            /* Arme */
            case epee_fer :
                return new Weapon(name, 46, 460, Weapon.Rang.E, 1, 5, 5, 90, 0, WeaponType.epee, typeObjet);
            case hache_fer :
                return new Weapon(name, 45, 270, Weapon.Rang.E, 1, 10, 8, 75, 0, WeaponType.hache, typeObjet);
			case lance_fer:
				return new Weapon(name, 45, 270, Weapon.Rang.E, 1, 10, 8, 75, 0, WeaponType.lance, typeObjet);
			default:
				break;
        }
        return null;
    }
    
}
