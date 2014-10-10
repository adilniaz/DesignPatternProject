package implementations.decorators.weapons.axedecorators;

import abstracts_interfaces.decorators.weapons.WeaponAbstract;
import abstracts_interfaces.decorators.weapons.weaponsdecorators.GunDecorator;

public class AxeDamage extends GunDecorator{

	public AxeDamage(WeaponAbstract gun) {
		super(gun);
	}

	@Override
	public int getDamage() {
		return super.getDamage()+5;
	}

}
