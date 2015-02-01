package implementations.decorators.weapons.weaponsdecorators;

import abstracts_interfaces.decorators.weapons.WeaponAbstract;
import abstracts_interfaces.decorators.weapons.weaponsdecorators.WeaponDecorator;

public class Range extends WeaponDecorator{

	public Range(WeaponAbstract weapon) {
		super(weapon);
	}
	

	@Override
	public int getRange(){
		int stat = super.getRange();
		stat = stat + (stat*125)/100;
		return stat;
	}

}
