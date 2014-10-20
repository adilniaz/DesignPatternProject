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
	public int getDamage(){
		return damage;
	}
	public int getAccuracy(){
		return accuracy;
	}
	public int getRange(){
		return range;
	}
	
}
