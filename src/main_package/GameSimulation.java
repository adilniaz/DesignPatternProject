package main_package;

import implementations.decorators.weapons.weapondecorators.WeaponAccuracy;
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
import abstracts_interfaces.decorators.weapons.WeaponAbstract;
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
	private JButton buttonShowCharacterStats;
	private JButton buttonSpeak;
	private JButton buttonFight;
	private JButton buttonWarMode;
	private JButton buttonPeaceMode;
	private JButton buttonUndefinedMode;
	private JButton buttonRetreatMode;
	private JButton buttonClearText;
	private JButton buttonBack;
	private JButton buttonExit;

	/**
	 * 		FOR WEAPON DECORATION
	 * 
	 */
	private JButton buttonIncreaseAccuracy;
	private JButton buttonIncreaseDamage;
	private JButton buttonIncreaseRange;

	/**
	 * 		FOR STAT DECORATION
	 * 
	 */
	private JButton buttonIncreaseAgility;
	private JButton buttonIncreaseHealth;
	private JButton buttonIncreaseSpeed;
	private JButton buttonIncreaseStrength;

	private int firstUnit, secondUnit, thirdUnit, forthUnit;
	
	private WeaponAbstract wA;
	
	
	/**
	 * 		FOR DIMENTIONS OF COMPONENTS
	 */
	private Measurements measurements;
	private int frameHeight;
	private int frameWidth;
	private int buttonHeight;
	private int buttonWidth;
	private int framePositionX;
	private int framePositionY;
	
	public enum CharactersType{
		UNDEFINED,
		TANK, SOLDIER, FIGHTERAIRCRAFT, SHIP,
		PRINCESS, KNIGHT, ARCHER, FOOTSOLDIER
	}
	
	public enum Strategy{
		UNDEFINED,
		WARMODE, PEACEMODE, RETREATMODE
	}
	
	private CreateCharactersFactoryAbstract factory;
	
	public GameSimulation(CreateCharactersFactoryAbstract factory, ArrayList<String> charList){
		
		measurements = new Measurements();

		this.buttonWidth = measurements.buttonWidth;
		this.buttonHeight = measurements.buttonHeight;
		this.frameWidth = measurements.frameWidth;
		this.frameHeight = measurements.frameHeight;
		this.framePositionX = measurements.framePositionX;
		this.framePositionY = measurements.framePositionY;
		
		this.factory = factory;
		firstUnit=0;
		secondUnit=0;
		thirdUnit=0;
		forthUnit=0;
		
		subject = new Organisation();
		this.charList = charList;
		charactersList = new ArrayList<CharacterAbstract>();
		initComponentsGame();
	}
	
	
	public void createCharacters(String name, Organisation subj, CharactersType characType){
		CharacterAbstract character = factory.createCharacter(name, subj, characType);
		addCharactersToList(character);
		textArea.setText(textArea.getText() + name + " created.\n");
	}
	
	
	public void addCharactersToList(CharacterAbstract... characs) {
		for (CharacterAbstract charac : characs) {
			charactersList.add(charac);
		}
	}
	
	private void initComponentsGame() {
		
		frame = new JFrame();
		frame.setLocation(framePositionX, framePositionY);
		frame.setSize(frameWidth, frameHeight);
		
		
		int width = frameWidth-buttonWidth-2;
		int height = frameHeight-2;
		
		panel = new JPanel();
		panel = new JPanel();
		panel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		paneltextArea = new JPanel();
		paneltextArea.setBounds(buttonWidth+1, 1, width, height);
		
		textArea = new JTextArea();
		
		scrollPane = new JScrollPane(textArea);
		scrollPane.setPreferredSize(new Dimension(width-5, height-10));
		
		buttonChar1 = new JButton(charList.get(0));
		buttonChar1.addActionListener(this);
		buttonChar1.setSize(buttonWidth, buttonHeight);
		buttonChar1.setBounds(1, 1,
				buttonWidth, buttonHeight);
		
		buttonChar2 = new JButton(charList.get(1));
		buttonChar2.addActionListener(this);
		buttonChar2.setSize(buttonWidth, buttonHeight);
		buttonChar2.setBounds(1, buttonHeight+1,
				buttonWidth, buttonHeight);
		
		buttonChar3 = new JButton(charList.get(2));
		buttonChar3.addActionListener(this);
		buttonChar3.setSize(buttonWidth, buttonHeight);
		buttonChar3.setBounds(1, buttonHeight*2+1,
				buttonWidth, buttonHeight);
		
		buttonChar4 = new JButton(charList.get(3));
		buttonChar4.addActionListener(this);
		buttonChar4.setSize(buttonWidth, buttonHeight);
		buttonChar4.setBounds(1, buttonHeight*3+1,
				buttonWidth, buttonHeight);
		

		buttonShowCharacterNames = new JButton("SHOW ALL CHARACTERS");
		buttonShowCharacterNames.addActionListener(this);
		buttonShowCharacterNames.setBounds(1, buttonHeight*4+2,
				buttonWidth, buttonHeight);
		
		buttonShowCharacterStats = new JButton("CHARACTERS' STATS");
		buttonShowCharacterStats.addActionListener(this);
		buttonShowCharacterStats.setBounds(1, buttonHeight*5+2,
				buttonWidth, buttonHeight);
		
		buttonSpeak = new JButton("SPEAK");
		buttonSpeak.addActionListener(this);
		buttonSpeak.setBounds(1, buttonHeight*6+2,
				buttonWidth, buttonHeight);
		
		buttonFight = new JButton("ATTACK!!");
		buttonFight.addActionListener(this);
		buttonFight.setBounds(1, buttonHeight*7+2,
				buttonWidth, buttonHeight);
		
		buttonWarMode = new JButton("WAR MODE");
		buttonWarMode.addActionListener(this);
		buttonWarMode.setBounds(1, buttonHeight*8+2,
				buttonWidth, buttonHeight);
		
		buttonPeaceMode = new JButton("PEACE MODE");
		buttonPeaceMode.addActionListener(this);
		buttonPeaceMode.setBounds(1, buttonHeight*9+2,
				buttonWidth, buttonHeight);
		buttonRetreatMode = new JButton("RETREAT MODE");
		buttonRetreatMode.addActionListener(this);
		buttonRetreatMode.setBounds(1, buttonHeight*10+2,
				buttonWidth, buttonHeight);

		/*
		buttonUndefinedMode = new JButton("UNDEFINED MODE");
		buttonUndefinedMode.addActionListener(this);
		buttonUndefinedMode.setBounds(1, 1+buttonsHeight*11,
				buttonsWidth, buttonsHeight);
		*/
		
		/**
		 * 		FOR WEAPON DECORATION
		 * 
		 */
//		buttonIncreaseAccuracy = buttonInitialiser(buttonIncreaseAccuracy, "INCREASE ACCURACY", 11, 3);
		buttonIncreaseAccuracy = new JButton("INCREASE ACCURACY");
		buttonIncreaseAccuracy.addActionListener(this);
		buttonIncreaseAccuracy.setBounds(1, buttonHeight*11+3,
				buttonWidth, buttonHeight);
		buttonIncreaseDamage = new JButton("INCREASE Damage");
		buttonIncreaseDamage.addActionListener(this);
		buttonIncreaseDamage.setBounds(1, buttonHeight*12+3,
				buttonWidth, buttonHeight);
		
		//	Applicable only some Specialties
		buttonIncreaseRange = new JButton("INCREASE RANGE");
		buttonIncreaseRange.addActionListener(this);
		buttonIncreaseRange.setBounds(1, buttonHeight*13+3,
				buttonWidth, buttonHeight);
		
		/**
		 * 		FOR STAT DECORATION
		 * 
		 */
		buttonIncreaseAgility = new JButton("INCREASE RANGE");
		buttonIncreaseAgility.addActionListener(this);
		buttonIncreaseAgility.setBounds(1, buttonHeight*14+4,
				buttonWidth, buttonHeight);
		
		buttonIncreaseHealth = new JButton("INCREASE HEALTH");
		buttonIncreaseHealth.addActionListener(this);
		buttonIncreaseHealth.setBounds(1, buttonHeight*15+4,
				buttonWidth, buttonHeight);
		
		buttonIncreaseSpeed = new JButton("INCREASE SPEED");
		buttonIncreaseSpeed.addActionListener(this);
		buttonIncreaseSpeed.setBounds(1, buttonHeight*16+4,
				buttonWidth, buttonHeight);
		
		buttonIncreaseStrength = new JButton("INCREASE STRENGTH");
		buttonIncreaseStrength.addActionListener(this);
		buttonIncreaseStrength.setBounds(1, buttonHeight*17+4,
				buttonWidth, buttonHeight);
		
		
		/**
		 * 		OTHER OPTIONS
		 * 
		 */
		buttonClearText = new JButton("CLEAR TEXT");
		buttonClearText.addActionListener(this);
		buttonClearText.setBounds(1, buttonHeight*18+5,
				buttonWidth, buttonHeight);

		buttonBack = new JButton("BACK");
		buttonBack.addActionListener(this);
		buttonBack.setSize(buttonWidth, buttonHeight);
		buttonBack.setBounds(1, buttonHeight*19+6,
				buttonWidth, buttonHeight);

		buttonExit = new JButton("EXIT");
		buttonExit.addActionListener(this);
		buttonExit.setSize(buttonWidth, buttonHeight);
		buttonExit.setBounds(1, buttonHeight*20+6,
				buttonWidth, buttonHeight);

		
		frame.add(panel);
		
		panelManager();
		
		paneltextArea.setBackground(Color.BLACK);
		paneltextArea.add(scrollPane, BorderLayout.CENTER);
		
		frame.setUndecorated(true);
		frame.setVisible(true);
	}


	private JButton buttonInitialiser(JButton button, String name, int i, int j) {
		button = new JButton(name);
		button.addActionListener(this);
		button.setBounds(1, buttonHeight*i+j,
				buttonWidth, buttonHeight);
		return button;
	}

	public void panelManager() {
		
		panel.setBackground(Color.BLACK);
		
		panel.setLayout(null);
		
		/**
		 * 		CREATE CHARACTERS
		 * 
		 */
		panel.add(buttonChar1);
		panel.add(buttonChar2);
		panel.add(buttonChar3);
		panel.add(buttonChar4);
		
		/**
		 * 		OPTIONS
		 * 
		 */
		panel.add(buttonShowCharacterNames);
		panel.add(buttonShowCharacterStats);
		panel.add(buttonSpeak);
		panel.add(buttonFight);
		panel.add(buttonWarMode);
		panel.add(buttonPeaceMode);
		panel.add(buttonRetreatMode);
		
		/**
		 * 		FOR WEAPON DECORATION
		 * 
		 */
		panel.add(buttonIncreaseAccuracy);
		panel.add(buttonIncreaseDamage);
		panel.add(buttonIncreaseRange);
		
		/**
		 * 		FOR STAT DECORATION
		 * 
		 */
		panel.add(buttonIncreaseAgility);
		panel.add(buttonIncreaseHealth);
		panel.add(buttonIncreaseSpeed);
		panel.add(buttonIncreaseStrength);
		
		/**
		 * 		OTHER OPTIONS
		 * 
		 */
		panel.add(buttonClearText);
		panel.add(buttonBack);
		panel.add(buttonExit);
		panel.add(paneltextArea);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (!charactersList.isEmpty()) {
			if(e.getSource()==buttonShowCharacterNames){
				displayer(everyCharactersName());
			}
			if(e.getSource()==buttonShowCharacterStats){
				/*		character's health 
				 * 		character's speed
				 * 		character's strength
				 * 		character's agility
				 * 		etc.
				 */
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
			if(e.getSource()==buttonIncreaseAccuracy){
				for (int i = 0; i < charactersList.size(); i++) {
					wA = new WeaponAccuracy(charactersList.get(i).getWeapon());
					charactersList.get(i).getWeapon().accuracy = wA.getAccuracy();
				}
			}
			if(e.getSource()==buttonClearText){
				textArea.setText("");
			}
		}
		
		if(e.getSource()==buttonBack){
			GUIEra guiEra = new GUIEra();
			guiEra.initComponents();
			frame.dispose();
		}
		if(e.getSource()==buttonExit){
			frame.dispose();
		}
		if(e.getSource()==buttonChar1){
			firstUnit++;
			if (charList.get(0)=="PRINCESS") {
				createCharacters(charList.get(0)+ firstUnit, null, CharactersType.PRINCESS);
			} else {
				createCharacters(charList.get(0)+ firstUnit, subject, CharactersType.TANK);
			}
		}
		if(e.getSource()==buttonChar2){
			secondUnit++;
			if (charList.get(1)=="KNIGHT") {
				createCharacters(charList.get(1)+ secondUnit, subject, CharactersType.KNIGHT);
			} else {
				createCharacters(charList.get(1)+ secondUnit, subject, CharactersType.SOLDIER);
			}
		}
		if(e.getSource()==buttonChar3){
			thirdUnit++;
			if (charList.get(2)=="ARCHER") {
				createCharacters(charList.get(2) + thirdUnit, subject, CharactersType.ARCHER);
			} else {
				createCharacters(charList.get(2) + thirdUnit, subject, CharactersType.FIGHTERAIRCRAFT);
			}
		}
		if(e.getSource()==buttonChar4){
			forthUnit++;
			if (charList.get(3)=="FOOTSOLDIER") {
				createCharacters(charList.get(3) + forthUnit, subject, CharactersType.FOOTSOLDIER);
			} else {
				createCharacters(charList.get(3) + forthUnit, subject, CharactersType.SHIP);
			}
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
			text+=charactersList.get(i).checkWeapons() + "\n";
			text+=charactersList.get(i).fight();
			text+="\n";
		}
		return text;
	}
	public void changeBehaviour(){}
}