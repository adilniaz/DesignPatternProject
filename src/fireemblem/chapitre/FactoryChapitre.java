package fireemblem.chapitre;

import implementations.organisations.Organisation;
import abstracts_interfaces.CharacterAbstract;
import abstracts_interfaces.factories.characters.CreateCharactersFactoryAbstract;
import abstracts_interfaces.factories.gameplatforms.GameEnvironnementAbstract;
import abstracts_interfaces.factories.gameplatforms.GameEnvironnementFactoryAbstract;
import abstracts_interfaces.factories.gameplatforms.GamePlatformAbstract;
import fireemblem.Position;
import fireemblem.controlleur.Chapitre;
import fireemblem.objet.FactoryObjet;
import fireemblem.objet.TypeObjet;
import fireemblem.personnage.FactoryPersonnageFireEmblem;
import fireemblem.personnage.TypePersonnageFireEmblem;
import fireemblem.personnage.Personnage;
import fireemblem.plateauJeu.FactoryTerrain;
import fireemblem.plateauJeu.PlateauJeu;;

public class FactoryChapitre {
    
    public Chapitre createChapitre (Chapitres chapitres) {
    	GameEnvironnementFactoryAbstract factoryEnvironnementDeJeu = new FactoryTerrain();
        GameEnvironnementAbstract environnementJeu = factoryEnvironnementDeJeu.createGameEnvironnement();
        Organisation orga = new Organisation();
        CreateCharactersFactoryAbstract factoryPersonnage = new FactoryPersonnageFireEmblem();
        FactoryObjet factoryObjet = new FactoryObjet();
        
        switch (chapitres) {
            case blazing_sword :
                GamePlatformAbstract plateauDeJeu = environnementJeu.createGamePlatform();
                CharacterAbstract lyn = factoryPersonnage.createCharacter("Lyn", orga, TypePersonnageFireEmblem.lord_lyn);
                ((Personnage)lyn).setPosition(new Position(19, 9));
                ((Personnage)lyn).ajouterObjet(factoryObjet.createObjet("epee-fer", TypeObjet.epee_fer));
                CharacterAbstract eliwood = factoryPersonnage.createCharacter("Eliwood", orga, TypePersonnageFireEmblem.lord_eliwood);
                ((Personnage)eliwood).setPosition(new Position(19, 10));
                ((Personnage)eliwood).ajouterObjet(factoryObjet.createObjet("epee-fer", TypeObjet.epee_fer));
                CharacterAbstract hector = factoryPersonnage.createCharacter("Hector", orga, TypePersonnageFireEmblem.lord_hector);
                ((Personnage)hector).setPosition(new Position(19, 11));
                ((Personnage)hector).ajouterObjet(factoryObjet.createObjet("hache-fer", TypeObjet.hache_fer));
                CharacterAbstract naesala = factoryPersonnage.createCharacter("Naesala", orga, TypePersonnageFireEmblem.corbeau);
                ((Personnage)naesala).setPosition(new Position(19, 8));
                ((Personnage)naesala).ajouterObjet(factoryObjet.createObjet("bec", TypeObjet.bec));
                CharacterAbstract ina = factoryPersonnage.createCharacter("Ina", orga, TypePersonnageFireEmblem.dragon_rouge);
                ((Personnage)ina).setPosition(new Position(19, 7));
                ((Personnage)ina).ajouterObjet(factoryObjet.createObjet("souffle", TypeObjet.souffle));
                CharacterAbstract nasir = factoryPersonnage.createCharacter("Nasir", orga, TypePersonnageFireEmblem.dragon_blanc);
                ((Personnage)nasir).setPosition(new Position(19, 6));
                ((Personnage)nasir).ajouterObjet(factoryObjet.createObjet("souffle", TypeObjet.souffle));
                CharacterAbstract tibarn = factoryPersonnage.createCharacter("Tibarn", orga, TypePersonnageFireEmblem.faucon);
                ((Personnage)tibarn).setPosition(new Position(19, 5));
                ((Personnage)tibarn).ajouterObjet(factoryObjet.createObjet("bec", TypeObjet.bec));
                CharacterAbstract giffca = factoryPersonnage.createCharacter("Giffca", orga, TypePersonnageFireEmblem.lion);
                ((Personnage)giffca).setPosition(new Position(19, 4));
                ((Personnage)giffca).ajouterObjet(factoryObjet.createObjet("griffe", TypeObjet.griffe));
                CharacterAbstract caeghnis = factoryPersonnage.createCharacter("Caeghnis", orga, TypePersonnageFireEmblem.lion);
                ((Personnage)caeghnis).setPosition(new Position(19, 3));
                ((Personnage)caeghnis).ajouterObjet(factoryObjet.createObjet("griffe", TypeObjet.griffe));
                CharacterAbstract eirika = factoryPersonnage.createCharacter("Eirika", orga, TypePersonnageFireEmblem.lord_eirika);
                ((Personnage)eirika).setPosition(new Position(19, 2));
                ((Personnage)eirika).ajouterObjet(factoryObjet.createObjet("epee-fer", TypeObjet.epee_fer));
                CharacterAbstract ephraim = factoryPersonnage.createCharacter("Ephraim", orga, TypePersonnageFireEmblem.lord_ephraim);
                ((Personnage)ephraim).setPosition(new Position(19, 1));
                ((Personnage)ephraim).ajouterObjet(factoryObjet.createObjet("lance-fer", TypeObjet.lance_fer));
                CharacterAbstract roy = factoryPersonnage.createCharacter("Roy", orga, TypePersonnageFireEmblem.lord_roy);
                ((Personnage)roy).setPosition(new Position(19, 12));
                ((Personnage)roy).ajouterObjet(factoryObjet.createObjet("epee-fer", TypeObjet.epee_fer));
                CharacterAbstract merlinus = factoryPersonnage.createCharacter("Merlinus", orga, TypePersonnageFireEmblem.marchand);
                ((Personnage)merlinus).setPosition(new Position(19, 13));
                CharacterAbstract ike = factoryPersonnage.createCharacter("Ike", orga, TypePersonnageFireEmblem.ranger);
                ((Personnage)ike).setPosition(new Position(19, 14));
                ((Personnage)ike).ajouterObjet(factoryObjet.createObjet("epee-fer", TypeObjet.epee_fer));

                CharacterAbstract nergal = factoryPersonnage.createCharacter("Nergal", orga, TypePersonnageFireEmblem.dark_druide);
                ((Personnage)nergal).setPosition(new Position(0, 10));
                ((Personnage)nergal).ajouterObjet(factoryObjet.createObjet("Ereshkigal", TypeObjet.ereshkigal));
                CharacterAbstract combattant = factoryPersonnage.createCharacter("Combattant", orga, TypePersonnageFireEmblem.combattant);
                ((Personnage)combattant).setPosition(new Position(15, 10));
                ((Personnage)combattant).ajouterObjet(factoryObjet.createObjet("hache-fer", TypeObjet.hache_fer));

                ((PlateauJeu)plateauDeJeu).ajouterPersonnage(lyn);
                ((PlateauJeu)plateauDeJeu).ajouterPersonnage(eliwood);
                ((PlateauJeu)plateauDeJeu).ajouterPersonnage(hector);
                ((PlateauJeu)plateauDeJeu).ajouterEnnemie(nergal);
                ((PlateauJeu)plateauDeJeu).ajouterEnnemie(combattant);
                return new Chapitre("blazing sword", (PlateauJeu)plateauDeJeu, "battre le boss");
            case sword_of_seal :
            	GamePlatformAbstract plateauDeJeu1 = environnementJeu.createGamePlatform();

            	CharacterAbstract ashnard = factoryPersonnage.createCharacter("Ashnard", orga, TypePersonnageFireEmblem.king_daein);
                ((Personnage)ashnard).setPosition(new Position(0, 10));
                ((Personnage)ashnard).ajouterObjet(factoryObjet.createObjet("Alondite", TypeObjet.alondite));
                CharacterAbstract chevalier_noir = factoryPersonnage.createCharacter("Chevalier noir", orga, TypePersonnageFireEmblem.chevalier_noir);
                ((Personnage)chevalier_noir).setPosition(new Position(2, 10));
                ((Personnage)chevalier_noir).ajouterObjet(factoryObjet.createObjet("Alondite", TypeObjet.alondite));

                ((PlateauJeu)plateauDeJeu1).ajouterEnnemie(chevalier_noir);
                return new Chapitre("sword of seal", (PlateauJeu)plateauDeJeu1, "battre le boss");
            case path_of_radiance :
            	GamePlatformAbstract plateauDeJeu2 = environnementJeu.createGamePlatform();
                return new Chapitre("path of radiance", (PlateauJeu)plateauDeJeu2, "battre le boss");
        }
        return null;
    }
    
}
