package implementations.character;

import implementations.decorators.weapons.Bow;
import implementations.organizations.Organization;
import abstracts_interfaces.CharacterAbstract;

public class Archer extends CharacterAbstract{

	public Archer(Organization subject, String name) {
		super(subject, name);
		setWeapon(new Bow());
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
