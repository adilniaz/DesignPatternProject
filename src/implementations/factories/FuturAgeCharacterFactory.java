package implementations.factories;

import implementations.character.Cyborg;
import implementations.character.Hovercraft;
import implementations.character.LaserGunner;
import implementations.character.LaserSniper;
import implementations.organizations.Organization;
import abstracts_interfaces.CharacterAbstract;
import abstracts_interfaces.factories.characters.CharactersType;
import abstracts_interfaces.factories.characters.CreateCharactersFactoryAbstract;

public class FuturAgeCharacterFactory extends CreateCharactersFactoryAbstract{

	@Override
	public CharacterAbstract createCharacter(String name,
			Organization organisation, CharactersType typeCharacter) {
		CharacterAbstract character = null;
		switch (Characters.valueOf(typeCharacter.toString())) {
			case LASERGUNNER:
				character = new LaserGunner(organisation, name);
				break;
			case LASERSNIPER:
				character = new LaserSniper(organisation, name);
				break;
			case CYBORG:
				character = new Cyborg(organisation, name);
				break;
			case HOVERCRAFT:
				character = new Hovercraft(organisation, name);
				break;
	
			default:
				break;
		}
		character.cost = cost;
		character.statistics = stats;
		return character;
	}

}
