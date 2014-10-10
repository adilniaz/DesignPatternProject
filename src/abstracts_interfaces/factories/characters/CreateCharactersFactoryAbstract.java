package abstracts_interfaces.factories.characters;

import abstracts_interfaces.CharacterAbstract;
import main_package.SimulationJeu.typeCharacters;
import implementations.organisations.Organisation;

public abstract class CreateCharactersFactoryAbstract {
	public abstract CharacterAbstract createCharacter(String name, Organisation org, typeCharacters typeCharacter);
}
