package implementations.character;

import implementations.decorators.weapons.SniperRifle;
import implementations.organizations.Organization;
import abstracts_interfaces.CharacterAbstract;

public class Sniper extends CharacterAbstract{

	public Sniper(Organization subject, String name) {
		super(subject, name);
		setWeapon(new SniperRifle());
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
