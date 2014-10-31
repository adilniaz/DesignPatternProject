package fireemblem.swing;

import fireemblem.controlleur.Chapitre;
import fireemblem.controlleur.Combat;
import fireemblem.controlleur.Demarrage;

public class Vues {
    
    public static void createVue (Demarrage demarrage, Fenetre fenetre) {
        VueSwing_demarrage vueSwing_demarrage = new VueSwing_demarrage(demarrage, fenetre);
    }
    
    /*public static void createVue (PlateauDeJeu plateauDeJeu, Fenetre fenetre) {
        Panel panel = new Panel();
        VueSwing_plateauDeJeu vueSwing_plateauDeJeu = new VueSwing_plateauDeJeu(plateauDeJeu);
        vueSwing_plateauDeJeu.display(panel, fenetre);
        fenetre.ajouterPanel(panel);
    }*/
    
    public static void createVue (Combat combat, Fenetre fenetre) {
        VueSwing_combat swing_combat = new VueSwing_combat(combat, fenetre);
    }
    
    public static void createVue (Chapitre chapitre, Fenetre fenetre) {
        VueSwing_chapitre swing_chapitre = new VueSwing_chapitre(chapitre, fenetre);
    }
    
}
