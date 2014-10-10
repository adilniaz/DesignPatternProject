package implementations.decorators.weapons;

import abstracts_interfaces.decorators.weapons.WeaponAbstract;

public class Gun extends WeaponAbstract{

	@Override
	public int getDamage() {
		return 3;
	}

	@Override
	public int getAccuracy() {
		return 50;
	}

	@Override
	public int getRange() {
		return 30;
	}

}
