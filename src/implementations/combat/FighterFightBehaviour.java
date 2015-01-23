package implementations.combat;

import implementations.object.WeaponType;

public class FighterFightBehaviour extends FightBehaviour {
	
	public FighterFightBehaviour () {
		this.weaponTypes = new WeaponType[1];
		this.weaponTypes[0] = WeaponType.hache;
	}

}