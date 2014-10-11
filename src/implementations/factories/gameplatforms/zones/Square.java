package implementations.factories.gameplatforms.zones;

import abstracts_interfaces.factories.gameplatforms.ZoneAbstract;


public class Square extends ZoneAbstract{
	
	public Square(String str) {
		this.name = str;
	}
	
	public Square() {
		this.name = "";
	}
	
	@Override
	public String createZone() {
		return "Square "+ name +" Created";
	}
	
}