package implementations.chapters;

import implementations.controller.Chapter;
import implementations.gameplatform.FactoryTerrain;
import implementations.gameplatform.GamePlatform;
import abstracts_interfaces.factories.gameplatforms.GameEnvironnementAbstract;
import abstracts_interfaces.factories.gameplatforms.GameEnvironnementFactoryAbstract;
import abstracts_interfaces.factories.gameplatforms.GamePlatformAbstract;

public class ChapterFactory {
    
    public Chapter createChapter (Chapters chapitres) {
    	GameEnvironnementFactoryAbstract factoryEnvironnementDeJeu = new FactoryTerrain(chapitres);
        GameEnvironnementAbstract environnementJeu = factoryEnvironnementDeJeu.createGameEnvironnement();
        
        switch (chapitres) {
            case blazing_sword :
                GamePlatformAbstract plateauDeJeu = environnementJeu.createGamePlatform();
                return new Chapter("blazing sword", (GamePlatform)plateauDeJeu, "battre le boss");
            case sword_of_seal :
            	GamePlatformAbstract plateauDeJeu1 = environnementJeu.createGamePlatform();
                return new Chapter("sword of seal", (GamePlatform)plateauDeJeu1, "battre le boss");
            case path_of_radiance :
            	GamePlatformAbstract plateauDeJeu2 = environnementJeu.createGamePlatform();
                return new Chapter("path of radiance", (GamePlatform)plateauDeJeu2, "battre le boss");
        }
        return null;
    }
    
}
