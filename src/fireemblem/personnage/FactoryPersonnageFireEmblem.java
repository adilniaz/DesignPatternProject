package fireemblem.personnage;

import fireemblem.deplacement.DeplacementLord;
import implementations.organisations.Organisation;
import abstracts_interfaces.CharacterAbstract;
import abstracts_interfaces.factories.characters.CharactersType;
import abstracts_interfaces.factories.characters.CreateCharactersFactoryAbstract;

public class FactoryPersonnageFireEmblem extends CreateCharactersFactoryAbstract {

	@Override
	public CharacterAbstract createCharacter(String nom, Organisation organisation, CharactersType typeCharacter) {
		if (typeCharacter instanceof TypePersonnageFireEmblem) {
            TypePersonnageFireEmblem typePersonnage = (TypePersonnageFireEmblem) typeCharacter;
            
            switch (typePersonnage) {
                case archimage :
                    Personnage athos = new Personnage(nom, organisation);
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
                case archer :
                    Personnage archer = new Personnage(nom, organisation);
                    /*archer.setComportementPersonnage(new ComportementArcher());
                    archer.setComportementDeplacement(new DeplacementArcher());
                    archer.setComportementCombat(new CombatAPiedAvecArc());*/
                    return archer;
                case chevalier :
                    
                case combattant :
                    Personnage combattant = new Personnage(nom, organisation);
                    combattant.setComportementPersonnage(new ComportementCombattant());
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
                case dark_druide :
                    Personnage nergal = new Personnage(nom, organisation);
                    nergal.setComportementPersonnage(new ComportementDarkDruide());
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
                case lord_eliwood :
                    Personnage eliwood = new Personnage(nom, organisation);
                    eliwood.setComportementPersonnage(new ComportementLord());
                    eliwood.setMoveBehaviour(new DeplacementLord());
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
                    return eliwood;
                case lord_hector :
                    Personnage hector = new Personnage(nom, organisation);
                    hector.setComportementPersonnage(new ComportementLord());
                    hector.setMoveBehaviour(new DeplacementLord());
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
                    Personnage lyn = new Personnage(nom, organisation);
                    lyn.setComportementPersonnage(new ComportementLord());
                    lyn.setMoveBehaviour(new DeplacementLord());
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
                default :
                    Personnage perso = new Personnage(nom, organisation);
                    /*perso.setComportementPersonnage(new ComportementArcher());
                    perso.setComportementDeplacement(new DeplacementArcher());
                    perso.setComportementCombat(new CombatAPiedAvecArc());*/
                    return perso;
                    
            }
            
        }
        return null;
	}
    
}
