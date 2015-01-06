package implementations.controlleur;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Controlleur {
    
    protected PropertyChangeSupport pcsControlleurVue;
    protected boolean fin;
    protected int waitTime;

    public Controlleur() {
        this.pcsControlleurVue = new PropertyChangeSupport(this);
    }
    
    public Controlleur(Runnable runnable) {
        this.pcsControlleurVue = new PropertyChangeSupport(this);
        this.waitTime = 100;
    }
    
    public void run () {
    	while (!this.fin) {
    		this.continuer();
    		this.attendre(waitTime);
    	}
    }
    
    protected abstract void continuer ();
    
    protected void attendre (int milliseconde) {
        try {
            Thread.sleep(milliseconde);
        } catch (InterruptedException ex) {
            Logger.getLogger(Controlleur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void ajouterEcouteur(PropertyChangeListener ecouteur) {
        this.pcsControlleurVue.addPropertyChangeListener(ecouteur);
    }
    
}