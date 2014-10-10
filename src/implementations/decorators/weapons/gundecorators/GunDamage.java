package implementations.decorators.weapons.gundecorators;

import abstracts_interfaces.decorators.weapons.WeaponAbstract;
import abstracts_interfaces.decorators.weapons.weaponsdecorators.GunDecorator;

public class GunDamage extends GunDecorator{

	public GunDamage(WeaponAbstract gun) {
		super(gun);
	}

	@Override
	public int getDamage() {
		return super.getDamage()+5;
	}

}
