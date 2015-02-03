package implementations.chapters;

import implementations.controller.Chapter;

public class UnitsState extends DefaultState {

	private int page;
	
	public UnitsState (Chapter chapter) {
		super(chapter);
		this.page = 1;
		this.chapter.fire(this.chapter.UNITES, this.page, null);
	}

	@Override
	public void action() {
		this.chapter.fire(this.chapter.HIDE_UNITS, null, null);
		this.chapter.setState(new FreeState(this.chapter));
	}

	@Override
	public void cancel() {
		this.chapter.fire(this.chapter.HIDE_UNITS, null, null);
		this.chapter.setState(new FreeState(this.chapter));
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
}