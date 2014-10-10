package implementations.factories.gameplatforms.platformtypes;

import implementations.factories.gameplatforms.accesses.Neighbour;
import implementations.factories.gameplatforms.zones.Square;

import java.util.ArrayList;

import abstracts_interfaces.factories.gameplatforms.AccessAbstract;
import abstracts_interfaces.factories.gameplatforms.GamePlatformFactoryAbstract;
import abstracts_interfaces.factories.gameplatforms.ZoneAbstract;

public class MazeFactory extends GamePlatformFactoryAbstract{
	
	private ArrayList<ZoneAbstract> squares;
	private ArrayList<AccessAbstract> neighbours;
	
	public MazeFactory(String str){
		this.name = str;
		
		buildZonesAndAccesses();
	}
	
	public ArrayList<ZoneAbstract> getZones() {
		return squares;
	}
	public void setZones(ArrayList<ZoneAbstract> zones) {
		this.squares = zones;
	}
	
	public ArrayList<AccessAbstract> getAccess() {
		return neighbours;
	}
	public void setAccess(ArrayList<AccessAbstract> access) {
		this.neighbours = access;
	}
	
	public MazeFactory(){
		
		this.name = "";
		
		buildZonesAndAccesses();
		
	}

	@Override
	public void buildZonesAndAccesses() {
		squares = new ArrayList<ZoneAbstract>();
		neighbours = new ArrayList<AccessAbstract>();
		
		ZoneAbstract zone;
		ZoneAbstract zone0 = new Square("square 0");
		for (int i = 0; i < 5; i++) {
			zone = new Square("square " + i);
			squares.add(zone);
		}
		for (int i = 0; i < 5; i++) {
			AccessAbstract neigh = new Neighbour();
			if (i<4) {
				neigh.CreateAccess(zone0, squares.get(i+1));
			neighbours.add(neigh);
			}
		}
		zones = squares;
		accesses = neighbours;
	}
	
}
