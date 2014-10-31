package fireemblem;

import fireemblem.chapitre.Chapitres;
import fireemblem.chapitre.FactoryChapitre;
import fireemblem.controlleur.Demarrage;
import fireemblem.swing.Fenetre;
import fireemblem.swing.Vues;

public class SimulationJeu {
    
    public void simuler () {
        
        Fenetre fenetre = new Fenetre("Fire emblem");
        
        FactoryChapitre factoryChapitre = new FactoryChapitre();
        
        Demarrage demarrage = new Demarrage();
        Vues.createVue(demarrage, fenetre);
        demarrage.addChapitre(factoryChapitre.createChapitre(Chapitres.nergal));
        demarrage.run();
        
    }
    
}