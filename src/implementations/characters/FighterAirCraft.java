package implementations.characters;

import abstracts_interfaces.CharacterAbstract;
import implementations.behaviours.combat.CombatFighterAirCraft;
import implementations.behaviours.talk.TalkFighterAirCraft;
import implementations.organisations.Organisation;

public class FighterAirCraft extends CharacterAbstract{
	
	public FighterAirCraft(String lenom, Organisation theSubject) {
		super(theSubject, lenom);
		comportement = new CombatFighterAirCraft();
		son = new TalkFighterAirCraft();
	}
	

	@Override
	public String Afficher() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String SeDeplacer() {
		// TODO Auto-generated method stub
		return null;
	}
}
