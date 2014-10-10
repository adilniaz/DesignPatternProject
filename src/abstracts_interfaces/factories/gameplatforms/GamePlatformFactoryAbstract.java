package abstracts_interfaces.factories.gameplatforms;

import java.util.ArrayList;


public abstract class GamePlatformFactoryAbstract {
	
	public String name;
	public ArrayList<ZoneAbstract> zones;
	public ArrayList<AccessAbstract> accesses;
	
	public abstract void buildZonesAndAccesses();
	
}
