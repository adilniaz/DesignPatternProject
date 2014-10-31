package fireemblem.plateauJeu;

import abstracts_interfaces.factories.gameplatforms.GameEnvironnementAbstract;
import abstracts_interfaces.factories.gameplatforms.GameEnvironnementFactoryAbstract;

public class FactoryTerrain extends GameEnvironnementFactoryAbstract {

    @Override
    public GameEnvironnementAbstract createGameEnvironnement() {
        return new Terrain();
    }
    
}
