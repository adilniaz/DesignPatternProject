package abstracts_interfaces.decorators.weapons.weaponsdecorators;

import abstracts_interfaces.decorators.weapons.WeaponAbstract;

public abstract class WeaponDecorator extends WeaponAbstract{

	protected final WeaponAbstract weapon;
	
	public WeaponDecorator(WeaponAbstract weapon) {
		this.weapon = weapon;
	}
	
	@Override
	public int getDamage() {
		return weapon.getDamage();
	}

	@Override
	public int getAccuracy() {
		return weapon.getAccuracy();
	}

	@Override
	public int getRange() {
		return weapon.getRange();
	}

}
