package fireemblem.swing;

import fireemblem.controlleur.Chapitre;
import fireemblem.controlleur.Combat;
import fireemblem.controlleur.Demarrage;

public class Vues {
    
    public static void createVue (Demarrage demarrage, Fenetre fenetre) {
        new VueSwing_demarrage(demarrage, fenetre);
    }
    
    public static void createVue (Combat combat, Fenetre fenetre) {
        new VueSwing_combat(combat, fenetre);
    }
    
    public static void createVue (Chapitre chapitre, Fenetre fenetre) {
        new VueSwing_chapitre(chapitre, fenetre);
    }
    
}
