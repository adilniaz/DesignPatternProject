package implementations.decorators.weapons;

import abstracts_interfaces.decorators.weapons.WeaponAbstract;

public class Bow extends WeaponAbstract{

	@Override
	public int getDamage() {
		return 1;
	}

	@Override
	public int getAccuracy() {
		return 40;
	}

	@Override
	public int getRange() {
		return 60;
	}

}
