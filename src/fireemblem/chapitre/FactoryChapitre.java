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
            case nergal :
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
                return new Chapitre("nergal", (PlateauJeu)plateauDeJeu, "battre le boss");
        }
        return null;
    }
    
}
