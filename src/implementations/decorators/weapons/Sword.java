package implementations.decorators.weapons;

import abstracts_interfaces.decorators.weapons.WeaponAbstract;

public class Sword extends WeaponAbstract{
	
	public Sword() {
		name = "Sword";
		damage = 3;
		accuracy = 80;
		range = 8;
	}
	
}
