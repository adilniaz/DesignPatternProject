package implementations.keyevent;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;


public class MenuKeyAction implements KeyListener  {
    
    private Border oldBorder;
    private Border newBorder;
    private JComponent[] components;
    protected PropertyChangeSupport pcsControlleurVue;
    private int cursor;
    
    public MenuKeyAction () {
    	this.cursor = 0;
        this.pcsControlleurVue = new PropertyChangeSupport(this);
    }
    
    public MenuKeyAction (JComponent[] components) {
        this.components = components;
        this.cursor = 0;
        this.oldBorder = this.components[this.cursor].getBorder();
        this.newBorder = new LineBorder(Color.yellow, 10);
        this.components[this.cursor].setBorder(this.newBorder);
        this.pcsControlleurVue = new PropertyChangeSupport(this);
    }
    
    public void init (JComponent[] components) {
    	this.components = components;
        this.oldBorder = this.components[this.cursor].getBorder();
        this.newBorder = new LineBorder(Color.yellow, 10);
        this.components[this.cursor].setBorder(this.newBorder);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        System.out.println("Code touche pressée : " + ke.getKeyCode() + " - caractère touche pressée : " + ke.getKeyChar());
        switch (ke.getKeyCode()) {
            case ButtonCode.BAS :
            	if (this.cursor < this.components.length -1) {
            		this.components[this.cursor].setBorder(this.oldBorder);
            		this.cursor++;
                    this.components[this.cursor].setBorder(this.newBorder);
            	}
                break;
            case ButtonCode.DROITE :
                break;
            case ButtonCode.GAUCHE :
                break;
            case ButtonCode.HAUT :
            	if (this.cursor > 0) {
            		this.components[this.cursor].setBorder(this.oldBorder);
            		this.cursor--;
                    this.components[this.cursor].setBorder(this.newBorder);
            	}
                break;
            case ButtonCode.ACTION :
            	((JButton)this.components[this.cursor]).getActionListeners()[0].actionPerformed(null);
                //this.pcsControlleurVue.firePropertyChange("action", null, null);
                break;
            case ButtonCode.ANNULATION :
                this.pcsControlleurVue.firePropertyChange("annulation", null, null);
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