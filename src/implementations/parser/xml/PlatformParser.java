package implementations.parser.xml;

import implementations.Position;
import implementations.gameplatform.Square;
import implementations.gameplatform.SquareTypes;
import implementations.gameplatform.GamePlatform;
import implementations.gameplatform.Terrain;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;

import abstracts_interfaces.factories.gameplatforms.AccessAbstract;
import abstracts_interfaces.factories.gameplatforms.ZoneAbstract;

public class PlatformParser implements XMLParser {
	
	 private int width;
	 private int height;
	 private Terrain terrain;
	 private GamePlatform plateauJeu;
	 private ZoneAbstract[][] zoneAbstracts;
	    
    public PlatformParser (Terrain terrain, GamePlatform plateauJeu) {
    	this.terrain = terrain;
    	this.plateauJeu = plateauJeu;
    }
	
	public void parse (XMLStreamReader reader) {
		try {
            while (reader.hasNext()) {
                int eventType = reader.next();
            	switch (eventType) {
                    case XMLEvent.END_ELEMENT:
                    	this.endElement(reader);
                        break;
                    case XMLEvent.START_ELEMENT:
                        this.startElement(reader);
                        break;
                }
            }
        } catch (XMLStreamException ex) {
            Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
        }
	}
	
	private void startElement (XMLStreamReader reader) {
		if ("map".equals(reader.getLocalName())) {
			for (int i = 0 ; i < reader.getAttributeCount() ; i++) {
	        	if ("width".equals(reader.getAttributeLocalName(i))) {
	        		this.width = Integer.valueOf(reader.getAttributeValue(i));
	        	} else if ("height".equals(reader.getAttributeLocalName(i))) {
	        		this.height = Integer.valueOf(reader.getAttributeValue(i));
	        	}
	        }
			this.zoneAbstracts = new ZoneAbstract[this.width][this.height];
			this.plateauJeu.setWidth(width);
			this.plateauJeu.setHeight(height);
		} else if ("case".equals(reader.getLocalName())) {
			String typeName = null;
        	int x = 0;
        	int y = 0;
			for (int i = 0 ; i < reader.getAttributeCount() ; i++) {
	        	if ("type".equals(reader.getAttributeLocalName(i))) {
	        		typeName = reader.getAttributeValue(i);
	        	} else if ("x".equals(reader.getAttributeLocalName(i))) {
	        		x = Integer.valueOf(reader.getAttributeValue(i));
	        	} else if ("y".equals(reader.getAttributeLocalName(i))) {
	        		y = Integer.valueOf(reader.getAttributeValue(i));
	        	}
	        }
			this.zoneAbstracts[x][y] = this.terrain.creerZone(SquareTypes.valueOf(typeName), new Position(x, y));
		}
	}
	
	private void endElement (XMLStreamReader reader) {
		if ("map".equals(reader.getLocalName())) {
			for (int i = 0 ; i < this.width ; i++) {
                for (int j = 0 ; j < this.height; j++) {
                	if (this.zoneAbstracts[i][j] == null) {
                		this.plateauJeu.ajouterZone(this.terrain.creerZone(SquareTypes.plaine, new Position(i, j)));
                	} else {
                		this.plateauJeu.ajouterZone(this.zoneAbstracts[i][j]);
                	}
                }
            }
			for (ZoneAbstract zone : this.plateauJeu.getZones()) {
	            Square c1 = (Square) zone;
	            for (ZoneAbstract zone2 : this.plateauJeu.getZones()) {
	                Square c2 = (Square) zone2;
	                if (c2.getPosition().equals(new Position(c1.getPosition().getPositionX()+1, c1.getPosition().getPositionY())) ||
	                        c2.getPosition().equals(new Position(c1.getPosition().getPositionX()-1, c1.getPosition().getPositionY())) ||
	                        c2.getPosition().equals(new Position(c1.getPosition().getPositionX(), c1.getPosition().getPositionY()+1)) ||
	                        c2.getPosition().equals(new Position(c1.getPosition().getPositionX(), c1.getPosition().getPositionY()-1))) {
	                    AccessAbstract acces = this.terrain.creerAcces(zone, zone2);
	                    this.plateauJeu.ajouterAcces(acces);
	                    AccessAbstract acces2 = this.terrain.creerAcces(zone2, zone);
	                    this.plateauJeu.ajouterAcces(acces2);
	                }
	            }
	        }
		}
	}
}
