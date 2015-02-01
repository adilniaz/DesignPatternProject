package implementations.decorators.weapons;

import abstracts_interfaces.decorators.weapons.WeaponAbstract;

public class SuperHugeGun extends WeaponAbstract{
	public SuperHugeGun() {
		setDamage(90);
		setAttackRate(2);
		setRange(45);
	}
}
