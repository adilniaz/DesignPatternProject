package implementations.decorators.weapons;

import abstracts_interfaces.decorators.weapons.WeaponAbstract;

public class BigSling extends WeaponAbstract{
	public BigSling() {
		setDamage(25);
		setAttackRate(3);
		setRange(15);
	}
}
