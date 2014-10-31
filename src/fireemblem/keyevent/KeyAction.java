package fireemblem.keyevent;

import fireemblem.Position;
import fireemblem.plateauJeu.Case;
import fireemblem.plateauJeu.PlateauJeu;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Map;

import javax.swing.JComponent;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import abstracts_interfaces.factories.gameplatforms.ZoneAbstract;

public class KeyAction implements KeyListener  {
    
    private Border oldBorder;
    private Border newBorder;
    private PlateauJeu plateauDeJeu;
    private Map<ZoneAbstract, JComponent> componentZone;
    private ZoneAbstract currentZone;
    protected PropertyChangeSupport pcsControlleurVue;
    
    public KeyAction () {
        this.pcsControlleurVue = new PropertyChangeSupport(this);
    }
    
    public KeyAction (PlateauJeu plateauDeJeu, Map<ZoneAbstract, JComponent> componentZone) {
        this.plateauDeJeu = plateauDeJeu;
        this.componentZone = componentZone;
        this.currentZone = this.plateauDeJeu.getZones().get(0);
        this.oldBorder = this.componentZone.get(this.currentZone).getBorder();
        this.newBorder = new LineBorder(Color.yellow, 10);
        this.componentZone.get(this.currentZone).setBorder(this.newBorder);
        this.pcsControlleurVue = new PropertyChangeSupport(this);
    }
    
    public void init (PlateauJeu plateauDeJeu, Map<ZoneAbstract, JComponent> componentZone) {
        this.plateauDeJeu = plateauDeJeu;
        this.componentZone = componentZone;
        this.currentZone = this.plateauDeJeu.getZones().get(0);
        this.oldBorder = this.componentZone.get(this.currentZone).getBorder();
        this.newBorder = new LineBorder(Color.yellow, 10);
        this.componentZone.get(this.currentZone).setBorder(this.newBorder);
    }
    
    public ZoneAbstract getZoneByPosition (Position p) {
        for (ZoneAbstract zone : this.plateauDeJeu.getZones()) {
            Case tmp = (Case) zone;
            if (tmp.getPosition().equals(p)) {
                return tmp;
            }
        }
        return null;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }
    
    private void modifyCursor (Case c, ZoneAbstract z) {
        if (z != null) {
            Position oldPosition = c.getPosition();
            this.componentZone.get(this.currentZone).setBorder(this.oldBorder);
            this.currentZone = z;
            this.componentZone.get(this.currentZone).setBorder(this.newBorder);
            Position newPosition = ((Case) this.currentZone).getPosition();
            this.pcsControlleurVue.firePropertyChange("cursorPosition", oldPosition, newPosition);
        }
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        System.out.println("Code touche pressée : " + ke.getKeyCode() + " - caractère touche pressée : " + ke.getKeyChar());
        switch (ke.getKeyCode()) {
            case CodeBouton.BAS :
                Case c1 = (Case) this.currentZone;
                ZoneAbstract zone1 = this.getZoneByPosition(new Position(c1.getPosition().getPositionX()+1, c1.getPosition().getPositionY()));
                this.modifyCursor(c1, zone1);
                break;
            case CodeBouton.DROITE :
                Case c2 = (Case) this.currentZone;
                ZoneAbstract zone2 = this.getZoneByPosition(new Position(c2.getPosition().getPositionX(), c2.getPosition().getPositionY()+1));
                this.modifyCursor(c2, zone2);
                break;
            case CodeBouton.GAUCHE :
                Case c3 = (Case) this.currentZone;
                ZoneAbstract zone3 = this.getZoneByPosition(new Position(c3.getPosition().getPositionX(), c3.getPosition().getPositionY()-1));
                this.modifyCursor(c3, zone3);
                break;
            case CodeBouton.HAUT :
                Case c4 = (Case) this.currentZone;
                ZoneAbstract zone4 = this.getZoneByPosition(new Position(c4.getPosition().getPositionX()-1, c4.getPosition().getPositionY()));
                this.modifyCursor(c4, zone4);
                break;
            case CodeBouton.ACTION :
                Case c5 = (Case) this.currentZone;
                this.pcsControlleurVue.firePropertyChange("action", c5.getPosition(), null);
                break;
            case CodeBouton.ANNULATION :
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
