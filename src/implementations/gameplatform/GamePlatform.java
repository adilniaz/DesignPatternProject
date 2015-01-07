package implementations.gameplatform;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private final Map<CharacterAbstract, Etat> etatPersonnage;
    private final Map<CharacterAbstract, Etat> etatEnnemies;
    private final Map<CharacterAbstract, Etat> etatAnnexes;
    
    public enum Etat {
    	normal, attendre;
    }
    
    public GamePlatform () {
        this.zones = new ArrayList<>();
        this.acces = new ArrayList<>();
        this.personnages = new ArrayList<>();
        this.ennemies = new ArrayList<>();
        this.annexes = new ArrayList<>();
        this.etatPersonnage = new HashMap<CharacterAbstract, GamePlatform.Etat>();
        this.etatEnnemies = new HashMap<CharacterAbstract, GamePlatform.Etat>();
        this.etatAnnexes = new HashMap<CharacterAbstract, GamePlatform.Etat>();
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
        this.etatPersonnage.put(p, Etat.normal);
    }
    
    public List<CharacterAbstract> getPersonnages () {
        return this.personnages;
    }
    
    public void ajouterEnnemie (CharacterAbstract p) {
        this.ennemies.add(p);
        this.etatEnnemies.put(p, Etat.normal);
    }
    
    public List<CharacterAbstract> getEnnemies () {
        return this.ennemies;
    }
    
    public void ajouterAnnexe (CharacterAbstract p) {
        this.annexes.add(p);
        this.etatAnnexes.put(p, Etat.normal);
    }

	public List<CharacterAbstract> getAnnexes() {
		return annexes;
	}
	
	public Etat getEtatByCharacter (CharacterAbstract p) {
		if (this.etatPersonnage.containsKey(p)) {
			return this.etatPersonnage.get(p);
		} else if (this.etatEnnemies.containsKey(p)) {
			return this.etatEnnemies.get(p);
		} else if (this.etatAnnexes.containsKey(p)) {
			return this.etatAnnexes.get(p);
		}
		return null;
	}
	
	public void changeEtatCharacter (CharacterAbstract p, Etat e) {
		if (this.etatPersonnage.containsKey(p)) {
			this.etatPersonnage.put(p, e);
		} else if (this.etatEnnemies.containsKey(p)) {
			this.etatEnnemies.put(p, e);
		} else if (this.etatAnnexes.containsKey(p)) {
			this.etatAnnexes.put(p, e);
		}
	}

	@Override
	public void addAccess(ArrayList<AccessAbstract> access) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addZone(ArrayList<ZoneAbstract> zones) {
		// TODO Auto-generated method stub
		
	}

}
