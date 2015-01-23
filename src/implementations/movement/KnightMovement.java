package implementations.movement;

import implementations.gameplatform.SquareTypes;
import abstracts_interfaces.behaviours.BehaviourMoveAbstract;

public class KnightMovement extends BehaviourMoveAbstract {

    public KnightMovement() {
        this.movementRate = 4;
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