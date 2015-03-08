 package abstracts_interfaces.factories.gameplatforms;

public abstract class AccessAbstract {
	
	public String id;
	private ZoneAbstract zoneA;
	private ZoneAbstract zoneB;
	
	public AccessAbstract () {
		
	}
	
	public AccessAbstract (ZoneAbstract zone1, ZoneAbstract zone2) {
		this.zoneA = zone1;
		this.zoneB = zone2;
	}
	
	public ZoneAbstract getZoneA () {
		return zoneA;
	}
	
	public ZoneAbstract getZoneB () {
		return zoneB;
	}
	
}
