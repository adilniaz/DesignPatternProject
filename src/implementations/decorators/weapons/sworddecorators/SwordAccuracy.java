package implementations.decorators.weapons.sworddecorators;

import abstracts_interfaces.decorators.weapons.WeaponAbstract;
import abstracts_interfaces.decorators.weapons.weaponsdecorators.AxeDecorator;

public class SwordAccuracy extends AxeDecorator{

	public SwordAccuracy(WeaponAbstract sword) {
		super(sword);
	}

	@Override
	public int getAccuracy() {
		return super.getAccuracy()+5;
	}

}
