package implementations.decorators.weapons.weapondecorators;

import abstracts_interfaces.decorators.weapons.WeaponAbstract;
import abstracts_interfaces.decorators.weapons.weaponsdecorators.WeaponDecorator;

public class WeaponRange extends WeaponDecorator{

	public WeaponRange(WeaponAbstract weapon) {
		super(weapon);
	}
	
	@Override
	public int getRange() {
		return super.getRange()+5;
	}
}
