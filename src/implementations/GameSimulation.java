package implementations;

import abstracts_interfaces.SimulationAbstract;
import abstracts_interfaces.factories.gameplatforms.GameEnvironnementAbstract;
import abstracts_interfaces.factories.gameplatforms.GameEnvironnementFactoryAbstract;
import abstracts_interfaces.factories.gameplatforms.GamePlatformAbstract;
import implementations.controller.Chapter;
import implementations.dbconnection.DBConnection;
import implementations.dbconnection.DBCreation;
import implementations.gameplatform.FactoryTerrain;
import implementations.gameplatform.GamePlatform;
import implementations.views.View;
import implementations.views.Window;

public class GameSimulation implements SimulationAbstract {
    
    public void run (Window window) {
    	
    	if (this.hasSavedGame()) {
    		
    	} else {
    		GameEnvironnementFactoryAbstract factoryEnvironnementDeJeu = new FactoryTerrain();
            GameEnvironnementAbstract environnementJeu = factoryEnvironnementDeJeu.createGameEnvironnement();
    		GamePlatformAbstract plateauDeJeu = environnementJeu.createGamePlatform();
            Chapter chapter = new Chapter("blazing sword", (GamePlatform)plateauDeJeu, "battre le boss");
            View.createVue(chapter, window);
            chapter.run();
    	}
    	
    	/*DBConnection connection = new DBConnection();
    	DBCreation creationBase = new DBCreation(connection.getConnexionHSQL("fireemblem", "sa", ""));
    	creationBase.createBase();
    	connection.closeHSQLConnection();
        
        Window window = new Window("Fire emblem");
        
        ChapterFactory chapterFactory = new ChapterFactory();
        
        Start start = new Start();
        View.createVue(start, window);
        start.addChapter(chapterFactory.createChapter(Chapters.blazing_sword));
        start.addChapter(chapterFactory.createChapter(Chapters.sword_of_seal));
        start.addChapter(chapterFactory.createChapter(Chapters.path_of_radiance));
        start.run();*/
        
    }
    
    private boolean hasSavedGame () {
    	return false;
    }
    
}