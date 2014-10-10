package implementations.decorators.weapons;

import abstracts_interfaces.decorators.weapons.WeaponAbstract;

public class Sword extends WeaponAbstract{

	@Override
	public int getDamage() {
		return 3;
	}

	@Override
	public int getAccuracy() {
		return 80;
	}

	@Override
	public int getRange() {
		return 8;
	}

}
