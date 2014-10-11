package main_package;

import implementations.factories.characters.CreateCharactersMiddleAgeFactory;
import implementations.factories.gameplatforms.GameEnvironment;
import implementations.organisations.Organisation;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import abstracts_interfaces.CharacterAbstract;
import abstracts_interfaces.factories.characters.CreateCharactersFactoryAbstract;

public class GameSimulation extends JFrame implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8963259860157176989L;
	ArrayList<CharacterAbstract> charactersList;
	Organisation subject;
	
	private JPanel buttonsPanel, textAreaPanel;
	
	private JScrollPane scrollPane;
	
	private JTextArea textArea;
	
	
	private JButton buttonShowCharacterNames;
	private JButton buttonSpeak;
	private JButton buttonFight;
	private JButton buttonWarMode;
	private JButton buttonPeaceMode;
	private JButton buttonUndefinedMode;
	private JButton buttonRetreatMode;
	private JButton buttonClearText;

	public enum CharactersType{
		UNDEFINED,
		TANK, SOLDIER, FIGHTERAIRCRAFT, SHIP,
		PRINCESS, KNIGHT, ARCHER, FOOTSOLDIER
	}
	
	public CharactersType tCharacs;

	public enum GamePlatformType{
		UNDEFINED,
		MAZE, ANTCOLONY, WORLDMAP
	}

	public enum Strategy{
		UNDEFINED,
		WARMODE, PEACEMODE, RETREATMODE
	}
	
	public Strategy strategy;
	
	public GamePlatformType tGamePlatform;
	
	public GameSimulation(){
		initComponents();
		charactersList = new ArrayList<CharacterAbstract>();
		
		CreateCharactersFactoryAbstract middleAgeFactoryAbstract = new CreateCharactersMiddleAgeFactory();
		createCharacters(middleAgeFactoryAbstract);
		
		GameEnvironment gE = new GameEnvironment();
		gE.createGamePlatform(GamePlatformType.WORLDMAP);
	}
	
	public void createCharacters(CreateCharactersFactoryAbstract factory){
		subject = new Organisation();
		CharacterAbstract chev = factory.createCharacter("Sir Gwain", subject, CharactersType.KNIGHT);
		CharacterAbstract arch = factory.createCharacter("Robin", subject, CharactersType.ARCHER);
		addCharactersToList(chev, arch);
	}
	
	public void addCharactersToList(CharacterAbstract...characs) {
		for (CharacterAbstract charac : characs) {
			charactersList.add(charac);
		}
	}
	
	private void initComponents() {
		setLocation(200, 100);
		setSize(600, 600);
		
		buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new GridLayout(7,1));
		
		textAreaPanel = new JPanel();
		
		buttonShowCharacterNames = new JButton("SHOW ALL CHARACTERS");
		buttonShowCharacterNames.addActionListener(this);
		
		buttonSpeak = new JButton("SPEAK");
		buttonSpeak.addActionListener(this);
		
		buttonFight = new JButton("ATTACK!!");
		buttonFight.addActionListener(this);
		
		buttonWarMode = new JButton("WAR MODE");
		buttonWarMode.addActionListener(this);
		
		buttonPeaceMode = new JButton("PEACE MODE");
		buttonPeaceMode.addActionListener(this);
		
		buttonUndefinedMode = new JButton("UNDEFINED MODE");
		buttonUndefinedMode.addActionListener(this);

		buttonRetreatMode = new JButton("RETREAT MODE");
		buttonRetreatMode.addActionListener(this);
		
		buttonClearText = new JButton("CLEAR TEXT");
		buttonClearText.setPreferredSize(new Dimension(200,51));
		buttonClearText.addActionListener(this);
		
		
		GridLayout myLayout = new GridLayout(1,2);
		setLayout(myLayout);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		
		scrollPane = new JScrollPane(textArea);
		scrollPane.setPreferredSize(new Dimension(280, 500));

		textAreaPanel.add(scrollPane, BorderLayout.CENTER);
		textAreaPanel.add(buttonClearText, BorderLayout.SOUTH);
		
		
		add(buttonsPanel);
		add(textAreaPanel);
		
		buttonsPanel.add(buttonShowCharacterNames);
		buttonsPanel.add(buttonSpeak);
		buttonsPanel.add(buttonFight);
		buttonsPanel.add(buttonWarMode);
		buttonsPanel.add(buttonPeaceMode);
		buttonsPanel.add(buttonUndefinedMode);
		buttonsPanel.add(buttonRetreatMode);
		
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==buttonShowCharacterNames){
			displayer(everyCharactersName());
		}
		if(e.getSource()==buttonSpeak){
			displayer(everyCharactersSpeech());
		}
		if(e.getSource()==buttonFight){
			displayer(startBattle());
		}
		if(e.getSource()==buttonWarMode){
			displayer(strategy(Strategy.WARMODE));
		}
		if(e.getSource()==buttonPeaceMode){
			displayer(strategy(Strategy.PEACEMODE));
		}
		if(e.getSource()==buttonUndefinedMode){
			displayer(strategy(Strategy.UNDEFINED));
		}
		if(e.getSource()==buttonRetreatMode){
			displayer(strategy(Strategy.RETREATMODE));
		}
		if(e.getSource()==buttonClearText){
			textArea.setText("");
		}
	}
	
	public String strategy(Strategy strategy) {
		subject.setOperatingMode(strategy);
		String text=textArea.getText() + "\n";
		for (int i = 0; i < charactersList.size(); i++) {
			text+=charactersList.get(i).getOperatingState();
			text+="\n";
		}
		return text;
	}
	
	public void displayer(String text){
		textArea.setText(text);
	}
	
	public String everyCharactersName(){
		String text=textArea.getText() + "\nNames :\n";
		for (int i = 0; i < charactersList.size(); i++) {
			text+=charactersList.get(i).getName();
			text+="\n";
		}
		return text;
	}
	
	public String everyCharactersSpeech(){
		String text=textArea.getText() + "\nSpeech :\n";
		for (int i = 0; i < charactersList.size(); i++) {
			text+=charactersList.get(i).speak();
			text+="\n";
		}
		return text;
	}
	
	public String startBattle(){
		String text=textArea.getText() + "\nFight :\n";
		for (int i = 0; i < charactersList.size(); i++) {
			text+=charactersList.get(i).fight();
			text+="\n";
		}
		return text;
	}
	
	public void changeBehaviour(){}
	
}
