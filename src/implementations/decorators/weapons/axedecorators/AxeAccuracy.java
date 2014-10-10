package implementations.decorators.weapons.axedecorators;

import abstracts_interfaces.decorators.weapons.WeaponAbstract;
import abstracts_interfaces.decorators.weapons.weaponsdecorators.AxeDecorator;

public class AxeAccuracy extends AxeDecorator{

	public AxeAccuracy(WeaponAbstract axe) {
		super(axe);
	}

	@Override
	public int getAccuracy() {
		return super.getAccuracy()+5;
	}

}
