package fireemblem.controlleur;

import java.util.ArrayList;
import java.util.List;

import fireemblem.connexionBD.Connexion;
import fireemblem.connexionBD.ConnexionBD;
import fireemblem.swing.Fenetre;
import fireemblem.swing.Vues;

public class Demarrage extends Controlleur {
    
    private final List<Chapitre> chapitres;
    private Fenetre fenetre;
    
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
        this.fenetre = new Fenetre("fire emblem");
        Vues.createVue(this.chapitres.get(choix), this.fenetre);
        this.chapitres.get(choix).setFenetre(this.fenetre);
        new RunControlleur(this.chapitres.get(choix)).start();
    }
    
    public void continuerChapitre () {
    	ConnexionBD connexionBD = new ConnexionBD();
    	Connexion connexion = new Connexion(connexionBD.getConnexionHSQL("fireemblem", "sa", ""));
    	Chapitre chapitre = connexion.loadPartie();
    	connexionBD.fermerConnexionHSQL();
    	this.fenetre = new Fenetre("fire emblem");
        Vues.createVue(chapitre, this.fenetre);
    	new RunControlleur(chapitre).start();
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
			fenetre.close();
		}
		
	}
    
}
