package implementations.decorators.weapons.weaponsdecorators;

import abstracts_interfaces.decorators.weapons.WeaponAbstract;
import abstracts_interfaces.decorators.weapons.weaponsdecorators.WeaponDecorator;

public class AttackRate extends WeaponDecorator{

	public AttackRate(WeaponAbstract weapon) {
		super(weapon);
	}
	

	@Override
	public int getAttackRate(){
		int stat = super.getAttackRate();
		stat = stat + (stat*125)/100;
		return stat;
	}

}
