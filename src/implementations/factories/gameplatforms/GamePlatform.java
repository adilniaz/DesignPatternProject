package implementations.factories.gameplatforms;

import java.util.ArrayList;

import abstracts_interfaces.factories.gameplatforms.AccessAbstract;
import abstracts_interfaces.factories.gameplatforms.GamePlatformAbstract;
import abstracts_interfaces.factories.gameplatforms.ZoneAbstract;

public class GamePlatform extends GamePlatformAbstract{

	@Override
	public void addAccess(ArrayList<AccessAbstract> access) {
		for (AccessAbstract a : access) {
			System.out.println("access :" + a.id + " added.\n");
		}
	}

	@Override
	public void addZone(ArrayList<ZoneAbstract> zones) {
		for (ZoneAbstract z : zones) {
			System.out.println("zone :" + z.name + " added.\n");
		}
	}
	
}
