package implementations.character;

import implementations.decorators.weapons.LaserCanon;
import implementations.organizations.Organization;
import abstracts_interfaces.CharacterAbstract;

public class Hovercraft extends CharacterAbstract{

	public Hovercraft(Organization subject, String name) {
		super(subject, name);
		setWeapon(new LaserCanon());
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
