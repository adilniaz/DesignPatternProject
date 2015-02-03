package implementations.chapters;

import implementations.controller.Chapter;
import implementations.controller.Chapter.actionPerso;

public class ObjectActionState extends DefaultState {
	
	public ObjectActionState (Chapter chapter) {
		super(chapter);
	}

	@Override
	public void cancel() {
		this.chapter.fire(this.chapter.CANCEL_OBJET_ACTION, null, null);
		this.chapter.actionPerso(actionPerso.objet);
	}
}