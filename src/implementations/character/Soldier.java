package implementations.character;

import implementations.decorators.weapons.Rifle;
import implementations.organizations.Organization;
import abstracts_interfaces.CharacterAbstract;

public class Soldier extends CharacterAbstract{

	public Soldier(Organization subject, String name) {
		super(subject, name);
		setWeapon(new Rifle());
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
