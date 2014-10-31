package abstracts_interfaces.factories.gameplatforms;

public abstract class GameEnvironnementAbstract {
	
	public abstract GamePlatformAbstract createGamePlatform ();
    
    abstract public ZoneAbstract creerZone ();
    
    abstract public AccessAbstract creerAcces (ZoneAbstract z1, ZoneAbstract z2);

}
