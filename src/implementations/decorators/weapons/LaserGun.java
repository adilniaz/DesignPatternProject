package implementations.decorators.weapons;

import abstracts_interfaces.decorators.weapons.WeaponAbstract;

public class LaserGun extends WeaponAbstract{
	public LaserGun() {
		setDamage(70);
		setAttackRate(4);
		setRange(30);
	}
}
