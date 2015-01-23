package implementations.character;

import implementations.behaviour.GeneralBehaviour;
import implementations.behaviour.KnightBehaviour;
import implementations.behaviour.LordHappiaBehaviour;
import implementations.behaviour.LordLynBehaviour;
import implementations.behaviour.LordEliwoodBehaviour;
import implementations.behaviour.LordEquusBehaviour;
import implementations.behaviour.LordHectorBehaviour;
import implementations.behaviour.LordSparthaBehaviour;
import implementations.behaviour.WarriorBehaviour;
import implementations.combat.FighterFightBehaviour;
import implementations.combat.GeneralFightBehaviour;
import implementations.combat.KnightFightBehaviour;
import implementations.combat.LordEliwoodFightBehaviour;
import implementations.combat.LordEquusFightBehaviour;
import implementations.combat.LordHappiaFightBehavior;
import implementations.combat.LordHectorFightBehaviour;
import implementations.combat.LordLynFightBehaviour;
import implementations.combat.LordSparthaFightBehaviour;
import implementations.movement.GeneralMovement;
import implementations.movement.KnightMovement;
import implementations.movement.LordEquusMovement;
import implementations.movement.LordHappiaMovement;
import implementations.movement.LordMovement;
import implementations.movement.LordSparthaMovement;
import implementations.movement.WarriorMovement;
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
                case chevalier :
                	Character chevalier = new Character(nom, organisation);
                	chevalier.setComportementPersonnage(new KnightBehaviour());
                	chevalier.setMoveBehaviour(new KnightMovement());
                	chevalier.setBehaviour(new KnightFightBehaviour());
                    return chevalier;
                case combattant :
                    Character combattant = new Character(nom, organisation);
                    combattant.setComportementPersonnage(new WarriorBehaviour());
                    combattant.setMoveBehaviour(new WarriorMovement());
                    combattant.setBehaviour(new FighterFightBehaviour());
                    return combattant;
                case general :
                    Character general = new Character(nom, organisation);
                    general.setComportementPersonnage(new GeneralBehaviour());
                    general.setMoveBehaviour(new GeneralMovement());
                    general.setBehaviour(new GeneralFightBehaviour());
                    return general;
                case lord_eliwood :
                    Character eliwood = new Character(nom, organisation);
                    eliwood.setComportementPersonnage(new LordEliwoodBehaviour());
                    eliwood.getComportementPersonnage().setPromotedClass(FireEmblemCharacterType.lord_equus);
                    eliwood.setMoveBehaviour(new LordMovement());
                    eliwood.setBehaviour(new LordEliwoodFightBehaviour());
                    eliwood.setPourcentagePv(80);
                    eliwood.setPourcentagePuissance(45);
                    eliwood.setPourcentageCapacite(50);
                    eliwood.setPourcentageVitesse(40);
                    eliwood.setPourcentageChance(45);
                    eliwood.setPourcentageDefense(30);
                    eliwood.setPourcentageResistance(35);
                    eliwood.setExperience(90);
                    return eliwood;
                case lord_equus :
                    Character lord_equus = new Character(nom, organisation);
                    lord_equus.setComportementPersonnage(new LordEquusBehaviour());
                    lord_equus.setMoveBehaviour(new LordEquusMovement());
                    lord_equus.setBehaviour(new LordEquusFightBehaviour());
                    lord_equus.setPourcentagePv(80);
                    lord_equus.setPourcentagePuissance(45);
                    lord_equus.setPourcentageCapacite(50);
                    lord_equus.setPourcentageVitesse(40);
                    lord_equus.setPourcentageChance(45);
                    lord_equus.setPourcentageDefense(30);
                    lord_equus.setPourcentageResistance(35);
                    return lord_equus;
                case lord_happia :
                    Character lord_happia = new Character(nom, organisation);
                    lord_happia.setComportementPersonnage(new LordHappiaBehaviour());
                    lord_happia.setMoveBehaviour(new LordHappiaMovement());
                    lord_happia.setBehaviour(new LordHappiaFightBehavior());
                    lord_happia.setPourcentagePv(90);
                    lord_happia.setPourcentagePuissance(60);
                    lord_happia.setPourcentageCapacite(45);
                    lord_happia.setPourcentageVitesse(35);
                    lord_happia.setPourcentageChance(30);
                    lord_happia.setPourcentageDefense(50);
                    lord_happia.setPourcentageResistance(25);
                    return lord_happia;
                case lord_hector :
                    Character hector = new Character(nom, organisation);
                    hector.setComportementPersonnage(new LordHectorBehaviour());
                    hector.getComportementPersonnage().setPromotedClass(FireEmblemCharacterType.lord_happia);
                    hector.setMoveBehaviour(new LordMovement());
                    hector.setBehaviour(new LordHectorFightBehaviour());
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
                    lyn.setComportementPersonnage(new LordLynBehaviour());
                    lyn.getComportementPersonnage().setPromotedClass(FireEmblemCharacterType.lord_spatha);
                    lyn.setMoveBehaviour(new LordMovement());
                    lyn.setBehaviour(new LordLynFightBehaviour());
                    lyn.setPourcentagePv(70);
                    lyn.setPourcentagePuissance(40);
                    lyn.setPourcentageCapacite(60);
                    lyn.setPourcentageVitesse(60);
                    lyn.setPourcentageChance(55);
                    lyn.setPourcentageDefense(20);
                    lyn.setPourcentageResistance(30);
                    return lyn;
                case lord_spatha :
                    Character lord_spartha = new Character(nom, organisation);
                    lord_spartha.setComportementPersonnage(new LordSparthaBehaviour());
                    lord_spartha.setMoveBehaviour(new LordSparthaMovement());
                    lord_spartha.setBehaviour(new LordSparthaFightBehaviour());
                    lord_spartha.setPourcentagePv(70);
                    lord_spartha.setPourcentagePuissance(40);
                    lord_spartha.setPourcentageCapacite(60);
                    lord_spartha.setPourcentageVitesse(60);
                    lord_spartha.setPourcentageChance(55);
                    lord_spartha.setPourcentageDefense(20);
                    lord_spartha.setPourcentageResistance(30);
                    return lord_spartha;
                default :
                    Character perso = new Character(nom, organisation);
                    return perso;
                    
            }
            
        }
        return null;
	}
    
}
