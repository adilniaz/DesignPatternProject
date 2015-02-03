package implementations.views;

import implementations.Position;
import implementations.character.Character;
import implementations.character.Character.Status;
import implementations.combat.FightBehaviour;
import implementations.controller.Chapter;
import implementations.controller.Chapter.ObjectAction;
import implementations.controller.Chapter.Ordre;
import implementations.controller.Chapter.Tour;
import implementations.controller.Chapter.menu;
import implementations.gameplatform.Square;
import implementations.keyevent.MapKeyAction;
import implementations.keyevent.KeyDispatcher;
import implementations.keyevent.MenuKeyAction;
import implementations.keyevent.SimpleKeyAction;
import implementations.media.character.CharacterImage;
import implementations.media.map.MapImage;
import implementations.object.Objet;
import implementations.object.Weapon;
import implementations.object.WeaponType;
import implementations.views.MyPopup.Alignement;

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

import abstracts_interfaces.CharacterAbstract;
import abstracts_interfaces.factories.gameplatforms.ZoneAbstract;

public class ChapterView {
    
    private final Chapter chapitre;
    private final Window fenetre;
    
    private final MapKeyAction keyAction;
    private final MyPopup fenetreObjectif;
    private final MyPopup fenetreTerrain;
    private final MyPopup fenetrePerso;
    private final MyPopup fenetreMenu;
    private MyPopup fenetreObjet;
    private ComponentView components[][][];
    
    private MyPopup popupPv;
    private PanelPv panelPvPerso;
    
    private Camera camera;
    private Position centerPosition;
    private Panel panel;
    private JPanel menuPanel;
    private int width = 70;
    private int height = 50;
    
    public ChapterView (Chapter chapitre, Window fenetre) {
        this.chapitre = chapitre;
        this.fenetre = fenetre;
        this.keyAction = new MapKeyAction();
        this.camera = new Camera();
        this.centerPosition = new Position(10, 10);
        this.camera.setCenterPoint(this.centerPosition);

        this.fenetreObjectif = new MyPopup(600, 50, this.fenetre);
        this.fenetreTerrain = new MyPopup(50, 400, this.fenetre);
        this.fenetrePerso = new MyPopup(50, 40, this.fenetre);
        this.fenetreMenu = new MyPopup(50, 150, this.fenetre);
        
        
        this.keyAction.ajouterEcouteur(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (evt.getPropertyName().equals("cursorPosition")) {
                    changeCursorPosition((Position) evt.getOldValue(), (Position) evt.getNewValue());
                } else if (evt.getPropertyName().equals("action")) {
                    action();
                } else if (evt.getPropertyName().equals("annulation")) {
                    annulation();
                } else if (evt.getPropertyName().equals("info")) {
                	info();
                }
            }
        });
        this.chapitre.addListener(new PropertyChangeListener() {
            @SuppressWarnings("unchecked")
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
                } else if (evt.getPropertyName().equals(ChapterView.this.chapitre.CANCEL_ACTION_PERSO)) {
                	cancelActionPerso();
                } else if (evt.getPropertyName().equals(ChapterView.this.chapitre.CANCEL_OBJET_ACTION)) {
                	cancelMenuObjet();
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
                } else if (evt.getPropertyName().equals(ChapterView.this.chapitre.FREE_STATE)) {
                	freeState();
                } else if (evt.getPropertyName().equals(ChapterView.this.chapitre.GAME_OVER)) {
                    gameOver();
                } else if (evt.getPropertyName().equals(ChapterView.this.chapitre.HIDE_CHARACTER_VIEW)) {
                	hideCharacterView();
                } else if (evt.getPropertyName().equals(ChapterView.this.chapitre.HIDE_MENU)) {
                    hideMenu();
                } else if (evt.getPropertyName().equals(ChapterView.this.chapitre.HIDE_ORDRE)) {
                	hideMenu();
                } else if (evt.getPropertyName().equals(ChapterView.this.chapitre.HIDE_STATUS)) {
                	hideUnits();
                } else if (evt.getPropertyName().equals(ChapterView.this.chapitre.HIDE_UNITS)) {
                	hideUnits();
                } else if (evt.getPropertyName().equals(ChapterView.this.chapitre.ORDRES)) {
                    afficheOrdre((Ordre[])evt.getOldValue());
                } else if (evt.getPropertyName().equals(ChapterView.this.chapitre.OBJETS)) {
                    afficheObjets((Objet[])evt.getOldValue());
                } else if (evt.getPropertyName().equals(ChapterView.this.chapitre.OBJETS_ACTION)) {
                	afficheMenuObjet((ObjectAction[])evt.getOldValue(), (int)evt.getNewValue());
                } else if (evt.getPropertyName().equals(ChapterView.this.chapitre.SHOW_CHARACTER_VIEW)) {
                    showCharacterView((Character) evt.getOldValue(), (int)evt.getNewValue());
                } else if (evt.getPropertyName().equals(ChapterView.this.chapitre.SIMULATION_COMBAT)) {
                    simulationCombatKey();
                } else if (evt.getPropertyName().equals(ChapterView.this.chapitre.STATUS)) {
                    status();
                } else if (evt.getPropertyName().equals(ChapterView.this.chapitre.UNITES)) {
                    unites((int)evt.getOldValue());
                } else if (evt.getPropertyName().equals(ChapterView.this.chapitre.USE_OBJECT)) {
                	useObject((Character)evt.getOldValue(), (Character)evt.getNewValue());
                } else if (evt.getPropertyName().equals(ChapterView.this.chapitre.VICTOIRE)) {
                    victoire();
                }
            }
        });
    }
    
    public void afficheMap () {
        Map<ZoneAbstract, JComponent> componentZone = new HashMap<>();
        components = new ComponentView[this.chapitre.getPlateauDeJeu().getWidth()][this.chapitre.getPlateauDeJeu().getHeight()][4];
        for (ZoneAbstract zone : this.chapitre.getPlateauDeJeu().getZones()) {
            Square c = (Square) zone;
            PanelImage panelImage = new PanelImage(MapImage.getImageFromZone(zone), width, height);
            componentZone.put(zone, panelImage);
            components[c.getPosition().getPositionX()][c.getPosition().getPositionY()][0] = new ComponentView(panelImage);
        }
        for (CharacterAbstract perso : this.chapitre.getPlateauDeJeu().getPersonnages()) {
            Character p = (Character) perso;
            components[p.getPosition().getPositionX()][p.getPosition().getPositionY()][2] = new ComponentView(new PanelDrawing(Color.BLUE, PanelDrawing.drawingType.circle, width, height));
            components[p.getPosition().getPositionX()][p.getPosition().getPositionY()][3] = new ComponentView(new PanelImage(CharacterImage.getImageIconMapFromPersonnage(perso), width, height));
        }
        for (CharacterAbstract perso : this.chapitre.getPlateauDeJeu().getAnnexes()) {
            Character p = (Character) perso;
            components[p.getPosition().getPositionX()][p.getPosition().getPositionY()][2] = new ComponentView(new PanelDrawing(Color.GREEN, PanelDrawing.drawingType.circle, width, height));
            components[p.getPosition().getPositionX()][p.getPosition().getPositionY()][3] = new ComponentView(new PanelImage(CharacterImage.getImageIconMapFromPersonnage(perso), width, height));
        }
        for (CharacterAbstract perso : this.chapitre.getPlateauDeJeu().getEnnemies()) {
            Character p = (Character) perso;
            components[p.getPosition().getPositionX()][p.getPosition().getPositionY()][2] = new ComponentView(new PanelDrawing(Color.RED, PanelDrawing.drawingType.circle, width, height));
            components[p.getPosition().getPositionX()][p.getPosition().getPositionY()][3] = new ComponentView(new PanelImage(CharacterImage.getImageIconMapFromPersonnage(perso), width, height));
        }
        this.panel = this.camera.getCameraView(this.centerPosition, components);
        this.keyAction.init(this.chapitre.getPlateauDeJeu(), componentZone);
        this.keyAction.setCursorPosition(this.centerPosition);
        this.fenetre.addKeyBoardManager(new KeyDispatcher(this.keyAction));
        this.fenetre.ajouterPanel(this.panel);
        JLabel label = new JLabel(this.chapitre.getObjectif());
        this.fenetreObjectif.addComponent(label, true);
        this.changeCursorPosition(this.centerPosition, this.centerPosition);
    }
    
    private void diplayPanel () {
    	this.fenetre.ajouterPanel(this.panel);
    	this.fenetre.addKeyBoardManager(new KeyDispatcher(this.keyAction));
    }
    
    private void freeState () {
    	this.fenetre.addKeyBoardManager(new KeyDispatcher(this.keyAction));
    }
    
    private void afficheMenu (menu menu[]) {
        JPanel panel = new JPanel (new GridLayout(menu.length, 1));
        JComponent[] components = new JComponent[menu.length];
        int indice = 0;
        for (menu m : menu) {
            JButton bouton = new JButton(m.name());
            bouton.addActionListener(new boutonMenu(m));
            panel.add(bouton);
            components[indice] = bouton;
            indice++;
        }
        
        MenuKeyAction menuKeyAction = new MenuKeyAction(components);
        menuKeyAction.ajouterEcouteur(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (evt.getPropertyName().equals("action")) {
                    action();
                } else if (evt.getPropertyName().equals("annulation")) {
                    annulation();
                }
            }
        });
        this.fenetreObjectif.hide();
        this.fenetreTerrain.hide();
        //this.addToPanel(this.fenetreMenuContent, panel);
        this.fenetreMenu.addComponent(panel, true);
        this.fenetre.addKeyBoardManager(new KeyDispatcher(menuKeyAction));
    }
    
    private void hideMenu () {
    	this.fenetreMenu.hide();
    	this.fenetre.addKeyBoardManager(new KeyDispatcher(this.keyAction));
    	this.fenetreObjectif.show();
    	this.fenetreTerrain.show();
    }
    
    private void afficheActionPerso (Chapter.actionPerso actions[]) {
        JPanel panel = new JPanel (new GridLayout(actions.length, 1));
        JComponent[] components = new JComponent[actions.length];
        int indice = 0;
        for (Chapter.actionPerso action : actions) {
            JButton bouton = new JButton(action.name());
            bouton.addActionListener(new boutonActionPerso(action));
            panel.add(bouton);
            components[indice] = bouton;
            indice++;
        }
        this.fenetreMenu.addComponent(panel, true);
        MenuKeyAction menuKeyAction = new MenuKeyAction(components);
        menuKeyAction.ajouterEcouteur(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (evt.getPropertyName().equals("action")) {
                    action();
                } else if (evt.getPropertyName().equals("annulation")) {
                    annulation();
                }
            }
        });
        this.fenetre.addKeyBoardManager(new KeyDispatcher(menuKeyAction));
    }
    
    private void cancelActionPerso () {
    	this.fenetreMenu.hide();
    	this.fenetre.addKeyBoardManager(new KeyDispatcher(this.keyAction));
    }
    
    private void afficheObjets (Objet[] objets) {
        JPanel panel = new JPanel (new GridLayout(objets.length, 1));
        JComponent[] components = new JComponent[objets.length];
        int indice = 0;
        for (Objet objet : objets) {
            JButton bouton = new JButton(objet.getName());
            bouton.addActionListener(new boutonChoixObjet(indice));
            panel.add(bouton);
            components[indice] = bouton;
            indice++;
        }
        this.fenetreMenu.addComponent(panel, true);
        MenuKeyAction menuKeyAction = new MenuKeyAction(components);
        menuKeyAction.ajouterEcouteur(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (evt.getPropertyName().equals("action")) {
                    action();
                } else if (evt.getPropertyName().equals("annulation")) {
                    annulation();
                }
            }
        });
        this.fenetre.addKeyBoardManager(new KeyDispatcher(menuKeyAction));
    }
    
    private void afficheMenuObjet (ObjectAction[] actions, int indice) {
    	JPanel panel = new JPanel (new GridLayout(actions.length, 1));
        JComponent[] components = new JComponent[actions.length];
        int i = 0;
        for (ObjectAction objectAction : actions) {
        	JButton bouton = new JButton(objectAction.name());
        	if (objectAction == ObjectAction.utiliser) {
        		bouton.addActionListener(new boutonUtiliseObjet(indice));
        	} else if (objectAction == ObjectAction.equiper) {
        		bouton.addActionListener(new boutonEquiperObjet(indice));
        	} else if (objectAction == ObjectAction.jeter) {
        		bouton.addActionListener(new boutonJeterObjet(indice));
        	}
        	panel.add(bouton);
        	components[i] = bouton;
        	i++;
        }
    	this.fenetreObjet = new MyPopup(170, 150, this.fenetre);
    	this.fenetreObjet.addComponent(panel, true);
    	MenuKeyAction menuKeyAction = new MenuKeyAction(components);
        menuKeyAction.ajouterEcouteur(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (evt.getPropertyName().equals("action")) {
                    action();
                } else if (evt.getPropertyName().equals("annulation")) {
                    annulation();
                }
            }
        });
        this.fenetre.addKeyBoardManager(new KeyDispatcher(menuKeyAction));
    }
    
    private void cancelMenuObjet () {
    	this.fenetreObjet.hide();
    }
    
    private void afficheOrdre (Ordre ordres[]) {
        JPanel panel = new JPanel (new GridLayout(ordres.length, 1));
        JComponent[] components = new JComponent[ordres.length];
        int indice = 0;
        for (Ordre ordre : ordres) {
            JButton bouton = new JButton(ordre.name());
            bouton.addActionListener(new boutonOrdre(ordre));
            panel.add(bouton);
            components[indice] = bouton;
            indice++;
        }
        this.fenetreMenu.addComponent(panel, true);
        MenuKeyAction menuKeyAction = new MenuKeyAction(components);
        menuKeyAction.ajouterEcouteur(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (evt.getPropertyName().equals("action")) {
                    action();
                } else if (evt.getPropertyName().equals("annulation")) {
                    annulation();
                }
            }
        });
        this.fenetre.addKeyBoardManager(new KeyDispatcher(menuKeyAction));
    }
    
    private void refresh () {
    	this.panel = this.camera.getCameraView(this.centerPosition, components);
    	this.fenetre.ajouterPanel(this.panel);
    }
    
    private void changeCursorPosition (Position oldPosition, Position newPosition) {
    	this.chapitre.setCurrentPosition(newPosition);
    	if (this.camera.move(oldPosition, newPosition, this.components.length, this.components[0].length)) {
    		this.refresh();
    	}
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
        this.fenetreTerrain.addComponent(panelTerrain, true);

        for (CharacterAbstract perso : this.chapitre.getPlateauDeJeu().getPersonnages()) {
            Character p = (Character) perso;
            if (p.getPosition().equals(oldPosition)) {
            	this.fenetrePerso.hide();
                this.camera.refresh(this.panel, p.getPosition(), 3, this.centerPosition, this.components, new ComponentView(new PanelImage(CharacterImage.getImageIconMapFromPersonnage(perso), width, height)));
            }
        }
        
        for (CharacterAbstract perso : this.chapitre.getPlateauDeJeu().getAnnexes()) {
            Character p = (Character) perso;
            if (p.getPosition().equals(oldPosition)) {
            	this.fenetrePerso.hide();
            }
        }
        
        for (CharacterAbstract perso : this.chapitre.getPlateauDeJeu().getEnnemies()) {
            Character p = (Character) perso;
            if (p.getPosition().equals(oldPosition)) {
            	this.fenetrePerso.hide();
            }
        }

        for (CharacterAbstract perso : this.chapitre.getPlateauDeJeu().getPersonnages()) {
            Character p = (Character) perso;
            if (p.getPosition().equals(newPosition)) {
                this.afficheFenetrePerso(p);
                this.camera.refresh(this.panel, p.getPosition(), 3, this.centerPosition, this.components, new ComponentView(new PanelImage(CharacterImage.getImageIconMapFocusFromPersonnage(perso), width, height)));
            }
        }
        
        for (CharacterAbstract perso : this.chapitre.getPlateauDeJeu().getAnnexes()) {
            Character p = (Character) perso;
            if (p.getPosition().equals(newPosition)) {
                this.afficheFenetrePerso(p);
            }
        }
        
        for (CharacterAbstract perso : this.chapitre.getPlateauDeJeu().getEnnemies()) {
            Character p = (Character) perso;
            if (p.getPosition().equals(newPosition)) {
                this.afficheFenetrePerso(p);
            }
        }
    }
    
    private void afficherTour (Tour tour) {
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
    	MyPopup popup = new MyPopup((this.fenetre.getWidth()/2) - (label.getWidth() /2), (this.fenetre.getHeight()/2) - (label.getHeight() /2), this.fenetre);
    	popup.addComponent(label, true);
    	this.attendre(2000);
    	popup.hide();
    	chapitre.doContinue();
    }
    
    private void gameOver () {
    	MyPopup popup = new MyPopup(fenetre, Alignement.CENTER);
    	popup.addComponent(new JLabel("GAME OVER"), true);
    	this.attendre(2000);
    	popup.hide();
    }
    
    private void victoire () {
    	MyPopup popup = new MyPopup(fenetre, Alignement.CENTER);
    	popup.addComponent(new JLabel("VICTOIRE"), true);
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
        this.fenetrePerso.addComponent(boxPerso, true);
    }
    
    private void deplacePerso (CharacterAbstract perso, Position oldPosition) {
        Character p = (Character) perso;
        Color color = null;
        if (this.chapitre.getPlateauDeJeu().getPersonnages().contains(p)) {
        	color = Color.BLUE;
        } else if (this.chapitre.getPlateauDeJeu().getEnnemies().contains(p)) {
        	color = Color.RED;
        } else if (this.chapitre.getPlateauDeJeu().getAnnexes().contains(p)) {
        	color = Color.GREEN;
        }
        if (oldPosition != null) {
        	this.camera.refresh(this.panel, oldPosition, 2, this.centerPosition, this.components, null);
        	this.camera.refresh(this.panel, oldPosition, 3, this.centerPosition, this.components, null);
        }
        this.camera.refresh(this.panel, p.getPosition(), 2, this.centerPosition, this.components, new ComponentView(new PanelDrawing(color, PanelDrawing.drawingType.circle, width, height)));
    	this.camera.refresh(this.panel, p.getPosition(), 3, this.centerPosition, this.components, new ComponentView(new PanelImage(CharacterImage.getImageIconMapFromPersonnage(perso), width, height)));
    }
    
    private void enlevePerso (Character perso) {
    	this.camera.refresh(this.panel, perso.getPosition(), 2, this.centerPosition, this.components, null);
    	this.camera.refresh(this.panel, perso.getPosition(), 3, this.centerPosition, this.components, null);
    }
    
    private void afficheArmesPerso (CharacterAbstract personnage) {
        Character perso = (Character) personnage;
        int nbComponent = 0;
        for (Objet obj : perso.getObjets()) {
            if (obj instanceof Weapon && ((FightBehaviour)perso.getBehaviour()).isWeaponAvailable((Weapon)obj)) {
            	nbComponent++;
            }
        }
        JPanel panel = new JPanel (new GridLayout(nbComponent, 1));
        JComponent[] components = new JComponent[nbComponent];
        int indice = 0;
        int objIndice = 0;
        for (Objet obj : perso.getObjets()) {
            if (obj instanceof Weapon && ((FightBehaviour)perso.getBehaviour()).isWeaponAvailable((Weapon)obj)) {
                JButton bouton = new JButton(obj.getName());
                bouton.addActionListener(new weaponChoiceButton(objIndice));
                panel.add(bouton);
                components[indice] = bouton;
                indice++;
            }
            objIndice++;
        }
        this.fenetreMenu.addComponent(panel, true);
        
        MenuKeyAction menuKeyAction = new MenuKeyAction(components);
        menuKeyAction.ajouterEcouteur(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (evt.getPropertyName().equals("action")) {
                    action();
                } else if (evt.getPropertyName().equals("annulation")) {
                    annulation();
                }
            }
        });
        this.fenetre.addKeyBoardManager(new KeyDispatcher(menuKeyAction));
    }
    
    private void simulationCombatKey () {
        
    	SimpleKeyAction simpleKeyAction = new SimpleKeyAction();
    	simpleKeyAction.ajouterEcouteur(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (evt.getPropertyName().equals("action")) {
                    action();
                } else if (evt.getPropertyName().equals("annulation")) {
                    annulation();
                }
            }
        });
        this.fenetre.addKeyBoardManager(new KeyDispatcher(simpleKeyAction));
    }
    
    private void afficheDeplacementDisponible (List<ZoneAbstract> zones) {
        for (ZoneAbstract zone : zones) {
            Square c = (Square) zone;
            this.camera.refresh(this.panel, c.getPosition(), 1, this.centerPosition, this.components, new ComponentView(new ColoredCase(width, height, new Color(0, 0, 255, 50))));
        }
    }
    
    private void effaceDeplacementDisponible (List<ZoneAbstract> zones) {
        for (ZoneAbstract zone : zones) {
            Square c = (Square) zone;
            this.camera.refresh(this.panel, c.getPosition(), 1, this.centerPosition, this.components, null);
        }
    }
    
    private void afficheAttaqueDisponible (List<ZoneAbstract> zones) {
        for (ZoneAbstract zone : zones) {
            Square c = (Square) zone;
            this.camera.refresh(this.panel, c.getPosition(), 1, this.centerPosition, this.components, new ComponentView(new ColoredCase(width, height, new Color(255, 0, 0, 50))));
        }
    }
    
    private void effaceAttaqueDisponible (List<ZoneAbstract> zones) {
        for (ZoneAbstract zone : zones) {
            Square c = (Square) zone;
            this.camera.refresh(this.panel, c.getPosition(), 1, this.centerPosition, this.components, null);
        }
    }
    
    private void unites (int page) {
    	if (page == 1) {
    		Box box = Box.createVerticalBox();
        	String[] columnNames = {"", "Nom", "Classe", "Niv", "Exp", "PV  Max"};
        	Object[][] data = new Object[this.chapitre.getPlateauDeJeu().getPersonnages().size()][6];
        	for(int i = 0 ; i < this.chapitre.getPlateauDeJeu().getPersonnages().size() ; i++) {
        		Character perso = (Character) this.chapitre.getPlateauDeJeu().getPersonnages().get(i);
        		data[i][0] = "";
        		data[i][1] = perso.getName();
        		data[i][2] = perso.getComportementPersonnage().getName();
        		data[i][3] = perso.getNiv();
        		data[i][4] = perso.getExperience();
        		data[i][5] = perso.getPv() + " / " + perso.getPvMax();
            }
        	box.add(new JLabel("Personnages  (1/4)"));
        	JTable table = new JTable(data, columnNames);
        	box.add(new JScrollPane(table));
        	this.fenetreMenu.hide();
        	this.fenetre.ajouterPanel(box);
    	} else if (page == 2) {
    		Box box = Box.createVerticalBox();
        	String[] columnNames = {"", "Nom", "F/M", "Tec", "Vit", "Ch", "Déf", "Rés"};
        	Object[][] data = new Object[this.chapitre.getPlateauDeJeu().getPersonnages().size()][8];
        	for(int i = 0 ; i < this.chapitre.getPlateauDeJeu().getPersonnages().size() ; i++) {
        		Character perso = (Character) this.chapitre.getPlateauDeJeu().getPersonnages().get(i);
        		data[i][0] = "";
        		data[i][1] = perso.getName();
        		data[i][2] = perso.getPuissance();
        		data[i][3] = perso.getCapacite();
        		data[i][4] = perso.getVitesse();
        		data[i][5] = perso.getChance();
        		data[i][6] = perso.getDef();
        		data[i][7] = perso.getResistance();
            }
        	box.add(new JLabel("Aptitudes  (2/4)"));
        	JTable table = new JTable(data, columnNames);
        	box.add(new JScrollPane(table));
        	this.fenetre.ajouterPanel(box);
    	} else if (page == 3) {
    		Box box = Box.createVerticalBox();
        	String[] columnNames = {"", "Nom", "Equip", "Dmg", "Prc", "Esq"};
        	Object[][] data = new Object[this.chapitre.getPlateauDeJeu().getPersonnages().size()][6];
        	for(int i = 0 ; i < this.chapitre.getPlateauDeJeu().getPersonnages().size() ; i++) {
        		Character perso = (Character) this.chapitre.getPlateauDeJeu().getPersonnages().get(i);
        		
        		Weapon weapon = perso.getArme();
                int sRankBonus = weapon.getRang() == Weapon.Rang.S ? 5 : 0;
                int precision = weapon.getPrecision() + (perso.getCapacite() * 2) + (perso.getChance() /2) + sRankBonus;
                int vitesseAttaque = perso.getVitesse() - (weapon.getPoids() - perso.getConstitution());
                int avoid = (vitesseAttaque * 2) + perso.getChance();
        		
        		data[i][0] = "";
        		data[i][1] = perso.getName();
        		data[i][2] = weapon.getName();
        		data[i][3] = perso.getPuissance() + weapon.getPuisance();
        		data[i][4] = precision;
        		data[i][5] = avoid;
            }
        	box.add(new JLabel("Equipement  (3/4)"));
        	JTable table = new JTable(data, columnNames);
        	box.add(new JScrollPane(table));
        	this.fenetre.ajouterPanel(box);
    	} else if (page == 4) {
    		Box box = Box.createVerticalBox();
        	String[] columnNames = {"", "Nom", "Mvt", "Cons", "Arme"};
        	Object[][] data = new Object[this.chapitre.getPlateauDeJeu().getPersonnages().size()][5];
        	for(int i = 0 ; i < this.chapitre.getPlateauDeJeu().getPersonnages().size() ; i++) {
        		Character perso = (Character) this.chapitre.getPlateauDeJeu().getPersonnages().get(i);
        		WeaponType[] weaponTypes = ((FightBehaviour)perso.getBehaviour()).getWeaponTypes();
        		String weapons = "";
        		for (WeaponType weaponType : weaponTypes) {
        			weapons += weaponType.name() + ";";
        		}
        		data[i][0] = "";
        		data[i][1] = perso.getName();
        		data[i][2] = perso.getMove().getNbDeplacement();
        		data[i][3] = perso.getConstitution();
        		data[i][4] = weapons;
            }
        	box.add(new JLabel("Attributs  (4/4)"));
        	JTable table = new JTable(data, columnNames);
        	box.add(new JScrollPane(table));
        	this.fenetre.ajouterPanel(box);
    	}
    	SimpleKeyAction simpleKeyAction = new SimpleKeyAction();
    	simpleKeyAction.ajouterEcouteur(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (evt.getPropertyName().equals("action")) {
                    action();
                } else if (evt.getPropertyName().equals("annulation")) {
                    annulation();
                } else if (evt.getPropertyName().equals("left")) {
                    left();
                } else if (evt.getPropertyName().equals("right")) {
                    right();
                } else if (evt.getPropertyName().equals("up")) {
                    up();
                } else if (evt.getPropertyName().equals("down")) {
                    down();
                }
            }
        });
        this.fenetre.addKeyBoardManager(new KeyDispatcher(simpleKeyAction));
    }
    
    private void hideUnits () {
    	this.diplayPanel();
    	this.fenetreTerrain.show();
    	this.fenetreObjectif.show();
    }
    
    private void status () {
    	this.fenetreMenu.hide();
    	Box box = Box.createVerticalBox();
    	JLabel labelChapitre = new JLabel("Chapitre : "+this.chapitre.getNom());
    	box.add(labelChapitre);
    	JPanel panelInfo = new JPanel(new GridLayout(1, 2));
    	Box panelLeft = Box.createVerticalBox();
    	JPanel panelNbJoueur = new JPanel(new GridLayout(1, 2));
    	JLabel nbJoueurPerso = new JLabel("Joueur : "+this.chapitre.getPlateauDeJeu().getPersonnages().size());
    	JLabel nbJoueurAdv = new JLabel("Ennemi : "+this.chapitre.getPlateauDeJeu().getEnnemies().size());
    	panelNbJoueur.add(nbJoueurPerso);
    	panelNbJoueur.add(nbJoueurAdv);
    	JPanel panelObjectif = new JPanel(new GridLayout(3, 1));
    	panelObjectif.add(new JLabel("Objectif"));
    	panelObjectif.add(new JLabel(this.chapitre.getObjectif()));
    	panelObjectif.add(new JLabel("Tour : " + this.chapitre.getTour()));
    	panelLeft.add(panelNbJoueur);
    	panelLeft.add(panelObjectif);
    	Box panelRight = Box.createVerticalBox();
    	panelRight.add(this.getBossBox(this.chapitre.getPlateauDeJeu().getPersonnages()));
    	panelRight.add(this.getBossBox(this.chapitre.getPlateauDeJeu().getEnnemies()));
    	panelInfo.add(panelLeft);
    	panelInfo.add(panelRight);
    	box.add(panelInfo);
    	this.fenetre.ajouterPanel(box);
    }
    
    private Box getBossBox (List<CharacterAbstract> list) {
    	Character character = null;
    	for (CharacterAbstract characterAbstract : list) {
    		character = (Character) characterAbstract;
    		if (character.getStatus() == Status.boss) {
    			break;
    		}
    	}
    	Box panelPerso = Box.createVerticalBox();
    	Box ligne1 = Box.createHorizontalBox();
    	PanelImage panelImageMap = new PanelImage(CharacterImage.getImageIconMapFromPersonnage(character), width, height);
    	ligne1.add(panelImageMap);
    	ligne1.add(new JLabel(character.getName()));
    	Box ligne2 = Box.createHorizontalBox();
    	Box boxStat = Box.createVerticalBox();
    	PanelImage panelImagePerso = new PanelImage(CharacterImage.getImageMenuFromPersonnage(character), 70, 50);
    	boxStat.add(new JLabel("Niv : " + character.getNiv()));
    	boxStat.add(new JLabel("PV : " + character.getPv() + " / " + character.getPvMax()));
    	ligne2.add(boxStat);
    	ligne2.add(panelImagePerso);
    	panelPerso.add(ligne1);
    	panelPerso.add(ligne2);
    	return panelPerso;
    }
    
    private void showCharacterView (Character character, int page) {
    	this.menuPanel = new JPanel();
    	Box panelBox = Box.createHorizontalBox();
    	Box boxPerso = Box.createVerticalBox();
    	PanelImage panelImagePerso = new PanelImage(CharacterImage.getImageMenuFromPersonnage(character), 200, 150);
    	boxPerso.add(panelImagePerso);
    	boxPerso.add(new JLabel(character.getName()));
    	Box boxPersoInfo = Box.createHorizontalBox();
    	Box boxPersoInfoStat = Box.createVerticalBox();
    	boxPersoInfoStat.add(new JLabel(character.getComportementPersonnage().getName()));
    	boxPersoInfoStat.add(new JLabel("Niv " + character.getNiv() + " E " + character.getExperience()));
    	boxPersoInfoStat.add(new JLabel("PV " + character.getPv() + " / " + character.getPvMax()));
    	boxPersoInfo.add(boxPersoInfoStat);
    	PanelImage panelImageMap = new PanelImage(CharacterImage.getImageIconMapFromPersonnage(character), width, height);
    	boxPersoInfo.add(panelImageMap);
    	boxPerso.add(boxPersoInfo);
    	panelBox.add(boxPerso);
    	if (page == 1) {
    		Box attributsBox = Box.createVerticalBox();
    		attributsBox.add(new JLabel("Attributs  1/2"));
    		JPanel panelAttributs = new JPanel(new GridLayout(4, 2));
    		panelAttributs.add(new JLabel("Frc  " + character.getPuissance()));
    		panelAttributs.add(new JLabel("Tec  " + character.getCapacite()));
    		panelAttributs.add(new JLabel("Vit  " + character.getVitesse()));
    		panelAttributs.add(new JLabel("Ch  " + character.getChance()));
    		panelAttributs.add(new JLabel("Déf  " + character.getDef()));
    		panelAttributs.add(new JLabel("Rés  " + character.getResistance()));
    		panelAttributs.add(new JLabel("Mvt  " + character.getMove().getNbDeplacement()));
    		panelAttributs.add(new JLabel("Cons  " + character.getConstitution()));
    		attributsBox.add(panelAttributs);
    		attributsBox.add(new JLabel("C " + character.getNbCombat() + " V " + character.getNbVictoire() + "D " + character.getNbDefaite()));
    		panelBox.add(attributsBox);
    	} else if (page == 2) {
    		Box inventaireBox = Box.createVerticalBox();
    		inventaireBox.add(new JLabel("Inventaire  2/2"));
    		for (Objet objet : character.getObjets()) {
    			if (objet != null) {
    				String equip = objet.equals(character.getArme()) ? " E" : "";
    				inventaireBox.add(new JLabel(objet.getName() + "   " + objet.getUtilisation() + "/"+objet.getUtilisationMax()+ equip));
    			}
    		}
    		Weapon weapon = character.getArme();
            int sRankBonus = weapon.getRang() == Weapon.Rang.S ? 5 : 0;
            int critique = weapon.getCritique() + (character.getCapacite() / 2) + sRankBonus;
            int precision = weapon.getPrecision() + (character.getCapacite() * 2) + (character.getChance() /2) + sRankBonus;
            int vitesseAttaque = character.getVitesse() - (weapon.getPoids() - character.getConstitution());
            int avoid = (vitesseAttaque * 2) + character.getChance();
            JPanel panelEquipement = new JPanel(new GridLayout(3, 2));
    		panelEquipement.add(new JLabel("Equipement"));
    		panelEquipement.add(new JLabel("Pté " + weapon.getPorte()));
    		panelEquipement.add(new JLabel("Dmg  " + (character.getPuissance() + weapon.getPuisance())));
    		panelEquipement.add(new JLabel("Crt  " + critique));
    		panelEquipement.add(new JLabel("Prc  " + precision));
    		panelEquipement.add(new JLabel("Esq  " + avoid));
    		inventaireBox.add(panelEquipement);
    		panelBox.add(inventaireBox);
    	}
    	this.menuPanel.add(panelBox);
    	this.fenetre.ajouterPanel(this.menuPanel);
    	this.fenetreObjectif.hide();
    	this.fenetrePerso.hide();
        this.fenetreTerrain.hide();
    	SimpleKeyAction simpleKeyAction = new SimpleKeyAction();
    	simpleKeyAction.ajouterEcouteur(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (evt.getPropertyName().equals("action")) {
                    action();
                } else if (evt.getPropertyName().equals("annulation")) {
                    annulation();
                } else if (evt.getPropertyName().equals("left")) {
                    left();
                } else if (evt.getPropertyName().equals("right")) {
                    right();
                } else if (evt.getPropertyName().equals("up")) {
                    up();
                } else if (evt.getPropertyName().equals("down")) {
                    down();
                }
            }
        });
        this.fenetre.addKeyBoardManager(new KeyDispatcher(simpleKeyAction));
    }
    
    private void useObject (Character character, Character character2) {
    	this.fenetreMenu.hide();
    	this.fenetreObjet.hide();
    	this.popupPv = new MyPopup(fenetre, Alignement.CENTER);
    	this.panelPvPerso = new PanelPv(character.getPv(), character.getPvMax(), 240, 50);
    	this.popupPv.addComponent(this.panelPvPerso, true);
    	this.panelPvPerso.ajoutePv(character2.getPv() - character.getPv());
    	this.popupPv.hide();
    }
    
    private void hideCharacterView () {
    	this.diplayPanel();
    	this.fenetreTerrain.show();
    	this.fenetreObjectif.show();
    	this.fenetrePerso.show();
    }
    
    private void action () {
        this.chapitre.action();
    }
    
    private void annulation () {
        this.chapitre.annulation();
    }
    
    private void info () {
        this.chapitre.info();
    }
    
    private void left () {
        this.chapitre.left();
    }
    
    private void right () {
        this.chapitre.right();
    }
    
    private void up () {
        this.chapitre.up();
    }
    
    private void down () {
        this.chapitre.down();
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
        	fenetreMenu.hide();
            chapitre.actionPerso(this.actionPerso);
        }
    }
    
    private class boutonOrdre implements ActionListener {
        
        private final Ordre ordre;
        
        public boutonOrdre (Ordre ordre) {
            this.ordre = ordre;
        }
        
        @Override
        public void actionPerformed (ActionEvent event) {
            chapitre.ordre(this.ordre);
            MyPopup popup = new MyPopup(fenetre, Alignement.CENTER);
            JLabel label = null;
            switch (this.ordre) {
            	case immobile:
            		label = new JLabel("Rester à votre position.");
            		break;
            	case plusProche:
            		label = new JLabel("Dirigez vous vers l'adversaire le plus proche.");
            		break;
            	case portee:
            		label = new JLabel("Avancée vers l'adversaire seulement si il est à votre portée.");
            		break;
            	case rien:
            		label = new JLabel("Ne faites rien..");
            		break;
            }
            System.out.println("label.getWidth() : " + label.getWidth());
            popup.addComponent(label, false);
            new PopupDisplay(popup, 2000).start();
        }
    }
    
    private class weaponChoiceButton implements ActionListener {
        
        private final int choice;
        
        public weaponChoiceButton (int choice) {
            this.choice = choice;
        }
        
        @Override
        public void actionPerformed (ActionEvent event) {
        	fenetreMenu.hide();
            chapitre.choiceWeapon(this.choice);
        }
    }
    
    private class boutonChoixObjet implements ActionListener {
        
        private final int indice;
        
        public boutonChoixObjet (int indice) {
            this.indice = indice;
        }
        
        @Override
        public void actionPerformed (ActionEvent event) {
            chapitre.choixObjet(this.indice);
        }
    }
    
    private class boutonUtiliseObjet implements ActionListener {
        
        private final int indice;
        
        public boutonUtiliseObjet (int indice) {
            this.indice = indice;
        }
        
        @Override
        public void actionPerformed (ActionEvent event) {
            chapitre.utiliserObjet(this.indice);
        }
    }
    
    private class boutonEquiperObjet implements ActionListener {
        
        private final int indice;
        
        public boutonEquiperObjet (int indice) {
            this.indice = indice;
        }
        
        @Override
        public void actionPerformed (ActionEvent event) {
            chapitre.equiperObjet(this.indice);
        }
    }
    
    private class boutonJeterObjet implements ActionListener {
        
        private final int indice;
        
        public boutonJeterObjet (int indice) {
            this.indice = indice;
        }
        
        @Override
        public void actionPerformed (ActionEvent event) {
            chapitre.jeterObjet(this.indice);
        }
    }
    
    private class PopupDisplay extends Thread {
    	
    	private MyPopup popup;
    	private int duration;
    	
    	public PopupDisplay (MyPopup popup, int duration) {
    		this.popup = popup;
    		this.duration = duration;
    	}
    	
    	public void run () {
    		this.popup.show();
    		this.attendre(this.duration);
    		this.popup.hide();
    	}
        
        public synchronized void attendre (int time) {
            try {
                this.wait(time);
            } catch (InterruptedException ex) {
                Logger.getLogger(CombatView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    	
    }
    
}
