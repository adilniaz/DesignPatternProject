package implementations.chapters;

import implementations.controller.Chapter;

public class FightSimulationState extends DefaultState {
	
	public FightSimulationState (Chapter chapter) {
		super(chapter);
	}

	@Override
	public void action() {
		this.chapter.combat();
	}

	@Override
	public void cancel() {
		this.chapter.fightSimulationStateCancel();
	}

}
