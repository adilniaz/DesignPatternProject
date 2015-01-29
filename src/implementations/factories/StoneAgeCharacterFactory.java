package implementations.factories;

import implementations.character.Caveman;
import implementations.character.RaptorRider;
import implementations.character.RhinoRider;
import implementations.character.Slinger;
import implementations.organizations.Organization;
import abstracts_interfaces.CharacterAbstract;
import abstracts_interfaces.factories.characters.CharactersType;
import abstracts_interfaces.factories.characters.CreateCharactersFactoryAbstract;

public class StoneAgeCharacterFactory extends CreateCharactersFactoryAbstract{

	@Override
	public CharacterAbstract createCharacter(String name,
			Organization organisation, CharactersType typeCharacter) {
		CharacterAbstract character = null;
		switch (Characters.valueOf(typeCharacter.toString())) {
			case CAVEMAN:
				character = new Caveman(organisation, name);
				break;
			case SLINGER:
				character = new Slinger(organisation, name);
				break;
			case RAPTORRIDER:
				character = new RaptorRider(organisation, name);
				break;
			case RHINORIDER:
				character = new RhinoRider(organisation, name);
				break;
	
			default:
				break;
		}
		character.cost = cost;
		character.statistics = stats;
		return character;
	}

}
