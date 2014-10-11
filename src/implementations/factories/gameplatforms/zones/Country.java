package implementations.factories.gameplatforms.zones;

import abstracts_interfaces.factories.gameplatforms.ZoneAbstract;


public class Country extends ZoneAbstract{
	
	public Country(String str) {
		this.name = str;
	}
	
	public Country() {
		this.name = "";
	}
	
	@Override
	public String createZone() {
		return "Country "+ name +" Created";
	}
	
}