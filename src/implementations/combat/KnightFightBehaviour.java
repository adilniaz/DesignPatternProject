package implementations.combat;

import implementations.object.WeaponType;

public class KnightFightBehaviour extends FightBehaviour {
	
	public KnightFightBehaviour () {
		this.weaponTypes = new WeaponType[1];
		this.weaponTypes[0] = WeaponType.lance;
	}

}