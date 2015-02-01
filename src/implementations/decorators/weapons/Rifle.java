package implementations.decorators.weapons;

import abstracts_interfaces.decorators.weapons.WeaponAbstract;

public class Rifle extends WeaponAbstract{
	public Rifle() {
		setDamage(60);
		setAttackRate(3);
		setRange(20);
	}
}
