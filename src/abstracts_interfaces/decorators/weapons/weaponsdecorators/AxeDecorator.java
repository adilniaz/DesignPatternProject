package abstracts_interfaces.decorators.weapons.weaponsdecorators;

import abstracts_interfaces.decorators.weapons.WeaponAbstract;

public abstract class AxeDecorator extends WeaponAbstract{
	
	protected final WeaponAbstract axe;
	
	public AxeDecorator(WeaponAbstract axe) {
		this.axe = axe;
	}
	
	
	@Override
	public int getDamage() {
		return axe.getDamage();
	}

	@Override
	public int getAccuracy() {
		return axe.getAccuracy();
	}

	@Override
	public int getRange() {
		return axe.getRange();
	}

}
