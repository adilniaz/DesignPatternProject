package implementations.decorators.statistics.statisticsdecorators;

import abstracts_interfaces.decorators.statistics.StatisticsAbstract;
import abstracts_interfaces.decorators.statistics.statisticsdecorators.StatisticsDecorator;

public class Health extends StatisticsDecorator{

	public Health(StatisticsAbstract stats) {
		super(stats);
	}
	
	@Override
	public int getHealth(){
		int stat = super.getHealth();
		stat = stat + (stat*125)/100;
		return stat;
	}

}
