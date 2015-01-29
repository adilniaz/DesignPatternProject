package abstracts_interfaces.decorators.statistics.statisticsdecorators;

import abstracts_interfaces.decorators.statistics.StatisticsAbstract;

public abstract class StatisticsDecorator extends StatisticsAbstract{

	protected final StatisticsAbstract stats;
	
	public StatisticsDecorator(StatisticsAbstract stats) {
		this.stats = stats;
	}
	
	@Override
	public int getHealth(){
		return stats.getHealth();
	}
	
	@Override
	public int getSpeed(){
		return stats.getSpeed();
	}
	
	@Override
	public int getDefence(){
		return stats.getDefence();
	}
	
}
