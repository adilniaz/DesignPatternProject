package implementations.characters;

import abstracts_interfaces.CharacterAbstract;
import implementations.behaviours.combat.CombatShip;
import implementations.behaviours.talk.TalkShip;
import implementations.organisations.Organisation;

public class Ship extends CharacterAbstract{
	
	public Ship(String lenom, Organisation theSubject) {
		super(theSubject, lenom);
		comportement = new CombatShip();
		son = new TalkShip();
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
