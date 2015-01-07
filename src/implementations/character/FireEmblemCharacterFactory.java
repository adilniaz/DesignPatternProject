package implementations.character;

import implementations.movement.LordMovement;
import implementations.organizations.Organization;
import abstracts_interfaces.CharacterAbstract;
import abstracts_interfaces.factories.characters.CharactersType;
import abstracts_interfaces.factories.characters.CreateCharactersFactoryAbstract;

public class FireEmblemCharacterFactory extends CreateCharactersFactoryAbstract {

	@Override
	public CharacterAbstract createCharacter(String nom, Organization organisation, CharactersType typeCharacter) {
		if (typeCharacter instanceof FireEmblemCharacterType) {
            FireEmblemCharacterType typePersonnage = (FireEmblemCharacterType) typeCharacter;
            
            switch (typePersonnage) {
	            case archer :
	                Character archer = new Character(nom, organisation);
	                /*archer.setComportementPersonnage(new ComportementArcher());
	                archer.setComportementDeplacement(new DeplacementArcher());
	                archer.setComportementCombat(new CombatAPiedAvecArc());*/
	                return archer;
                case archimage :
                    Character athos = new Character(nom, organisation);
                    /*athos.setComportementPersonnage(new ComportementArchimange());
                    athos.setComportementDeplacement(new DeplacementArchimage());
                    athos.setComportementCombat(new CombatAPiedAvecArc());*/
                    athos.setNiv(20);
                    athos.setPv(40);
                    athos.setPvMax(40);
                    athos.setPuissance(30);
                    athos.setCapacite(24);
                    athos.setVitesse(20);
                    athos.setChance(25);
                    athos.setDef(20);
                    athos.setResistance(28);
                    athos.setConstitution(9);
                    return athos;
                case chevalier :
                    
                case chevalier_noir :
                	Character chevalier_noir = new Character(nom, organisation);
                	return chevalier_noir;
                case combattant :
                    Character combattant = new Character(nom, organisation);
                    combattant.setComportementPersonnage(new WarriorBehaviour());
                    combattant.setMoveBehaviour(new LordMovement());
                    /*combattant.setComportementDeplacement(new DeplacementCombattant());
                    combattant.setComportementCombat(new CombatAPiedAvecArc());*/
                    combattant.setNiv(1);
                    combattant.setPv(20);
                    combattant.setPvMax(20);
                    combattant.setPuissance(5);
                    combattant.setCapacite(2);
                    combattant.setVitesse(4);
                    combattant.setChance(0);
                    combattant.setDef(2);
                    combattant.setResistance(0);
                    combattant.setConstitution(11);
                    return combattant;
                case corbeau :
                	Character corbeau = new Character(nom, organisation);
                	return corbeau;
                case dark_druide :
                    Character nergal = new Character(nom, organisation);
                    nergal.setComportementPersonnage(new DarkDruidBehaviour());
                    /*nergal.setComportementDeplacement(new DeplacementDarkDruide());
                    nergal.setComportementCombat(new CombatAPiedAvecArc());*/
                    nergal.setNiv(20);
                    nergal.setPv(75);
                    nergal.setPvMax(75);
                    nergal.setPuissance(30);
                    nergal.setCapacite(18);
                    nergal.setVitesse(15);
                    nergal.setChance(20);
                    nergal.setDef(28);
                    nergal.setResistance(30);
                    nergal.setConstitution(10);
                    return nergal;
                case dragon_rouge :
                	Character dragon_rouge = new Character(nom, organisation);
                	return dragon_rouge;
                case dragon_blanc :
                	Character dragon_blanc = new Character(nom, organisation);
                	return dragon_blanc;
                case faucon :
                	Character faucon = new Character(nom, organisation);
                	return faucon;
                case king_daein :
                	Character ashnard = new Character(nom, organisation);
                	return ashnard;
                case lion :
                	Character lion = new Character(nom, organisation);
                	return lion;
                case lord_eirika :
                	Character eirika = new Character(nom, organisation);
                	return eirika;
                case lord_eliwood :
                    Character eliwood = new Character(nom, organisation);
                    eliwood.setComportementPersonnage(new LordBehaviour());
                    eliwood.getComportementPersonnage().setPromotedClass(new LordEquusBehaviour());
                    eliwood.setMoveBehaviour(new LordMovement());
                    //eliwood.setComportementCombat(new CombatAPiedAvecArc());
                    eliwood.setNiv(1);
                    eliwood.setPv(18);
                    eliwood.setPvMax(18);
                    eliwood.setPuissance(5);
                    eliwood.setCapacite(5);
                    eliwood.setVitesse(7);
                    eliwood.setChance(7);
                    eliwood.setDef(5);
                    eliwood.setResistance(0);
                    eliwood.setConstitution(7);
                    eliwood.setPourcentagePv(80);
                    eliwood.setPourcentagePuissance(45);
                    eliwood.setPourcentageCapacite(50);
                    eliwood.setPourcentageVitesse(40);
                    eliwood.setPourcentageChance(45);
                    eliwood.setPourcentageDefense(30);
                    eliwood.setPourcentageResistance(35);
                    eliwood.setExperience(90);
                    return eliwood;
                case lord_ephraim :
                	Character ephraim = new Character(nom, organisation);
                	return ephraim;
                case lord_hector :
                    Character hector = new Character(nom, organisation);
                    hector.setComportementPersonnage(new LordBehaviour());
                    hector.setMoveBehaviour(new LordMovement());
                    /*hector.setComportementCombat(new CombatAPiedAvecArc());*/
                    hector.setNiv(1);
                    hector.setPv(19);
                    hector.setPvMax(19);
                    hector.setPuissance(7);
                    hector.setCapacite(4);
                    hector.setVitesse(5);
                    hector.setChance(3);
                    hector.setDef(8);
                    hector.setResistance(0);
                    hector.setConstitution(13);
                    hector.setPourcentagePv(90);
                    hector.setPourcentagePuissance(60);
                    hector.setPourcentageCapacite(45);
                    hector.setPourcentageVitesse(35);
                    hector.setPourcentageChance(30);
                    hector.setPourcentageDefense(50);
                    hector.setPourcentageResistance(25);
                    return hector;
                case lord_lyn :
                    Character lyn = new Character(nom, organisation);
                    lyn.setComportementPersonnage(new LordBehaviour());
                    lyn.setMoveBehaviour(new LordMovement());
                    /*lyn.setComportementCombat(new CombatAPiedAvecArc());*/
                    lyn.setNiv(1);
                    lyn.setPv(16);
                    lyn.setPvMax(16);
                    lyn.setPuissance(4);
                    lyn.setCapacite(7);
                    lyn.setVitesse(9);
                    lyn.setChance(5);
                    lyn.setDef(2);
                    lyn.setResistance(0);
                    lyn.setConstitution(5);
                    lyn.setPourcentagePv(70);
                    lyn.setPourcentagePuissance(40);
                    lyn.setPourcentageCapacite(60);
                    lyn.setPourcentageVitesse(60);
                    lyn.setPourcentageChance(55);
                    lyn.setPourcentageDefense(20);
                    lyn.setPourcentageResistance(30);
                    return lyn;
                case lord_roy :
                	Character roy = new Character(nom, organisation);
                	return roy;
                case marchand :
                	Character marchand = new Character(nom, organisation);
                	return marchand;
                case ranger :
                	Character ike = new Character(nom, organisation);
                	return ike;
                default :
                    Character perso = new Character(nom, organisation);
                    /*perso.setComportementPersonnage(new ComportementArcher());
                    perso.setComportementDeplacement(new DeplacementArcher());
                    perso.setComportementCombat(new CombatAPiedAvecArc());*/
                    return perso;
                    
            }
            
        }
        return null;
	}
    
}
