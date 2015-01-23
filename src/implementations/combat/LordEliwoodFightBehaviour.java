package implementations.combat;

import implementations.object.WeaponType;

public class LordEliwoodFightBehaviour extends FightBehaviour {
	
	public LordEliwoodFightBehaviour () {
		this.weaponTypes = new WeaponType[1];
		this.weaponTypes[0] = WeaponType.epee;
	}

}