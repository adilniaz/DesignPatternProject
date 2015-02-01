package implementations.decorators.weapons;

import abstracts_interfaces.decorators.weapons.WeaponAbstract;

public class Stone extends WeaponAbstract{
	public Stone() {
		setDamage(50);
		setAttackRate(1);
		setRange(30);
	}
}
