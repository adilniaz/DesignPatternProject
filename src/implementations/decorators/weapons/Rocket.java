package implementations.decorators.weapons;

import abstracts_interfaces.decorators.weapons.WeaponAbstract;

public class Rocket extends WeaponAbstract{
	public Rocket() {
		setDamage(80);
		setAttackRate(1);
		setRange(40);
	}
}
