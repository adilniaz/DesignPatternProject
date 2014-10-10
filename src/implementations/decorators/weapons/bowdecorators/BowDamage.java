package implementations.decorators.weapons.bowdecorators;

import abstracts_interfaces.decorators.weapons.WeaponAbstract;
import abstracts_interfaces.decorators.weapons.weaponsdecorators.BowDecorator;

public class BowDamage extends BowDecorator{

	public BowDamage(WeaponAbstract bow) {
		super(bow);
	}

	@Override
	public int getDamage() {
		return super.getDamage()+5;
	}

}
