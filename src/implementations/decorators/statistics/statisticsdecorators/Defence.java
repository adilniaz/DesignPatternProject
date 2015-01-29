package implementations.decorators.statistics.statisticsdecorators;

import abstracts_interfaces.decorators.statistics.StatisticsAbstract;
import abstracts_interfaces.decorators.statistics.statisticsdecorators.StatisticsDecorator;

public class Defence extends StatisticsDecorator{

	public Defence(StatisticsAbstract stats) {
		super(stats);
	}
	
	@Override
	public int getDefence(){
		int stat = super.getDefence();
		stat = stat + (stat*125)/100;
		return stat;
	}

}
