package implementations.character;

import implementations.decorators.weapons.KnightSword;
import implementations.organizations.Organization;
import abstracts_interfaces.CharacterAbstract;

public class Knight extends CharacterAbstract{

	public Knight(Organization subject, String name) {
		super(subject, name);
		setWeapon(new KnightSword());
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
