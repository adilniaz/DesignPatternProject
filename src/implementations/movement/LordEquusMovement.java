package implementations.movement;

import implementations.gameplatform.SquareTypes;
import abstracts_interfaces.behaviours.BehaviourMoveAbstract;

public class LordEquusMovement extends BehaviourMoveAbstract {

    public LordEquusMovement() {
        this.movementRate = 7;
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
                return 6;
            default :
                return 1;
        }
    }
}