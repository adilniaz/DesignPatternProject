package implementations.decorators.statistics;

import abstracts_interfaces.decorators.statistics.StatisticsAbstract;

public class Statistics extends StatisticsAbstract{
	public Statistics(int ag, int h, int sp, int str) {
		agility = ag;
		health = h;
		speed = sp;
		strength = str;
	}
}
