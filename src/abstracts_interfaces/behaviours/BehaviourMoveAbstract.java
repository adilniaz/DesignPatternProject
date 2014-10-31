package abstracts_interfaces.behaviours;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import abstracts_interfaces.CharacterAbstract;
import abstracts_interfaces.factories.gameplatforms.AccessAbstract;
import abstracts_interfaces.factories.gameplatforms.ZoneAbstract;
import fireemblem.Position;
import fireemblem.personnage.Personnage;
import fireemblem.plateauJeu.Case;
import fireemblem.plateauJeu.CaseTypes;
import fireemblem.plateauJeu.PlateauJeu;

public abstract class BehaviourMoveAbstract {
    
    protected int deplacement;
    
    public abstract String getDeplacement ();
    
    public abstract int getDeplacement (CaseTypes type);
    
    public int getDeplacement (List<CharacterAbstract> personnages, Position position) {
        for (CharacterAbstract perso : personnages) {
            if (((Personnage)perso).getPosition().equals(position)) {
                return 100;
            }
        }
        return 0;
    }
    
    public List<ZoneAbstract> getCaseAvailable (PlateauJeu plateauDeJeu, Personnage perso) {
        List<ZoneAbstract> list = new ArrayList<>();
        Map<ZoneAbstract, List<Integer>> couts = new HashMap<>();
        Position positionPerso = perso.getPosition();
        for (ZoneAbstract zone : plateauDeJeu.getZones()) {
            Case c = (Case) zone;
            if (c.getPosition().equals(positionPerso)) {
                list.add(zone);
                couts.put(zone, new ArrayList<Integer>());
                couts.get(zone).add(0);
                break;
            }
        }
        for (AccessAbstract acce : plateauDeJeu.getAcces()) {
            if (acce.getZoneA().equals(list.get(0))) {
                this.addChemin(list, plateauDeJeu.getEnnemies(), couts, plateauDeJeu.getAcces(), acce.getZoneB(), this.deplacement);
            }
        }
        
        return list;
    }
    
    public void addChemin (List<ZoneAbstract> list, List<CharacterAbstract> personnages, Map<ZoneAbstract, List<Integer>> couts, List<AccessAbstract> acces, ZoneAbstract z, int deplacementRestant) {
        Case c = (Case) z;
        deplacementRestant = deplacementRestant - this.getDeplacement(c.getType()) - this.getDeplacement(personnages, c.getPosition());
        if (deplacementRestant >= 0) {
            if (!list.contains(z)) {
                list.add(z);
                couts.put(z, new ArrayList<Integer>());
            }
            if (!couts.get(z).contains(deplacementRestant)) {
                couts.get(z).add(deplacementRestant);
                for (AccessAbstract acce : acces) {
                    if (acce.getZoneA().equals(z)) {
                        this.addChemin(list, personnages, couts, acces, acce.getZoneB(), deplacementRestant);
                    }
                }
            }
        }
    }

    public int getNbDeplacement() {
        return deplacement;
    }

}
