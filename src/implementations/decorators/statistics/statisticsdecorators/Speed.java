package implementations.decorators.statistics.statisticsdecorators;

import abstracts_interfaces.decorators.statistics.StatisticsAbstract;
import abstracts_interfaces.decorators.statistics.statisticsdecorators.StatisticsDecorator;

public class Speed extends StatisticsDecorator{

	public Speed(StatisticsAbstract stats) {
		super(stats);
	}
	
	@Override
	public int getSpeed(){
		int stat = super.getSpeed();
		stat = stat + (stat*125)/100;
		return stat;
	}

}
