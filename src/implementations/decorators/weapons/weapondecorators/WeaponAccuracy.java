package implementations.decorators.weapons.weapondecorators;

import abstracts_interfaces.decorators.weapons.WeaponAbstract;
import abstracts_interfaces.decorators.weapons.weaponsdecorators.WeaponDecorator;

public class WeaponAccuracy extends WeaponDecorator{

	public WeaponAccuracy(WeaponAbstract weapon) {
		super(weapon);
	}

	@Override
	public int getAccuracy() {
		return super.getAccuracy()+5;
	}

}
