package fireemblem.plateauJeu;

import fireemblem.chapitre.Chapitres;
import abstracts_interfaces.factories.gameplatforms.GameEnvironnementAbstract;
import abstracts_interfaces.factories.gameplatforms.GameEnvironnementFactoryAbstract;

public class FactoryTerrain extends GameEnvironnementFactoryAbstract {
	
	private Chapitres chapitres;
	
	public FactoryTerrain (Chapitres chapitres){
		this.chapitres = chapitres;
	}

    @Override
    public GameEnvironnementAbstract createGameEnvironnement() {
        return new Terrain(this.chapitres);
    }
    
}
