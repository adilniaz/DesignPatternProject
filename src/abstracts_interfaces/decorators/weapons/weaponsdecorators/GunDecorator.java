package abstracts_interfaces.decorators.weapons.weaponsdecorators;

import abstracts_interfaces.decorators.weapons.WeaponAbstract;

public abstract class GunDecorator extends WeaponAbstract{
	
	protected final WeaponAbstract gun;
	
	public GunDecorator(WeaponAbstract gun) {
		this.gun = gun;
	}
	
	
	@Override
	public int getDamage() {
		return gun.getDamage();
	}

	@Override
	public int getAccuracy() {
		return gun.getAccuracy();
	}

	@Override
	public int getRange() {
		return gun.getRange();
	}

}
