package implementations.parser.xml;

import java.io.File;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import abstracts_interfaces.CharacterAbstract;

import implementations.character.Character;
import implementations.controller.Chapter;
import implementations.object.Objet;

public class XMLWriter {
	
	public void write (Chapter chapter) {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder builder = factory.newDocumentBuilder();
	        Document doc = builder.newDocument();
	        Element chapitre = doc.createElement("chapitre");
	        chapitre.setAttribute("name", chapter.getNom());
	        chapitre.setAttribute("objectif", chapter.getObjectif());
	        List<CharacterAbstract> personnages = chapter.getPlateauDeJeu().getPersonnages();
	        if (!personnages.isEmpty()) {
	        	Element elemPersonnages = doc.createElement("personnages");
	        	for (CharacterAbstract characterAbstract : personnages) {
	        		Character character = (Character) characterAbstract;
	        		Element personnage = doc.createElement("personnage");
	        		this.createPersonnage(character, personnage, doc);
	        		elemPersonnages.appendChild(personnage);
	        	}
	        	chapitre.appendChild(elemPersonnages);
	        }
	        List<CharacterAbstract> annexes = chapter.getPlateauDeJeu().getAnnexes();
	        if (!annexes.isEmpty()) {
	        	Element elemPersonnages = doc.createElement("annexes");
	        	for (CharacterAbstract characterAbstract : annexes) {
	        		Character character = (Character) characterAbstract;
	        		Element personnage = doc.createElement("annexe");
	        		this.createPersonnage(character, personnage, doc);
	        		elemPersonnages.appendChild(personnage);
	        	}
	        	chapitre.appendChild(elemPersonnages);
	        }
	        List<CharacterAbstract> ennemies = chapter.getPlateauDeJeu().getEnnemies();
	        if (!ennemies.isEmpty()) {
	        	Element elemPersonnages = doc.createElement("ennemies");
	        	for (CharacterAbstract characterAbstract : ennemies) {
	        		Character character = (Character) characterAbstract;
	        		Element personnage = doc.createElement("ennemie");
	        		this.createPersonnage(character, personnage, doc);
	        		elemPersonnages.appendChild(personnage);
	        	}
	        	chapitre.appendChild(elemPersonnages);
	        }
	        doc.appendChild(chapitre);
	        File fichierXml = new File("fireemblem.xml");
            XMLGenerator generator = new XMLGenerator();
            generator.generate(doc, fichierXml);
		} catch (ParserConfigurationException | DOMException e) {
            System.out.println("Exception: " + e.getMessage());
        }
		
	}
	
	private void createPersonnage (Character character, Element personnage, Document doc) {
		personnage.setAttribute("nom", character.getName());
		personnage.setAttribute("type", character.getComportementPersonnage().getType().name());
		personnage.setAttribute("status", character.getStatus().name());
		personnage.setAttribute("etat", character.getEtat().name());
		Element stat = doc.createElement("stat");
		stat.setAttribute("niv", String.valueOf(character.getNiv()));
		stat.setAttribute("pv", String.valueOf(character.getPv()));
		stat.setAttribute("pvgagne", String.valueOf(character.getPvGagne()));
		stat.setAttribute("puissancegagne", String.valueOf(character.getPuissanceGagne()));
		stat.setAttribute("capacitegagne", String.valueOf(character.getCapaciteGagne()));
		stat.setAttribute("vitessegagne", String.valueOf(character.getVitesseGagne()));
		stat.setAttribute("chancegagne", String.valueOf(character.getChanceGagner()));
		stat.setAttribute("defgagne", String.valueOf(character.getDefGagne()));
		stat.setAttribute("resistancegagne", String.valueOf(character.getResistanceGagne()));
		stat.setAttribute("experience", String.valueOf(character.getExperience()));
		personnage.appendChild(stat);
		Element position = doc.createElement("position");
		position.setAttribute("x", String.valueOf(character.getPosition().getPositionX()));
		position.setAttribute("y", String.valueOf(character.getPosition().getPositionY()));
		personnage.appendChild(position);
		Element elemObjets = doc.createElement("objets");
		Objet[] objets = character.getObjets();
		for (Objet obj : objets) {
			if (obj != null) {
				Element objet = doc.createElement("objet");
				objet.setAttribute("nom", obj.getName());
				objet.setAttribute("type", obj.getType().name());
				elemObjets.appendChild(objet);
			}
		}
		personnage.appendChild(elemObjets);
		if (character.getStrategie() != null) {
			Element strategie = doc.createElement("strategie");
			strategie.setAttribute("nom", character.getStrategie().name());
			personnage.appendChild(strategie);
		}
	}

}
