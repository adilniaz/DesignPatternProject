package implementations.characters;

import abstracts_interfaces.CharacterAbstract;
import implementations.behaviours.combat.CombatSoldier;
import implementations.behaviours.talk.TalkSoldier;
import implementations.decorators.weapons.Gun;
import implementations.organisations.Organisation;

public class Soldier extends CharacterAbstract{
	
	public Soldier(String theName, Organisation theSubject) {
		super(theSubject, theName);
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
