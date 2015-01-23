package implementations.combat;

import implementations.object.WeaponType;
import abstracts_interfaces.behaviours.BehaviourCombatAbstract;

public class FightBehaviour extends BehaviourCombatAbstract {
	
	protected WeaponType [] weaponTypes;

    @Override
    public String fight() {
        return "combat a pied avec arc.";
    }
    
}