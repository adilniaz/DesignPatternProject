package implementations.gameplatform;

import implementations.chapters.Chapters;
import abstracts_interfaces.factories.gameplatforms.GameEnvironnementAbstract;
import abstracts_interfaces.factories.gameplatforms.GameEnvironnementFactoryAbstract;

public class FactoryTerrain extends GameEnvironnementFactoryAbstract {
	
	private Chapters chapitres;
	
	public FactoryTerrain (Chapters chapitres){
		this.chapitres = chapitres;
	}

    @Override
    public GameEnvironnementAbstract createGameEnvironnement() {
        return new Terrain(this.chapitres);
    }
    
}
