package implementations.decorators.weapons.weaponsdecorators;

import abstracts_interfaces.decorators.weapons.WeaponAbstract;
import abstracts_interfaces.decorators.weapons.weaponsdecorators.WeaponDecorator;

public class Damage extends WeaponDecorator{

	public Damage(WeaponAbstract weapon) {
		super(weapon);
	}
	

	@Override
	public int getDamage(){
		int stat = super.getDamage();
		stat = stat + (stat*125)/100;
		return stat;
	}

}
