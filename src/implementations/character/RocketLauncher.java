package implementations.character;

import implementations.decorators.weapons.Rocket;
import implementations.organizations.Organization;
import abstracts_interfaces.CharacterAbstract;

public class RocketLauncher extends CharacterAbstract{

	public RocketLauncher(Organization subject, String name) {
		super(subject, name);
		setWeapon(new Rocket());
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
