package implementations.character;

import implementations.decorators.weapons.Gun;
import implementations.organizations.Organization;
import abstracts_interfaces.CharacterAbstract;

public class Gunner extends CharacterAbstract{

	public Gunner(Organization subject, String name) {
		super(subject, name);
		setWeapon(new Gun());
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
