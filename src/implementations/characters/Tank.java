package implementations.characters;

import abstracts_interfaces.CharacterAbstract;
import implementations.behaviours.combat.CombatTank;
import implementations.behaviours.talk.TalkTank;
import implementations.organisations.Organisation;

public class Tank extends CharacterAbstract{
	
	public Tank(String lenom, Organisation theSubject) {
		super(theSubject, lenom);
		comportement = new CombatTank();
		son = new TalkTank();
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
