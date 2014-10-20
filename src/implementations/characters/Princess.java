package implementations.characters;

import abstracts_interfaces.CharacterAbstract;
import implementations.behaviours.talk.TalkPrincess;
import implementations.decorators.statistics.Statistics;
import implementations.organisations.Organisation;

public class Princess extends CharacterAbstract{

	private int agility;
	private int health;
	private int speed;
	
	public Princess(String theName, Organisation theSubject) {
		super(theSubject, theName);
		agility = 30;
		health = 10;
		speed = 10;
		stats = new Statistics(agility, health, speed);
		speech = new TalkPrincess();
	}

	@Override
	public String display() {
		return null;
	}
	
	@Override
	public String move() {
		return null;
	}

	@Override
	public void update() { }
}
