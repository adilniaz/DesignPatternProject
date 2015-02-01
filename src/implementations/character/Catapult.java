package implementations.character;

import implementations.decorators.weapons.Stone;
import implementations.organizations.Organization;
import abstracts_interfaces.CharacterAbstract;

public class Catapult extends CharacterAbstract{

	public Catapult(Organization subject, String name) {
		super(subject, name);
		setWeapon(new Stone());
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
