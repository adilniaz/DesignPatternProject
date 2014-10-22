package example_main_package;

import implementations.factories.gameplatforms.GameEnvironment;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUIPlatformMenu extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3174305292185357385L;
	/**
	 * 		FOR INITCOMPONENTS3
	 */
	private JPanel panel;
	private JButton buttonMaze;
	private JButton buttonAntColony;
	private JButton buttonWorldMap;
	private JButton buttonExit;
	private JButton buttonBack;

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
	private Color componentColor;
	
	public enum GamePlatformType{
		UNDEFINED,
		MAZE, ANTCOLONY, WORLDMAP
	}
	public GUIPlatformMenu() {

		measurements = new Measurements();

		this.buttonWidth = measurements.buttonWidth;
		this.buttonHeight = measurements.buttonHeight;
		this.frameWidth = measurements.frameWidth;
		this.frameHeight = measurements.frameHeight;
		this.framePositionX = measurements.framePositionX;
		this.framePositionY = measurements.framePositionY;
		this.componentColor = measurements.componentColor;
		
	}
	public void initComponents3() {
		setLocation(framePositionX, framePositionY);
		setSize(frameWidth, frameHeight);
		
		panel = new JPanel();
		panel.setBorder(BorderFactory.createLineBorder(Color.black));
		panel.setBackground(componentColor);
		
		int centerWidth = frameWidth/2-buttonWidth/2;
		int centerHeight = frameHeight/2-buttonHeight/2;
		
		
		// MAZE, ANTCOLONY, WORLDMAP
		buttonMaze = new JButton("MAZE (not available)");
		buttonMaze.addActionListener(this);
		buttonMaze.setSize(buttonWidth, buttonHeight);
		buttonMaze.setBounds(centerWidth, centerHeight,
				buttonWidth, buttonHeight);
		buttonMaze.setBackground(componentColor);
		
		buttonAntColony = new JButton("ANTCOLONY (not available)");
		buttonAntColony.addActionListener(this);
		buttonAntColony.setSize(buttonWidth, buttonHeight);
		buttonAntColony.setBounds(centerWidth, centerHeight+buttonHeight,
				buttonWidth, buttonHeight);
		buttonAntColony.setBackground(componentColor);
		
		buttonWorldMap = new JButton("WORLDMAP");
		buttonWorldMap.addActionListener(this);
		buttonWorldMap.setSize(buttonWidth, buttonHeight);
		buttonWorldMap.setBounds(centerWidth, centerHeight+buttonHeight*2,
				buttonWidth, buttonHeight);
		buttonWorldMap.setBackground(componentColor);
		
		buttonBack = new JButton("BACK");
		buttonBack.addActionListener(this);
		buttonBack.setSize(buttonWidth, buttonHeight);
		buttonBack.setBounds(centerWidth, centerHeight+buttonHeight*4,
				buttonWidth, buttonHeight);
		buttonBack.setBackground(componentColor);
		
		buttonExit = new JButton("EXIT");
		buttonExit.addActionListener(this);
		buttonExit.setSize(buttonWidth, buttonHeight);
		buttonExit.setBounds(centerWidth, centerHeight+buttonHeight*5,
				buttonWidth, buttonHeight);
		buttonExit.setBackground(componentColor);
		
		add(panel);
		
		panel.setLayout(null);
		panel.add(buttonMaze);
		panel.add(buttonAntColony);
		panel.add(buttonWorldMap);
		panel.add(buttonBack);
		panel.add(buttonExit);
		
		setUndecorated(true);
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		/**
		 * 		FOR INITCOMPONENTS3
		 */
		if(e.getSource()==buttonAntColony){
			GameEnvironment gE = new GameEnvironment();
			gE.createGamePlatform(GamePlatformType.ANTCOLONY);
			
			dispose();
		}
		if(e.getSource()==buttonMaze){
			GameEnvironment gE = new GameEnvironment();
			gE.createGamePlatform(GamePlatformType.MAZE);
			
			dispose();
		}
		if(e.getSource()==buttonWorldMap){
			GameEnvironment gE = new GameEnvironment();
			gE.createGamePlatform(GamePlatformType.WORLDMAP);
			
			GUIEra era = new GUIEra();
			era.initComponents();
			
			dispose();
		}
		if(e.getSource()==buttonBack){
			MainGUI mgui = new MainGUI();
			mgui.initComponentsS();
			dispose();
		}
		if(e.getSource()==buttonExit){
			dispose();
		}
	}

}
