package implementations.chapters;

import implementations.controller.Chapter;
import abstracts_interfaces.state.AbstractState;

public class MoveState implements AbstractState {
	
	private Chapter chapter;
	
	public MoveState (Chapter chapter) {
		this.chapter = chapter;
	}

	@Override
	public void action() {
		this.chapter.moveStateAction();
	}

	@Override
	public void cancel() {
		this.chapter.moveStateCancel();
	}

	@Override
	public void left() {
	}

	@Override
	public void right() {
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