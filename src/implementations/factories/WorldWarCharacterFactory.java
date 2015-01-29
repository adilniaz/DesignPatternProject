package implementations.factories;

import implementations.character.Grenadier;
import implementations.character.LongCanon;
import implementations.character.Soldier;
import implementations.character.Tank;
import implementations.organizations.Organization;
import abstracts_interfaces.CharacterAbstract;
import abstracts_interfaces.factories.characters.CharactersType;
import abstracts_interfaces.factories.characters.CreateCharactersFactoryAbstract;

public class WorldWarCharacterFactory extends CreateCharactersFactoryAbstract{

	@Override
	public CharacterAbstract createCharacter(String name,
			Organization organisation, CharactersType typeCharacter) {
		CharacterAbstract character = null;
		switch (Characters.valueOf(typeCharacter.toString())) {
			case SOLDIER:
				character = new Soldier(organisation, name);
				break;
			case GRENADIER:
				character = new Grenadier(organisation, name);
				break;
			case LONGCANON:
				character = new LongCanon(organisation, name);
				break;
			case TANK:
				character = new Tank(organisation, name);
				break;
	
			default:
				break;
		}
		character.cost = cost;
		character.statistics = stats;
		return character;
	}

}
