package implementations.chapters;

import implementations.controller.Chapter;

public class ViewMoveState extends DefaultState {
	
	public ViewMoveState (Chapter chapter) {
		super(chapter);
	}

	@Override
	public void cancel() {
		this.chapter.fire(this.chapter.EFFACE_DEPLACEMENT_DISPONIBLE, this.chapter.getZonesSelectionner(), null);
        this.chapter.fire(this.chapter.EFFACE_ATTAQUE_DISPONIBLE, this.chapter.getZonesAtkSelectionner(), null);
        this.chapter.setState(new FreeState(this.chapter));
	}

}