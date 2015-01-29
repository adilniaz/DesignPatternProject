package implementations.decorators.statistics;

import abstracts_interfaces.decorators.statistics.StatisticsAbstract;

public class Statistics extends StatisticsAbstract{
	public Statistics(int def, int h, int sp) {
		defence = def;
		health = h;
		speed = sp;
	}
}
