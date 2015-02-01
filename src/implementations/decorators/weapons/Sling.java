package implementations.decorators.weapons;

import abstracts_interfaces.decorators.weapons.WeaponAbstract;

public class Sling extends WeaponAbstract{
	public Sling() {
		setDamage(15);
		setAttackRate(3);
		setRange(10);
	}
}
