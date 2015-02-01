package abstracts_interfaces.decorators.statistics;

public abstract class StatisticsAbstract {
	public int health;
	public int speed;
	public int defence;
	
	public void setHealth(int health) {
		this.health = health;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public void setDefence(int defence) {
		this.defence = defence;
	}
	
	public int getHealth(){
		return health;
	}
	public int getSpeed(){
		return speed;
	}	
	public int getDefence(){
		return defence;
	}
}
