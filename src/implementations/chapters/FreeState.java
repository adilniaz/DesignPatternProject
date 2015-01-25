package implementations.chapters;

import implementations.controller.Chapter;
import abstracts_interfaces.state.AbstractState;

public class FreeState implements AbstractState {
	
	private Chapter chapter;
	
	public FreeState (Chapter chapter) {
		this.chapter = chapter;
	}

	@Override
	public void action() {
		/*boolean aPerso = false;
        for (CharacterAbstract perso : this.plateauDeJeu.getPersonnages()) {
            Character p = (Character) perso;
            if (p.getPosition().equals(pos)) {
                aPerso = true;
                List<ZoneAbstract> zones = p.getMove().getCaseAvailable(this.plateauDeJeu, p);
                List<ZoneAbstract> zonesAtk = this.getPorteAttaque(zones, this.plateauDeJeu.getZones());
                this.pcsControlleurVue.firePropertyChange(AFFICHE_DEPLACEMENT_DISPONIBLE, zones, null);
                this.pcsControlleurVue.firePropertyChange(AFFICHE_ATTAQUE_DISPONIBLE, zonesAtk, null);
                this.mode = Mode.deplacement;
                this.persoEnCours = p;
                this.zonesSelectionner = zones;
                this.zonesAtkSelectionner = zonesAtk;
                break;
            }
        }
        if (!aPerso) {
            this.pcsControlleurVue.firePropertyChange(AFFICHE_MENU, menu.values(), null);
        }*/
	}

	@Override
	public void cancel() {
		/* nothing */

	}

	@Override
	public void left() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void right() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void up() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void down() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void select() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void info() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void l() {
		// TODO Auto-generated method stub
		
	}

}
