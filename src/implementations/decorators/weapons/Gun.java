package implementations.decorators.weapons;

import abstracts_interfaces.decorators.weapons.WeaponAbstract;

public class Gun extends WeaponAbstract{
	public Gun() {
		setDamage(65);
		setAttackRate(3);
		setRange(25);
	}
}
