package implementations.characters;

import abstracts_interfaces.CharacterAbstract;
import implementations.behaviours.combat.CombatShip;
import implementations.behaviours.talk.TalkShip;
import implementations.decorators.statistics.Statistics;
import implementations.organisations.Organisation;

public class Ship extends CharacterAbstract{

	private int agility;
	private int health;
	private int speed;
	
	public Ship(String theName, Organisation theSubject) {
		super(theSubject, theName);
		agility = 3;
		health = 150;
		speed = 150;
		stats = new Statistics(agility, health, speed);
		behaviour = new CombatShip();
		speech = new TalkShip();
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
