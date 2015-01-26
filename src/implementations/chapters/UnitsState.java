package implementations.chapters;

import implementations.controller.Chapter;
import abstracts_interfaces.state.AbstractState;

public class UnitsState implements AbstractState {
	
	private Chapter chapter;
	
	public UnitsState (Chapter chapter) {
		this.chapter = chapter;
	}

	@Override
	public void action() {
		// TODO Auto-generated method stub

	}

	@Override
	public void cancel() {
		this.chapter.unitsStateCancel();
	}

	@Override
	public void left() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void right() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void up() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void down() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void select() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void info() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void l() {
		// TODO Auto-generated method stub
		
	}
}