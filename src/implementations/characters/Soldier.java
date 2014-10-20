package implementations.characters;

import abstracts_interfaces.CharacterAbstract;
import implementations.behaviours.combat.CombatSoldier;
import implementations.behaviours.talk.TalkSoldier;
import implementations.decorators.statistics.Statistics;
import implementations.decorators.weapons.Gun;
import implementations.organisations.Organisation;

public class Soldier extends CharacterAbstract{

	private int agility;
	private int health;
	private int speed;
	
	public Soldier(String theName, Organisation theSubject) {
		super(theSubject, theName);
		agility = 55;
		health = 70;
		speed = 10;
		stats = new Statistics(agility, health, speed);
		behaviour = new CombatSoldier();
		speech = new TalkSoldier();
		weapon = new Gun();
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
