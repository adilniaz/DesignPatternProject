package fireemblem.controlleur;

import java.util.ArrayList;
import java.util.List;

import fireemblem.swing.Fenetre;
import fireemblem.swing.Vues;

public class Demarrage extends Controlleur {
    
    private final List<Chapitre> chapitres;
    
    public Demarrage () {
        this.chapitres = new ArrayList<>();
    }

    public List<Chapitre> getChapitres() {
        return chapitres;
    }
    
    public void addChapitre (Chapitre chapitre) {
        this.chapitres.add(chapitre);
    }
    
    public void run() {
        this.pcsControlleurVue.firePropertyChange("menu", null, null);
    }
    
    public void chapitres () {
        this.pcsControlleurVue.firePropertyChange("chapitres", null, null);
    }
    
    public void chapitre (int choix) {
        Fenetre fenetre = new Fenetre("fire emblem");
        Vues.createVue(this.chapitres.get(choix), fenetre);
        this.chapitres.get(choix).setFenetre(fenetre);
        this.chapitres.get(choix).run();
    }
    
}
