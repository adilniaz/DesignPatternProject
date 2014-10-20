package main_package;

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
		
	}
	public void initComponents3() {
		/*
		secondFrame = new JFrame();
        secondFrame.setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);

        JPanel panel2 = new JPanel();
        JPanel panelButtons = new JPanel();
        JPanel panelPanel = new JPanel();
        Container container = secondFrame.getContentPane();
        panel2.setSize(100,100);
        panel2.setLayout(new GridLayout(1,2));
        panel2.add(panelButtons);
        panelButtons.setLayout(new GridLayout(1000,1));
        panel2.add(panelPanel);
        for(int i = 0; i<1000;i++){
        	JButton button = new JButton("JLabel "+i);
        	button.setPreferredSize(new Dimension(100, 100));
        	panelButtons.add(button);
        }
        JScrollPane scrollPane = new JScrollPane(panel2);
        container.add(scrollPane);
        secondFrame.setSize(200,200);
        secondFrame.setLocation(750, 100);
        secondFrame.setUndecorated(true);
        secondFrame.setVisible(true);
        */
		
		

		setLocation(framePositionX, framePositionY);
		setSize(frameWidth, frameHeight);
		
		panel = new JPanel();
		panel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		
		int centerWidth = frameWidth/2-buttonWidth/2;
		int centerHeight = frameHeight/2-buttonHeight/2;
		
		
		// MAZE, ANTCOLONY, WORLDMAP
		buttonMaze = new JButton("MAZE (not available)");
		buttonMaze.addActionListener(this);
		buttonMaze.setSize(buttonWidth, buttonHeight);
		buttonMaze.setBounds(centerWidth, centerHeight,
				buttonWidth, buttonHeight);

		buttonAntColony = new JButton("ANTCOLONY (not available)");
		buttonAntColony.addActionListener(this);
		buttonAntColony.setSize(buttonWidth, buttonHeight);
		buttonAntColony.setBounds(centerWidth, centerHeight+buttonHeight,
				buttonWidth, buttonHeight);

		buttonWorldMap = new JButton("WORLDMAP");
		buttonWorldMap.addActionListener(this);
		buttonWorldMap.setSize(buttonWidth, buttonHeight);
		buttonWorldMap.setBounds(centerWidth, centerHeight+buttonHeight*2,
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
