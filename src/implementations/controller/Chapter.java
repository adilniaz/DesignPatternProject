package implementations.controller;

import implementations.Position;
import implementations.chapters.ActionMenuState;
import implementations.chapters.CharacterViewState;
import implementations.chapters.FightSimulationState;
import implementations.chapters.FreeState;
import implementations.chapters.MenuState;
import implementations.chapters.MoveState;
import implementations.chapters.OtherPhaseState;
import implementations.chapters.UnitsState;
import implementations.chapters.ViewMoveState;
import implementations.chapters.WeaponChoiceState;
import implementations.character.Character;
import implementations.character.Character.Etat;
import implementations.character.FireEmblemCharacterFactory;
import implementations.character.FireEmblemCharacterType;
import implementations.gameplatform.Square;
import implementations.gameplatform.GamePlatform;
import implementations.object.ObjetFactory;
import implementations.object.ObjetType;
import implementations.organizations.Organization;
import implementations.parser.xml.XMLWriter;
import implementations.strategy.AttackNearestStrategy;
import implementations.views.View;
import implementations.views.Window;

import java.util.ArrayList;
import java.util.List;

import abstracts_interfaces.CharacterAbstract;
import abstracts_interfaces.factories.gameplatforms.ZoneAbstract;
import abstracts_interfaces.state.AbstractState;

public class Chapter extends Controller {
    
    private final GamePlatform plateauDeJeu;
    private Window fenetre;
    private final String nom;
    private final String objectif;
    private List<ZoneAbstract> zonesSelectionner;
    private List<ZoneAbstract> zonesAtkSelectionner;
    private CharacterAbstract persoEnCours;
    private CharacterAbstract persoAttaquer;
    private Position currentPosition;
    private Position oldPosition;
    private Tour tour;
    private boolean continuer;
    private Combat combat;
    private int nbTour;
    private AbstractState state;
    private boolean renfortAppeler;
    private Organization organization;
    
    public final String AFFICHE_ACTION_PERSO = "afficheActionPerso";
    public final String AFFICHE_ARMES_PERSO = "afficheArmePerso";
    public final String AFFICHE_ATTAQUE_DISPONIBLE = "afficheAttaqueDisponible";
    public final String AFFICHE_DEPLACEMENT_DISPONIBLE = "afficheDeplacementDisponible";
    public final String AFFICHE_MAP = "afficheMap";
    public final String AFFICHE_MENU = "afficheMenu";
    public final String CANCEL_ACTION_PERSO = "cancelActionPerso";
    public final String CHANGE_TOUR = "changeTour";
    public final String DEPLACE_PERSO = "deplacePerso";
    public final String EFFACE_ATTAQUE_DISPONIBLE = "effaceAttaqueDisponible";
    public final String EFFACE_DEPLACEMENT_DISPONIBLE = "effaceDeplacementDisponible";
    public final String ENLEVE_PERSO = "enlevePerso";
    public final String FREE_STATE = "freeState";
    public final String GAME_OVER = "gameOver";
    public final String HIDE_CHARACTER_VIEW = "hideCaractereView";
    public final String HIDE_MENU = "hideMenu";
    public final String HIDE_STATUS = "hideStatus";
    public final String HIDE_UNITS = "hideUnits";
    public final String PARTIE_SUSPENDU = "partieSuspendu";
    public final String SHOW_CHARACTER_VIEW = "showCaractereView";
    public final String SIMULATION_COMBAT = "simulationCombat";
    public final String STATUS = "status";
    public final String SUSPENDRE = "suspendre";
    public final String UNITES = "unites";
    public final String VICTOIRE = "victoire";
    
    public enum menu {
        unite, statut, renfort, ordre, suspen, fin;
    }
    
    public enum actionPerso {
        attaquer, objet, echange, attendre;
    }
    
    public enum Tour {
    	perso, ennemie, annexes;
    }
    
    public Chapter (String nom, GamePlatform plateauDeJeu, Organization organization, String objectif) {
        this.nom = nom;
        this.plateauDeJeu = plateauDeJeu;
        this.objectif = objectif;
        this.nbTour = 1;
        this.organization = organization;
        this.renfortAppeler = false;
        this.state = new FreeState(this);
    }
    
    public Window getFenetre () {
    	return this.fenetre;
    }
    
    public void setFenetre (Window fenetre) {
        this.fenetre = fenetre;
    }

    public String getNom() {
        return nom;
    }

    public String getObjectif() {
        return objectif;
    }

    public GamePlatform getPlateauDeJeu() {
        return plateauDeJeu;
    }
    
    public void setPersoAttaquer (CharacterAbstract characterAbstract) {
    	this.persoAttaquer = characterAbstract;
    }
    
    public int getTour () {
    	return this.nbTour;
    }
    
    public void setTour (int tour) {
    	this.nbTour = tour;
    }
    
    public void setCurrentPosition (Position position) {
    	this.currentPosition = position;
    }
    
    public void run () {
        this.pcsControlleurVue.firePropertyChange(AFFICHE_MAP, null, null);
        this.tour = Tour.perso;
        this.continuer = true;
        while (!this.fin) {
        	if (this.continuer) {
        		this.continuer = false;
        		this.continuer();
        	}
        	this.attendre(100);
        }
        this.finPartie();
    }
    
    private void finPartie () {
    	if (this.plateauDeJeu.getEnnemies().isEmpty()) {
    		this.pcsControlleurVue.firePropertyChange(VICTOIRE, null, null);
    	} else if (this.plateauDeJeu.getPersonnages().isEmpty()) {
    		this.pcsControlleurVue.firePropertyChange(GAME_OVER, null, null);
    	}
    }
    
    public void doContinue () {
    	this.continuer = true;
    }
    
    public void continuer () {
    	System.out.println("continuer : " + this.tour);
    	switch (this.tour) {
			case perso:
				boolean hasPersoNormal = false;
				for (CharacterAbstract perso : this.plateauDeJeu.getPersonnages()) {
					if (((Character)perso).getEtat() == Etat.normal) {
						hasPersoNormal = true;
						break;
					}
				}
				if (!hasPersoNormal) {
					this.tour = Tour.ennemie;
					this.state = new OtherPhaseState(this);
					for (CharacterAbstract perso : this.plateauDeJeu.getPersonnages()) {
						((Character)perso).setEtat(Etat.normal);
						this.pcsControlleurVue.firePropertyChange(DEPLACE_PERSO, perso, null);
					}
					this.pcsControlleurVue.firePropertyChange(CHANGE_TOUR, this.tour, null);
				}
				break;
			case ennemie:
				System.out.println("ennemies : " + this.plateauDeJeu.getEnnemies().size());
				for (int i = 0 ; i < this.plateauDeJeu.getEnnemies().size() ; i++) {
					Character p = (Character) this.plateauDeJeu.getEnnemies().get(i);
					System.out.println("ennemie : " + p.getName());
					this.persoEnCours = p;
					p.getStrategie().run(this);
					if (p.estKo()) {
						pcsControlleurVue.firePropertyChange(ENLEVE_PERSO, p, null);
						this.plateauDeJeu.getEnnemies().remove(p);
						i--;
					} else {
						p.setEtat(Etat.attendre);
		                this.pcsControlleurVue.firePropertyChange(DEPLACE_PERSO, p, null);
					}
					this.attendre(2000);
				}
				if (this.plateauDeJeu.getEnnemies().isEmpty()) {
					this.fin = true;
				} else {
					this.tour = Tour.annexes;
					this.pcsControlleurVue.firePropertyChange(CHANGE_TOUR, this.tour, null);
					for (CharacterAbstract perso : this.plateauDeJeu.getEnnemies()) {
						((Character)perso).setEtat(Etat.normal);
						this.pcsControlleurVue.firePropertyChange(DEPLACE_PERSO, perso, null);
					}
				}
				break;
			case annexes:
				for (int i = 0 ; i < this.plateauDeJeu.getAnnexes().size() ; i++) {
					Character p = (Character) this.plateauDeJeu.getAnnexes().get(i);
					this.persoEnCours = p;
					p.getStrategie().run(this);
					if (p.estKo()) {
						pcsControlleurVue.firePropertyChange(ENLEVE_PERSO, p, null);
						this.plateauDeJeu.getAnnexes().remove(p);
						i--;
					} else {
						p.setEtat(Etat.attendre);
		                this.pcsControlleurVue.firePropertyChange(DEPLACE_PERSO, p, null);
					}
					this.attendre(2000);
				}
				this.tour = Tour.perso;
				this.pcsControlleurVue.firePropertyChange(CHANGE_TOUR, this.tour, null);
				this.renfortAppeler = false;
				this.state = new FreeState(this);
				this.nbTour++;
				for (CharacterAbstract perso : this.plateauDeJeu.getAnnexes()) {
					((Character)perso).setEtat(Etat.normal);
					this.pcsControlleurVue.firePropertyChange(DEPLACE_PERSO, perso, null);
				}
				break;
		}
    }
    
    public void action () {
    	System.out.println(this.state.getClass().getName());
    	this.state.action();
    }
    
    public void annulation () {
    	this.state.cancel();
    }
    
    public void info () {
    	this.state.info();
    }
    
    public void freeStateAction () {
    	boolean aPerso = false;
        for (CharacterAbstract perso : this.plateauDeJeu.getPersonnages()) {
            Character p = (Character) perso;
            if (p.getPosition().equals(this.currentPosition) && p.getEtat() == Etat.normal) {
                aPerso = true;
                List<ZoneAbstract> zones = p.getMove().getCaseAvailable(this.plateauDeJeu, p);
                List<ZoneAbstract> zonesAtk = this.getPorteAttaque(zones, this.plateauDeJeu.getZones());
                this.pcsControlleurVue.firePropertyChange(AFFICHE_DEPLACEMENT_DISPONIBLE, zones, null);
                this.pcsControlleurVue.firePropertyChange(AFFICHE_ATTAQUE_DISPONIBLE, zonesAtk, null);
                this.persoEnCours = p;
                this.zonesSelectionner = zones;
                this.zonesAtkSelectionner = zonesAtk;
                this.state = new MoveState(this);
                break;
            }
        }
        if (!aPerso) {
        	for (CharacterAbstract perso : this.plateauDeJeu.getEnnemies()) {
                Character p = (Character) perso;
                if (p.getPosition().equals(this.currentPosition)) {
                    aPerso = true;
                    List<ZoneAbstract> zones = p.getMove().getCaseAvailable(this.plateauDeJeu, p);
                    List<ZoneAbstract> zonesAtk = this.getPorteAttaque(zones, this.plateauDeJeu.getZones());
                    this.pcsControlleurVue.firePropertyChange(AFFICHE_DEPLACEMENT_DISPONIBLE, zones, null);
                    this.pcsControlleurVue.firePropertyChange(AFFICHE_ATTAQUE_DISPONIBLE, zonesAtk, null);
                    this.persoEnCours = p;
                    this.zonesSelectionner = zones;
                    this.zonesAtkSelectionner = zonesAtk;
                    this.state = new ViewMoveState(this);
                    break;
                }
            }
        }
        if (!aPerso) {
        	for (CharacterAbstract perso : this.plateauDeJeu.getAnnexes()) {
                Character p = (Character) perso;
                if (p.getPosition().equals(this.currentPosition)) {
                    aPerso = true;
                    List<ZoneAbstract> zones = p.getMove().getCaseAvailable(this.plateauDeJeu, p);
                    List<ZoneAbstract> zonesAtk = this.getPorteAttaque(zones, this.plateauDeJeu.getZones());
                    this.pcsControlleurVue.firePropertyChange(AFFICHE_DEPLACEMENT_DISPONIBLE, zones, null);
                    this.pcsControlleurVue.firePropertyChange(AFFICHE_ATTAQUE_DISPONIBLE, zonesAtk, null);
                    this.persoEnCours = p;
                    this.zonesSelectionner = zones;
                    this.zonesAtkSelectionner = zonesAtk;
                    this.state = new ViewMoveState(this);
                    break;
                }
            }
        }
        if (!aPerso) {
        	menu[] menus;
        	if (this.renfortAppeler) {
        		menus = new menu[menu.values().length-1];
        		int indice = 0;
        		for (menu m : menu.values()) {
        			if (m != menu.renfort) {
        				menus[indice] = m;
        				indice++;
        			}
        		}
        	} else if (this.plateauDeJeu.getAnnexes().isEmpty()) {
        		menus = new menu[menu.values().length-1];
        		int indice = 0;
        		for (menu m : menu.values()) {
        			if (m != menu.ordre) {
        				menus[indice] = m;
        				indice++;
        			}
        		}
        	} else {
        		menus = menu.values();
        	}
            this.pcsControlleurVue.firePropertyChange(AFFICHE_MENU, menus, null);
            this.state = new MenuState(this);
        }
    }
    
    public void freeStateInfo () {
    	for (CharacterAbstract perso : this.plateauDeJeu.getPersonnages()) {
            Character p = (Character) perso;
            if (p.getPosition().equals(this.currentPosition)) {
                this.pcsControlleurVue.firePropertyChange(SHOW_CHARACTER_VIEW, p, 1);
                this.persoEnCours = p;
                this.state = new CharacterViewState(this);
                break;
            }
        }
    	for (CharacterAbstract perso : this.plateauDeJeu.getAnnexes()) {
            Character p = (Character) perso;
            if (p.getPosition().equals(this.currentPosition)) {
                this.pcsControlleurVue.firePropertyChange(SHOW_CHARACTER_VIEW, p, 1);
                this.persoEnCours = p;
                this.state = new CharacterViewState(this);
                break;
            }
        }
    	for (CharacterAbstract perso : this.plateauDeJeu.getEnnemies()) {
            Character p = (Character) perso;
            if (p.getPosition().equals(this.currentPosition)) {
                this.pcsControlleurVue.firePropertyChange(SHOW_CHARACTER_VIEW, p, 1);
                this.persoEnCours = p;
                this.state = new CharacterViewState(this);
                break;
            }
        }
    }
    
    public void menuStateCancel () {
    	this.pcsControlleurVue.firePropertyChange(HIDE_MENU, null, null);
    	this.state = new FreeState(this);
    }
    
    public void moveStateAction () {
    	boolean aPerso2 = false;
        for (CharacterAbstract perso : this.plateauDeJeu.getPersonnages()) {
            Character p = (Character) perso;
            if (p.getPosition().equals(this.currentPosition) && !p.equals(this.persoEnCours)) {
                aPerso2 = true;
                break;
            }
        }
        if (!aPerso2) {
            Character p = (Character) this.persoEnCours;
            this.deplacePerso(p, this.currentPosition);
            List<actionPerso> list = new ArrayList<>();
            list.add(actionPerso.attaquer);
            list.add(actionPerso.objet);
            list.add(actionPerso.echange);
            list.add(actionPerso.attendre);
            actionPerso actions[] = new actionPerso[list.size()];
            for (int i =  0 ; i < list.size() ; i++) {
                actions[i] = list.get(i);
            }
            this.pcsControlleurVue.firePropertyChange(AFFICHE_ACTION_PERSO, actions, null);
            this.pcsControlleurVue.firePropertyChange(EFFACE_DEPLACEMENT_DISPONIBLE, this.zonesSelectionner, null);
            this.pcsControlleurVue.firePropertyChange(EFFACE_ATTAQUE_DISPONIBLE, this.zonesAtkSelectionner, null);
            this.state = new ActionMenuState(this);
        }
    }
    
    public void moveStateCancel () {
    	this.pcsControlleurVue.firePropertyChange(EFFACE_DEPLACEMENT_DISPONIBLE, this.zonesSelectionner, null);
        this.pcsControlleurVue.firePropertyChange(EFFACE_ATTAQUE_DISPONIBLE, this.zonesAtkSelectionner, null);
        this.state = new FreeState(this);
    }
    
    public void actionMenuStateCancel () {
    	Character perso = (Character) this.persoEnCours;
        Position pos = new Position(perso.getPosition());
        perso.setPosition(this.oldPosition);
        this.pcsControlleurVue.firePropertyChange(DEPLACE_PERSO, this.persoEnCours, pos);
        this.pcsControlleurVue.firePropertyChange(AFFICHE_DEPLACEMENT_DISPONIBLE, this.zonesSelectionner, null);
        this.pcsControlleurVue.firePropertyChange(AFFICHE_ATTAQUE_DISPONIBLE, this.zonesAtkSelectionner, null);
        this.pcsControlleurVue.firePropertyChange(CANCEL_ACTION_PERSO, null, null);
        this.state = new MoveState(this);
    }
    
    public void characterViewStateCancel () {
    	this.pcsControlleurVue.firePropertyChange(HIDE_CHARACTER_VIEW, null, null);
    	this.state = new FreeState(this);
    }
    
    public void unitsStateCancel () {
    	this.pcsControlleurVue.firePropertyChange(HIDE_UNITS, null, null);
    	this.state = new FreeState(this);
    }
    
    public void statusStateCancel () {
    	this.pcsControlleurVue.firePropertyChange(HIDE_STATUS, null, null);
    	this.state = new FreeState(this);
    }
    
    public void weaponChoiceStateCancel () {
    	List<actionPerso> list = new ArrayList<>();
        list.add(actionPerso.attaquer);
        list.add(actionPerso.objet);
        list.add(actionPerso.echange);
        list.add(actionPerso.attendre);
        actionPerso actions[] = new actionPerso[list.size()];
        for (int i =  0 ; i < list.size() ; i++) {
            actions[i] = list.get(i);
        }
    	this.pcsControlleurVue.firePropertyChange(AFFICHE_ACTION_PERSO, actions, null);
        this.state = new ActionMenuState(this);
    }
    
    public void choiceWeapon (int indice) {
    	Character p = (Character) this.persoEnCours;
    	p.setArme(indice);
    	this.simuleCombat();
    }
    
    public void simuleCombat () {
        for (CharacterAbstract perso : this.plateauDeJeu.getEnnemies()) {
            Character p = (Character) perso;
            Character p2 = (Character) this.persoEnCours;
            if (p.getPosition().equals(new Position(p2.getPosition().getPositionX()-1, p2.getPosition().getPositionY()))) {
                this.combat = new Combat(p2, p, this);
                View.createVue(combat, this.fenetre);
                this.combat.simulerCombat();
            } else if (p.getPosition().equals(new Position(p2.getPosition().getPositionX()+1, p2.getPosition().getPositionY()))) {
                this.combat = new Combat(p2, p, this);
                View.createVue(combat, this.fenetre);
                this.combat.simulerCombat();
            } else if (p.getPosition().equals(new Position(p2.getPosition().getPositionX(), p2.getPosition().getPositionY()-1))) {
                this.combat = new Combat(p2, p, this);
                View.createVue(combat, this.fenetre);
                this.combat.simulerCombat();
            } else if (p.getPosition().equals(new Position(p2.getPosition().getPositionX(), p2.getPosition().getPositionY()+1))) {
                this.combat = new Combat(p2, p, this);
                View.createVue(combat, this.fenetre);
                this.combat.simulerCombat();
            }
        }
        this.pcsControlleurVue.firePropertyChange(SIMULATION_COMBAT, null, null);
        this.state = new FightSimulationState(this);
    }
    
    public void fightSimulationStateCancel () {
    	this.combat.finSimulation();
    	this.pcsControlleurVue.firePropertyChange(AFFICHE_ARMES_PERSO, this.persoEnCours, null);
        this.state = new WeaponChoiceState(this);
    }
    
    public void combat () {
        for (CharacterAbstract perso : this.plateauDeJeu.getEnnemies()) {
            Character p = (Character) perso;
            Character p2 = (Character) this.persoEnCours;
            if (p.getPosition().equals(new Position(p2.getPosition().getPositionX()-1, p2.getPosition().getPositionY()))) {
            	this.persoAttaquer = p;
            	this.combat.finSimulation();
                new RunControlleur(combat).start();
                p2.setEtat(Etat.attendre);
                this.pcsControlleurVue.firePropertyChange(DEPLACE_PERSO, p2, null);
                this.state = new FreeState(this);
                this.pcsControlleurVue.firePropertyChange(FREE_STATE, null, null);
                break;
            } else if (p.getPosition().equals(new Position(p2.getPosition().getPositionX()+1, p2.getPosition().getPositionY()))) {
            	this.persoAttaquer = p;
            	this.combat.finSimulation();
                new RunControlleur(combat).start();
                p2.setEtat(Etat.attendre);
                this.pcsControlleurVue.firePropertyChange(DEPLACE_PERSO, p2, null);
                this.state = new FreeState(this);
                this.pcsControlleurVue.firePropertyChange(FREE_STATE, null, null);
                break;
            } else if (p.getPosition().equals(new Position(p2.getPosition().getPositionX(), p2.getPosition().getPositionY()+1))) {
            	this.persoAttaquer = p;
            	this.combat.finSimulation();
                new RunControlleur(combat).start();
                p2.setEtat(Etat.attendre);
                this.pcsControlleurVue.firePropertyChange(DEPLACE_PERSO, p2, null);
                this.state = new FreeState(this);
                this.pcsControlleurVue.firePropertyChange(FREE_STATE, null, null);
                break;
            } else if (p.getPosition().equals(new Position(p2.getPosition().getPositionX(), p2.getPosition().getPositionY()-1))) {
            	this.persoAttaquer = p;
            	this.combat.finSimulation();
                new RunControlleur(combat).start();
                p2.setEtat(Etat.attendre);
                this.pcsControlleurVue.firePropertyChange(DEPLACE_PERSO, p2, null);
                this.state = new FreeState(this);
                this.pcsControlleurVue.firePropertyChange(FREE_STATE, null, null);
                break;
            }
        }
    }
    
    public void deplacePerso (Character perso, Position p) {
    	this.oldPosition = new Position(perso.getPosition());
    	perso.setPosition(p);
        this.pcsControlleurVue.firePropertyChange(DEPLACE_PERSO, perso, this.oldPosition);
    }
    
    public List<ZoneAbstract> getPorteAttaque (List<ZoneAbstract> zones, List<ZoneAbstract> allZones) {
        List<ZoneAbstract> zonesAtk = new ArrayList<>();
        for (ZoneAbstract zone : zones) {
            Square c = (Square) zone;
            Position p1 = new Position(c.getPosition().getPositionX()+1, c.getPosition().getPositionY());
            this.addZoneAttaque(zones, allZones, zonesAtk, p1);
            Position p2 = new Position(c.getPosition().getPositionX()-1, c.getPosition().getPositionY());
            this.addZoneAttaque(zones, allZones, zonesAtk, p2);
            Position p3 = new Position(c.getPosition().getPositionX(), c.getPosition().getPositionY()+1);
            this.addZoneAttaque(zones, allZones, zonesAtk, p3);
            Position p4 = new Position(c.getPosition().getPositionX(), c.getPosition().getPositionY()-1);
            this.addZoneAttaque(zones, allZones, zonesAtk, p4);
        }
        return zonesAtk;
    }
    
    public void addZoneAttaque (List<ZoneAbstract> zones, List<ZoneAbstract> allZones, List<ZoneAbstract> zonesAtk, Position p) {
        boolean find = false;
        for (ZoneAbstract ztmp : zonesAtk) {
            Square ctmp = (Square) ztmp;
            if (ctmp.getPosition().equals(p)) {
                find = true;
                break;
            }
        }
        if (!find) {
            for (ZoneAbstract ztmp : zones) {
                Square ctmp = (Square) ztmp;
                if (ctmp.getPosition().equals(p)) {
                    find = true;
                    break;
                }
            }
        }
        if (!find) {
            for (ZoneAbstract ztmp : allZones) {
                Square ctmp = (Square) ztmp;
                if (ctmp.getPosition().equals(p)) {
                    zonesAtk.add(ctmp);
                    break;
                }
            }
        }
    }
    
    public void menu (menu m) {
        switch (m) {
            case unite:
            	this.pcsControlleurVue.firePropertyChange(UNITES, null, null);
            	this.state = new UnitsState(this);
               break;
            case statut:
            	this.pcsControlleurVue.firePropertyChange(STATUS, null, null);
               break;
            case renfort:
            	if (!this.renfortAppeler) {
            		FireEmblemCharacterFactory characterFactory = new FireEmblemCharacterFactory();
            		ObjetFactory objetFactory = new ObjetFactory();
            		Character character = (Character)characterFactory.createCharacter("chevalier", this.organization, FireEmblemCharacterType.chevalier);
            		character.setStrategie(new AttackNearestStrategy(character));
            		character.ajouterObjet(objetFactory.createObjet("lance-fer", ObjetType.hache_fer));
            		character.setPosition(new Position(19, 8));
            		this.plateauDeJeu.ajouterAnnexe(character);
            		this.pcsControlleurVue.firePropertyChange(DEPLACE_PERSO, character, null);
            		this.renfortAppeler = true;
            		this.menuStateCancel();
            	}
            	break;
            case ordre:
            	break;
            case suspen:
            	this.pcsControlleurVue.firePropertyChange(SUSPENDRE, null, null);
            	XMLWriter xmlWriter = new XMLWriter();
            	xmlWriter.write(this);
            	this.pcsControlleurVue.firePropertyChange(PARTIE_SUSPENDU, null, null);
            	this.fin = true;
               break;
            case fin:
            	for (CharacterAbstract perso : this.plateauDeJeu.getPersonnages()) {
            		((Character)perso).setEtat(Etat.attendre);
            		this.pcsControlleurVue.firePropertyChange(DEPLACE_PERSO, perso, null);
            	}
            	this.menuStateCancel();
            	this.continuer = true;
               break;
        }
    }
    
    public void actionPerso (actionPerso action) {
        switch (action) {
            case attaquer:
                this.pcsControlleurVue.firePropertyChange(AFFICHE_ARMES_PERSO, this.persoEnCours, null);
                this.state = new WeaponChoiceState(this);
                break;
            case objet:
                break;
            case echange:
                break;
            case attendre:
            	((Character)persoEnCours).setEtat(Etat.attendre);
            	this.pcsControlleurVue.firePropertyChange(DEPLACE_PERSO, this.persoEnCours, null);
            	this.state = new FreeState(this);
            	this.pcsControlleurVue.firePropertyChange(FREE_STATE, null, null);
            	this.continuer = true;
            	break;
        }
    }
    
    public void verifMort () {
    	if (((Character)this.persoAttaquer).estKo()) {
			pcsControlleurVue.firePropertyChange(ENLEVE_PERSO, this.persoAttaquer, null);
			if (this.plateauDeJeu.getAnnexes().contains(this.persoAttaquer)) {
				this.plateauDeJeu.getAnnexes().remove(this.persoAttaquer);
			} else if (this.plateauDeJeu.getEnnemies().contains(this.persoAttaquer)) {
				this.plateauDeJeu.getEnnemies().remove(this.persoAttaquer);
			} else if (this.plateauDeJeu.getPersonnages().contains(this.persoAttaquer)) {
				this.plateauDeJeu.getPersonnages().remove(this.persoAttaquer);
			}
		}
    	if (((Character)this.persoEnCours).estKo()) {
			pcsControlleurVue.firePropertyChange(ENLEVE_PERSO, this.persoEnCours, null);
			if (this.plateauDeJeu.getAnnexes().contains(this.persoEnCours)) {
				this.plateauDeJeu.getAnnexes().remove(this.persoEnCours);
			} else if (this.plateauDeJeu.getEnnemies().contains(this.persoEnCours)) {
				this.plateauDeJeu.getEnnemies().remove(this.persoEnCours);
			} else if (this.plateauDeJeu.getPersonnages().contains(this.persoEnCours)) {
				this.plateauDeJeu.getPersonnages().remove(this.persoEnCours);
			}
		}
    }
	
	public class RunControlleur extends Thread {
		
		private Controller controleur;
		
		public RunControlleur(Controller controleur) {
			this.controleur = controleur;
		}
		
		@Override
		public void run () {
			this.controleur.run();
			((Character)persoEnCours).setEtat(Etat.attendre);
			pcsControlleurVue.firePropertyChange(DEPLACE_PERSO, persoEnCours, null);
			verifMort();
			continuer = true;
		}
		
	}
    
}
