package implementations.character;

import implementations.decorators.weapons.BigGun;
import implementations.organizations.Organization;
import abstracts_interfaces.CharacterAbstract;

public class Tank extends CharacterAbstract{

	public Tank(Organization subject, String name) {
		super(subject, name);
		setWeapon(new BigGun());
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
