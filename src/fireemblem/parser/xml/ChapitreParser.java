package fireemblem.parser.xml;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;

import abstracts_interfaces.factories.characters.CreateCharactersFactoryAbstract;
import fireemblem.Position;
import fireemblem.objet.FactoryObjet;
import fireemblem.objet.TypeObjet;
import fireemblem.personnage.FactoryPersonnageFireEmblem;
import fireemblem.personnage.Personnage;
import fireemblem.personnage.TypePersonnageFireEmblem;
import fireemblem.plateauJeu.PlateauJeu;
import fireemblem.strategie.Strategie;
import fireemblem.strategie.StrategieImmobile;
import fireemblem.strategie.StrategiePlusProche;
import fireemblem.strategie.StrategiePortee;

public class ChapitreParser implements XMLParser {
	
	 private PlateauJeu plateauJeu;
	 private Personnage perosEnCours;
	 private CreateCharactersFactoryAbstract factoryPersonnage;
     private FactoryObjet factoryObjet;
	    
	 public ChapitreParser (PlateauJeu plateauJeu) {
		 this.plateauJeu = plateauJeu;
	     this.factoryPersonnage = new FactoryPersonnageFireEmblem();
	     this.factoryObjet = new FactoryObjet();
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
		if ("personnage".equals(reader.getLocalName())) {
			this.perosEnCours = this.createPersonnage(reader);
		} else if ("ennemie".equals(reader.getLocalName())) {
			this.perosEnCours = this.createPersonnage(reader);
		} else if ("annexe".equals(reader.getLocalName())) {
			this.perosEnCours = this.createPersonnage(reader);
		} else if ("position".equals(reader.getLocalName())) {
			int x = 0;
			int y = 0;
			for (int i = 0 ; i < reader.getAttributeCount() ; i++) {
	        	if ("x".equals(reader.getAttributeLocalName(i))) {
	        		x = Integer.valueOf(reader.getAttributeValue(i));
	        	} else if ("y".equals(reader.getAttributeLocalName(i))) {
	        		y = Integer.valueOf(reader.getAttributeValue(i));
	        	}
	        }
			this.perosEnCours.setPosition(new Position(x, y));
		} else if ("objet".equals(reader.getLocalName())) {
			String nom = null;
			String type = null;
			for (int i = 0 ; i < reader.getAttributeCount() ; i++) {
	        	if ("nom".equals(reader.getAttributeLocalName(i))) {
	        		nom = reader.getAttributeValue(i);
	        	} else if ("type".equals(reader.getAttributeLocalName(i))) {
	        		type = reader.getAttributeValue(i);
	        	}
	        }
			this.perosEnCours.ajouterObjet(factoryObjet.createObjet(nom, TypeObjet.valueOf(type)));
		} else if ("strategie".equals(reader.getLocalName())) {
			String nom = null;
			for (int i = 0 ; i < reader.getAttributeCount() ; i++) {
	        	if ("nom".equals(reader.getAttributeLocalName(i))) {
	        		nom = reader.getAttributeValue(i);
	        	}
	        }
			this.perosEnCours.setStrategie(this.getStrategieByName(nom));
		}
	}
	
	private void endElement (XMLStreamReader reader) {
		if ("personnage".equals(reader.getLocalName())) {
			this.plateauJeu.ajouterPersonnage(this.perosEnCours);
		} else if ("ennemie".equals(reader.getLocalName())) {
			this.plateauJeu.ajouterEnnemie(this.perosEnCours);
		} else if ("annexe".equals(reader.getLocalName())) {
			this.plateauJeu.ajouterAnnexe(this.perosEnCours);
		}
	}
	
	private Personnage createPersonnage (XMLStreamReader reader) {
		String nom = null;
		String type = null;
		for (int i = 0 ; i < reader.getAttributeCount() ; i++) {
        	if ("nom".equals(reader.getAttributeLocalName(i))) {
        		nom = reader.getAttributeValue(i);
        	} else if ("type".equals(reader.getAttributeLocalName(i))) {
        		type = reader.getAttributeValue(i);
        	}
        }
		Personnage perso = (Personnage)factoryPersonnage.createCharacter(nom, null, TypePersonnageFireEmblem.valueOf(type));
		return perso;
	}
	
	private Strategie getStrategieByName (String nom) {
		switch (nom) {
			case "immobile":
				return new StrategieImmobile(this.perosEnCours);
			case "plusProche":
				return new StrategiePlusProche(this.perosEnCours);
			case "portee":
				return new StrategiePortee(this.perosEnCours);
			default:
				return null;
		}
	}
}