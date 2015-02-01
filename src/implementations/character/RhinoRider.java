package implementations.character;

import implementations.decorators.weapons.StoneClub;
import implementations.organizations.Organization;
import abstracts_interfaces.CharacterAbstract;

public class RhinoRider extends CharacterAbstract{

	public RhinoRider(Organization subject, String name) {
		super(subject, name);
		setWeapon(new StoneClub());
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
