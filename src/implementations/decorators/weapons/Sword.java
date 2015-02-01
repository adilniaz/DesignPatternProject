package implementations.decorators.weapons;

import abstracts_interfaces.decorators.weapons.WeaponAbstract;

public class Sword extends WeaponAbstract{
	public Sword() {
		setDamage(40);
		setAttackRate(2);
		setRange(0);
	}
}
