package implementations.characters;

import abstracts_interfaces.CharacterAbstract;
import implementations.behaviours.combat.CombatFootSoldier;
import implementations.behaviours.talk.TalkFootSoldier;
import implementations.organisations.Organisation;

public class FootSoldier extends CharacterAbstract{

	public FootSoldier(String lenom, Organisation theSubject) {
		super(theSubject, lenom);
		comportement = new CombatFootSoldier();
		son = new TalkFootSoldier();
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
