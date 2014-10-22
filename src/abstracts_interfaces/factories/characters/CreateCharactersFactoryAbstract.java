package abstracts_interfaces.factories.characters;

import example_main_package.GameSimulation.CharactersType;
import abstracts_interfaces.CharacterAbstract;
import implementations.organisations.Organisation;

public abstract class CreateCharactersFactoryAbstract {
	public abstract CharacterAbstract createCharacter(String name, Organisation organisation, CharactersType typeCharacter);
}
