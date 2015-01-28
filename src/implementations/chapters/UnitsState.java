package implementations.chapters;

import implementations.controller.Chapter;
import abstracts_interfaces.state.AbstractState;

public class UnitsState implements AbstractState {
	
	private Chapter chapter;
	private int page;
	
	public UnitsState (Chapter chapter) {
		this.chapter = chapter;
		this.page = 1;
		this.chapter.fire(this.chapter.UNITES, this.page, null);
	}

	@Override
	public void action() {
		this.chapter.unitsStateCancel();
	}

	@Override
	public void cancel() {
		this.chapter.unitsStateCancel();
	}

	@Override
	public void left() {
		if (this.page > 1) {
			this.page--;
			this.chapter.fire(this.chapter.UNITES, this.page, null);
		}
	}

	@Override
	public void right() {
		if (this.page < 4) {
			this.page++;
			this.chapter.fire(this.chapter.UNITES, this.page, null);
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