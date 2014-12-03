package fireemblem.deplacement;

import abstracts_interfaces.behaviours.BehaviourMoveAbstract;
import fireemblem.plateauJeu.CaseTypes;

public class DeplacementCombattant extends BehaviourMoveAbstract {

    public DeplacementCombattant() {
        this.deplacement = 5;
    }

    @Override
    public String getDeplacement() {
        return "deplacement a cheval";
    }

    @Override
    public int getDeplacement(CaseTypes type) {
        switch (type) {
            case fort :
                return 2;
            case montagne :
                return 4;
            default :
                return 1;
        }
    }
}