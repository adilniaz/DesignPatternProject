package abstracts_interfaces.decorators.weapons;

public abstract class WeaponAbstract {
	
	public String name;
	public int damage;
	public int accuracy;
	public int range;
	public int attackRate;
	
	
	public int getAttackRate() {
		return attackRate;
	}
	public void setAttackRate(int attackRate) {
		this.attackRate = attackRate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setDamage(int damage) {
		this.damage = damage;
	}
	public void setAccuracy(int accuracy) {
		this.accuracy = accuracy;
	}
	public void setRange(int range) {
		this.range = range;
	}
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
