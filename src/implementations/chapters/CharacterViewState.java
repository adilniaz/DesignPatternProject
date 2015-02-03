package implementations.chapters;

import implementations.controller.Chapter;

public class CharacterViewState extends DefaultState {
	int page;
	
	public CharacterViewState (Chapter chapter) {
		super(chapter);
		this.page = 1;
		this.chapter.fire(this.chapter.SHOW_CHARACTER_VIEW, this.chapter.getPersoEnCours(), this.page);
	}

	@Override
	public void cancel() {
		this.chapter.fire(this.chapter.HIDE_CHARACTER_VIEW, null, null);
		this.chapter.setState(new FreeState(this.chapter));
	}

	@Override
	public void left() {
		if (this.page == 2) {
			this.page--;
			this.chapter.fire(this.chapter.SHOW_CHARACTER_VIEW, this.chapter.getPersoEnCours(), this.page);
		}
	}

	@Override
	public void right() {
		if (this.page == 1) {
			this.page++;
			this.chapter.fire(this.chapter.SHOW_CHARACTER_VIEW, this.chapter.getPersoEnCours(), this.page);
		}
	}

}