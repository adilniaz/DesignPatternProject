package implementations.characters;

import abstracts_interfaces.CharacterAbstract;
import implementations.behaviours.combat.CombatShip;
import implementations.behaviours.talk.TalkShip;
import implementations.organisations.Organisation;

public class Ship extends CharacterAbstract{
	
	public Ship(String theName, Organisation theSubject) {
		super(theSubject, theName);
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
