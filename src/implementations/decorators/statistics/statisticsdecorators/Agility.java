package implementations.decorators.statistics.statisticsdecorators;

import abstracts_interfaces.decorators.statistics.StatisticsAbstract;
import abstracts_interfaces.decorators.statistics.statisticsdecorators.StatisticsDecorator;

public class Agility extends StatisticsDecorator{

	public Agility(StatisticsAbstract stats) {
		super(stats);
	}

	@Override
	public int getAgility(){
		return super.getAgility()+5;
	}
	
}
