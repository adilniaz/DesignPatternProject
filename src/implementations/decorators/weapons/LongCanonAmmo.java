package implementations.decorators.weapons;

import abstracts_interfaces.decorators.weapons.WeaponAbstract;

public class LongCanonAmmo extends WeaponAbstract{
	public LongCanonAmmo() {
		setDamage(70);
		setAttackRate(2);
		setRange(45);
	}
}
