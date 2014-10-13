package main_package;

import implementations.organisations.Organisation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
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
	
	ArrayList<String> charList;
	
	ArrayList<CharacterAbstract> charactersList;
	Organisation subject;
	
	private JFrame frame;
	private JPanel panel, paneltextArea;
	private JButton buttonChar1, buttonChar2, buttonChar3, buttonChar4;
	private JTextArea textArea;
	private JScrollPane scrollPane;
	private JButton buttonShowCharacterNames;
	private JButton buttonSpeak;
	private JButton buttonFight;
	private JButton buttonWarMode;
	private JButton buttonPeaceMode;
	private JButton buttonUndefinedMode;
	private JButton buttonRetreatMode;
	private JButton buttonClearText;

	private int firstUnit, secondUnit, thirdUnit, forthUnit;
	
	
	public enum CharactersType{
		UNDEFINED,
		TANK, SOLDIER, FIGHTERAIRCRAFT, SHIP,
		PRINCESS, KNIGHT, ARCHER, FOOTSOLDIER
	}
	
	public CharactersType tCharacs;
	
	public enum Strategy{
		UNDEFINED,
		WARMODE, PEACEMODE, RETREATMODE
	}
	
	public Strategy strategy;
	private CreateCharactersFactoryAbstract factory;
	
	public GameSimulation(CreateCharactersFactoryAbstract factory, ArrayList<String> charList){
		
		this.factory = factory;
		firstUnit=0;
		secondUnit=0;
		thirdUnit=0;
		forthUnit=0;
		
		subject = new Organisation();
		this.charList = charList;
		charactersList = new ArrayList<CharacterAbstract>();
		initComponentsGame();
//		createCharacters(factory);
	}
	
	public void createCharacters(CreateCharactersFactoryAbstract factory){
//		subject = new Organisation();
		CharacterAbstract chev = factory.createCharacter("Sir Gwain", subject, CharactersType.KNIGHT);
		CharacterAbstract arch = factory.createCharacter("Robin", subject, CharactersType.ARCHER);
		addCharactersToList(chev, arch);
	}
	
	public void addCharactersToList(CharacterAbstract... characs) {
		for (CharacterAbstract charac : characs) {
			charactersList.add(charac);
		}
	}
	
	
	private void initComponentsGame() {
		
		frame = new JFrame();
		frame.setLocation(200, 100);
		frame.setSize(600, 600);
		
		
		int buttonsWidth = 200;
		int buttonsHeight = 40;
		
		int width = frame.getWidth()-buttonsWidth-2;
		int height = frame.getHeight()-2;
		
		panel = new JPanel();
		panel = new JPanel();
		panel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		paneltextArea = new JPanel();
		paneltextArea.setBounds(buttonsWidth+1, 1, width, height);
		
		textArea = new JTextArea();
		
		scrollPane = new JScrollPane(textArea);
		scrollPane.setPreferredSize(new Dimension(width-5, height-10));
		
		buttonChar1 = new JButton(charList.get(0));
		buttonChar1.addActionListener(this);
		buttonChar1.setSize(buttonsWidth, buttonsHeight);
		buttonChar1.setBounds(1, 1,
				buttonsWidth, buttonsHeight);
		
		buttonChar2 = new JButton(charList.get(1));
		buttonChar2.addActionListener(this);
		buttonChar2.setSize(buttonsWidth, buttonsHeight);
		buttonChar2.setBounds(1, 1+buttonsHeight,
				buttonsWidth, buttonsHeight);
		
		buttonChar3 = new JButton(charList.get(2));
		buttonChar3.addActionListener(this);
		buttonChar3.setSize(buttonsWidth, buttonsHeight);
		buttonChar3.setBounds(1, 1+buttonsHeight*2,
				buttonsWidth, buttonsHeight);
		
		buttonChar4 = new JButton(charList.get(3));
		buttonChar4.addActionListener(this);
		buttonChar4.setSize(buttonsWidth, buttonsHeight);
		buttonChar4.setBounds(1, 1+buttonsHeight*3,
				buttonsWidth, buttonsHeight);
		

		buttonShowCharacterNames = new JButton("SHOW ALL CHARACTERS");
		buttonShowCharacterNames.addActionListener(this);
		buttonShowCharacterNames.setBounds(1, 1+buttonsHeight*5,
				buttonsWidth, buttonsHeight);
		
		buttonSpeak = new JButton("SPEAK");
		buttonSpeak.addActionListener(this);
		buttonSpeak.setBounds(1, 1+buttonsHeight*6,
				buttonsWidth, buttonsHeight);
		
		buttonFight = new JButton("ATTACK!!");
		buttonFight.addActionListener(this);
		buttonFight.setBounds(1, 1+buttonsHeight*7,
				buttonsWidth, buttonsHeight);
		
		buttonWarMode = new JButton("WAR MODE");
		buttonWarMode.addActionListener(this);
		buttonWarMode.setBounds(1, 1+buttonsHeight*8,
				buttonsWidth, buttonsHeight);
		
		buttonPeaceMode = new JButton("PEACE MODE");
		buttonPeaceMode.addActionListener(this);
		buttonPeaceMode.setBounds(1, 1+buttonsHeight*9,
				buttonsWidth, buttonsHeight);
		buttonRetreatMode = new JButton("RETREAT MODE");
		buttonRetreatMode.addActionListener(this);
		buttonRetreatMode.setBounds(1, 1+buttonsHeight*10,
				buttonsWidth, buttonsHeight);

		/*
		buttonUndefinedMode = new JButton("UNDEFINED MODE");
		buttonUndefinedMode.addActionListener(this);
		buttonUndefinedMode.setBounds(1, 1+buttonsHeight*11,
				buttonsWidth, buttonsHeight);
		*/
		
		buttonClearText = new JButton("CLEAR TEXT");
		buttonClearText.addActionListener(this);
		buttonClearText.setBounds(1, 1+buttonsHeight*13,
				buttonsWidth, buttonsHeight);
		
		frame.add(panel);
		
		panel.setLayout(null);
		panel.add(buttonChar1);
		panel.add(buttonChar2);
		panel.add(buttonChar3);
		panel.add(buttonChar4);
		panel.add(buttonShowCharacterNames);
		panel.add(buttonClearText);
		panel.add(buttonSpeak);
		panel.add(buttonFight);
		panel.add(buttonWarMode);
		panel.add(buttonPeaceMode);
		panel.add(buttonRetreatMode);
		panel.add(paneltextArea);
		
		paneltextArea.add(scrollPane, BorderLayout.CENTER);
		
		frame.setUndecorated(true);
		frame.setVisible(true);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (!charactersList.isEmpty()) {
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
		
		
		
		if(e.getSource()==buttonChar1){
			firstUnit++;
			if (charList.get(0)=="PRINCESS") {
				CharacterAbstract princess = factory.createCharacter(charList.get(0)+ firstUnit, null, CharactersType.PRINCESS);
				addCharactersToList(princess);
				
			} else {
				CharacterAbstract tank = factory.createCharacter(charList.get(0)+ firstUnit, subject, CharactersType.TANK);
				addCharactersToList(tank);
			}
			textArea.setText(textArea.getText() + charList.get(0)+ firstUnit + " created.\n");
		}
		if(e.getSource()==buttonChar2){
			secondUnit++;
			if (charList.get(1)=="KNIGHT") {
				CharacterAbstract knight = factory.createCharacter(charList.get(1)+ secondUnit, subject, CharactersType.KNIGHT);
				addCharactersToList(knight);
				
			} else {
				CharacterAbstract soldier = factory.createCharacter(charList.get(1)+ secondUnit, subject, CharactersType.SOLDIER);
				addCharactersToList(soldier);
			}
			textArea.setText(textArea.getText() + charList.get(1)+ secondUnit + " created.\n");
		}
		if(e.getSource()==buttonChar3){
			thirdUnit++;
			if (charList.get(2)=="ARCHER") {
				CharacterAbstract archer = factory.createCharacter(charList.get(2)+ thirdUnit, subject, CharactersType.ARCHER);
				addCharactersToList(archer);
				
			} else {
				CharacterAbstract fighterAircraft = factory.createCharacter(charList.get(2)+ thirdUnit, subject, CharactersType.FIGHTERAIRCRAFT);
				addCharactersToList(fighterAircraft);
			}
			textArea.setText(textArea.getText() + charList.get(2)+ thirdUnit + " created.\n");
		}
		if(e.getSource()==buttonChar4){
			forthUnit++;
			if (charList.get(3)=="FOOTSOLDIER") {
				CharacterAbstract footSoldier = factory.createCharacter(charList.get(3)+ forthUnit, subject, CharactersType.FOOTSOLDIER);
				addCharactersToList(footSoldier);
				
			} else {
				CharacterAbstract ship = factory.createCharacter(charList.get(3)+ forthUnit, subject, CharactersType.SHIP);
				addCharactersToList(ship);
			}
			textArea.setText(textArea.getText() + charList.get(3)+ forthUnit + " created.\n");
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
