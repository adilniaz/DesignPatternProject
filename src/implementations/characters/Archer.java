package implementations.characters;

import abstracts_interfaces.CharacterAbstract;
import implementations.behaviours.combat.CombatArcher;
import implementations.behaviours.talk.TalkArcher;
import implementations.decorators.weapons.Bow;
import implementations.organisations.Organisation;

public class Archer extends CharacterAbstract{
	
	public Archer(String theName, Organisation theSubject) {
		super(theSubject, theName);
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
