package implementations.decorators.weapons.sworddecorators;

import abstracts_interfaces.decorators.weapons.WeaponAbstract;
import abstracts_interfaces.decorators.weapons.weaponsdecorators.SwordDecorator;

public class SwordRange extends SwordDecorator{

	public SwordRange(WeaponAbstract sword) {
		super(sword);
	}
	
	@Override
	public int getRange() {
		return super.getRange()+5;
	}


}
