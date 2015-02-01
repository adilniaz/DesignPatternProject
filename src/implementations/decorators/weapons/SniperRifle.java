package implementations.decorators.weapons;

import abstracts_interfaces.decorators.weapons.WeaponAbstract;

public class SniperRifle extends WeaponAbstract{
	public SniperRifle() {
		setDamage(70);
		setAttackRate(1);
		setRange(45);
	}
}
