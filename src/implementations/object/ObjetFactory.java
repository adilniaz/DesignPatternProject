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
            case epee_acier :
                return new Weapon(name, 30, 600, Weapon.Rang.D, 1, 10, 8, 75, 0, WeaponType.epee, typeObjet);
            case epee_argent :
                return new Weapon(name, 20, 1500, Weapon.Rang.A, 1, 8, 13, 80, 0, WeaponType.epee, typeObjet);
            case hache_fer :
                return new Weapon(name, 45, 270, Weapon.Rang.E, 1, 10, 8, 75, 0, WeaponType.hache, typeObjet);
            case hache_acier :
                return new Weapon(name, 30, 360, Weapon.Rang.E, 1, 15, 11, 65, 0, WeaponType.hache, typeObjet);
            case hache_argent :
                return new Weapon(name, 20, 1000, Weapon.Rang.A, 1, 12, 15, 70, 0, WeaponType.hache, typeObjet);
			case lance_fer:
				return new Weapon(name, 45, 360, Weapon.Rang.E, 1, 8, 7, 80, 0, WeaponType.lance, typeObjet);
			case lance_acier:
				return new Weapon(name, 30, 480, Weapon.Rang.D, 1, 13, 10, 70, 0, WeaponType.lance, typeObjet);
			case lance_argent:
				return new Weapon(name, 20, 1200, Weapon.Rang.A, 1, 10, 14, 75, 0, WeaponType.lance, typeObjet);
			default:
				return null;
        }
    }
    
}
