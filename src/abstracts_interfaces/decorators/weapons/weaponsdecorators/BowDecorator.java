package abstracts_interfaces.decorators.weapons.weaponsdecorators;

import abstracts_interfaces.decorators.weapons.WeaponAbstract;

public abstract class BowDecorator extends WeaponAbstract{
	
	protected final WeaponAbstract bow;
	
	public BowDecorator(WeaponAbstract bow) {
		this.bow = bow;
	}
	
	
	@Override
	public int getDamage() {
		return bow.getDamage();
	}

	@Override
	public int getAccuracy() {
		return bow.getAccuracy();
	}

	@Override
	public int getRange() {
		return bow.getRange();
	}

}
