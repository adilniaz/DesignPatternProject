package implementations.plateauJeu;

import abstracts_interfaces.factories.gameplatforms.AccessAbstract;
import abstracts_interfaces.factories.gameplatforms.ZoneAbstract;

public class Adjacent extends AccessAbstract {

    public Adjacent(ZoneAbstract z1, ZoneAbstract z2) {
        super(z1, z2);
    }

	@Override
	public String createAccess(ZoneAbstract zone1, ZoneAbstract zone2) {
		// TODO Auto-generated method stub
		return null;
	}
    
}
