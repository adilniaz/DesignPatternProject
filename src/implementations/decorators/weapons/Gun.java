package implementations.decorators.weapons;

import abstracts_interfaces.decorators.weapons.WeaponAbstract;

public class Gun extends WeaponAbstract{
	
	public Gun(){
		name = "Gun";
		damage = 3;
		accuracy = 50;
		range = 30;
	}
	
}
