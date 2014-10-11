package implementations.characters;

import abstracts_interfaces.CharacterAbstract;
import implementations.behaviours.combat.CombatTank;
import implementations.behaviours.talk.TalkTank;
import implementations.organisations.Organisation;

public class Tank extends CharacterAbstract{
	
	public Tank(String theName, Organisation theSubject) {
		super(theSubject, theName);
		behaviour = new CombatTank();
		speech = new TalkTank();
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
