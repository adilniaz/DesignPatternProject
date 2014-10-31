package fireemblem.deplacement;

import fireemblem.plateauJeu.CaseTypes;
import abstracts_interfaces.behaviours.BehaviourMoveAbstract;

public class DeplacementLord extends BehaviourMoveAbstract {

    public DeplacementLord() {
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
