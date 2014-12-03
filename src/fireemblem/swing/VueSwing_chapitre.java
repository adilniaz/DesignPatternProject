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
import javax.swing.JTable;
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
    
    private List<ZoneAbstract> zonesSelectionner;
    private Personnage persoEnCours;
    ViewComponent components[][][];
    
    private Camera camera;
    private Position centerPosition;
    Panel panel;
    int width = 125;
    int height = 65;
    
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
                    deplacePerso((Personnage) evt.getOldValue(), (Position) evt.getNewValue());
                } else if (evt.getPropertyName().equals(VueSwing_chapitre.this.chapitre.EFFACE_ATTAQUE_DISPONIBLE)) {
                    effaceAttaqueDisponible((List<ZoneAbstract>) evt.getOldValue());
                } else if (evt.getPropertyName().equals(VueSwing_chapitre.this.chapitre.EFFACE_DEPLACEMENT_DISPONIBLE)) {
                    effaceDeplacementDisponible((List<ZoneAbstract>) evt.getOldValue());
                } else if (evt.getPropertyName().equals(VueSwing_chapitre.this.chapitre.ENLEVE_PERSO)) {
                    enlevePerso((Personnage) evt.getOldValue());
                } else if (evt.getPropertyName().equals(VueSwing_chapitre.this.chapitre.GAME_OVER)) {
                    gameOver();
                } else if (evt.getPropertyName().equals(VueSwing_chapitre.this.chapitre.STATUS)) {
                    status();
                } else if (evt.getPropertyName().equals(VueSwing_chapitre.this.chapitre.UNITES)) {
                    unites();
                } else if (evt.getPropertyName().equals(VueSwing_chapitre.this.chapitre.VICTOIRE)) {
                    victoire();
                }
            }
        });
    }
    
    public void afficheMap () {
        Map<ZoneAbstract, JComponent> componentZone = new HashMap<>();
        components = new ViewComponent[20][20][3];
        for (ZoneAbstract zone : this.chapitre.getPlateauDeJeu().getZones()) {
            Case c = (Case) zone;
            PanelImage panelImage = new PanelImage(ImageMap.getImageFromZone(zone), width, height);
            componentZone.put(zone, panelImage);
            components[c.getPosition().getPositionX()][c.getPosition().getPositionY()][0] = new ViewComponent(panelImage);
        }
        for (CharacterAbstract perso : this.chapitre.getPlateauDeJeu().getPersonnages()) {
            Personnage p = (Personnage) perso;
            components[p.getPosition().getPositionX()][p.getPosition().getPositionY()][1] = new ViewComponent(new PanelDrawing(Color.BLUE, PanelDrawing.drawingType.circle, width, height));
            components[p.getPosition().getPositionX()][p.getPosition().getPositionY()][2] = new ViewComponent(new PanelImage(ImagePersonnage.getImageIconMapFromPersonnage(perso), width, height));
        }
        for (CharacterAbstract perso : this.chapitre.getPlateauDeJeu().getEnnemies()) {
            Personnage p = (Personnage) perso;
            components[p.getPosition().getPositionX()][p.getPosition().getPositionY()][1] = new ViewComponent(new PanelDrawing(Color.RED, PanelDrawing.drawingType.circle, width, height));
            components[p.getPosition().getPositionX()][p.getPosition().getPositionY()][2] = new ViewComponent(new PanelImage(ImagePersonnage.getImageIconMapFromPersonnage(perso), width, height));
        }
        panel = this.camera.getCameraView(this.centerPosition, components);
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
    
    private void refresh () {
    	Panel panel = this.camera.getCameraView(this.centerPosition, components);
    	this.fenetre.ajouterPanel(panel);
    }
    
    private void changeCursorPosition (Position oldPosition, Position newPosition) {
    	if (newPosition.getPositionX() == this.centerPosition.getPositionX() + (this.camera.getLigne()/2)) {
        	this.centerPosition.setPositionX(this.centerPosition.getPositionX()+1);
        	this.refresh();
        } else if (newPosition.getPositionY() == this.centerPosition.getPositionY() + (this.camera.getColone()/2)) {
        	this.centerPosition.setPositionY(this.centerPosition.getPositionY()+1);
        	this.refresh();
        } else if (newPosition.getPositionX() == this.centerPosition.getPositionX() - (this.camera.getLigne()/2)) {
        	this.centerPosition.setPositionX(this.centerPosition.getPositionX()-1);
        	this.refresh();
        } else if (newPosition.getPositionY() == this.centerPosition.getPositionY() - (this.camera.getColone()/2)) {
        	this.centerPosition.setPositionY(this.centerPosition.getPositionY()-1);
        	this.refresh();
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
                this.components[p.getPosition().getPositionX()][p.getPosition().getPositionY()][2] = new ViewComponent(new PanelImage(ImagePersonnage.getImageIconMapFocusFromPersonnage(perso), width, height));
                this.refresh();
                break;
            } else if (p.getPosition().equals(oldPosition)) {
                this.fenetrePerso.hide();
                this.components[p.getPosition().getPositionX()][p.getPosition().getPositionY()][2] = new ViewComponent(new PanelImage(ImagePersonnage.getImageIconMapFromPersonnage(perso), width, height));
                this.refresh();
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
    	chapitre.doContinue();
    }
    
    private void gameOver () {
    	Popup popup;
    	JLabel label = new JLabel("GAME OVER");
    	popup = popupFactory.getPopup(fenetre, label, 100, 100);
    	popup.show();
    	this.attendre(2000);
    	popup.hide();
    }
    
    private void victoire () {
    	Popup popup;
    	JLabel label = new JLabel("VICTOIRE");
    	popup = popupFactory.getPopup(fenetre, label, 100, 100);
    	popup.show();
    	this.attendre(2000);
    	popup.hide();
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
    
    private void deplacePerso (CharacterAbstract perso, Position olPosition) {
        Personnage p = (Personnage) perso;
        Color color = null;
        if (this.chapitre.getPlateauDeJeu().getPersonnages().contains(p)) {
        	color = Color.BLUE;
        } else if (this.chapitre.getPlateauDeJeu().getEnnemies().contains(p)) {
        	color = Color.RED;
        } else if (this.chapitre.getPlateauDeJeu().getAnnexes().contains(p)) {
        	color = Color.GREEN;
        }
        this.components[olPosition.getPositionX()][olPosition.getPositionY()][1] = null;
    	this.components[olPosition.getPositionX()][olPosition.getPositionY()][2] = null;
    	this.components[p.getPosition().getPositionX()][p.getPosition().getPositionY()][1] = new ViewComponent(new PanelDrawing(color, PanelDrawing.drawingType.circle, width, height));
    	this.components[p.getPosition().getPositionX()][p.getPosition().getPositionY()][2] = new ViewComponent(new PanelImage(ImagePersonnage.getImageIconMapFromPersonnage(perso), width, height));
        this.refresh();
    }
    
    private void enlevePerso (Personnage perso) {
        this.components[perso.getPosition().getPositionX()][perso.getPosition().getPositionY()][1] = null;
        this.components[perso.getPosition().getPositionX()][perso.getPosition().getPositionY()][2] = null;
        this.refresh();
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
            this.components[c.getPosition().getPositionX()][c.getPosition().getPositionY()][1] = new ViewComponent(new ColoredCase(width, height, new Color(0, 0, 255, 50)));
        }
        this.refresh();
    }
    
    private void effaceDeplacementDisponible (List<ZoneAbstract> zones) {
        for (ZoneAbstract zone : zones) {
            Case c = (Case) zone;
            this.components[c.getPosition().getPositionX()][c.getPosition().getPositionY()][1] = null;
        }
        this.refresh();
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
            this.components[c.getPosition().getPositionX()][c.getPosition().getPositionY()][1] = new ViewComponent(new ColoredCase(width, height, new Color(255, 0, 0, 50)));
        }
        this.refresh();
    }
    
    private void effaceAttaqueDisponible (List<ZoneAbstract> zones) {
        for (ZoneAbstract zone : zones) {
            Case c = (Case) zone;
            this.components[c.getPosition().getPositionX()][c.getPosition().getPositionY()][1] = null;
        }
        this.refresh();
    }
    
    private void unites () {
    	Box box = Box.createVerticalBox();
    	String[] columnNames = {"image", "Nom", "Classe", "Niv", "Exp", "PV", "Max"};
    	Object[][] data = new Object[this.chapitre.getPlateauDeJeu().getPersonnages().size()][7];
    	for(int i = 0 ; i < this.chapitre.getPlateauDeJeu().getPersonnages().size() ; i++) {
    		Personnage perso = (Personnage) this.chapitre.getPlateauDeJeu().getPersonnages().get(i);
    		data[i][0] = "";
    		data[i][1] = perso.getName();
    		data[i][2] = "";
    		data[i][3] = perso.getNiv();
    		data[i][4] = 0;
    		data[i][5] = perso.getPv();
    		data[i][6] = perso.getPvMax();
        }
    	box.add(new JLabel("Personnage"));
    	JTable table = new JTable(data, columnNames);
    	box.add(table);
    	this.fenetre.ajouterPanel(box);
    }
    
    private void status () {
    	Box box = Box.createVerticalBox();
    	JLabel labelChapitre = new JLabel(this.chapitre.getNom());
    	box.add(labelChapitre);
    	JPanel panelNbJoueur = new JPanel(new GridLayout(1, 2));
    	JLabel nbJoueurPerso = new JLabel(""+this.chapitre.getPlateauDeJeu().getPersonnages().size());
    	JLabel nbJoueurAdv = new JLabel(""+this.chapitre.getPlateauDeJeu().getEnnemies().size());
    	panelNbJoueur.add(nbJoueurPerso);
    	panelNbJoueur.add(nbJoueurAdv);
    	box.add(panelNbJoueur);
    	JPanel panelObjectif = new JPanel(new GridLayout(3, 1));
    	JLabel labelObjectif = new JLabel(this.chapitre.getObjectif());
    	panelObjectif.add(labelObjectif);
    	box.add(panelObjectif);
    	this.fenetre.ajouterPanel(box);
    }
    
    public synchronized void attendre (int time) {
        try {
            this.wait(time);
        } catch (InterruptedException ex) {
            Logger.getLogger(VueSwing_combat.class.getName()).log(Level.SEVERE, null, ex);
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
        	fenetreActionPerso.hide();
            chapitre.actionPerso(this.actionPerso);
        }
    }
    
    private class boutonCombat implements ActionListener {
        
        @Override
        public void actionPerformed (ActionEvent event) {
        	fenetreActionPerso.hide();
            chapitre.combat();
        }
    }
    
}
