package implementations.factories;

import implementations.decorators.statistics.Statistics;
import implementations.organizations.Organization;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import abstracts_interfaces.CharacterAbstract;
import abstracts_interfaces.decorators.statistics.StatisticsAbstract;
import abstracts_interfaces.factories.characters.CharactersType;
import abstracts_interfaces.factories.characters.CreateCharactersFactoryAbstract;

public class GeneralCharacterFactory {
	
	int ageIndex, characterIndex;
	Organization subject;
	
	String characterName;
	CreateCharactersFactoryAbstract factory;
	StatisticsAbstract statistics;
	
	int cost;
	
	public enum CharacterType implements CharactersType{
		CAVEMAN, SLINGER, RAPTORRIDER, RHINORIDER,
		SWORDSMAN, ARCHER, CATAPULT, KNIGHT,
		SOLDIER, GRENADIER, LONGCANON, TANK,
		GUNNER, SNIPER, ROCKETLAUNCHER, SUPERTANK,
		LASERGUNNER, LASERSNIPER, CYBORG, HOVERCRAFT
	}
	
	CharacterType typeCharacter;
	
	public GeneralCharacterFactory(){
		this.ageIndex = 0;
		this.characterIndex = 0;
		this.subject = null;
	}
	
	public CharacterAbstract createCharacter(int ageIndex, int characterIndex, Organization subject){
		
		this.ageIndex = ageIndex;
		this.characterIndex = characterIndex;
		this.subject = subject;
		
		typeCharacter = null;
		characterName = "";
		
		try {
			File fXmlFile = new File("src\\res\\ressources.xml");

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			
			doc.getDocumentElement().normalize();
			
			NodeList nList = doc.getElementsByTagName("age");
			Node node = nList.item(ageIndex);
			Element elemAge = (Element) node;
			
			Node node3 = elemAge.getElementsByTagName("character").item(characterIndex);
			Element elemCharac = (Element) node3;
			
			characterName = elemCharac.getAttributes().item(4).getNodeValue();
			
			int health = Integer.parseInt(elemCharac.getAttributes().item(3).getNodeValue());
			int speed = Integer.parseInt(elemCharac.getAttributes().item(5).getNodeValue());
			int defence = Integer.parseInt(elemCharac.getAttributes().item(2).getNodeValue());
			
			statistics = new Statistics(defence, health, speed);
			cost = Integer.parseInt(elemCharac.getAttributes().item(1).getNodeValue());
			
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		switch (characterName) {
		
		case "caveman":
			factory = new StoneAgeCharacterFactory();
			typeCharacter = CharacterType.CAVEMAN;
			break;
		case "slinger":
			factory = new StoneAgeCharacterFactory();
			typeCharacter = CharacterType.SLINGER;
			break;
		case "raptorRider":
			factory = new StoneAgeCharacterFactory();
			typeCharacter = CharacterType.RAPTORRIDER;
			break;
		case "rhinoRider":
			factory = new StoneAgeCharacterFactory();
			typeCharacter = CharacterType.RHINORIDER;
			break;
			

		case "swordsman":
			factory = new MiddleAgeCharacterFactory();
			typeCharacter = CharacterType.SWORDSMAN;
		case "archer":
			factory = new MiddleAgeCharacterFactory();
			typeCharacter = CharacterType.ARCHER;
		case "catapult":
			factory = new MiddleAgeCharacterFactory();
			typeCharacter = CharacterType.CATAPULT;
		case "knight":
			factory = new MiddleAgeCharacterFactory();
			typeCharacter = CharacterType.KNIGHT;
			

		case "soldier":
			factory = new WorldWarCharacterFactory();
			typeCharacter = CharacterType.SOLDIER;
			break;
		case "grenadier":
			factory = new WorldWarCharacterFactory();
			typeCharacter = CharacterType.GRENADIER;
			break;
		case "longCanon":
			factory = new WorldWarCharacterFactory();
			typeCharacter = CharacterType.LONGCANON;
			break;
		case "tank":
			factory = new WorldWarCharacterFactory();
			typeCharacter = CharacterType.TANK;
			break;

		case "gunner":
			factory = new ContemporaryAgeCharacterFactory();
			typeCharacter = CharacterType.GUNNER;
			break;
		case "sniper":
			factory = new ContemporaryAgeCharacterFactory();
			typeCharacter = CharacterType.SNIPER;
			break;
		case "rocketLauncher":
			factory = new ContemporaryAgeCharacterFactory();
			typeCharacter = CharacterType.ROCKETLAUNCHER;
			break;
		case "superTank":
			factory = new ContemporaryAgeCharacterFactory();
			typeCharacter = CharacterType.SUPERTANK;
			break;

		case "laserGunner":
			factory = new FuturAgeCharacterFactory();
			typeCharacter = CharacterType.LASERGUNNER;
			break;
		case "laserSniper":
			factory = new FuturAgeCharacterFactory();
			typeCharacter = CharacterType.LASERSNIPER;
			break;
		case "cyborg":
			factory = new FuturAgeCharacterFactory();
			typeCharacter = CharacterType.CYBORG;
			break;
		case "hovercraft":
			factory = new FuturAgeCharacterFactory();
			typeCharacter = CharacterType.HOVERCRAFT;
			break;
			
		default:
			break;
		}
		
		factory.stats = statistics;
		factory.cost = cost;
		
		CharacterAbstract character = factory.createCharacter(characterName, subject, typeCharacter);
		return character;
	}
}
