package implementations.gameplatform;

import implementations.Position;
import implementations.chapters.Chapters;
import implementations.parser.xml.ChapterParser;
import implementations.parser.xml.Parser;
import implementations.parser.xml.PlatformParser;
import abstracts_interfaces.factories.gameplatforms.AccessAbstract;
import abstracts_interfaces.factories.gameplatforms.GameEnvironnementAbstract;
import abstracts_interfaces.factories.gameplatforms.GamePlatformAbstract;
import abstracts_interfaces.factories.gameplatforms.ZoneAbstract;

public class Terrain extends GameEnvironnementAbstract {
	
	private Chapters chapitres;
	
	public Terrain (Chapters chapitres) {
		this.chapitres = chapitres;
	}
    
    @Override
    public GamePlatformAbstract createGamePlatform () {
    	GamePlatform plateauDeJeu = new GamePlatform();
    	switch (chapitres) {
    		case blazing_sword:
    			Parser.Parse("src/implementations/xml/map/blazing_sword.xml", new PlatformParser(this, plateauDeJeu));
    			Parser.Parse("src/implementations/xml/character/blazing_sword.xml", new ChapterParser(plateauDeJeu));
    			break;
    		default :
    	        for (int i = 0 ; i < 20 ; i++) {
    	            for (int j = 0 ; j < 20; j++) {
    	                ZoneAbstract z;
    	                if ((i == 8 && j == 6) || (i == 8 && j == 14)) {
    	                    z = creerZone(SquareTypes.fort, new Position(i, j));
    	                } else {
    	                    z = creerZone(SquareTypes.plaine, new Position(i, j));
    	                }
    	                plateauDeJeu.ajouterZone(z);
    	            }
    	        }
    	        for (ZoneAbstract zone : plateauDeJeu.getZones()) {
    	            Square c1 = (Square) zone;
    	            for (ZoneAbstract zone2 : plateauDeJeu.getZones()) {
    	                Square c2 = (Square) zone2;
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
    
    public ZoneAbstract creerZone (SquareTypes type, Position p) {
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
