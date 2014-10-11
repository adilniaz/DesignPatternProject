package abstracts_interfaces.factories.characters;

import abstracts_interfaces.CharacterAbstract;
import main_package.GameSimulation.CharactersType;
import implementations.organisations.Organisation;

public abstract class CreateCharactersFactoryAbstract {
	public abstract CharacterAbstract createCharacter(String name, Organisation organisation, CharactersType typeCharacter);
}
