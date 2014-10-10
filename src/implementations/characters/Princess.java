package implementations.characters;

import abstracts_interfaces.CharacterAbstract;
import implementations.behaviours.talk.TalkPrincess;
import implementations.organisations.Organisation;

public class Princess extends CharacterAbstract{
	
	public Princess(String lenom, Organisation theSubject) {
		super(theSubject, lenom);
		son = new TalkPrincess();
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

	@Override
	public void Update() {
		// TODO Auto-generated method stub
		
	}
}
