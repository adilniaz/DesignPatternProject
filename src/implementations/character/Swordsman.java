package implementations.character;

import implementations.decorators.weapons.Sword;
import implementations.organizations.Organization;
import abstracts_interfaces.CharacterAbstract;

public class Swordsman extends CharacterAbstract{

	public Swordsman(Organization subject, String name) {
		super(subject, name);
		setWeapon(new Sword());
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
