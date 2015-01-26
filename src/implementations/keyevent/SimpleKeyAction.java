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
        System.out.println("Code touche pressée : " + ke.getKeyCode() + " - caractère touche pressée : " + ke.getKeyChar());
        switch (ke.getKeyCode()) {
            case ButtonCode.BAS :
                break;
            case ButtonCode.DROITE :
                break;
            case ButtonCode.GAUCHE :
                break;
            case ButtonCode.HAUT :
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