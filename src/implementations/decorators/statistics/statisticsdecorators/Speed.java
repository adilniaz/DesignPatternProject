package implementations.decorators.statistics.statisticsdecorators;

import abstracts_interfaces.decorators.statistics.StatisticsAbstract;
import abstracts_interfaces.decorators.statistics.statisticsdecorators.StatisticsDecorator;

public class Speed extends StatisticsDecorator{

	public Speed(StatisticsAbstract stats) {
		super(stats);
	}
	
	@Override
	public int getSpeed(){
		return super.getSpeed()+5;
	}
	
}
