package example_main_package;

import java.util.ArrayList;

import abstracts_interfaces.CharacterAbstract;

public class Functionnalities {
	
	public String everyCharactersName(
			ArrayList<CharacterAbstract> charactersList) {
		String text="\nNames :\n";
		for (int i = 0; i < charactersList.size(); i++) {
			text+=charactersList.get(i).getName();
			text+="\n";
		}
		return text;
	}

	public String everyCharactersStats(
			ArrayList<CharacterAbstract> charactersList) {
		String text="\nStatistics :\n";
		String tab = "";
		for (int i = 0; i < charactersList.size(); i++) {
			tab = "";
			if (charactersList.get(i).getName().length()<12) {
				tab = "\t";
			}
			text += charactersList.get(i).getName() +tab+ "\t::\tAgility : " + charactersList.get(i).getStats().agility + " "
					+ "Health : " + charactersList.get(i).getStats().health + " "
					+ "Speed : " + charactersList.get(i).getStats().speed;
			text+="\n";
		}
		return text;
	}

	public String everyCharactersSpeech(
			ArrayList<CharacterAbstract> charactersList) {
		String text="\nSpeech :\n";
		for (int i = 0; i < charactersList.size(); i++) {
			text+=charactersList.get(i).speak();
			text+="\n";
		}
		return text;
	}

	public String startBattle(ArrayList<CharacterAbstract> charactersList) {
		String text="\nFight :\n";
		for (int i = 0; i < charactersList.size(); i++) {
			text+=charactersList.get(i).fight() + " *** ";
			text+=charactersList.get(i).checkWeapons();
			text+="\n";
		}
		return text;
	}
}
