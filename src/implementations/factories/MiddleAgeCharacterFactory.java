package implementations.factories;

import implementations.character.Archer;
import implementations.character.Catapult;
import implementations.character.Knight;
import implementations.character.Swordsman;
import implementations.organizations.Organization;
import abstracts_interfaces.CharacterAbstract;
import abstracts_interfaces.factories.characters.CharactersType;
import abstracts_interfaces.factories.characters.CreateCharactersFactoryAbstract;

public class MiddleAgeCharacterFactory extends CreateCharactersFactoryAbstract{

	@Override
	public CharacterAbstract createCharacter(String name,
			Organization organisation, CharactersType typeCharacter) {
		CharacterAbstract character = null;
		switch (Characters.valueOf(typeCharacter.toString())) {
			case SWORDSMAN:
				character = new Swordsman(organisation, name);
				break;
			case ARCHER:
				character = new Archer(organisation, name);
				break;
			case CATAPULT:
				character = new Catapult(organisation, name);
				break;
			case KNIGHT:
				character = new Knight(organisation, name);
				break;
	
			default:
				break;
		}
		character.cost = cost;
		character.statistics = stats;
		return character;
	}

}
