package implementations.character;

import implementations.decorators.weapons.Grenade;
import implementations.organizations.Organization;
import abstracts_interfaces.CharacterAbstract;

public class Grenadier extends CharacterAbstract{

	public Grenadier(Organization subject, String name) {
		super(subject, name);
		setWeapon(new Grenade());
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
