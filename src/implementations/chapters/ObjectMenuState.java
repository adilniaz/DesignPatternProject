package implementations.chapters;

import implementations.controller.Chapter;
import abstracts_interfaces.state.AbstractState;

public class ObjectMenuState implements AbstractState {
	
	private Chapter chapter;
	
	public ObjectMenuState (Chapter chapter) {
		this.chapter = chapter;
	}

	@Override
	public void action() {
		
	}

	@Override
	public void cancel() {
		this.chapter.weaponChoiceStateCancel();
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