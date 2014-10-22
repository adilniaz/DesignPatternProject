package implementations.factories.characters;

import example_main_package.GameSimulation.CharactersType;
import implementations.characters.Archer;
import implementations.characters.FootSoldier;
import implementations.characters.Knight;
import implementations.characters.Princess;
import implementations.organisations.Organisation;
import abstracts_interfaces.CharacterAbstract;
import abstracts_interfaces.factories.characters.CreateCharactersFactoryAbstract;

public class CreateCharactersMiddleAgeFactory extends CreateCharactersFactoryAbstract{

	@Override
	public CharacterAbstract createCharacter(String name, Organisation org,
			CharactersType typeCharacter) {
		switch (typeCharacter) {
			case ARCHER:
				return new Archer(name, org);
			case PRINCESS:
				return new Princess(name, org);
			case KNIGHT:
				return new Knight(name, org);
			case FOOTSOLDIER:
				return new FootSoldier(name, org);
			default:
				return null;
		}
	}
}
