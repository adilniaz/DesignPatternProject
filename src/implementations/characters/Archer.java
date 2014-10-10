package implementations.characters;

import abstracts_interfaces.CharacterAbstract;
import implementations.behaviours.combat.CombatArcher;
import implementations.behaviours.talk.TalkArcher;
import implementations.organisations.Organisation;

public class Archer extends CharacterAbstract{
	
	public Archer(String lenom, Organisation theSubject) {
		super(theSubject, lenom);
		comportement = new CombatArcher();
		son = new TalkArcher();
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
