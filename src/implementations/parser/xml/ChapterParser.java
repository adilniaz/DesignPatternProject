package implementations.parser.xml;

import implementations.Position;
import implementations.character.FireEmblemCharacterFactory;
import implementations.character.Character;
import implementations.character.FireEmblemCharacterType;
import implementations.gameplatform.GamePlatform;
import implementations.object.ObjetFactory;
import implementations.object.ObjetType;
import implementations.strategy.Strategy;
import implementations.strategy.NoMovementStrategy;
import implementations.strategy.AttackNearestStrategy;
import implementations.strategy.RangeStrategy;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;

import abstracts_interfaces.factories.characters.CreateCharactersFactoryAbstract;

public class ChapterParser implements XMLParser {
	
	 private GamePlatform plateauJeu;
	 private Character perosEnCours;
	 private CreateCharactersFactoryAbstract factoryPersonnage;
     private ObjetFactory factoryObjet;
	    
	 public ChapterParser (GamePlatform plateauJeu) {
		 this.plateauJeu = plateauJeu;
	     this.factoryPersonnage = new FireEmblemCharacterFactory();
	     this.factoryObjet = new ObjetFactory();
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
		} else if ("stat".equals(reader.getLocalName())) {
			int niv = 1;
			for (int i = 0 ; i < reader.getAttributeCount() ; i++) {
	        	if ("niv".equals(reader.getAttributeLocalName(i))) {
	        		niv = Integer.valueOf(reader.getAttributeValue(i));
	        	}
	        }
			this.perosEnCours.changeNiveau(niv);
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
			this.perosEnCours.ajouterObjet(factoryObjet.createObjet(nom, ObjetType.valueOf(type)));
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
	
	private Character createPersonnage (XMLStreamReader reader) {
		String nom = null;
		String type = null;
		for (int i = 0 ; i < reader.getAttributeCount() ; i++) {
        	if ("nom".equals(reader.getAttributeLocalName(i))) {
        		nom = reader.getAttributeValue(i);
        	} else if ("type".equals(reader.getAttributeLocalName(i))) {
        		type = reader.getAttributeValue(i);
        	}
        }
		Character perso = (Character)factoryPersonnage.createCharacter(nom, null, FireEmblemCharacterType.valueOf(type));
		return perso;
	}
	
	private Strategy getStrategieByName (String nom) {
		switch (nom) {
			case "immobile":
				return new NoMovementStrategy(this.perosEnCours);
			case "plusProche":
				return new AttackNearestStrategy(this.perosEnCours);
			case "portee":
				return new RangeStrategy(this.perosEnCours);
			default:
				return null;
		}
	}
}