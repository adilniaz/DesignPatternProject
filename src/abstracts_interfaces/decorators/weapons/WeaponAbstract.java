package abstracts_interfaces.decorators.weapons;

public abstract class WeaponAbstract {
	
	
	public String name;
	public int damage;
	public int accuracy;
	public int range;
	/*
	protected enum WeaponType{
		UNDEFINED, 
		SWORD, AXE, GUN, BOW
	}
	
	protected WeaponType weapon;
	*/
	public abstract int getDamage();
	public abstract int getAccuracy();
	public abstract int getRange();
	
}
