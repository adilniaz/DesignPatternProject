package implementations.decorators.weapons;

import abstracts_interfaces.decorators.weapons.WeaponAbstract;

public class LaserSniperGun extends WeaponAbstract{
	public LaserSniperGun() {
		setDamage(80);
		setAttackRate(2);
		setRange(45);
	}
}
