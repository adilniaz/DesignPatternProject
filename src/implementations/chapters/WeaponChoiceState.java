package implementations.chapters;

import java.util.ArrayList;
import java.util.List;

import implementations.controller.Chapter;
import implementations.controller.Chapter.actionPerso;
import abstracts_interfaces.CharacterAbstract;

public class WeaponChoiceState extends DefaultState {
	
	public WeaponChoiceState (Chapter chapter) {
		super(chapter);
	}

	@Override
	public void cancel() {
		List<actionPerso> list = new ArrayList<>();
        List<CharacterAbstract> ennemies = this.chapter.getEnnemies();
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
        this.chapter.setState(new ActionMenuState(this.chapter));
		
	}

}
