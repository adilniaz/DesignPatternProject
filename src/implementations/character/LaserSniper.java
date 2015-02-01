package implementations.character;

import implementations.decorators.weapons.LaserSniperGun;
import implementations.organizations.Organization;
import abstracts_interfaces.CharacterAbstract;

public class LaserSniper extends CharacterAbstract{

	public LaserSniper(Organization subject, String name) {
		super(subject, name);
		setWeapon(new LaserSniperGun());
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
