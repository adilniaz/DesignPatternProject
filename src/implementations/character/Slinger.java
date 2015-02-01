package implementations.character;

import implementations.decorators.weapons.Sling;
import implementations.organizations.Organization;
import abstracts_interfaces.CharacterAbstract;

public class Slinger extends CharacterAbstract{

	public Slinger(Organization subject, String name) {
		super(subject, name);
		setWeapon(new Sling());
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
