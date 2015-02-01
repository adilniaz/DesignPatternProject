package implementations.decorators.weapons;

import abstracts_interfaces.decorators.weapons.WeaponAbstract;

public class LaserCanon extends WeaponAbstract{
	public LaserCanon() {
		setDamage(90);
		setAttackRate(3);
		setRange(45);
	}
}
