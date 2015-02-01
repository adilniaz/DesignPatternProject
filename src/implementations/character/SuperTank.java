package implementations.character;

import implementations.decorators.weapons.SuperHugeGun;
import implementations.organizations.Organization;
import abstracts_interfaces.CharacterAbstract;

public class SuperTank extends CharacterAbstract{

	public SuperTank(Organization subject, String name) {
		super(subject, name);
		setWeapon(new SuperHugeGun());
	}

	@Override
	public String display() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String move() {
		// TODO Auto-generated method stub
		return null;
	}

}
