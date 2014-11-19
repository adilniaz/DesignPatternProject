package fireemblem.controlleur;

import java.util.ArrayList;
import java.util.List;

import abstracts_interfaces.CharacterAbstract;
import abstracts_interfaces.factories.gameplatforms.ZoneAbstract;
import fireemblem.Position;
import fireemblem.personnage.Personnage;
import fireemblem.plateauJeu.Case;
import fireemblem.plateauJeu.PlateauJeu;
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
    private Position oldPosition;
    
    public final String AFFICHE_ACTION_PERSO = "afficheActionPerso";
    public final String AFFICHE_ARMES_PERSO = "afficheArmePerso";
    public final String AFFICHE_ATTAQUE_DISPONIBLE = "afficheAttaqueDisponible";
    public final String AFFICHE_DEPLACEMENT_DISPONIBLE = "afficheDeplacementDisponible";
    public final String AFFICHE_MAP = "afficheMap";
    public final String AFFICHE_MENU = "afficheMenu";
    public final String DEPLACE_PERSO = "deplacePerso";
    public final String EFFACE_ATTAQUE_DISPONIBLE = "effaceAttaqueDisponible";
    public final String EFFACE_DEPLACEMENT_DISPONIBLE = "effaceDeplacementDisponible";
    
    public enum menu {
        unite, statut, suspen, fin;
    }
    
    public enum actionPerso {
        attaquer, baton, objet, echange, convoi, attendre;
    }
    
    private enum Mode {
        libre, deplacement, action, arme;
    }
    
    public Chapitre (String nom, PlateauJeu plateauDeJeu, String objectif) {
        this.nom = nom;
        this.plateauDeJeu = plateauDeJeu;
        this.mode = Mode.libre;
        this.objectif = objectif;
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
    
    public void run () {
        this.pcsControlleurVue.firePropertyChange(AFFICHE_MAP, null, null);
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
                    this.oldPosition = new Position(p.getPosition());
                    p.setPosition(pos);
                    this.pcsControlleurVue.firePropertyChange(DEPLACE_PERSO, this.persoEnCours, this.oldPosition);
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
        }
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
        }
    }
    
    public void menu (menu m) {
        switch (m) {
            case unite:
               break;
            case statut:
               break;
            case suspen:
               break;
            case fin:
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
            	((Personnage) this.persoEnCours).setaJouer(true);
                this.finAction();
            	break;
        }
    }
    
    public void simuleCombat () {
        for (CharacterAbstract perso : this.plateauDeJeu.getEnnemies()) {
            Personnage p = (Personnage) perso;
            Personnage p2 = (Personnage) this.persoEnCours;
            if (p.getPosition().equals(new Position(p2.getPosition().getPositionX()-1, p2.getPosition().getPositionY()))) {
                Combat combat = new Combat(p2, p);
                Vues.createVue(combat, this.fenetre);
                combat.simulerCombat();
            }
        }
    }
    
    public void combat () {
        for (CharacterAbstract perso : this.plateauDeJeu.getEnnemies()) {
            Personnage p = (Personnage) perso;
            Personnage p2 = (Personnage) this.persoEnCours;
            if (p.getPosition().equals(new Position(p2.getPosition().getPositionX()-1, p2.getPosition().getPositionY()))) {
                Combat combat = new Combat(p2, p);
                Vues.createVue(combat, this.fenetre);
                combat.combat();
                p2.setaJouer(true);
                this.finAction();
                break;
            }
        }
    }
    
    public void finAction () {
    	boolean fin = true;
    	for (CharacterAbstract perso : this.plateauDeJeu.getPersonnages()) {
        	Personnage p = (Personnage) perso;
        	if (!p.isaJouer()) {
        		fin = false;
        	}
        }
    	if (fin) {
    		this.tourEnnemie();
    		this.tourAnnexe();
    	}
    }
    
    public void tourEnnemie () {
    	for (CharacterAbstract perso : this.plateauDeJeu.getEnnemies()) {
    		Personnage p = (Personnage) perso;
    		p.getStrategie().run(this);
        }
    }
    
    public void tourAnnexe () {
    	for (CharacterAbstract perso : this.plateauDeJeu.getAnnexes()) {
    		Personnage p = (Personnage) perso;
    		p.getStrategie().run(this);
        }
    }
    
}
