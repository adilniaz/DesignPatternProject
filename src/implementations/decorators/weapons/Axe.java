package implementations.decorators.weapons;

import abstracts_interfaces.decorators.weapons.WeaponAbstract;

public class Axe extends WeaponAbstract{

	@Override
	public int getDamage() {
		return 2;
	}

	@Override
	public int getAccuracy() {
		return 80;
	}

	@Override
	public int getRange() {
		return 5;
	}

}
