package implementations.decorators.weapons.sworddecorators;

import abstracts_interfaces.decorators.weapons.WeaponAbstract;
import abstracts_interfaces.decorators.weapons.weaponsdecorators.GunDecorator;

public class SwordDamage extends GunDecorator{

	public SwordDamage(WeaponAbstract sword) {
		super(sword);
	}

	@Override
	public int getDamage() {
		return super.getDamage()+5;
	}

}
