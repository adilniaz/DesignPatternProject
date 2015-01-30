package implementations;

import implementations.views.Window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.HashMap;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import abstracts_interfaces.SimulationAbstract;

public class AgeOfWar implements SimulationAbstract{
	
	JPanel mainPanel;
	
	JPanel gamePanel, actionsPanel, displayPanel;
	
	JTabbedPane tabbedPane;
	
	HashMap<String, JTextArea> textAreaMap;
	
	JButton characterButton1, characterButton2, characterButton3, characterButton4;
	JButton weaponBonus, characterBonus, specialAttack, ageButton, goldExpBonus;
	JButton exitButton;
	
	@Override
	public void run(Window window) {
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
		

//		weaponBonus, characterBonus, specialAttack, ageButton, goldExpBonus, exitButton;
		characterButton1 = actionComponents(characterButton1, "character1.png", 0);
		characterButton2 = actionComponents(characterButton2, "character2.png", 1);
		characterButton3 = actionComponents(characterButton3, "character3.png", 2);
		characterButton4 = actionComponents(characterButton4, "character4.png", 3);

		weaponBonus = actionComponents(weaponBonus, "weaponBonus.png", 5);
		characterBonus = actionComponents(characterBonus, "characterbonus.png", 6);
		specialAttack = actionComponents(specialAttack, "specialattack.png", 7);
		ageButton = actionComponents(ageButton, "agebutton.png", 8);
		goldExpBonus = actionComponents(goldExpBonus, "goldexpbonus.png", 9);
		exitButton = actionComponents(exitButton, "exitbutton.png", 10);
		
		
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
		actionsPanel.add(exitButton);
		
		addTabs("Player");
		addTabs("AI");
		
		refresher(window);
		
		
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
		textAreaMap.put(textAreaDead, textAreaAliveCharacters);
	}

}
