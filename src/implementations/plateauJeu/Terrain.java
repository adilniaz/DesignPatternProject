package implementations.plateauJeu;

import implementations.Position;
import implementations.chapitre.Chapitres;
import implementations.parser.xml.ChapitreParser;
import implementations.parser.xml.Parser;
import implementations.parser.xml.PlateauParser;
import abstracts_interfaces.factories.gameplatforms.AccessAbstract;
import abstracts_interfaces.factories.gameplatforms.GameEnvironnementAbstract;
import abstracts_interfaces.factories.gameplatforms.GamePlatformAbstract;
import abstracts_interfaces.factories.gameplatforms.ZoneAbstract;

public class Terrain extends GameEnvironnementAbstract {
	
	private Chapitres chapitres;
	
	public Terrain (Chapitres chapitres) {
		this.chapitres = chapitres;
	}
    
    @Override
    public GamePlatformAbstract createGamePlatform () {
    	PlateauJeu plateauDeJeu = new PlateauJeu();
    	switch (chapitres) {
    		case blazing_sword:
    			Parser.Parse("src/implementations/xml/map/blazing_sword.xml", new PlateauParser(this, plateauDeJeu));
    			Parser.Parse("src/implementations/xml/personnage/blazing_sword.xml", new ChapitreParser(plateauDeJeu));
    			break;
    		default :
    	        for (int i = 0 ; i < 20 ; i++) {
    	            for (int j = 0 ; j < 20; j++) {
    	                ZoneAbstract z;
    	                if ((i == 8 && j == 6) || (i == 8 && j == 14)) {
    	                    z = creerZone(CaseTypes.fort, new Position(i, j));
    	                } else {
    	                    z = creerZone(CaseTypes.plaine, new Position(i, j));
    	                }
    	                plateauDeJeu.ajouterZone(z);
    	            }
    	        }
    	        for (ZoneAbstract zone : plateauDeJeu.getZones()) {
    	            Case c1 = (Case) zone;
    	            for (ZoneAbstract zone2 : plateauDeJeu.getZones()) {
    	                Case c2 = (Case) zone2;
    	                if (c2.getPosition().equals(new Position(c1.getPosition().getPositionX()+1, c1.getPosition().getPositionY())) ||
    	                        c2.getPosition().equals(new Position(c1.getPosition().getPositionX()-1, c1.getPosition().getPositionY())) ||
    	                        c2.getPosition().equals(new Position(c1.getPosition().getPositionX(), c1.getPosition().getPositionY()+1)) ||
    	                        c2.getPosition().equals(new Position(c1.getPosition().getPositionX(), c1.getPosition().getPositionY()-1))) {
    	                    AccessAbstract acces = creerAcces(zone, zone2);
    	                    plateauDeJeu.ajouterAcces(acces);
    	                    AccessAbstract acces2 = creerAcces(zone2, zone);
    	                    plateauDeJeu.ajouterAcces(acces2);
    	                }
    	            }
    	        }
    			break;
    	}
        
        
        return plateauDeJeu;
    }

    @Override
    public ZoneAbstract creerZone() {
        return null;
    }
    
    public ZoneAbstract creerZone (CaseTypes type, Position p) {
        switch (type) {
            case plaine :
                return new CasePlaine(p);
            case fort :
                return new CaseFort(p);
            default:
                return null;
        }
    }

    @Override
    public AccessAbstract creerAcces(ZoneAbstract z1, ZoneAbstract z2) {
        return new Adjacent(z1, z2);
    }
    
}
