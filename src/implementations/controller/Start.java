package implementations.controller;

import implementations.dbconnection.Connexion;
import implementations.dbconnection.DBConnection;
import implementations.views.View;
import implementations.views.Window;

import java.util.ArrayList;
import java.util.List;


public class Start extends Controller {
    
    private final List<Chapter> chapitres;
    private Window fenetre;
    
    public Start () {
        this.chapitres = new ArrayList<>();
    }

    public List<Chapter> getChapitres() {
        return chapitres;
    }
    
    public void addChapitre (Chapter chapitre) {
        this.chapitres.add(chapitre);
    }
    
    public void run() {
        this.pcsControlleurVue.firePropertyChange("menu", null, null);
    }
    
    public void chapitres () {
        this.pcsControlleurVue.firePropertyChange("chapitres", null, null);
    }
    
    public void chapitre (int choix) {
        this.fenetre = new Window("fire emblem");
        View.createVue(this.chapitres.get(choix), this.fenetre);
        this.chapitres.get(choix).setFenetre(this.fenetre);
        new RunControlleur(this.chapitres.get(choix)).start();
    }
    
    public void continuerChapitre () {
    	DBConnection connexionBD = new DBConnection();
    	Connexion connexion = new Connexion(connexionBD.getConnexionHSQL("fireemblem", "sa", ""));
    	Chapter chapitre = connexion.loadPartie();
    	connexionBD.fermerConnexionHSQL();
    	this.fenetre = new Window("fire emblem");
        View.createVue(chapitre, this.fenetre);
    	new RunControlleur(chapitre).start();
    }

	@Override
	protected void continuer() {
		// TODO Auto-generated method stub
		
	}
	
	public class RunControlleur extends Thread {
		
		private Controller controleur;
		
		public RunControlleur(Controller controleur) {
			this.controleur = controleur;
		}
		
		@Override
		public void run () {
			this.controleur.run();
			Start.this.run();
			fenetre.close();
		}
		
	}
    
}
