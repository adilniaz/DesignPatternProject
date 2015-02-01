package implementations.character;

import implementations.decorators.weapons.BigSling;
import implementations.organizations.Organization;
import abstracts_interfaces.CharacterAbstract;

public class RaptorRider extends CharacterAbstract{

	public RaptorRider(Organization subject, String name) {
		super(subject, name);
		setWeapon(new BigSling());
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
