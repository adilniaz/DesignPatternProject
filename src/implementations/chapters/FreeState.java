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
		this.chapter.freeStateAction();
	}

	@Override
	public void cancel() {
		/* nothing */
	}

	@Override
	public void left() {
		/* nothing */
	}

	@Override
	public void right() {
		/* nothing */
	}

	@Override
	public void up() {
		/* nothing */
	}

	@Override
	public void down() {
		/* nothing */
	}

	@Override
	public void start() {
		/* nothing */
	}

	@Override
	public void select() {
		/* nothing */
	}

	@Override
	public void info() {
		/* Si perso, afffiche info du perso */
		this.chapter.freeStateInfo();
	}

	@Override
	public void l() {
		/* nothing */
	}

}
