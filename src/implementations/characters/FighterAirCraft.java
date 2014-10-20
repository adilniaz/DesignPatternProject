package implementations.characters;

import abstracts_interfaces.CharacterAbstract;
import implementations.behaviours.combat.CombatFighterAirCraft;
import implementations.behaviours.talk.TalkFighterAirCraft;
import implementations.decorators.statistics.Statistics;
import implementations.organisations.Organisation;

public class FighterAirCraft extends CharacterAbstract{

	private int agility;
	private int health;
	private int speed;
	
	public FighterAirCraft(String theName, Organisation theSubject) {
		super(theSubject, theName);
		agility = 60;
		health = 120;
		speed = 100;
		stats = new Statistics(agility, health, speed);
		behaviour = new CombatFighterAirCraft();
		speech = new TalkFighterAirCraft();
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
