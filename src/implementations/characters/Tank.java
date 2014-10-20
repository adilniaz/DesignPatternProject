package implementations.characters;

import abstracts_interfaces.CharacterAbstract;
import implementations.behaviours.combat.CombatTank;
import implementations.behaviours.talk.TalkTank;
import implementations.decorators.statistics.Statistics;
import implementations.organisations.Organisation;

public class Tank extends CharacterAbstract{
	
	private int agility;
	private int health;
	private int speed;
	
	public Tank(String theName, Organisation theSubject) {
		super(theSubject, theName);
		agility = 5;
		health = 100;
		speed = 50;
		stats = new Statistics(agility, health, speed);
		behaviour = new CombatTank();
		speech = new TalkTank();
	}
	
	@Override
	public String display() {
		return null;
	}
	
	@Override
	public String move() {
		return null;
	}
}
