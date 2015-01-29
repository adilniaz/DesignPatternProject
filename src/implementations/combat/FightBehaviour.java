package implementations.combat;

import implementations.object.Weapon;
import implementations.object.WeaponType;
import abstracts_interfaces.behaviours.BehaviourCombatAbstract;

public class FightBehaviour extends BehaviourCombatAbstract {
	
	protected WeaponType [] weaponTypes;

    @Override
    public String fight() {
        return "combat a pied avec arc.";
    }
    
    public boolean isWeaponAvailable (Weapon weapon) {
    	for (WeaponType weaponType : this.weaponTypes) {
    		if (weapon.getTypeArme() == weaponType) {
    			return true;
    		}
    	}
    	return false;
    }
    
    public WeaponType [] getWeaponTypes () {
    	return this.weaponTypes;
    }
    
}