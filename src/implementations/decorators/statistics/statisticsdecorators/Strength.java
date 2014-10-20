package implementations.decorators.statistics.statisticsdecorators;

import abstracts_interfaces.decorators.statistics.StatisticsAbstract;
import abstracts_interfaces.decorators.statistics.statisticsdecorators.StatisticsDecorator;

public class Strength extends StatisticsDecorator{

	public Strength(StatisticsAbstract stats) {
		super(stats);
	}

	@Override
	public int getStrength(){
		return super.getStrength()+5;
	}
}
