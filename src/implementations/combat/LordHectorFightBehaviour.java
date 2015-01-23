package implementations.combat;

import implementations.object.WeaponType;

public class LordHectorFightBehaviour extends FightBehaviour {
	
	public LordHectorFightBehaviour () {
		this.weaponTypes = new WeaponType[1];
		this.weaponTypes[0] = WeaponType.hache;
	}

}
