package implementations.chapters;

import implementations.controller.Chapter;
import abstracts_interfaces.state.AbstractState;

public class CharacterViewState implements AbstractState {
	
	private Chapter chapter;
	int page;
	
	public CharacterViewState (Chapter chapter) {
		this.chapter = chapter;
		this.page = 1;
		this.chapter.fire(this.chapter.SHOW_CHARACTER_VIEW, this.chapter.getPersoEnCours(), this.page);
	}

	@Override
	public void action() {
	}

	@Override
	public void cancel() {
		this.chapter.characterViewStateCancel();
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

	@Override
	public void up() {
	}

	@Override
	public void down() {
	}

	@Override
	public void start() {
	}

	@Override
	public void select() {
	}

	@Override
	public void info() {
	}

	@Override
	public void l() {
	}

}