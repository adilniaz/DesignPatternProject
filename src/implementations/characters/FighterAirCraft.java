package implementations.characters;

import abstracts_interfaces.CharacterAbstract;
import implementations.behaviours.combat.CombatFighterAirCraft;
import implementations.behaviours.talk.TalkFighterAirCraft;
import implementations.organisations.Organisation;

public class FighterAirCraft extends CharacterAbstract{
	
	public FighterAirCraft(String theName, Organisation theSubject) {
		super(theSubject, theName);
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
