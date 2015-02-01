package implementations.decorators.weapons;

import abstracts_interfaces.decorators.weapons.WeaponAbstract;

public class WoodenClub extends WeaponAbstract{
	public WoodenClub() {
		setDamage(20);
		setAttackRate(2);
		setRange(0);
	}
}
