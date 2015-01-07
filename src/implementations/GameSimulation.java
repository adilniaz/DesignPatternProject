package implementations;

import implementations.chapters.Chapters;
import implementations.chapters.ChapterFactory;
import implementations.controller.Start;
import implementations.dbconnection.DBConnection;
import implementations.dbconnection.DBCreation;
import implementations.views.View;
import implementations.views.Window;

public class GameSimulation {
    
    public void simulate () {
    	
    	DBConnection connection = new DBConnection();
    	DBCreation creationBase = new DBCreation(connection.getConnexionHSQL("fireemblem", "sa", ""));
    	creationBase.createBase();
    	connection.fermerConnexionHSQL();
        
        Window fenetre = new Window("Fire emblem");
        
        ChapterFactory factoryChapitre = new ChapterFactory();
        
        Start demarrage = new Start();
        View.createVue(demarrage, fenetre);
        demarrage.addChapitre(factoryChapitre.createChapitre(Chapters.blazing_sword));
        demarrage.addChapitre(factoryChapitre.createChapitre(Chapters.sword_of_seal));
        demarrage.addChapitre(factoryChapitre.createChapitre(Chapters.path_of_radiance));
        demarrage.run();
        
    }
    
}