package implementations.movement;

import implementations.gameplatform.SquareTypes;
import abstracts_interfaces.behaviours.BehaviourMoveAbstract;

public class LordHappiaMovement extends BehaviourMoveAbstract {

    public LordHappiaMovement() {
        this.movementRate = 5;
    }

    @Override
    public String getDeplacement() {
        return "deplacement a cheval";
    }

    @Override
    public int getDeplacement(SquareTypes type) {
        switch (type) {
            case fort :
                return 2;
            case montagne :
                return 100;
            default :
                return 1;
        }
    }
}