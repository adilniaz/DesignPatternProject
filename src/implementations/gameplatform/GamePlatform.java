package implementations.gameplatform;

import java.util.ArrayList;
import java.util.List;

import abstracts_interfaces.CharacterAbstract;
import abstracts_interfaces.factories.gameplatforms.AccessAbstract;
import abstracts_interfaces.factories.gameplatforms.GamePlatformAbstract;
import abstracts_interfaces.factories.gameplatforms.ZoneAbstract;

public class GamePlatform extends GamePlatformAbstract {
    
    private final List<ZoneAbstract> zones;
    private final List<AccessAbstract> acces;
    private final List<CharacterAbstract> personnages;
    private final List<CharacterAbstract> ennemies;
    private final List<CharacterAbstract> annexes;
    private int width;
    private int height;
    
    public GamePlatform () {
        this.zones = new ArrayList<>();
        this.acces = new ArrayList<>();
        this.personnages = new ArrayList<>();
        this.ennemies = new ArrayList<>();
        this.annexes = new ArrayList<>();
        this.width = 20;
        this.height = 20;
    }
    
    public void setWidth (int width) {
    	this.width = width;
    }
    
    public int getWidth () {
    	return this.width;
    }
    
    public void setHeight (int height) {
    	this.height = width;
    }
    
    public int getHeight () {
    	return this.height;
    }
    
    public void ajouterZone (ZoneAbstract zone) {
        this.zones.add(zone);
    }
    
    public List<ZoneAbstract> getZones () {
        return this.zones;
    }
    
    public void ajouterAcces (AccessAbstract acces) {
        this.acces.add(acces);
    }

    public List<AccessAbstract> getAcces() {
        return acces;
    }
    
    public void ajouterPersonnage (CharacterAbstract p) {
        this.personnages.add(p);
    }
    
    public List<CharacterAbstract> getPersonnages () {
        return this.personnages;
    }
    
    public void ajouterEnnemie (CharacterAbstract p) {
        this.ennemies.add(p);
    }
    
    public List<CharacterAbstract> getEnnemies () {
        return this.ennemies;
    }
    
    public void ajouterAnnexe (CharacterAbstract p) {
        this.annexes.add(p);
    }

	public List<CharacterAbstract> getAnnexes() {
		return annexes;
	}

	@Override
	public void addAccess(ArrayList<AccessAbstract> access) {
		
	}

	@Override
	public void addZone(ArrayList<ZoneAbstract> zones) {
		
	}

}
