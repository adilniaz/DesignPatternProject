package implementations.character;

import implementations.decorators.weapons.LongCanonAmmo;
import implementations.organizations.Organization;
import abstracts_interfaces.CharacterAbstract;

public class LongCanon extends CharacterAbstract{

	public LongCanon(Organization subject, String name) {
		super(subject, name);
		setWeapon(new LongCanonAmmo());
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
