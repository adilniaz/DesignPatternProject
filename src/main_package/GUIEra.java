package main_package;

import implementations.factories.characters.CreateCharactersGulfWarFactory;
import implementations.factories.characters.CreateCharactersMiddleAgeFactory;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import abstracts_interfaces.factories.characters.CreateCharactersFactoryAbstract;

public class GUIEra extends JFrame implements ActionListener{ 
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5063892328715601144L;
	private JPanel panel;
	private JButton buttonMiddleAge;
	private JButton buttonGulfWar;
	private JButton buttonBack;
	private JButton buttonExit;

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
	
	public GUIEra() {

		measurements = new Measurements();

		this.buttonWidth = measurements.buttonWidth;
		this.buttonHeight = measurements.buttonHeight;
		this.frameWidth = measurements.frameWidth;
		this.frameHeight = measurements.frameHeight;
		this.framePositionX = measurements.framePositionX;
		this.framePositionY = measurements.framePositionY;
		
	}
	public void initComponents(){
		
		setLocation(framePositionX, framePositionY);
		setSize(frameWidth, frameHeight);
		
		panel = new JPanel();
		panel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		int centerWidth = frameWidth/2-buttonWidth/2;
		int centerHeight = frameHeight/2-buttonHeight/2;
		
		buttonMiddleAge = new JButton("MIDDLE AGE");
		buttonMiddleAge.addActionListener(this);
		buttonMiddleAge.setSize(buttonWidth, buttonHeight);
		buttonMiddleAge.setBounds(centerWidth, centerHeight,
				buttonWidth, buttonHeight);

		buttonGulfWar = new JButton("GULF WAR");
		buttonGulfWar.addActionListener(this);
		buttonGulfWar.setSize(buttonWidth, buttonHeight);
		buttonGulfWar.setBounds(centerWidth, centerHeight+buttonHeight,
				buttonWidth, buttonHeight);
		

		buttonBack = new JButton("BACK");
		buttonBack.addActionListener(this);
		buttonBack.setSize(buttonWidth, buttonHeight);
		buttonBack.setBounds(centerWidth, centerHeight+buttonHeight*4,
				buttonWidth, buttonHeight);
		
		buttonExit = new JButton("EXIT");
		buttonExit.addActionListener(this);
		buttonExit.setSize(buttonWidth, buttonHeight);
		buttonExit.setBounds(centerWidth, centerHeight+buttonHeight*5,
				buttonWidth, buttonHeight);
		
		add(panel);
		
		panel.setLayout(null);
		panel.add(buttonMiddleAge);
		panel.add(buttonGulfWar);
		panel.add(buttonBack);
		panel.add(buttonExit);
		
		setUndecorated(true);
		setVisible(true);
	}
	
	ArrayList<String> charList;
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==buttonMiddleAge){
			charList = new ArrayList<>();
			charList.add("PRINCESS");
			charList.add("KNIGHT");
			charList.add("ARCHER");
			charList.add("FOOTSOLDIER");
			CreateCharactersFactoryAbstract middleAgeFactory = new CreateCharactersMiddleAgeFactory();
			new GameSimulation(middleAgeFactory, charList);
			dispose();
		}
		if(e.getSource()==buttonGulfWar){
			charList = new ArrayList<>();
			charList.add("TANK");
			charList.add("SOLDIER");
			charList.add("FIGHTERAIRCRAFT");
			charList.add("SHIP");
			CreateCharactersFactoryAbstract gulfWarFactory = new CreateCharactersGulfWarFactory();
			new GameSimulation(gulfWarFactory, charList);
			dispose();
		}
		if(e.getSource()==buttonBack){
			GUIPlatformMenu guiPlatform = new GUIPlatformMenu();
			guiPlatform.initComponents3();
			dispose();
		}
		if(e.getSource()==buttonExit){
			dispose();
		}
	}

}
