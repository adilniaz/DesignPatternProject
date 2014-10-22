package implementations.factories.characters;

import example_main_package.GameSimulation.CharactersType;
import implementations.characters.FighterAirCraft;
import implementations.characters.Ship;
import implementations.characters.Soldier;
import implementations.characters.Tank;
import implementations.organisations.Organisation;
import abstracts_interfaces.CharacterAbstract;
import abstracts_interfaces.factories.characters.CreateCharactersFactoryAbstract;

public class CreateCharactersGulfWarFactory extends CreateCharactersFactoryAbstract{

	@Override
	public CharacterAbstract createCharacter(String name, Organisation org,
			CharactersType typeCharacter) {
		switch (typeCharacter) {
			case TANK:
				return new Tank(name, org);
			case FIGHTERAIRCRAFT:
				return new FighterAirCraft(name, org);
			case SOLDIER:
				return new Soldier(name, org);
			case SHIP:
				return new Ship(name, org);
			default:
				return null;
		}
	}
}
