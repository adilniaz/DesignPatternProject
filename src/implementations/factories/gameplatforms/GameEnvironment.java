package implementations.factories.gameplatforms;

import implementations.factories.gameplatforms.platformtypes.MazeFactory;
import implementations.factories.gameplatforms.platformtypes.WorldMapFactory;
import main_package.GameSimulation.GamePlatformType;
import abstracts_interfaces.factories.gameplatforms.GamePlatformAbstract;
import abstracts_interfaces.factories.gameplatforms.GamePlatformFactoryAbstract;

public class GameEnvironment {
	
	public GamePlatformAbstract createGamePlatform(GamePlatformType gType) {
		GamePlatformFactoryAbstract factory = null;
		switch (gType) {
			case MAZE:
				factory = new MazeFactory("maze");
				break;
			case WORLDMAP:
				factory = new WorldMapFactory("map");
				break;
			case UNDEFINED:
				return null;
			case ANTCOLONY:
				return null;
			default:
				return null;
		}
		
		GamePlatformAbstract gamePlatform = new GamePlatform();

		gamePlatform.addZone(factory.zones);
		gamePlatform.addAccess(factory.accesses);
		
		return gamePlatform;
	}
	
}
