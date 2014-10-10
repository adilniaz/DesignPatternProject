package implementations.decorators.weapons.axedecorators;

import abstracts_interfaces.decorators.weapons.WeaponAbstract;
import abstracts_interfaces.decorators.weapons.weaponsdecorators.GunDecorator;

public class AxeRange extends GunDecorator{

	public AxeRange(WeaponAbstract gun) {
		super(gun);
	}
	
	@Override
	public int getRange() {
		return super.getRange()+5;
	}


}
