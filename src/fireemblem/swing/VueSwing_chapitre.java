package fireemblem.swing;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Popup;
import javax.swing.PopupFactory;

import abstracts_interfaces.CharacterAbstract;
import abstracts_interfaces.factories.gameplatforms.ZoneAbstract;
import fireemblem.Position;
import fireemblem.controlleur.Chapitre;
import fireemblem.controlleur.Chapitre.Tour;
import fireemblem.controlleur.Chapitre.menu;
import fireemblem.keyevent.KeyAction;
import fireemblem.keyevent.KeyDispatcher;
import fireemblem.media.map.ImageMap;
import fireemblem.media.personnage.ImagePersonnage;
import fireemblem.objet.Objet;
import fireemblem.personnage.Personnage;
import fireemblem.plateauJeu.Case;

public class VueSwing_chapitre {
    
    private final Chapitre chapitre;
    private final Fenetre fenetre;
    
    private final KeyAction keyAction;
    private Popup fenetreObjectif;
    private Popup fenetreTerrain;
    private Popup fenetrePerso;
    private Popup fenetreMenu;
    private Popup fenetreActionPerso;
    private final PopupFactory popupFactory;
    private PanelCentre panelCentre;
    private List<ZoneAbstract> zonesSelectionner;
    private Personnage persoEnCours;
    
    private Camera camera;
    private Position centerPosition;
    
    public VueSwing_chapitre (Chapitre chapitre, Fenetre fenetre) {
        this.chapitre = chapitre;
        this.fenetre = fenetre;
        this.keyAction = new KeyAction();
        this.popupFactory = PopupFactory.getSharedInstance();
        this.camera = new Camera();
        this.centerPosition = new Position(10, 10);
        
        this.keyAction.ajouterEcouteur(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (evt.getPropertyName().equals("cursorPosition")) {
                    changeCursorPosition((Position) evt.getOldValue(), (Position) evt.getNewValue());
                } else if (evt.getPropertyName().equals("action")) {
                    action((Position) evt.getOldValue());
                } else if (evt.getPropertyName().equals("annulation")) {
                    annulation();
                }
            }
        });
        this.chapitre.ajouterEcouteur(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (evt.getPropertyName().equals(VueSwing_chapitre.this.chapitre.AFFICHE_MAP)) {
                    afficheMap();
                } else if (evt.getPropertyName().equals(VueSwing_chapitre.this.chapitre.AFFICHE_ARMES_PERSO)) {
                    afficheArmesPerso((Personnage) evt.getOldValue());
                } else if (evt.getPropertyName().equals(VueSwing_chapitre.this.chapitre.AFFICHE_ATTAQUE_DISPONIBLE)) {
                    afficheAttaqueDisponible((List<ZoneAbstract>) evt.getOldValue());
                } else if (evt.getPropertyName().equals(VueSwing_chapitre.this.chapitre.AFFICHE_DEPLACEMENT_DISPONIBLE)) {
                    afficheDeplacementDisponible ((List<ZoneAbstract>) evt.getOldValue());
                } else if (evt.getPropertyName().equals(VueSwing_chapitre.this.chapitre.AFFICHE_MENU)) {
                    afficheMenu((menu[]) evt.getOldValue());
                } else if (evt.getPropertyName().equals(VueSwing_chapitre.this.chapitre.AFFICHE_ACTION_PERSO)) {
                    afficheActionPerso((Chapitre.actionPerso[]) evt.getOldValue());
                } else if (evt.getPropertyName().equals(VueSwing_chapitre.this.chapitre.CHANGE_TOUR)) {
                	afficherTour((Tour) evt.getOldValue());
                } else if (evt.getPropertyName().equals(VueSwing_chapitre.this.chapitre.DEPLACE_PERSO)) {
                    DeplacePerso((Personnage) evt.getOldValue(), (Position) evt.getNewValue());
                } else if (evt.getPropertyName().equals(VueSwing_chapitre.this.chapitre.EFFACE_ATTAQUE_DISPONIBLE)) {
                    effaceAttaqueDisponible((List<ZoneAbstract>) evt.getOldValue());
                } else if (evt.getPropertyName().equals(VueSwing_chapitre.this.chapitre.EFFACE_DEPLACEMENT_DISPONIBLE)) {
                    effaceDeplacementDisponible((List<ZoneAbstract>) evt.getOldValue());
                }
            }
        });
    }
    
    public void afficheMap () {
        //Panel panel = new Panel();
        this.panelCentre = new PanelCentreParcelle(20, 20, 1250, 650);
        Map<ZoneAbstract, JComponent> componentZone = new HashMap<>();
        for (ZoneAbstract zone : this.chapitre.getPlateauDeJeu().getZones()) {
            Case c = (Case) zone;
            PanelImage panelImage = new PanelImage(ImageMap.getImageFromZone(zone), 60, 30);
            this.panelCentre.ajouterViewBackground(new ViewComponent(panelImage), c.getPosition().getPositionX(), c.getPosition().getPositionY());
            componentZone.put(zone, panelImage);
        }
        for (CharacterAbstract perso : this.chapitre.getPlateauDeJeu().getPersonnages()) {
            Personnage p = (Personnage) perso;
            this.panelCentre.ajouterViewContent(new ViewComponent(new PanelDrawing(Color.BLUE, PanelDrawing.drawingType.circle, 60, 30)), p.getPosition().getPositionX(), p.getPosition().getPositionY(), 1);
            this.panelCentre.ajouterViewContent(new ViewComponent(new PanelImage(ImagePersonnage.getImageIconMapFromPersonnage(perso), 60, 30)), p.getPosition().getPositionX(), p.getPosition().getPositionY(), 2);
        }
        for (CharacterAbstract perso : this.chapitre.getPlateauDeJeu().getEnnemies()) {
            Personnage p = (Personnage) perso;
            this.panelCentre.ajouterViewContent(new ViewComponent(new PanelDrawing(Color.RED, PanelDrawing.drawingType.circle, 60, 30)), p.getPosition().getPositionX(), p.getPosition().getPositionY(), 1);
            this.panelCentre.ajouterViewContent(new ViewComponent(new PanelImage(ImagePersonnage.getImageIconMapFromPersonnage(perso), 60, 30)), p.getPosition().getPositionX(), p.getPosition().getPositionY(), 2);
        }
        //panel.changerCentre(this.panelCentre, 20, 20, 1250, 650);
        Panel panel = this.camera.getCameraView(this.centerPosition, this.panelCentre);
        this.keyAction.init(this.chapitre.getPlateauDeJeu(), componentZone);
        this.keyAction.setCursorPosition(this.centerPosition);
        fenetre.addKeyBoardManager(new KeyDispatcher(this.keyAction));
        this.fenetre.ajouterPanel(panel);
    }
    
    private void afficheMenu (menu menu[]) {
        JPanel panel = new JPanel (new GridLayout(menu.length, 1));
        for (menu m : menu) {
            JButton bouton = new JButton(m.name());
            bouton.addActionListener(new boutonMenu(m));
            panel.add(bouton);
        }
        this.fenetreMenu = this.popupFactory.getPopup(this.fenetre, panel, 100, 250);
        this.fenetreMenu.show();
    }
    
    private void afficheActionPerso (Chapitre.actionPerso actions[]) {
        JPanel panel = new JPanel (new GridLayout(actions.length, 1));
        for (Chapitre.actionPerso action : actions) {
            JButton bouton = new JButton(action.name());
            bouton.addActionListener(new boutonActionPerso(action));
            panel.add(bouton);
        }
        this.fenetreActionPerso = this.popupFactory.getPopup(this.fenetre, panel, 100, 250);
        this.fenetreActionPerso.show();
    }
    
    private void changeCursorPosition (Position oldPosition, Position newPosition) {
        if (newPosition.getPositionX() == this.centerPosition.getPositionX() + this.camera.getHeight()) {
        	this.centerPosition.setPositionX(this.centerPosition.getPositionX()+1);
        	Panel panel = this.camera.getCameraView(this.centerPosition, this.panelCentre);
        	this.fenetre.ajouterPanel(panel);
        } else if (newPosition.getPositionY() == this.centerPosition.getPositionY() + this.camera.getWidth()) {
        	this.centerPosition.setPositionY(this.centerPosition.getPositionY()+1);
        	Panel panel = this.camera.getCameraView(this.centerPosition, this.panelCentre);
        	this.fenetre.ajouterPanel(panel);
        } else if (newPosition.getPositionX() == this.centerPosition.getPositionX() - this.camera.getHeight()) {
        	this.centerPosition.setPositionX(this.centerPosition.getPositionX()-1);
        	Panel panel = this.camera.getCameraView(this.centerPosition, this.panelCentre);
        	this.fenetre.ajouterPanel(panel);
        } else if (newPosition.getPositionY() == this.centerPosition.getPositionY() - this.camera.getWidth()) {
        	this.centerPosition.setPositionY(this.centerPosition.getPositionY()-1);
        	Panel panel = this.camera.getCameraView(this.centerPosition, this.panelCentre);
        	this.fenetre.ajouterPanel(panel);
        }
    	JLabel label = new JLabel(this.chapitre.getObjectif());
        this.fenetreObjectif = this.popupFactory.getPopup(this.fenetre, label, 1200, 80);
        this.fenetreObjectif.show();

        JPanel panelTerrain = new JPanel (new GridLayout(2, 1));
        for (ZoneAbstract zone : this.chapitre.getPlateauDeJeu().getZones()) {
            Case c = (Case) zone;
            if (c.getPosition().equals(newPosition)) {
                JLabel labelCase = new JLabel(c.getNom());
                panelTerrain.add(labelCase);

                JPanel panelTerrainDesc = new JPanel (new GridLayout(2, 1));
                JLabel labelCaseDef = new JLabel("DEF   " + c.getDef());
                JLabel labelCaseEsq = new JLabel("ESQ   " + c.getEsq());
                panelTerrainDesc.add(labelCaseDef);
                panelTerrainDesc.add(labelCaseEsq);
                panelTerrain.add(panelTerrainDesc);
                break;
            }
        }
        this.fenetreTerrain = this.popupFactory.getPopup(this.fenetre, panelTerrain, 100, 600);
        this.fenetreTerrain.show();

        for (CharacterAbstract perso : this.chapitre.getPlateauDeJeu().getPersonnages()) {
            Personnage p = (Personnage) perso;
            if (p.getPosition().equals(newPosition)) {
                this.afficheFenetrePerso(p);
                this.panelCentre.ajouterViewContent(new ViewComponent(new PanelImage(ImagePersonnage.getImageIconMapFocusFromPersonnage(perso), 60, 30)), p.getPosition().getPositionX(), p.getPosition().getPositionY(), 2);
                break;
            } else if (p.getPosition().equals(oldPosition)) {
                this.fenetrePerso.hide();
                this.panelCentre.ajouterViewContent(new ViewComponent(new PanelImage(ImagePersonnage.getImageIconMapFromPersonnage(perso), 60, 30)), p.getPosition().getPositionX(), p.getPosition().getPositionY(), 2);
                break;
            }
        }

        for (CharacterAbstract perso : this.chapitre.getPlateauDeJeu().getEnnemies()) {
            Personnage p = (Personnage) perso;
            if (p.getPosition().equals(newPosition)) {
                this.afficheFenetrePerso(p);
                break;
            } else if (p.getPosition().equals(oldPosition)) {
                this.fenetrePerso.hide();
                break;
            }
        }
    }
    
    private void afficherTour (Tour tour) {
    	new AfficheTour(tour).start();
    }
    
    private void afficheFenetrePerso (Personnage perso) {
        Box boxPerso = Box.createHorizontalBox();
        PanelImage panelImage = new PanelImage(ImagePersonnage.getImageMenuFromPersonnage(perso), 70, 50);
        boxPerso.add(panelImage);
        Box boxStat = Box.createVerticalBox();
        JLabel labelPersoName = new JLabel(perso.getName());
        boxStat.add(labelPersoName);
        JLabel labelPersoPV = new JLabel("PV  " + perso.getPv() + "/" + perso.getPvMax());
        boxStat.add(labelPersoPV);
        boxPerso.add(boxStat);
        this.fenetrePerso = this.popupFactory.getPopup(this.fenetre, boxPerso, 100, 80);
        this.fenetrePerso.show();
    }
    
    private void DeplacePerso (CharacterAbstract perso, Position olPosition) {
        Personnage p = (Personnage) perso;
        this.panelCentre.ajouterViewContent(null, olPosition.getPositionX(), olPosition.getPositionY(), 1);
        this.panelCentre.ajouterViewContent(null, olPosition.getPositionX(), olPosition.getPositionY(), 2);
        this.panelCentre.ajouterViewContent(new ViewComponent(new PanelDrawing(Color.BLUE, PanelDrawing.drawingType.circle, 60, 30)), p.getPosition().getPositionX(), p.getPosition().getPositionY(), 1);
        this.panelCentre.ajouterViewContent(new ViewComponent(new PanelImage(ImagePersonnage.getImageIconMapFromPersonnage(perso), 60, 30)), p.getPosition().getPositionX(), p.getPosition().getPositionY(), 2);
    }
    
    private void action (Position pos) {
        this.chapitre.action(pos);
    }
    
    private void annulation () {
        this.chapitre.annulation();
    }
    
    private void afficheDeplacementDisponible (List<ZoneAbstract> zones) {
        for (ZoneAbstract zone : zones) {
            Case c = (Case) zone;
            this.panelCentre.ajouterViewContent(new ViewComponent(new ColoredCase(60, 30, new Color(0, 0, 255, 50))), c.getPosition().getPositionX(), c.getPosition().getPositionY(), 0);
        }
    }
    
    private void effaceDeplacementDisponible (List<ZoneAbstract> zones) {
        for (ZoneAbstract zone : zones) {
            Case c = (Case) zone;
            this.panelCentre.ajouterViewContent(null, c.getPosition().getPositionX(), c.getPosition().getPositionY(), 0);
        }
    }
    
    private void afficheArmesPerso (CharacterAbstract personnage) {
        Personnage perso = (Personnage) personnage;
        JPanel panel = new JPanel (new GridLayout(perso.getObjets().length, 1));
        for (Objet obj : perso.getObjets()) {
            if (obj != null) {
                JButton bouton = new JButton(obj.getName());
                bouton.addActionListener(new boutonCombat());
                panel.add(bouton);
            }
        }
        this.fenetreActionPerso = this.popupFactory.getPopup(this.fenetre, panel, 100, 250);
        this.fenetreActionPerso.show();
        this.chapitre.simuleCombat();
    }
    
    private void afficheAttaqueDisponible (List<ZoneAbstract> zones) {
        for (ZoneAbstract zone : zones) {
            Case c = (Case) zone;
            this.panelCentre.ajouterViewContent(new ViewComponent(new ColoredCase(60, 30, new Color(255, 0, 0, 50))), c.getPosition().getPositionX(), c.getPosition().getPositionY(), 0);
        }
    }
    
    private void effaceAttaqueDisponible (List<ZoneAbstract> zones) {
        for (ZoneAbstract zone : zones) {
            Case c = (Case) zone;
            this.panelCentre.ajouterViewContent(null, c.getPosition().getPositionX(), c.getPosition().getPositionY(), 0);
        }
    }
    
    public class AfficheTour extends Thread {
    	
    	private Tour tour;
    	
    	public AfficheTour (Tour tour) {
    		this.tour = tour;
    	}
    	
    	public void run () {
    		Popup popup;
        	String affichage = "";
        	switch (tour) {
        		case perso:
        			affichage = "Tour perso";
        			break;
        		case ennemie:
        			affichage = "Tour adverse";
        			break;
        		case annexes:
        			affichage = "Tour annexe";
        			break;
        	}
        	JLabel label = new JLabel(affichage);
        	popup = popupFactory.getPopup(fenetre, label, 100, 100);
        	popup.show();
        	this.attendre(2000);
        	popup.hide();
        	chapitre.continuer();
    	}
    	
        public synchronized void attendre (int time) {
            try {
                this.wait(time);
            } catch (InterruptedException ex) {
                Logger.getLogger(VueSwing_combat.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private class boutonMenu implements ActionListener {
        
        private final menu menu;
        
        public boutonMenu (menu menu) {
            this.menu = menu;
        }
        
        @Override
        public void actionPerformed (ActionEvent event) {
            chapitre.menu(this.menu);
        }
    }
    
    private class boutonActionPerso implements ActionListener {
        
        private final Chapitre.actionPerso actionPerso;
        
        public boutonActionPerso (Chapitre.actionPerso actionPerso) {
            this.actionPerso = actionPerso;
        }
        
        @Override
        public void actionPerformed (ActionEvent event) {
            chapitre.actionPerso(this.actionPerso);
        }
    }
    
    private class boutonCombat implements ActionListener {
        
        @Override
        public void actionPerformed (ActionEvent event) {
            chapitre.combat();
        }
    }
    
}
