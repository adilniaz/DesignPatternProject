package implementations.decorators.weapons.bowdecorators;

import abstracts_interfaces.decorators.weapons.WeaponAbstract;
import abstracts_interfaces.decorators.weapons.weaponsdecorators.BowDecorator;

public class BowRange extends BowDecorator{

	public BowRange(WeaponAbstract bow) {
		super(bow);
	}
	
	@Override
	public int getRange() {
		return super.getRange()+5;
	}


}
