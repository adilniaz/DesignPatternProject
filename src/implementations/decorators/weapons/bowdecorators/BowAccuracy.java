package implementations.decorators.weapons.bowdecorators;

import abstracts_interfaces.decorators.weapons.WeaponAbstract;
import abstracts_interfaces.decorators.weapons.weaponsdecorators.BowDecorator;

public class BowAccuracy extends BowDecorator{

	public BowAccuracy(WeaponAbstract bow) {
		super(bow);
	}

	@Override
	public int getAccuracy() {
		return super.getAccuracy()+5;
	}

}
