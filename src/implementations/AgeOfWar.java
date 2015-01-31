package implementations;

import implementations.factories.GeneralCharacterFactory;
import implementations.organizations.Organization;
import implementations.views.Window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.Timer;

import simulationlauncher.SimulationLauncher;
import abstracts_interfaces.CharacterAbstract;
import abstracts_interfaces.SimulationAbstract;

public class AgeOfWar implements SimulationAbstract, ActionListener{
	
	Timer timer;
	
	JPanel mainPanel;
	
	JPanel gamePanel, actionsPanel, displayPanel;
	
	JTabbedPane tabbedPane;
	
	HashMap<String, JTextArea> textAreaMap;
	
	JButton characterButton1, characterButton2, characterButton3, characterButton4;
	JButton weaponBonus, characterBonus, specialAttack, ageButton, goldExpBonus;
	JButton exitButton;
	
	JTextArea infoTextArea;
	
	int gold, experience;
	
	GeneralCharacterFactory characterFatory;
	
	int agePlayer, ageAI;

	LinkedList<CharacterAbstract> playersCharacters, artifIntelCharacters;
	LinkedList<CharacterAbstract> playersDeadCharacters, artifIntelDeadCharacters;
	
	Organization player, artifIntel;
	
	int count;
	
	@Override
	public void run(Window window) {
		agePlayer = 0;
		ageAI = 0;
		
		gold = 500;
		experience = 0;
		
		characterFatory = new GeneralCharacterFactory();
		
		playersCharacters = new LinkedList<CharacterAbstract>();
		artifIntelCharacters = new LinkedList<CharacterAbstract>();
		
		playersDeadCharacters = new LinkedList<CharacterAbstract>();
		artifIntelDeadCharacters = new LinkedList<CharacterAbstract>();
		
		player = new Organization();
		artifIntel = new Organization();
		
		count = 0;
		
		//size of the screen
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Double width = screenSize.getWidth();
		Double height = screenSize.getHeight();
		
		//height of the task bar
		Rectangle winSize = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
		int taskBarHeight = screenSize.height - winSize.height;

		int winW = width.intValue();
		int winH = height.intValue() - taskBarHeight;
		
		window.setSize(winW, winH);
		window.setLocation(0, 0);
		
		window.getContentPane().removeAll();
		window.setLayout(null);
		
		textAreaMap = new HashMap<String, JTextArea>();
		
		mainPanel = new JPanel();
		mainPanel.setBounds(0, 0, winW-6, winH-29);
		mainPanel.setLayout(null);
		
		actionsPanel = new JPanel();
		actionsPanel.setBounds(0, 0, mainPanel.getWidth(), 100);
		actionsPanel.setBackground(Color.BLACK);
		actionsPanel.setLayout(null);
		
		gamePanel = new JPanel();
		gamePanel.setBounds(0, actionsPanel.getHeight(), mainPanel.getWidth()-300, mainPanel.getHeight()-actionsPanel.getHeight());
		gamePanel.setLayout(null);
		
		displayPanel = new JPanel();
		displayPanel.setBounds(gamePanel.getWidth(), actionsPanel.getHeight(),
				mainPanel.getWidth()-gamePanel.getWidth(), mainPanel.getHeight()-actionsPanel.getHeight());
		displayPanel.setLayout(null);

		window.add(mainPanel);
		
		characterButton1 = actionComponents(characterButton1, "character1.png", 0);
		characterButton2 = actionComponents(characterButton2, "character2.png", 1);
		characterButton3 = actionComponents(characterButton3, "character3.png", 2);
		characterButton4 = actionComponents(characterButton4, "character4.png", 3);

		characterBonus = actionComponents(characterBonus, "characterbonus.png", 5);
		weaponBonus = actionComponents(weaponBonus, "weaponBonus.png", 6);
		specialAttack = actionComponents(specialAttack, "specialattack.png", 7);
		ageButton = actionComponents(ageButton, "agebutton.png", 8);
		goldExpBonus = actionComponents(goldExpBonus, "goldexpbonus.png", 9);
		exitButton = actionComponents(exitButton, "exitbutton.png", 12);
		exitButton.setLocation(exitButton.getLocation().x+34, exitButton.getLocation().y);

		infoTextArea = new JTextArea();
		infoTextArea.setSize(200, actionsPanel.getHeight());
		infoTextArea.setLocation(1274, 0);
		infoTextArea.setEditable(false);
		setInformation(gold, experience);
		
		mainPanel.add(gamePanel);
		mainPanel.add(actionsPanel);
		mainPanel.add(displayPanel);
		
		tabbedPane = new JTabbedPane();
		tabbedPane.setBounds(0, 0, displayPanel.getWidth(), displayPanel.getHeight());
		
		displayPanel.add(tabbedPane);
		actionsPanel.add(characterButton1);
		actionsPanel.add(characterButton2);
		actionsPanel.add(characterButton3);
		actionsPanel.add(characterButton4);
		actionsPanel.add(weaponBonus);
		actionsPanel.add(characterBonus);
		actionsPanel.add(specialAttack);
		actionsPanel.add(ageButton);
		actionsPanel.add(goldExpBonus);
		actionsPanel.add(infoTextArea);
		actionsPanel.add(exitButton);
		
		addTabs("player");
		addTabs("ai");
		
        timer = new Timer(20, this);
        timer.start();
        
        
		refresher(window);
	}

	private void setInformation(int gold, int experience) {
		infoTextArea.setText(" GOLD :\n " + gold + "\n EXPERIENCE :\n " + experience);
		infoTextArea.setFont(new Font("", 20, 20));
	}

	public void refresher(Window window) {
		window.revalidate();
		window.repaint();
	}

	private JButton actionComponents(JButton button, String name, int position) {
		Icon icon = new ImageIcon("src\\res\\"+name);
		button = new JButton(icon);
		button.setFont(new Font("", 10, 12));
		button.setSize(120, actionsPanel.getHeight());
		button.setLocation(button.getWidth()*position, 0);
		button.addActionListener(this);
		return button;
	}

	private void addTabs(String user) {
		String textAreaAlive = user + "textAreaAlive";
		String textAreaDead = user + "textAreaDead";
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.RED);
		
		JPanel panelAliveCharacters = new JPanel();
		panelAliveCharacters.setBounds(0, 0, displayPanel.getWidth()-5, displayPanel.getHeight()/2-11);
		panelAliveCharacters.setBackground(Color.BLACK);
		
		JPanel panelDeadCharacters = new JPanel();
		panelDeadCharacters.setBounds(0, displayPanel.getHeight()/2-11, displayPanel.getWidth()-5, displayPanel.getHeight()/2-16);
		panelDeadCharacters.setBackground(Color.BLUE);
		
		panel.add(panelAliveCharacters);
		panel.add(panelDeadCharacters);

		tabbedPane.addTab(user, panel);

		JTextArea textAreaAliveCharacters = new JTextArea(21, 26);
		textAreaAliveCharacters.setEditable(false);
		JScrollPane scrollPaneAliveCharacters = new JScrollPane(textAreaAliveCharacters);

		JTextArea textAreaDeadCharacters = new JTextArea(21, 26);
		textAreaDeadCharacters.setEditable(false);
		textAreaDeadCharacters.setForeground(Color.RED);
		JScrollPane scrollPaneDeadCharacters = new JScrollPane(textAreaDeadCharacters);
		
		panelAliveCharacters.add(scrollPaneAliveCharacters, BorderLayout.CENTER);
		panelDeadCharacters.add(scrollPaneDeadCharacters, BorderLayout.CENTER);

		textAreaMap.put(textAreaAlive, textAreaAliveCharacters);
		textAreaMap.put(textAreaDead, textAreaDeadCharacters);
	}

	public void setCharactersInformation(JTextArea textArea, LinkedList<CharacterAbstract> list){
		textArea.setText("");
		for (int i = 0; i < list.size(); i++) {
			textArea.append(list.get(i).getName() + "\n");
		}
	}

	public void setDeadCharactersInformation(JTextArea textArea, String text){
		textArea.append(text + "\n");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (count == 100) {
			aiManager();
			count = 0;
		} else {
			count++;
		}
		if (e.getSource() == characterButton1) {
			if (playersCharacters.size() < 10) {
				CharacterAbstract charac = characterFatory.createCharacter(agePlayer, 0, player);
				playersCharacters.add(charac);
				setCharactersInformation(textAreaMap.get("playertextAreaAlive"), playersCharacters);
			}
		}
		if (e.getSource() == characterButton2) {
			if (playersCharacters.size() < 10) {
				CharacterAbstract charac = characterFatory.createCharacter(agePlayer, 1, player);
				playersCharacters.add(charac);
				setCharactersInformation(textAreaMap.get("playertextAreaAlive"), playersCharacters);
			}
		}
		if (e.getSource() == characterButton3) {
			if (playersCharacters.size() < 10) {
				CharacterAbstract charac = characterFatory.createCharacter(agePlayer, 2, player);
				playersCharacters.add(charac);
				setCharactersInformation(textAreaMap.get("playertextAreaAlive"), playersCharacters);
			}
		}
		if (e.getSource() == characterButton4) {
			if (playersCharacters.size() < 10) {
				CharacterAbstract charac = characterFatory.createCharacter(agePlayer, 3, player);
				playersCharacters.add(charac);
				setCharactersInformation(textAreaMap.get("playertextAreaAlive"), playersCharacters);
			}
		}

		if (e.getSource() == ageButton) {
			if (agePlayer<4) {
				agePlayer++;
			}
		}

		if (e.getSource() == exitButton) {
			if (playersCharacters.size() > 0 ) {
				playersDeadCharacters.add(playersCharacters.pop());
				setCharactersInformation(textAreaMap.get("playertextAreaAlive"), playersCharacters);
				setCharactersInformation(textAreaMap.get("playertextAreaDead"), playersDeadCharacters);
			}
			if (artifIntelCharacters.size() > 0) {
				artifIntelDeadCharacters.add(artifIntelCharacters.pop());
				setCharactersInformation(textAreaMap.get("aitextAreaAlive"), artifIntelCharacters);
				setCharactersInformation(textAreaMap.get("aitextAreaDead"), artifIntelDeadCharacters);
			}
			new SimulationLauncher();
		}
		
		if (e.getSource() == specialAttack) {
			System.out.println("Special Attack");
		}

		if (e.getSource() == goldExpBonus) {
			System.out.println("Gold Exp Bonus");
		}
	}

	private void aiManager() {
		Random rand = new Random();
		int rN = rand.nextInt((3 - 0) + 1) + 0;
		
		CharacterAbstract charac = characterFatory.createCharacter(ageAI, rN, player);
		artifIntelCharacters.add(charac);
		setCharactersInformation(textAreaMap.get("aitextAreaAlive"), artifIntelCharacters);
	}
}
