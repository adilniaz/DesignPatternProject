package implementations.combat;

import implementations.object.WeaponType;

public class LordLynFightBehaviour extends FightBehaviour {
	
	public LordLynFightBehaviour () {
		this.weaponTypes = new WeaponType[1];
		this.weaponTypes[0] = WeaponType.epee;
	}

}