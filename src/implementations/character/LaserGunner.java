package implementations.character;

import implementations.decorators.weapons.LaserGun;
import implementations.organizations.Organization;
import abstracts_interfaces.CharacterAbstract;

public class LaserGunner extends CharacterAbstract{

	public LaserGunner(Organization subject, String name) {
		super(subject, name);
		setWeapon(new LaserGun());
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
