package abstracts_interfaces.factories.gameplatforms;

import java.util.ArrayList;

/**
 * @author NIAZ
 *
 */
public abstract class GamePlatformAbstract {
	public abstract void addAccess(ArrayList<AccessAbstract> access);
	public abstract void addZone(ArrayList<ZoneAbstract> zones);
}
