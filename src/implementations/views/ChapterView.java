package implementations.views;

import implementations.Position;
import implementations.character.Character;
import implementations.controller.Chapter;
import implementations.controller.Chapter.Tour;
import implementations.controller.Chapter.menu;
import implementations.gameplatform.Square;
import implementations.keyevent.KeyAction;
import implementations.keyevent.KeyDispatcher;
import implementations.media.character.CharacterImage;
import implementations.media.map.MapImage;
import implementations.object.Objet;

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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.Popup;
import javax.swing.PopupFactory;

import abstracts_interfaces.CharacterAbstract;
import abstracts_interfaces.factories.gameplatforms.ZoneAbstract;

public class ChapterView {
    
    private final Chapter chapitre;
    private final Window fenetre;
    
    private final KeyAction keyAction;
    private Popup fenetreObjectif;
    private Popup fenetreTerrain;
    private Popup fenetrePerso;
    private Popup fenetreMenu;
    private Popup fenetreActionPerso;
    private final PopupFactory popupFactory;
    
    private List<ZoneAbstract> zonesSelectionner;
    private Character persoEnCours;
    ComponentView components[][][];
    
    private Camera camera;
    private Position centerPosition;
    private Panel panel;
    int width = 125;
    int height = 65;
    
    public ChapterView (Chapter chapitre, Window fenetre) {
        this.chapitre = chapitre;
        this.fenetre = fenetre;
        this.keyAction = new KeyAction();
        this.popupFactory = PopupFactory.getSharedInstance();
        this.camera = new Camera();
        this.centerPosition = new Position(10, 10);
        this.camera.setCenterPoint(this.centerPosition);
        
        
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
        this.chapitre.addListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (evt.getPropertyName().equals(ChapterView.this.chapitre.AFFICHE_MAP)) {
                    afficheMap();
                } else if (evt.getPropertyName().equals(ChapterView.this.chapitre.AFFICHE_ARMES_PERSO)) {
                    afficheArmesPerso((Character) evt.getOldValue());
                } else if (evt.getPropertyName().equals(ChapterView.this.chapitre.AFFICHE_ATTAQUE_DISPONIBLE)) {
                    afficheAttaqueDisponible((List<ZoneAbstract>) evt.getOldValue());
                } else if (evt.getPropertyName().equals(ChapterView.this.chapitre.AFFICHE_DEPLACEMENT_DISPONIBLE)) {
                    afficheDeplacementDisponible ((List<ZoneAbstract>) evt.getOldValue());
                } else if (evt.getPropertyName().equals(ChapterView.this.chapitre.AFFICHE_MENU)) {
                    afficheMenu((menu[]) evt.getOldValue());
                } else if (evt.getPropertyName().equals(ChapterView.this.chapitre.AFFICHE_ACTION_PERSO)) {
                    afficheActionPerso((Chapter.actionPerso[]) evt.getOldValue());
                } else if (evt.getPropertyName().equals(ChapterView.this.chapitre.CHANGE_TOUR)) {
                	afficherTour((Tour) evt.getOldValue());
                } else if (evt.getPropertyName().equals(ChapterView.this.chapitre.DEPLACE_PERSO)) {
                    deplacePerso((Character) evt.getOldValue(), (Position) evt.getNewValue());
                } else if (evt.getPropertyName().equals(ChapterView.this.chapitre.EFFACE_ATTAQUE_DISPONIBLE)) {
                    effaceAttaqueDisponible((List<ZoneAbstract>) evt.getOldValue());
                } else if (evt.getPropertyName().equals(ChapterView.this.chapitre.EFFACE_DEPLACEMENT_DISPONIBLE)) {
                    effaceDeplacementDisponible((List<ZoneAbstract>) evt.getOldValue());
                } else if (evt.getPropertyName().equals(ChapterView.this.chapitre.ENLEVE_PERSO)) {
                    enlevePerso((Character) evt.getOldValue());
                } else if (evt.getPropertyName().equals(ChapterView.this.chapitre.GAME_OVER)) {
                    gameOver();
                } else if (evt.getPropertyName().equals(ChapterView.this.chapitre.STATUS)) {
                    status();
                } else if (evt.getPropertyName().equals(ChapterView.this.chapitre.UNITES)) {
                    unites();
                } else if (evt.getPropertyName().equals(ChapterView.this.chapitre.VICTOIRE)) {
                    victoire();
                }
            }
        });
    }
    
    public void afficheMap () {
        Map<ZoneAbstract, JComponent> componentZone = new HashMap<>();
        components = new ComponentView[this.chapitre.getPlateauDeJeu().getWidth()][this.chapitre.getPlateauDeJeu().getHeight()][3];
        for (ZoneAbstract zone : this.chapitre.getPlateauDeJeu().getZones()) {
            Square c = (Square) zone;
            PanelImage panelImage = new PanelImage(MapImage.getImageFromZone(zone), width, height);
            componentZone.put(zone, panelImage);
            components[c.getPosition().getPositionX()][c.getPosition().getPositionY()][0] = new ComponentView(panelImage);
        }
        for (CharacterAbstract perso : this.chapitre.getPlateauDeJeu().getPersonnages()) {
            Character p = (Character) perso;
            components[p.getPosition().getPositionX()][p.getPosition().getPositionY()][1] = new ComponentView(new PanelDrawing(Color.BLUE, PanelDrawing.drawingType.circle, width, height));
            components[p.getPosition().getPositionX()][p.getPosition().getPositionY()][2] = new ComponentView(new PanelImage(CharacterImage.getImageIconMapFromPersonnage(perso), width, height));
        }
        for (CharacterAbstract perso : this.chapitre.getPlateauDeJeu().getEnnemies()) {
            Character p = (Character) perso;
            components[p.getPosition().getPositionX()][p.getPosition().getPositionY()][1] = new ComponentView(new PanelDrawing(Color.RED, PanelDrawing.drawingType.circle, width, height));
            components[p.getPosition().getPositionX()][p.getPosition().getPositionY()][2] = new ComponentView(new PanelImage(CharacterImage.getImageIconMapFromPersonnage(perso), width, height));
        }
        this.panel = this.camera.getCameraView(this.centerPosition, components);
        this.keyAction.init(this.chapitre.getPlateauDeJeu(), componentZone);
        this.keyAction.setCursorPosition(this.centerPosition);
        this.fenetre.addKeyBoardManager(new KeyDispatcher(this.keyAction));
        this.fenetre.ajouterPanel(this.panel);
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
    
    private void afficheActionPerso (Chapter.actionPerso actions[]) {
        JPanel panel = new JPanel (new GridLayout(actions.length, 1));
        for (Chapter.actionPerso action : actions) {
            JButton bouton = new JButton(action.name());
            bouton.addActionListener(new boutonActionPerso(action));
            panel.add(bouton);
        }
        this.fenetreActionPerso = this.popupFactory.getPopup(this.fenetre, panel, 100, 250);
        this.fenetreActionPerso.show();
    }
    
    private void refresh () {
    	this.panel = this.camera.getCameraView(this.centerPosition, components);
    	this.fenetre.ajouterPanel(this.panel);
    }
    
    private void changeCursorPosition (Position oldPosition, Position newPosition) {
    	/*if (newPosition.getPositionX() == this.centerPosition.getPositionX() + (this.camera.getLigne()/2)) {
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
        }*/
    	if (this.camera.move(oldPosition, newPosition, this.components.length, this.components[0].length)) {
    		this.refresh();
    	}
    	JLabel label = new JLabel(this.chapitre.getObjectif());
        this.fenetreObjectif = this.popupFactory.getPopup(this.fenetre, label, 1200, 80);
        this.fenetreObjectif.show();

        JPanel panelTerrain = new JPanel (new GridLayout(2, 1));
        for (ZoneAbstract zone : this.chapitre.getPlateauDeJeu().getZones()) {
            Square c = (Square) zone;
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
            Character p = (Character) perso;
            if (p.getPosition().equals(newPosition)) {
                this.afficheFenetrePerso(p);
                this.camera.refresh(this.panel, p.getPosition(), 2, this.centerPosition, this.components, new ComponentView(new PanelImage(CharacterImage.getImageIconMapFocusFromPersonnage(perso), width, height)));
                /*this.components[p.getPosition().getPositionX()][p.getPosition().getPositionY()][2] = new ViewComponent(new PanelImage(ImagePersonnage.getImageIconMapFocusFromPersonnage(perso), width, height));
                this.panel.getPanelCentre().ajouterViewContent(this.components[p.getPosition().getPositionX()][p.getPosition().getPositionY()][2], p.getPosition().getPositionX(), p.getPosition().getPositionY(), 2);
                *///this.refresh();
                break;
            } else if (p.getPosition().equals(oldPosition)) {
                this.fenetrePerso.hide();
                this.camera.refresh(this.panel, p.getPosition(), 2, this.centerPosition, this.components, new ComponentView(new PanelImage(CharacterImage.getImageIconMapFromPersonnage(perso), width, height)));
                /*this.components[p.getPosition().getPositionX()][p.getPosition().getPositionY()][2] = new ViewComponent(new PanelImage(ImagePersonnage.getImageIconMapFromPersonnage(perso), width, height));
                this.panel.getPanelCentre().ajouterViewContent(this.components[p.getPosition().getPositionX()][p.getPosition().getPositionY()][2], p.getPosition().getPositionX(), p.getPosition().getPositionY(), 2);
                *///this.refresh();
                break;
            }
        }

        for (CharacterAbstract perso : this.chapitre.getPlateauDeJeu().getEnnemies()) {
            Character p = (Character) perso;
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
    
    private void afficheFenetrePerso (Character perso) {
        Box boxPerso = Box.createHorizontalBox();
        PanelImage panelImage = new PanelImage(CharacterImage.getImageMenuFromPersonnage(perso), 70, 50);
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
        Character p = (Character) perso;
        Color color = null;
        if (this.chapitre.getPlateauDeJeu().getPersonnages().contains(p)) {
        	color = Color.BLUE;
        } else if (this.chapitre.getPlateauDeJeu().getEnnemies().contains(p)) {
        	color = Color.RED;
        } else if (this.chapitre.getPlateauDeJeu().getAnnexes().contains(p)) {
        	color = Color.GREEN;
        }
        this.camera.refresh(this.panel, olPosition, 1, this.centerPosition, this.components, null);
    	this.camera.refresh(this.panel, olPosition, 2, this.centerPosition, this.components, null);
    	this.camera.refresh(this.panel, p.getPosition(), 1, this.centerPosition, this.components, new ComponentView(new PanelDrawing(color, PanelDrawing.drawingType.circle, width, height)));
    	this.camera.refresh(this.panel, p.getPosition(), 2, this.centerPosition, this.components, new ComponentView(new PanelImage(CharacterImage.getImageIconMapFromPersonnage(perso), width, height)));
        
    	/*this.components[olPosition.getPositionX()][olPosition.getPositionY()][1] = null;
    	this.components[olPosition.getPositionX()][olPosition.getPositionY()][2] = null;
    	this.components[p.getPosition().getPositionX()][p.getPosition().getPositionY()][1] = new ViewComponent(new PanelDrawing(color, PanelDrawing.drawingType.circle, width, height));
    	this.components[p.getPosition().getPositionX()][p.getPosition().getPositionY()][2] = new ViewComponent(new PanelImage(ImagePersonnage.getImageIconMapFromPersonnage(perso), width, height));
        this.panel.getPanelCentre().ajouterViewContent(null, olPosition.getPositionX(), olPosition.getPositionY(), 1);
        this.panel.getPanelCentre().ajouterViewContent(null, olPosition.getPositionX(), olPosition.getPositionY(), 2);
        this.panel.getPanelCentre().ajouterViewContent(this.components[p.getPosition().getPositionX()][p.getPosition().getPositionY()][1], p.getPosition().getPositionX(), p.getPosition().getPositionY(), 1);
        this.panel.getPanelCentre().ajouterViewContent(this.components[p.getPosition().getPositionX()][p.getPosition().getPositionY()][2], p.getPosition().getPositionX(), p.getPosition().getPositionY(), 2);*/
        //this.refresh();
    }
    
    private void enlevePerso (Character perso) {
    	this.camera.refresh(this.panel, perso.getPosition(), 1, this.centerPosition, this.components, null);
    	this.camera.refresh(this.panel, perso.getPosition(), 2, this.centerPosition, this.components, null);
        /*this.components[perso.getPosition().getPositionX()][perso.getPosition().getPositionY()][1] = null;
        this.components[perso.getPosition().getPositionX()][perso.getPosition().getPositionY()][2] = null;
        this.panel.getPanelCentre().ajouterViewContent(null, perso.getPosition().getPositionX(), perso.getPosition().getPositionY(), 1);
        this.panel.getPanelCentre().ajouterViewContent(null, perso.getPosition().getPositionX(), perso.getPosition().getPositionY(), 2);*/
        //this.refresh();
    }
    
    private void action (Position pos) {
        this.chapitre.action(pos);
    }
    
    private void annulation () {
        this.chapitre.annulation();
    }
    
    private void afficheDeplacementDisponible (List<ZoneAbstract> zones) {
        for (ZoneAbstract zone : zones) {
            Square c = (Square) zone;
            this.camera.refresh(this.panel, c.getPosition(), 1, this.centerPosition, this.components, new ComponentView(new ColoredCase(width, height, new Color(0, 0, 255, 50))));
            /*this.components[c.getPosition().getPositionX()][c.getPosition().getPositionY()][1] = new ViewComponent(new ColoredCase(width, height, new Color(0, 0, 255, 50)));
            this.panel.getPanelCentre().ajouterViewContent(this.components[c.getPosition().getPositionX()][c.getPosition().getPositionY()][1], c.getPosition().getPositionX(), c.getPosition().getPositionY(), 1);*/
        }
        //this.refresh();
    }
    
    private void effaceDeplacementDisponible (List<ZoneAbstract> zones) {
        for (ZoneAbstract zone : zones) {
            Square c = (Square) zone;
            this.camera.refresh(this.panel, c.getPosition(), 1, this.centerPosition, this.components, null);
            /*this.components[c.getPosition().getPositionX()][c.getPosition().getPositionY()][1] = null;
            this.panel.getPanelCentre().ajouterViewContent(null, c.getPosition().getPositionX(), c.getPosition().getPositionY(), 1);*/
        }
        //this.refresh();
    }
    
    private void afficheArmesPerso (CharacterAbstract personnage) {
        Character perso = (Character) personnage;
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
            Square c = (Square) zone;
            this.camera.refresh(this.panel, c.getPosition(), 1, this.centerPosition, this.components, new ComponentView(new ColoredCase(width, height, new Color(255, 0, 0, 50))));
            /*this.components[c.getPosition().getPositionX()][c.getPosition().getPositionY()][1] = new ViewComponent(new ColoredCase(width, height, new Color(255, 0, 0, 50)));
            this.panel.getPanelCentre().ajouterViewContent(this.components[c.getPosition().getPositionX()][c.getPosition().getPositionY()][1], c.getPosition().getPositionX(), c.getPosition().getPositionY(), 1);*/
        }
        //this.refresh();
    }
    
    private void effaceAttaqueDisponible (List<ZoneAbstract> zones) {
        for (ZoneAbstract zone : zones) {
            Square c = (Square) zone;
            this.camera.refresh(this.panel, c.getPosition(), 1, this.centerPosition, this.components, null);
            /*this.components[c.getPosition().getPositionX()][c.getPosition().getPositionY()][1] = null;
            this.panel.getPanelCentre().ajouterViewContent(null, c.getPosition().getPositionX(), c.getPosition().getPositionY(), 1);*/
        }
        //this.refresh();
    }
    
    private void unites () {
    	Box box = Box.createVerticalBox();
    	String[] columnNames = {"", "Nom", "Classe", "Niv", "Exp", "PV", "Max"};
    	Object[][] data = new Object[this.chapitre.getPlateauDeJeu().getPersonnages().size()][7];
    	for(int i = 0 ; i < this.chapitre.getPlateauDeJeu().getPersonnages().size() ; i++) {
    		Character perso = (Character) this.chapitre.getPlateauDeJeu().getPersonnages().get(i);
    		data[i][0] = "";
    		data[i][1] = perso.getName();
    		data[i][2] = perso.getComportementPersonnage().getName();
    		data[i][3] = perso.getNiv();
    		data[i][4] = perso.getExperience();
    		data[i][5] = perso.getPv();
    		data[i][6] = perso.getPvMax();
        }
    	box.add(new JLabel("Personnages"));
    	JTable table = new JTable(data, columnNames);
    	box.add(new JScrollPane(table));
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
            Logger.getLogger(CombatView.class.getName()).log(Level.SEVERE, null, ex);
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
        
        private final Chapter.actionPerso actionPerso;
        
        public boutonActionPerso (Chapter.actionPerso actionPerso) {
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
