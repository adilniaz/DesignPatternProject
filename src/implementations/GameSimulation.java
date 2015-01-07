package implementations;

import implementations.chapters.Chapters;
import implementations.chapters.ChapterFactory;
import implementations.controller.Start;
import implementations.dbconnection.DBConnection;
import implementations.dbconnection.DBCreation;
import implementations.views.View;
import implementations.views.Window;

public class GameSimulation {
    
    public void runSimulation () {
    	
    	DBConnection connection = new DBConnection();
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
        start.run();
        
    }
    
}