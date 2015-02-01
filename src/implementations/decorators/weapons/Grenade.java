package implementations.decorators.weapons;

import abstracts_interfaces.decorators.weapons.WeaponAbstract;

public class Grenade extends WeaponAbstract{
	public Grenade() {
		setDamage(65);
		setAttackRate(2);
		setRange(30);
	}
}
