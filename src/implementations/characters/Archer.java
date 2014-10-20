package implementations.characters;

import abstracts_interfaces.CharacterAbstract;
import implementations.behaviours.combat.CombatArcher;
import implementations.behaviours.talk.TalkArcher;
import implementations.decorators.statistics.Statistics;
import implementations.decorators.weapons.Bow;
import implementations.organisations.Organisation;

public class Archer extends CharacterAbstract{

	private int agility;
	private int health;
	private int speed;
	
	public Archer(String theName, Organisation theSubject) {
		super(theSubject, theName);
		agility = 50;
		health = 20;
		speed = 10;
		stats = new Statistics(agility, health, speed);
		behaviour = new CombatArcher();
		speech = new TalkArcher();
		weapon = new Bow();
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
