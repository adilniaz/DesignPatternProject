package implementations.keyevent;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SimpleKeyAction implements KeyListener  {
    
    protected PropertyChangeSupport pcsControlleurVue;
    
    public SimpleKeyAction () {
        this.pcsControlleurVue = new PropertyChangeSupport(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        switch (ke.getKeyCode()) {
            case ButtonCode.BAS :
            	this.pcsControlleurVue.firePropertyChange("down", null, null);
                break;
            case ButtonCode.DROITE :
            	this.pcsControlleurVue.firePropertyChange("right", null, null);
                break;
            case ButtonCode.GAUCHE :
            	this.pcsControlleurVue.firePropertyChange("left", null, null);
                break;
            case ButtonCode.HAUT :
            	this.pcsControlleurVue.firePropertyChange("up", null, null);
                break;
            case ButtonCode.ACTION :
                this.pcsControlleurVue.firePropertyChange("action", null, null);
                break;
            case ButtonCode.ANNULATION :
                this.pcsControlleurVue.firePropertyChange("annulation", null, null);
                break;
            case ButtonCode.R :
                this.pcsControlleurVue.firePropertyChange("info", null, null);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    
    public void ajouterEcouteur(PropertyChangeListener ecouteur) {
        this.pcsControlleurVue.addPropertyChangeListener(ecouteur);
    }
}