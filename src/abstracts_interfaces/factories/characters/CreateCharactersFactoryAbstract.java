package abstracts_interfaces.factories.characters;

import implementations.organizations.Organization;
import abstracts_interfaces.CharacterAbstract;

public abstract class CreateCharactersFactoryAbstract {
	public abstract CharacterAbstract createCharacter(String name, Organization organisation, CharactersType typeCharacter);
}
