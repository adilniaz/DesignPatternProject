package abstracts_interfaces.decorators.weapons.weaponsdecorators;

import abstracts_interfaces.decorators.weapons.WeaponAbstract;

public abstract class SwordDecorator extends WeaponAbstract{
	
	protected final WeaponAbstract sword;
	
	public SwordDecorator(WeaponAbstract sword) {
		this.sword = sword;
	}
	
	
	@Override
	public int getDamage() {
		return sword.getDamage();
	}

	@Override
	public int getAccuracy() {
		return sword.getAccuracy();
	}

	@Override
	public int getRange() {
		return sword.getRange();
	}

}
