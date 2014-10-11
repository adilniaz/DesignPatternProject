package implementations.characters;

import abstracts_interfaces.CharacterAbstract;
import implementations.behaviours.combat.CombatKnight;
import implementations.behaviours.talk.TalkKnight;
import implementations.decorators.weapons.Sword;
import implementations.organisations.Organisation;

public class Knight extends CharacterAbstract{

	public Knight(String theName, Organisation theSubject) {
		super(theSubject, theName);
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

