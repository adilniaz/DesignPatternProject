package implementations.factories.gameplatforms.accesses;

import abstracts_interfaces.factories.gameplatforms.AccessAbstract;
import abstracts_interfaces.factories.gameplatforms.ZoneAbstract;


public class Neighbour extends AccessAbstract{

	@Override
	public String CreateAccess(ZoneAbstract zone1, ZoneAbstract zone2) {
		id = zone1.name + " is neighbours with " + zone2.name;
		return id;
	}

}
