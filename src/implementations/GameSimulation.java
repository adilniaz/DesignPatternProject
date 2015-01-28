package implementations;

import abstracts_interfaces.SimulationAbstract;
import abstracts_interfaces.factories.gameplatforms.GameEnvironnementAbstract;
import abstracts_interfaces.factories.gameplatforms.GameEnvironnementFactoryAbstract;
import abstracts_interfaces.factories.gameplatforms.GamePlatformAbstract;
import implementations.controller.Chapter;
import implementations.gameplatform.FactoryTerrain;
import implementations.gameplatform.GamePlatform;
import implementations.organizations.Organization;
import implementations.views.View;
import implementations.views.Window;

public class GameSimulation implements SimulationAbstract {
    
    public void run (Window window) {
    	
    	GameEnvironnementFactoryAbstract factoryEnvironnementDeJeu = new FactoryTerrain();
        GameEnvironnementAbstract environnementJeu = factoryEnvironnementDeJeu.createGameEnvironnement();
		GamePlatformAbstract plateauDeJeu = environnementJeu.createGamePlatform();
        Chapter chapter = new Chapter("blazing sword", (GamePlatform)plateauDeJeu, new Organization(), "battre le boss");
        View.createVue(chapter, window);
        chapter.run();
        
    }
    
}