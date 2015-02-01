package implementations.decorators.weapons;

import abstracts_interfaces.decorators.weapons.WeaponAbstract;

public class Bow extends WeaponAbstract{
	public Bow() {
		setDamage(30);
		setAttackRate(3);
		setRange(20);
	}
}
