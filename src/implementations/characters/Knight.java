package implementations.characters;

import abstracts_interfaces.CharacterAbstract;
import implementations.behaviours.combat.CombatKnight;
import implementations.behaviours.talk.TalkKnight;
import implementations.decorators.statistics.Statistics;
import implementations.decorators.weapons.Sword;
import implementations.organisations.Organisation;

public class Knight extends CharacterAbstract{

	private int agility;
	private int health;
	private int speed;
	
	public Knight(String theName, Organisation theSubject) {
		super(theSubject, theName);
		agility = 40;
		health = 60;
		speed = 20;
		stats = new Statistics(agility, health, speed);
		behaviour = new CombatKnight();
		speech = new TalkKnight();
		weapon = new Sword();
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

