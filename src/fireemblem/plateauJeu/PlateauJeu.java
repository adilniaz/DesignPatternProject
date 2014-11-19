package fireemblem.plateauJeu;

import java.util.ArrayList;
import java.util.List;

import fireemblem.personnage.Personnage;
import abstracts_interfaces.CharacterAbstract;
import abstracts_interfaces.factories.gameplatforms.AccessAbstract;
import abstracts_interfaces.factories.gameplatforms.GamePlatformAbstract;
import abstracts_interfaces.factories.gameplatforms.ZoneAbstract;

public class PlateauJeu extends GamePlatformAbstract {
    
    private final List<ZoneAbstract> zones;
    private final List<AccessAbstract> acces;
    private final List<CharacterAbstract> personnages;
    private final List<CharacterAbstract> ennemies;
    private final List<CharacterAbstract> annexes;
    private final int width;
    private final int height;
    
    public PlateauJeu () {
        this.zones = new ArrayList<>();
        this.acces = new ArrayList<>();
        this.personnages = new ArrayList<>();
        this.ennemies = new ArrayList<>();
        this.annexes = new ArrayList<>();
        this.width = 20;
        this.height = 20;
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addZone(ArrayList<ZoneAbstract> zones) {
		// TODO Auto-generated method stub
		
	}

}
