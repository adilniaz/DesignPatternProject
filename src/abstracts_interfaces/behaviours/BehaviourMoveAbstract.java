package abstracts_interfaces.behaviours;

import implementations.Position;
import implementations.character.Character;
import implementations.gameplatform.Square;
import implementations.gameplatform.SquareTypes;
import implementations.gameplatform.GamePlatform;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import abstracts_interfaces.CharacterAbstract;
import abstracts_interfaces.factories.gameplatforms.AccessAbstract;
import abstracts_interfaces.factories.gameplatforms.ZoneAbstract;

public abstract class BehaviourMoveAbstract {
    
    protected int movementRate;
    
    public abstract String getDeplacement();
    
    public abstract int getDeplacement(SquareTypes type);
    
    public int getDeplacement (List<CharacterAbstract> personnages, Position position) {
        for (CharacterAbstract perso : personnages) {
            if (((Character)perso).getPosition().equals(position)) {
                return 100;
            }
        }
        return 0;
    }
    
    public List<ZoneAbstract> getCaseAvailable (GamePlatform plateauDeJeu, Character perso) {
        List<ZoneAbstract> list = new ArrayList<>();
        Map<ZoneAbstract, List<Integer>> couts = new HashMap<>();
        Position positionPerso = perso.getPosition();
        for (ZoneAbstract zone : plateauDeJeu.getZones()) {
            Square c = (Square) zone;
            if (c.getPosition().equals(positionPerso)) {
                list.add(zone);
                couts.put(zone, new ArrayList<Integer>());
                couts.get(zone).add(0);
                break;
            }
        }
        for (AccessAbstract acce : plateauDeJeu.getAcces()) {
            if (acce.getZoneA().equals(list.get(0))) {
                this.addChemin(list, plateauDeJeu.getEnnemies(), couts, plateauDeJeu.getAcces(), acce.getZoneB(), this.movementRate);
            }
        }
        
        return list;
    }
    
    public void addChemin (List<ZoneAbstract> list, List<CharacterAbstract> personnages, Map<ZoneAbstract, List<Integer>> couts, List<AccessAbstract> acces, ZoneAbstract z, int deplacementRestant) {
        Square c = (Square) z;
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
        return movementRate;
    }

}
