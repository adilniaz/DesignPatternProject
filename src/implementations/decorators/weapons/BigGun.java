package implementations.decorators.weapons;

import abstracts_interfaces.decorators.weapons.WeaponAbstract;

public class BigGun extends WeaponAbstract{
	public BigGun() {
		setDamage(75);
		setAttackRate(2);
		setRange(40);
	}
}
