package implementations.decorators.weapons.weapondecorators;

import abstracts_interfaces.decorators.weapons.WeaponAbstract;
import abstracts_interfaces.decorators.weapons.weaponsdecorators.WeaponDecorator;

public class WeaponDamage extends WeaponDecorator{

	public WeaponDamage(WeaponAbstract weapon) {
		super(weapon);
	}

	@Override
	public int getDamage() {
		return super.getDamage()+5;
	}

}
