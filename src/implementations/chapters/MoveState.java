package implementations.chapters;

import java.util.ArrayList;
import java.util.List;

import implementations.character.Character;
import implementations.controller.Chapter;
import implementations.controller.Chapter.actionPerso;
import implementations.gameplatform.Square;
import abstracts_interfaces.CharacterAbstract;
import abstracts_interfaces.factories.gameplatforms.ZoneAbstract;

public class MoveState extends DefaultState {
	
	public MoveState (Chapter chapter) {
		super(chapter);
	}

	@Override
	public void action() {
    	boolean inZone = false;
    	for (ZoneAbstract zone : this.chapter.getZonesSelectionner()) {
            Square c = (Square) zone;
            if (c.getPosition().equals(this.chapter.getCurrentPosition())) {
            	inZone = true;
                break;
            }
        }
    	if (!inZone) {
    		return;
    	}
        for (CharacterAbstract perso : this.chapter.getPlateauDeJeu().getPersonnages()) {
            Character p = (Character) perso;
            if (p.getPosition().equals(this.chapter.getCurrentPosition()) && !p.equals(this.chapter.getPersoEnCours())) {
                return;
            }
        }
        Character p = (Character) this.chapter.getPersoEnCours();
        this.chapter.deplacePerso(p, this.chapter.getCurrentPosition());
        List<CharacterAbstract> ennemies = this.chapter.getEnnemies();
        List<actionPerso> list = new ArrayList<>();
        if (!ennemies.isEmpty()) {
        	list.add(actionPerso.attaquer);
        }
        list.add(actionPerso.objet);
        List<CharacterAbstract> alies = this.chapter.getAlies();
        if (!alies.isEmpty()) {
        	list.add(actionPerso.echange);
        }
        list.add(actionPerso.attendre);
        actionPerso actions[] = new actionPerso[list.size()];
        for (int i =  0 ; i < list.size() ; i++) {
            actions[i] = list.get(i);
        }
        this.chapter.fire(this.chapter.AFFICHE_ACTION_PERSO, actions, null);
        this.chapter.fire(this.chapter.EFFACE_DEPLACEMENT_DISPONIBLE, this.chapter.getZonesSelectionner(), null);
        this.chapter.fire(this.chapter.EFFACE_ATTAQUE_DISPONIBLE, this.chapter.getZonesAtkSelectionner(), null);
        this.chapter.setState(new ActionMenuState(this.chapter));
	}

	@Override
	public void cancel() {
		this.chapter.fire(this.chapter.EFFACE_DEPLACEMENT_DISPONIBLE, this.chapter.getZonesSelectionner(), null);
        this.chapter.fire(this.chapter.EFFACE_ATTAQUE_DISPONIBLE, this.chapter.getZonesAtkSelectionner(), null);
        this.chapter.setState(new FreeState(this.chapter));
	}

}