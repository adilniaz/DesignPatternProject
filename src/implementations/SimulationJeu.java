package implementations;

import implementations.chapitre.Chapitres;
import implementations.chapitre.FactoryChapitre;
import implementations.connexionBD.ConnexionBD;
import implementations.connexionBD.CreationBase;
import implementations.controlleur.Demarrage;
import implementations.swing.Fenetre;
import implementations.swing.Vues;

public class SimulationJeu {
    
    public void simuler () {
    	
    	ConnexionBD connection = new ConnexionBD();
    	CreationBase creationBase = new CreationBase(connection.getConnexionHSQL("fireemblem", "sa", ""));
    	creationBase.createBase();
    	connection.fermerConnexionHSQL();
        
        Fenetre fenetre = new Fenetre("Fire emblem");
        
        FactoryChapitre factoryChapitre = new FactoryChapitre();
        
        Demarrage demarrage = new Demarrage();
        Vues.createVue(demarrage, fenetre);
        demarrage.addChapitre(factoryChapitre.createChapitre(Chapitres.blazing_sword));
        demarrage.addChapitre(factoryChapitre.createChapitre(Chapitres.sword_of_seal));
        demarrage.addChapitre(factoryChapitre.createChapitre(Chapitres.path_of_radiance));
        demarrage.run();
        
    }
    
}