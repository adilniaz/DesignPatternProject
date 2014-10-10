package implementations.characters;

import abstracts_interfaces.CharacterAbstract;
import implementations.behaviours.combat.CombatKnight;
import implementations.behaviours.talk.TalkKnight;
import implementations.organisations.Organisation;

public class Knight extends CharacterAbstract{

	public Knight(String lenom, Organisation theSubject) {
		super(theSubject, lenom);
		comportement = new CombatKnight();
		son = new TalkKnight();
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

