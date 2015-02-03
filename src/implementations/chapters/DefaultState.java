package implementations.chapters;

import implementations.controller.Chapter;
import abstracts_interfaces.state.AbstractState;

public class DefaultState implements AbstractState {
	
	protected Chapter chapter;
	
	public DefaultState (Chapter chapter) {
		this.chapter = chapter;
	}

	@Override
	public void action() {
	}

	@Override
	public void cancel() {
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
