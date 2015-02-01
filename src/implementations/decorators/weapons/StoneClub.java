package implementations.decorators.weapons;

import abstracts_interfaces.decorators.weapons.WeaponAbstract;

public class StoneClub extends WeaponAbstract{
	public StoneClub() {
		setDamage(30);
		setAttackRate(2);
		setRange(0);
	}
}
