package implementations.character;

import implementations.decorators.weapons.WoodenClub;
import implementations.organizations.Organization;
import abstracts_interfaces.CharacterAbstract;

public class Caveman extends CharacterAbstract{
	
	public Caveman(Organization subject, String name) {
		super(subject, name);
		setWeapon(new WoodenClub());
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
