package abstracts_interfaces.factories.characters;

import abstracts_interfaces.CharacterAbstract;
import abstracts_interfaces.Organisation;

public abstract class CreateCharactersFactoryAbstract {
	public abstract CharacterAbstract createCharacter(String name, Organisation organisation, CharactersType typeCharacter);
}
