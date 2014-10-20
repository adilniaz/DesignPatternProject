package implementations.decorators.weapons;

import abstracts_interfaces.decorators.weapons.WeaponAbstract;

public class Bow extends WeaponAbstract{
	
	public Bow(){
		name="Bow";
		damage = 1;
		accuracy = 40;
		range = 60;
	}

}
