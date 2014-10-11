package implementations.factories.gameplatforms.platformtypes;

import implementations.factories.gameplatforms.accesses.Border;
import implementations.factories.gameplatforms.zones.Country;

import java.util.ArrayList;

import abstracts_interfaces.factories.gameplatforms.AccessAbstract;
import abstracts_interfaces.factories.gameplatforms.GamePlatformFactoryAbstract;
import abstracts_interfaces.factories.gameplatforms.ZoneAbstract;

public class WorldMapFactory extends GamePlatformFactoryAbstract{
	
	private ArrayList<ZoneAbstract> country;
	private ArrayList<AccessAbstract> borders;

	public WorldMapFactory(){
		this.name = "";
		buildZonesAndAccesses();
	}

	public WorldMapFactory(String str){
		this.name = str;
		buildZonesAndAccesses();
	}
	
	public ArrayList<ZoneAbstract> getZones() {
		return country;
	}
	public void setZones(ArrayList<ZoneAbstract> zones) {
		this.country = zones;
	}
	
	public ArrayList<AccessAbstract> getAccess() {
		return borders;
	}
	public void setAccess(ArrayList<AccessAbstract> access) {
		
		this.borders = access;
	}
	
	@Override
	public void buildZonesAndAccesses() {
		country = new ArrayList<ZoneAbstract>();
		borders = new ArrayList<AccessAbstract>();
		
		ZoneAbstract zone;
		ZoneAbstract zone0 = new Country("country 0");
		for (int i = 0; i < 5; i++) {
			zone = new Country("country " + i);
			country.add(zone);
		}
		for (int i = 0; i < 5; i++) {
			AccessAbstract neigh = new Border();
			if (i<4) {
				neigh.createAccess(zone0, country.get(i+1));
				borders.add(neigh);
			}
		}
		zones = country;
		accesses = borders;
	}
	
}
