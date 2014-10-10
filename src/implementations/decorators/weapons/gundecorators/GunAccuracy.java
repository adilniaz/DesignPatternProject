package implementations.decorators.weapons.gundecorators;

import abstracts_interfaces.decorators.weapons.WeaponAbstract;
import abstracts_interfaces.decorators.weapons.weaponsdecorators.GunDecorator;

public class GunAccuracy extends GunDecorator{

	public GunAccuracy(WeaponAbstract gun) {
		super(gun);
	}

	@Override
	public int getAccuracy() {
		return super.getAccuracy()+5;
	}

}
