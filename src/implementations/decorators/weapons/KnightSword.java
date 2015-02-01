package implementations.decorators.weapons;

import abstracts_interfaces.decorators.weapons.WeaponAbstract;

public class KnightSword extends WeaponAbstract{
	public KnightSword() {
		setDamage(60);
		setAttackRate(2);
		setRange(0);
	}
}
