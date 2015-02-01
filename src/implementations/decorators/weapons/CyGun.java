package implementations.decorators.weapons;

import abstracts_interfaces.decorators.weapons.WeaponAbstract;

public class CyGun extends WeaponAbstract{
	public CyGun() {
		setDamage(85);
		setAttackRate(3);
		setRange(40);
	}
}
