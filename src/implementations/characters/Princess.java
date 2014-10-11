package implementations.characters;

import abstracts_interfaces.CharacterAbstract;
import implementations.behaviours.talk.TalkPrincess;
import implementations.organisations.Organisation;

public class Princess extends CharacterAbstract{
	
	public Princess(String theName, Organisation theSubject) {
		super(theSubject, theName);
		speech = new TalkPrincess();
	}

	@Override
	public String display() {
		return null;
	}
	
	@Override
	public String move() {
		return null;
	}

	@Override
	public void update() { }
}
