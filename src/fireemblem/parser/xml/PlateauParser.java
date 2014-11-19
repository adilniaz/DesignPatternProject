package fireemblem.parser.xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import abstracts_interfaces.factories.gameplatforms.ZoneAbstract;
import fireemblem.Position;
import fireemblem.plateauJeu.Case;
import fireemblem.plateauJeu.CaseTypes;
import fireemblem.plateauJeu.PlateauJeu;
import fireemblem.plateauJeu.Terrain;

public class PlateauParser extends DefaultHandler {

    private Terrain terrain;
    private PlateauJeu plateauJeu;
    
    private int width;
    private int height;
	
	private Attributes mCurrentAttList = null;
    
    public PlateauParser (Terrain terrain, PlateauJeu plateauJeu) {
    	this.terrain = terrain;
    	this.plateauJeu = plateauJeu;
    }

    @Override
    public void startDocument() throws SAXException {
    }

    @Override
    public void endDocument() throws SAXException {

    }

    @Override
    public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
        this.mCurrentAttList = atts;
        if (qName.equalsIgnoreCase("map")) {
        	this.width = Integer.valueOf(this.mCurrentAttList.getValue("width"));
        	this.height = Integer.valueOf(this.mCurrentAttList.getValue("height"));
        } else if (qName.equalsIgnoreCase("case")) {
        	String typeName = this.mCurrentAttList.getValue("type");
        	int x = Integer.valueOf(this.mCurrentAttList.getValue("x"));
        	int y = Integer.valueOf(this.mCurrentAttList.getValue("y"));
        	
        	this.plateauJeu.ajouterZone(this.terrain.creerZone(CaseTypes.valueOf(typeName), new Position(x, y)));
        	
        }
    }

    @Override
    public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
    	if (qName.equalsIgnoreCase("map")) {
    		for (int i = 0 ; i < this.width ; i++) {
                for (int j = 0 ; j < this.height; j++) {
                	if (this.plateauJeu.getZones().size() < (this.width * this.height)) {
	                	for (ZoneAbstract zone : this.plateauJeu.getZones()) {
	                        Case c = (Case) zone;
	                        if (c.getPosition().equals(new Position(i, j))) {
	                            break;
	                        }
	                        this.plateauJeu.ajouterZone(this.terrain.creerZone(CaseTypes.plaine, new Position(i, j)));
	                    }
                	}
                }
            }
        }
    }
}
