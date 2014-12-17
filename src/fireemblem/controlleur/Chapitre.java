package fireemblem.controlleur;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import abstracts_interfaces.CharacterAbstract;
import abstracts_interfaces.factories.gameplatforms.ZoneAbstract;
import fireemblem.Position;
import fireemblem.connexionBD.Connexion;
import fireemblem.connexionBD.ConnexionBD;
import fireemblem.personnage.Personnage;
import fireemblem.plateauJeu.Case;
import fireemblem.plateauJeu.PlateauJeu;
import fireemblem.plateauJeu.PlateauJeu.Etat;
import fireemblem.swing.Fenetre;
import fireemblem.swing.Vues;

public class Chapitre extends Controlleur {
    
    private final PlateauJeu plateauDeJeu;
    private Fenetre fenetre;
    private final String nom;
    private final String objectif;
    private Mode mode;
    private List<ZoneAbstract> zonesSelectionner;
    private List<ZoneAbstract> zonesAtkSelectionner;
    private CharacterAbstract persoEnCours;
    private CharacterAbstract persoAttaquer;
    private Position oldPosition;
    private Tour tour;
    private boolean continuer;
    private Combat combat;
    private int nbTour;
    
    public final String AFFICHE_ACTION_PERSO = "afficheActionPerso";
    public final String AFFICHE_ARMES_PERSO = "afficheArmePerso";
    public final String AFFICHE_ATTAQUE_DISPONIBLE = "afficheAttaqueDisponible";
    public final String AFFICHE_DEPLACEMENT_DISPONIBLE = "afficheDeplacementDisponible";
    public final String AFFICHE_MAP = "afficheMap";
    public final String AFFICHE_MENU = "afficheMenu";
    public final String CHANGE_TOUR = "changeTour";
    public final String DEPLACE_PERSO = "deplacePerso";
    public final String EFFACE_ATTAQUE_DISPONIBLE = "effaceAttaqueDisponible";
    public final String EFFACE_DEPLACEMENT_DISPONIBLE = "effaceDeplacementDisponible";
    public final String ENLEVE_PERSO = "enlevePerso";
    public final String GAME_OVER = "gameOver";
    public final String PARTIE_SUSPENDU = "partieSuspendu";
    public final String STATUS = "status";
    public final String SUSPENDRE = "suspendre";
    public final String UNITES = "unites";
    public final String VICTOIRE = "victoire";
    
    public enum menu {
        unite, statut, suspen, fin;
    }
    
    public enum actionPerso {
        attaquer, baton, objet, echange, convoi, attendre;
    }
    
    private enum Mode {
        libre, deplacement, action, arme;
    }
    
    public enum Tour {
    	perso, ennemie, annexes;
    }
    
    public Chapitre (String nom, PlateauJeu plateauDeJeu, String objectif) {
        this.nom = nom;
        this.plateauDeJeu = plateauDeJeu;
        this.mode = Mode.libre;
        this.objectif = objectif;
        this.nbTour = 1;
    }
    
    public Fenetre getFenetre () {
    	return this.fenetre;
    }
    
    public void setFenetre (Fenetre fenetre) {
        this.fenetre = fenetre;
    }

    public String getNom() {
        return nom;
    }

    public String getObjectif() {
        return objectif;
    }

    public PlateauJeu getPlateauDeJeu() {
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
					if (this.plateauDeJeu.getEtatByCharacter(perso) == Etat.normal) {
						hasPersoNormal = true;
						break;
					}
				}
				if (!hasPersoNormal) {
					this.tour = Tour.ennemie;
					for (CharacterAbstract perso : this.plateauDeJeu.getPersonnages()) {
						this.plateauDeJeu.changeEtatCharacter(perso, Etat.normal);
					}
					this.pcsControlleurVue.firePropertyChange(CHANGE_TOUR, this.tour, null);
				}
				break;
			case ennemie:
				System.out.println("ennemies : " + this.plateauDeJeu.getEnnemies().size());
				for (int i = 0 ; i < this.plateauDeJeu.getEnnemies().size() ; i++) {
					Personnage p = (Personnage) this.plateauDeJeu.getEnnemies().get(i);
					System.out.println("ennemie : " + p.getName());
					p.getStrategie().run(this);
					if (p.estKo()) {
						pcsControlleurVue.firePropertyChange(ENLEVE_PERSO, p, null);
						this.plateauDeJeu.getEnnemies().remove(p);
						i--;
					}
				}
				if (this.plateauDeJeu.getEnnemies().isEmpty()) {
					this.fin = true;
				} else {
					this.tour = Tour.annexes;
					this.pcsControlleurVue.firePropertyChange(CHANGE_TOUR, this.tour, null);
				}
				break;
			case annexes:
				for (CharacterAbstract perso : this.plateauDeJeu.getAnnexes()) {
					Personnage p = (Personnage) perso;
					p.getStrategie().run(this);
				}
				this.tour = Tour.perso;
				this.pcsControlleurVue.firePropertyChange(CHANGE_TOUR, this.tour, null);
				this.mode = Mode.libre;
				this.nbTour++;
				break;
		}
    }
    
    public void action (Position pos) {
        switch (this.mode) {
            case libre:
                boolean aPerso = false;
                for (CharacterAbstract perso : this.plateauDeJeu.getPersonnages()) {
                    Personnage p = (Personnage) perso;
                    if (p.getPosition().equals(pos)) {
                        aPerso = true;
                        List<ZoneAbstract> zones = p.getMove().getCaseAvailable(this.plateauDeJeu, p);
                        List<ZoneAbstract> zonesAtk = this.getPorteAttaque(zones, this.plateauDeJeu.getZones());
                        this.pcsControlleurVue.firePropertyChange(AFFICHE_DEPLACEMENT_DISPONIBLE, zones, null);
                        this.pcsControlleurVue.firePropertyChange(AFFICHE_ATTAQUE_DISPONIBLE, zonesAtk, null);
                        this.mode = Mode.deplacement;
                        this.persoEnCours = p;
                        this.zonesSelectionner = zones;
                        this.zonesAtkSelectionner = zonesAtk;
                        break;
                    }
                }
                if (!aPerso) {
                    this.pcsControlleurVue.firePropertyChange(AFFICHE_MENU, menu.values(), null);
                }
                break;
            case deplacement :
                boolean aPerso2 = false;
                for (CharacterAbstract perso : this.plateauDeJeu.getPersonnages()) {
                    Personnage p = (Personnage) perso;
                    if (p.getPosition().equals(pos) && !p.equals(this.persoEnCours)) {
                        aPerso2 = true;
                        break;
                    }
                }
                if (!aPerso2) {
                    Personnage p = (Personnage) this.persoEnCours;
                    this.deplacePerso(p, pos);
                    List<actionPerso> list = new ArrayList<>();
                    list.add(actionPerso.attaquer);
                    list.add(actionPerso.baton);
                    list.add(actionPerso.objet);
                    list.add(actionPerso.convoi);
                    list.add(actionPerso.echange);
                    list.add(actionPerso.attendre);
                    actionPerso actions[] = new actionPerso[list.size()];
                    for (int i =  0 ; i < list.size() ; i++) {
                        actions[i] = list.get(i);
                    }
                    this.pcsControlleurVue.firePropertyChange(AFFICHE_ACTION_PERSO, actions, null);
                    this.pcsControlleurVue.firePropertyChange(EFFACE_DEPLACEMENT_DISPONIBLE, this.zonesSelectionner, null);
                    this.pcsControlleurVue.firePropertyChange(EFFACE_ATTAQUE_DISPONIBLE, this.zonesAtkSelectionner, null);
                    this.mode = Mode.action;
                }
                break;
            case action :
            	break;
            case arme:
            	break;
        }
    }
    
    public void deplacePerso (Personnage perso, Position p) {
    	this.oldPosition = new Position(perso.getPosition());
    	perso.setPosition(p);
        this.pcsControlleurVue.firePropertyChange(DEPLACE_PERSO, perso, this.oldPosition);
    }
    
    public List<ZoneAbstract> getPorteAttaque (List<ZoneAbstract> zones, List<ZoneAbstract> allZones) {
        List<ZoneAbstract> zonesAtk = new ArrayList<>();
        for (ZoneAbstract zone : zones) {
            Case c = (Case) zone;
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
            Case ctmp = (Case) ztmp;
            if (ctmp.getPosition().equals(p)) {
                find = true;
                break;
            }
        }
        if (!find) {
            for (ZoneAbstract ztmp : zones) {
                Case ctmp = (Case) ztmp;
                if (ctmp.getPosition().equals(p)) {
                    find = true;
                    break;
                }
            }
        }
        if (!find) {
            for (ZoneAbstract ztmp : allZones) {
                Case ctmp = (Case) ztmp;
                if (ctmp.getPosition().equals(p)) {
                    zonesAtk.add(ctmp);
                    break;
                }
            }
        }
    }
    
    public void annulation () {
        switch (this.mode) {
        	case libre:
        		break;
            case deplacement:
                this.pcsControlleurVue.firePropertyChange(EFFACE_DEPLACEMENT_DISPONIBLE, this.zonesSelectionner, null);
                this.pcsControlleurVue.firePropertyChange(EFFACE_ATTAQUE_DISPONIBLE, this.zonesAtkSelectionner, null);
                this.mode = Mode.libre;
                break;
            case action:
                Personnage perso = (Personnage) this.persoEnCours;
                Position pos = new Position(perso.getPosition());
                perso.setPosition(this.oldPosition);
                this.pcsControlleurVue.firePropertyChange(DEPLACE_PERSO, this.persoEnCours, pos);
                this.pcsControlleurVue.firePropertyChange(AFFICHE_DEPLACEMENT_DISPONIBLE, this.zonesSelectionner, null);
                this.pcsControlleurVue.firePropertyChange(AFFICHE_ATTAQUE_DISPONIBLE, this.zonesAtkSelectionner, null);
                this.mode = Mode.deplacement;
                break;
            case arme:
            	break;
        }
    }
    
    public void menu (menu m) {
        switch (m) {
            case unite:
            	this.pcsControlleurVue.firePropertyChange(UNITES, null, null);
               break;
            case statut:
            	this.pcsControlleurVue.firePropertyChange(STATUS, null, null);
               break;
            case suspen:
            	this.pcsControlleurVue.firePropertyChange(SUSPENDRE, null, null);
            	ConnexionBD connexionBD = new ConnexionBD();
            	Connection connection = connexionBD.getConnexionHSQL("fireemblem", "sa", "");
            	Connexion connexion = new Connexion(connection);
            	connexion.savePartie(this);
            	connexionBD.fermerConnexionHSQL();
            	this.pcsControlleurVue.firePropertyChange(PARTIE_SUSPENDU, null, null);
            	this.fin = true;
               break;
            case fin:
            	for (CharacterAbstract perso : this.plateauDeJeu.getPersonnages()) {
            		this.plateauDeJeu.changeEtatCharacter(perso, Etat.attendre);
            	}
            	this.continuer = true;
               break;
        }
    }
    
    public void actionPerso (actionPerso action) {
        switch (action) {
            case attaquer:
                this.pcsControlleurVue.firePropertyChange(AFFICHE_ARMES_PERSO, this.persoEnCours, null);
                break;
            case baton:
                break;
            case objet:
                break;
            case echange:
                break;
            case convoi:
                break;
            case attendre:
            	this.plateauDeJeu.changeEtatCharacter(this.persoEnCours, Etat.attendre);
            	this.mode = Mode.libre;
            	this.continuer = true;
            	break;
        }
    }
    
    public void simuleCombat () {
        for (CharacterAbstract perso : this.plateauDeJeu.getEnnemies()) {
            Personnage p = (Personnage) perso;
            Personnage p2 = (Personnage) this.persoEnCours;
            if (p.getPosition().equals(new Position(p2.getPosition().getPositionX()-1, p2.getPosition().getPositionY()))) {
                this.combat = new Combat(p2, p, this);
                Vues.createVue(combat, this.fenetre);
                this.combat.simulerCombat();
            } else if (p.getPosition().equals(new Position(p2.getPosition().getPositionX()+1, p2.getPosition().getPositionY()))) {
                this.combat = new Combat(p2, p, this);
                Vues.createVue(combat, this.fenetre);
                this.combat.simulerCombat();
            } else if (p.getPosition().equals(new Position(p2.getPosition().getPositionX(), p2.getPosition().getPositionY()-1))) {
                this.combat = new Combat(p2, p, this);
                Vues.createVue(combat, this.fenetre);
                this.combat.simulerCombat();
            } else if (p.getPosition().equals(new Position(p2.getPosition().getPositionX(), p2.getPosition().getPositionY()+1))) {
                this.combat = new Combat(p2, p, this);
                Vues.createVue(combat, this.fenetre);
                this.combat.simulerCombat();
            }
        }
    }
    
    public void combat () {
        for (CharacterAbstract perso : this.plateauDeJeu.getEnnemies()) {
            Personnage p = (Personnage) perso;
            Personnage p2 = (Personnage) this.persoEnCours;
            if (p.getPosition().equals(new Position(p2.getPosition().getPositionX()-1, p2.getPosition().getPositionY()))) {
            	this.persoAttaquer = p;
            	this.combat.finSimulation();
                new RunControlleur(combat).start();
                this.plateauDeJeu.changeEtatCharacter(p2, Etat.attendre);
                this.mode = Mode.libre;
                break;
            } else if (p.getPosition().equals(new Position(p2.getPosition().getPositionX()+1, p2.getPosition().getPositionY()))) {
            	this.persoAttaquer = p;
            	this.combat.finSimulation();
                new RunControlleur(combat).start();
                this.plateauDeJeu.changeEtatCharacter(p2, Etat.attendre);
                this.mode = Mode.libre;
                break;
            } else if (p.getPosition().equals(new Position(p2.getPosition().getPositionX(), p2.getPosition().getPositionY()+1))) {
            	this.persoAttaquer = p;
            	this.combat.finSimulation();
                new RunControlleur(combat).start();
                this.plateauDeJeu.changeEtatCharacter(p2, Etat.attendre);
                this.mode = Mode.libre;
                break;
            } else if (p.getPosition().equals(new Position(p2.getPosition().getPositionX(), p2.getPosition().getPositionY()-1))) {
            	this.persoAttaquer = p;
            	this.combat.finSimulation();
                new RunControlleur(combat).start();
                this.plateauDeJeu.changeEtatCharacter(p2, Etat.attendre);
                this.mode = Mode.libre;
                break;
            }
        }
    }
    
    public void verifMort () {
    	if (((Personnage)this.persoAttaquer).estKo()) {
			pcsControlleurVue.firePropertyChange(ENLEVE_PERSO, this.persoAttaquer, null);
			if (this.plateauDeJeu.getAnnexes().contains(this.persoAttaquer)) {
				this.plateauDeJeu.getAnnexes().remove(this.persoAttaquer);
			} else if (this.plateauDeJeu.getEnnemies().contains(this.persoAttaquer)) {
				this.plateauDeJeu.getEnnemies().remove(this.persoAttaquer);
			} else if (this.plateauDeJeu.getPersonnages().contains(this.persoAttaquer)) {
				this.plateauDeJeu.getPersonnages().remove(this.persoAttaquer);
			}
		}
    	if (((Personnage)this.persoEnCours).estKo()) {
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
		
		private Controlleur controleur;
		
		public RunControlleur(Controlleur controleur) {
			this.controleur = controleur;
		}
		
		@Override
		public void run () {
			this.controleur.run();
			verifMort();
			continuer = true;
		}
		
	}
    
}
