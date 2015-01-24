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
    	}
        
        
        return plateauDeJeu;
    }

    @Override
    public ZoneAbstract creerZone() {
        return null;
    }
    
    public ZoneAbstract creerZone (SquareTypes type, Position p) {
        switch (type) {
        	case chateau :
        		return new CaseChateau(p);
            case fort :
                return new CaseFort(p);
            case montagne :
                return new CaseMontagne(p);
            case plaine :
                return new CasePlaine(p);
            default:
                return null;
        }
    }

    @Override
    public AccessAbstract creerAcces(ZoneAbstract z1, ZoneAbstract z2) {
        return new Adjacent(z1, z2);
    }
    
}
