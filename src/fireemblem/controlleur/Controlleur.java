package fireemblem.controlleur;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public abstract class Controlleur {
    
    protected PropertyChangeSupport pcsControlleurVue;

    public Controlleur() {
        this.pcsControlleurVue = new PropertyChangeSupport(this);
    }
    
    public void ajouterEcouteur(PropertyChangeListener ecouteur) {
        this.pcsControlleurVue.addPropertyChangeListener(ecouteur);
    }
    
}
