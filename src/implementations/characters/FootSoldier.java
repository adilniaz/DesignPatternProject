package implementations.characters;

import abstracts_interfaces.CharacterAbstract;
import implementations.behaviours.combat.CombatFootSoldier;
import implementations.behaviours.talk.TalkFootSoldier;
import implementations.decorators.weapons.Sword;
import implementations.organisations.Organisation;

public class FootSoldier extends CharacterAbstract{

	public FootSoldier(String theName, Organisation theSubject) {
		super(theSubject, theName);
		behaviour = new CombatFootSoldier();
		speech = new TalkFootSoldier();
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
