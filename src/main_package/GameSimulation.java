package main_package;

import implementations.decorators.statistics.statisticsdecorators.Agility;
import implementations.decorators.statistics.statisticsdecorators.Health;
import implementations.decorators.statistics.statisticsdecorators.Speed;
import implementations.decorators.weapons.weapondecorators.WeaponAccuracy;
import implementations.decorators.weapons.weapondecorators.WeaponDamage;
import implementations.decorators.weapons.weapondecorators.WeaponRange;
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
import abstracts_interfaces.decorators.statistics.StatisticsAbstract;
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
	private JTextArea textArea;
	private JScrollPane scrollPane;
	/**
	 * 		CREATE CHARACTERS
	 */
	private JButton buttonChar1, buttonChar2, buttonChar3, buttonChar4;
	private int firstUnit, secondUnit, thirdUnit, forthUnit;
	/**
	 * 		OPTIONS
	 */
	private JButton buttonShowCharacterNames;
	private JButton buttonShowCharacterStats;
	private JButton buttonSpeak;
	private JButton buttonFight;
	private JButton buttonWarMode;
	private JButton buttonPeaceMode;
	private JButton buttonUndefinedMode;
	private JButton buttonRetreatMode;
	/**
	 * 		OTHER OPTIONS
	 */
	private JButton buttonClearText;
	private JButton buttonBack;
	private JButton buttonExit;
	/**
	 * 		FOR WEAPON DECORATION
	 */
	private JButton buttonIncreaseAccuracy;
	private JButton buttonIncreaseDamage;
	private JButton buttonIncreaseRange;
	/**
	 * 		FOR STAT DECORATION
	 */
	private JButton buttonIncreaseAgility;
	private JButton buttonIncreaseHealth;
	private JButton buttonIncreaseSpeed;

	/**
	 * 		FOR COMPONENTS DIMENTIONS
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

	private WeaponAbstract weaponAbstract;
	private StatisticsAbstract statAbs;
	
	private CreateCharactersFactoryAbstract factory;
	
	private Functionnalities functionnalities;
	
	public GameSimulation(CreateCharactersFactoryAbstract factory, ArrayList<String> charList){
		
		measurements = new Measurements();
		functionnalities = new Functionnalities();
		
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
		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		paneltextArea = new JPanel();
		paneltextArea.setBounds(buttonWidth+1, 1, width, height);
		
		textArea = new JTextArea();
		
		scrollPane = new JScrollPane(textArea);
		scrollPane.setPreferredSize(new Dimension(width-5, height-10));
		
		/**
		 * 		CREATE CHARACTERS
		 */
		buttonChar1 = buttonInitialiser(charList.get(0), 0, 1);
		buttonChar2 = buttonInitialiser(charList.get(1), 1, 1);
		buttonChar3 = buttonInitialiser(charList.get(2), 2, 1);
		buttonChar4 = buttonInitialiser(charList.get(3), 3, 1);
		
		/**
		 * 		OPTIONS
		 */
		buttonShowCharacterNames = buttonInitialiser("SHOW ALL CHARACTERS", 4, 2);
		buttonShowCharacterStats = buttonInitialiser("CHARACTERS' STATS", 5, 2);
		buttonSpeak = buttonInitialiser("SPEAK", 6, 2);
		buttonFight = buttonInitialiser("ATTACK!!", 7, 2);
		buttonWarMode = buttonInitialiser("WAR MODE", 8, 2);
		buttonPeaceMode = buttonInitialiser("PEACE MODE", 9, 2);
		buttonRetreatMode = buttonInitialiser("RETREAT MODE", 10, 2);
//		buttonUndefinedMode = buttonInitialiser("UNDEFINED MODE", 11, 2);
		
		/**
		 * 		FOR WEAPON DECORATION
		 */
		buttonIncreaseAccuracy = buttonInitialiser("INCREASE ACCURACY", 11, 3);
		buttonIncreaseDamage = buttonInitialiser("INCREASE DAMAGE", 12, 3);
		buttonIncreaseRange = buttonInitialiser("INCREASE RANGE", 13, 3);		//	Applicable to only some Specialties
		
		/**
		 * 		FOR STAT DECORATION
		 */
		buttonIncreaseAgility = buttonInitialiser("INCREASE AGILITY", 14, 4);
		buttonIncreaseHealth = buttonInitialiser("INCREASE HEALTH", 15, 4);
		buttonIncreaseSpeed = buttonInitialiser("INCREASE SPEED", 16, 4);

		/**
		 * 		OTHER OPTIONS
		 */
		buttonClearText = buttonInitialiser("CLEAR TEXT", 18, 5);
		buttonBack = buttonInitialiser("BACK", 19, 6);
		buttonExit = buttonInitialiser("EXIT", 20, 6);
		
		frame.add(panel);
		
		panelsManager();
		
		frame.setUndecorated(true);
		frame.setVisible(true);
		
	}
	
	private JButton buttonInitialiser(String name, int i, int j) {
		JButton button = new JButton(name);
		button.addActionListener(this);
		button.setBounds(1, buttonHeight*i+j, buttonWidth, buttonHeight);
		return button;
	}
	
	public void panelsManager() {
		
		panel.setBackground(Color.BLACK);
		
		panel.setLayout(null);
		
		/**
		 * 		CREATE CHARACTERS
		 */
		panel.add(buttonChar1);
		panel.add(buttonChar2);
		panel.add(buttonChar3);
		panel.add(buttonChar4);
		
		/**
		 * 		OPTIONS
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
		 */
		panel.add(buttonIncreaseAccuracy);
		panel.add(buttonIncreaseDamage);
		panel.add(buttonIncreaseRange);
		
		/**
		 * 		FOR STAT DECORATION
		 */
		panel.add(buttonIncreaseAgility);
		panel.add(buttonIncreaseHealth);
		panel.add(buttonIncreaseSpeed);
	
		/**
		 * 		OTHER OPTIONS
		 */
		panel.add(buttonClearText);
		panel.add(buttonBack);
		panel.add(buttonExit);
		panel.add(paneltextArea);

		paneltextArea.setBackground(Color.BLACK);
		paneltextArea.add(scrollPane, BorderLayout.CENTER);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (!charactersList.isEmpty()) {
			
			/**
			 * 		OPTIONS
			 */
			if(e.getSource()==buttonShowCharacterNames){
				displayer(functionnalities.everyCharactersName(charactersList));
			}
			if(e.getSource()==buttonShowCharacterStats){
				displayer(functionnalities.everyCharactersStats(charactersList));
			}
			if(e.getSource()==buttonSpeak){
				displayer(functionnalities.everyCharactersSpeech(charactersList));
			}
			if(e.getSource()==buttonFight){
				displayer(functionnalities.startBattle(charactersList));
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
			
			/**
			 * 		FOR WEAPON DECORATION
			 */
			if(e.getSource()==buttonIncreaseAccuracy){
				for (int i = 0; i < charactersList.size(); i++) {
					if (charactersList.get(i).getWeapon()!=null) {
						weaponAbstract = new WeaponAccuracy(charactersList.get(i).getWeapon());
						charactersList.get(i).getWeapon().accuracy = weaponAbstract.getAccuracy();
					}
					
				}
			}
			if(e.getSource()==buttonIncreaseDamage){
				for (int i = 0; i < charactersList.size(); i++) {
					if (charactersList.get(i).getWeapon()!=null) {
						weaponAbstract = new WeaponDamage(charactersList.get(i).getWeapon());
						charactersList.get(i).getWeapon().damage = weaponAbstract.getDamage();
					}
					
				}
			}
			if(e.getSource()==buttonIncreaseRange){
				for (int i = 0; i < charactersList.size(); i++) {
					// for some units // To determine
					if (charactersList.get(i).getWeapon()!=null /*&& some condition*/) {
						weaponAbstract = new WeaponRange(charactersList.get(i).getWeapon());
						charactersList.get(i).getWeapon().range = weaponAbstract.getRange();
					}
				}
			}
			
			/**
			 * 		FOR STAT DECORATION
			 */
			if(e.getSource()==buttonIncreaseAgility){
				for (int i = 0; i < charactersList.size(); i++) {
					statAbs = new Agility(charactersList.get(i).getStats());
					charactersList.get(i).getStats().agility = statAbs.getAgility();
				}
			}
			if(e.getSource()==buttonIncreaseHealth){
				for (int i = 0; i < charactersList.size(); i++) {
					statAbs = new Health(charactersList.get(i).getStats());
					charactersList.get(i).getStats().health = statAbs.getHealth();
				}
			}
			if(e.getSource()==buttonIncreaseSpeed){
				for (int i = 0; i < charactersList.size(); i++) {
					statAbs = new Speed(charactersList.get(i).getStats());
					charactersList.get(i).getStats().speed = statAbs.getSpeed();
				}
			}
		}
		
		/**
		 * 		CREATE CHARACTERS
		 */
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
		
		/**
		 * 		OTHER OPTIONS
		 */
		if(e.getSource()==buttonClearText){
			textArea.setText("");
		}
		if(e.getSource()==buttonBack){
			GUIEra guiEra = new GUIEra();
			guiEra.initComponents();
			frame.dispose();
		}
		if(e.getSource()==buttonExit){
			frame.dispose();
		}
	}
	
	public String strategy(Strategy strategy) {
		subject.setOperatingMode(strategy);
		String text="\n";
		for (int i = 0; i < charactersList.size(); i++) {
			text+=charactersList.get(i).getOperatingState();
			text+="\n";
		}
		return text;
	}	
	public void displayer(String text){
		textArea.setText(textArea.getText() + text);
	}	
	
	public void changeBehaviour(){}
}