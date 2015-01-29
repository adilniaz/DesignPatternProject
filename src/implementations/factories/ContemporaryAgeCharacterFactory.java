package implementations.factories;

import implementations.character.Gunner;
import implementations.character.RocketLauncher;
import implementations.character.Sniper;
import implementations.character.SuperTank;
import implementations.organizations.Organization;
import abstracts_interfaces.CharacterAbstract;
import abstracts_interfaces.factories.characters.CharactersType;
import abstracts_interfaces.factories.characters.CreateCharactersFactoryAbstract;

public class ContemporaryAgeCharacterFactory extends CreateCharactersFactoryAbstract{
	
	@Override
	public CharacterAbstract createCharacter(String name,
			Organization organisation, CharactersType typeCharacter) {
		CharacterAbstract character = null;
		switch (Characters.valueOf(typeCharacter.toString())) {
			case GUNNER:
				character = new Gunner(organisation, name);
				break;
			case SNIPER:
				character = new Sniper(organisation, name);
				break;
			case ROCKETLAUNCHER:
				character = new RocketLauncher(organisation, name);
				break;
			case SUPERTANK:
				character = new SuperTank(organisation, name);
				break;
	
			default:
				break;
		}
		character.cost = cost;
		character.statistics = stats;
		return character;
	}

}
