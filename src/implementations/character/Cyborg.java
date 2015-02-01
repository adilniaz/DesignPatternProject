package implementations.character;

import implementations.decorators.weapons.CyGun;
import implementations.organizations.Organization;
import abstracts_interfaces.CharacterAbstract;

public class Cyborg extends CharacterAbstract{

	public Cyborg(Organization subject, String name) {
		super(subject, name);
		setWeapon(new CyGun());
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
