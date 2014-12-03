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
        //this.chapitres.get(choix).run();
        new RunControlleur(this.chapitres.get(choix)).start();
    }

	@Override
	protected void continuer() {
		// TODO Auto-generated method stub
		
	}
	
	public class RunControlleur extends Thread {
		
		private Controlleur controleur;
		
		public RunControlleur(Controlleur controleur) {
			this.controleur = controleur;
		}
		
		@Override
		public void run () {
			this.controleur.run();
			Demarrage.this.run();
		}
		
	}
    
}
