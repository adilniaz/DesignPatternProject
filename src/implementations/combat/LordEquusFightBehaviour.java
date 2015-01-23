package implementations.combat;

import implementations.object.WeaponType;

public class LordEquusFightBehaviour extends FightBehaviour {
	
	public LordEquusFightBehaviour () {
		this.weaponTypes = new WeaponType[2];
		this.weaponTypes[0] = WeaponType.epee;
		this.weaponTypes[1] = WeaponType.lance;
	}

}