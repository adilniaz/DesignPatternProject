package implementations.decorators.weapons.gundecorators;

import abstracts_interfaces.decorators.weapons.WeaponAbstract;
import abstracts_interfaces.decorators.weapons.weaponsdecorators.GunDecorator;

public class GunRange extends GunDecorator{

	public GunRange(WeaponAbstract gun) {
		super(gun);
	}
	
	@Override
	public int getRange() {
		return super.getRange()+5;
	}


}
