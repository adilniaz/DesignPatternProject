package abstracts_interfaces.factories.characters;

import implementations.organizations.Organization;
import abstracts_interfaces.CharacterAbstract;
import abstracts_interfaces.decorators.statistics.StatisticsAbstract;

public abstract class CreateCharactersFactoryAbstract {
	
	public enum Characters{
		DEFAULT,
		CAVEMAN, SLINGER, RAPTORRIDER, RHINORIDER,
		SWORDSMAN, ARCHER, CATAPULT, KNIGHT,
		SOLDIER, GRENADIER, LONGCANON, TANK,
		GUNNER, SNIPER, ROCKETLAUNCHER, SUPERTANK,
		LASERGUNNER, LASERSNIPER, CYBORG, HOVERCRAFT
	}
	public StatisticsAbstract stats;
	public int cost;

	public abstract CharacterAbstract createCharacter(String name, Organization organisation, CharactersType typeCharacter);
}
