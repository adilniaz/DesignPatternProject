package implementations.characters;

import abstracts_interfaces.CharacterAbstract;
import implementations.behaviours.combat.CombatSoldier;
import implementations.behaviours.talk.TalkSoldier;
import implementations.organisations.Organisation;

public class Soldier extends CharacterAbstract{
	
	public Soldier(String lenom, Organisation theSubject) {
		super(theSubject, lenom);
		comportement = new CombatSoldier();
		son = new TalkSoldier();
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
