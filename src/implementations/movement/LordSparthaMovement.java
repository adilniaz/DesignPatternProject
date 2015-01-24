package implementations.movement;

import implementations.gameplatform.SquareTypes;
import abstracts_interfaces.behaviours.BehaviourMoveAbstract;

public class LordSparthaMovement extends BehaviourMoveAbstract {

    public LordSparthaMovement() {
        this.movementRate = 6;
    }

    @Override
    public String getDeplacement() {
        return "deplacement a cheval";
    }

    @Override
    public int getDeplacement(SquareTypes type) {
        switch (type) {
	    	case chateau :
	    		return 100;
            case fort :
                return 2;
            case montagne :
                return 4;
            default :
                return 1;
        }
    }
}