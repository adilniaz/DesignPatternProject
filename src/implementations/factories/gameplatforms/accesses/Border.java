package implementations.factories.gameplatforms.accesses;

import abstracts_interfaces.factories.gameplatforms.AccessAbstract;
import abstracts_interfaces.factories.gameplatforms.ZoneAbstract;

public class Border extends AccessAbstract{

	@Override
	public String CreateAccess(ZoneAbstract zone1, ZoneAbstract zone2) {
		id = zone1.name + " and " + zone2.name + " share borders.";
		return id;
	}

}
