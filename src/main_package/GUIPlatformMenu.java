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
	
	public enum GamePlatformType{
		UNDEFINED,
		MAZE, ANTCOLONY, WORLDMAP
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
		
		

		setLocation(200, 100);
		setSize(600, 600);
		
		panel = new JPanel();
		panel.setBorder(BorderFactory.createLineBorder(Color.black));
		

		int buttonsWidth = 200;
		int buttonsHeight = 40;
		int centerWidth = getWidth()/2-buttonsWidth/2;
		int centerHeight = getHeight()/2-buttonsHeight/2;
		
		
		// MAZE, ANTCOLONY, WORLDMAP
		buttonMaze = new JButton("MAZE (not available)");
		buttonMaze.addActionListener(this);
		buttonMaze.setSize(buttonsWidth, buttonsHeight);
		buttonMaze.setBounds(centerWidth, centerHeight,
				buttonsWidth, buttonsHeight);

		buttonAntColony = new JButton("ANTCOLONY (not available)");
		buttonAntColony.addActionListener(this);
		buttonAntColony.setSize(buttonsWidth, buttonsHeight);
		buttonAntColony.setBounds(centerWidth, centerHeight+buttonsHeight,
				buttonsWidth, buttonsHeight);

		buttonWorldMap = new JButton("WORLDMAP");
		buttonWorldMap.addActionListener(this);
		buttonWorldMap.setSize(buttonsWidth, buttonsHeight);
		buttonWorldMap.setBounds(centerWidth, centerHeight+buttonsHeight*2,
				buttonsWidth, buttonsHeight);

		buttonExit = new JButton("EXIT");
		buttonExit.addActionListener(this);
		buttonExit.setSize(buttonsWidth, buttonsHeight);
		buttonExit.setBounds(centerWidth, centerHeight+buttonsHeight*4,
				buttonsWidth, buttonsHeight);
		
		add(panel);
		
		panel.setLayout(null);
		panel.add(buttonMaze);
		panel.add(buttonAntColony);
		panel.add(buttonWorldMap);
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
		if(e.getSource()==buttonExit){
			dispose();
		}
	}

}
