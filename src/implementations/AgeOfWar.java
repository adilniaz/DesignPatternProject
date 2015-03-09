package implementations;

import implementations.decorators.statistics.statisticsdecorators.Defence;
import implementations.decorators.statistics.statisticsdecorators.Health;
import implementations.decorators.statistics.statisticsdecorators.Speed;
import implementations.decorators.weapons.weaponsdecorators.AttackRate;
import implementations.decorators.weapons.weaponsdecorators.Damage;
import implementations.decorators.weapons.weaponsdecorators.Range;
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

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.Timer;
import javax.swing.border.Border;

import simulationlauncher.Launcher;
import simulationlauncher.SimulationLauncher;
import abstracts_interfaces.CharacterAbstract;
import abstracts_interfaces.SimulationAbstract;
import abstracts_interfaces.decorators.statistics.StatisticsAbstract;
import abstracts_interfaces.decorators.weapons.WeaponAbstract;

public class AgeOfWar implements SimulationAbstract, ActionListener{
	
	boolean gameRunning;
	
	Timer timer;
	
	JPanel mainPanel;
	
	JPanel gamePanel, actionsPanel, displayPanel;
	
	JTabbedPane tabbedPane;
	
	HashMap<String, JTextArea> textAreaMap;
	
	JButton characterButton1, characterButton2, characterButton3, characterButton4;
	JButton weaponBonus, characterBonus, specialAttack, ageButton, goldExpBonus;
	JButton exitButton;
	
	JTextArea infoTextArea;

	int playerGold, playerExperience;
	int aiGold, aiExperience;
	
	GeneralCharacterFactory characterFatory;
	
	int agePlayer, ageAI;
	
	LinkedList<CharacterAbstract> playersCharacters, artifIntelCharacters;
	LinkedList<CharacterAbstract> playersDeadCharacters, artifIntelDeadCharacters;
	
	Organization player, artifIntel;
	
	int count;
	
	LinkedList<JLabel> playerLabelList, aiLabelList;
	
	Window window;
	
	JLabel aiBase, playerBase;
	int aiBaseHealth, playerBaseHealth;
	
	int goldExperienceBonus;
	
	JTextArea finalTextArea;
	
	@Override
	public void run(Window window) {
		gameRunning = true;
		goldExperienceBonus = 120;
		aiBaseHealth = 1000;
		playerBaseHealth = 1000;
		
		this.window = window;
		finalTextArea = new JTextArea();
		finalTextArea.setBounds(600, 300, 500, 500);
		
		agePlayer = 0;
		ageAI = 0;

		playerGold = 1500;
		playerExperience = 0;

		aiGold = 500;
		aiExperience = 0;
		
		characterFatory = new GeneralCharacterFactory();
		
		playersCharacters = new LinkedList<CharacterAbstract>();
		artifIntelCharacters = new LinkedList<CharacterAbstract>();
		
		playersDeadCharacters = new LinkedList<CharacterAbstract>();
		artifIntelDeadCharacters = new LinkedList<CharacterAbstract>();
		
		player = new Organization();
		artifIntel = new Organization();

		count = 0;
		
		playerLabelList = new LinkedList<JLabel>();
		aiLabelList = new LinkedList<JLabel>();
		
		//size of the screen
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Double width = screenSize.getWidth();
		Double height = screenSize.getHeight();
		
		//height of the task bar
		Rectangle winSize = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
		int taskBarHeight = screenSize.height - winSize.height;
		
		int winW = width.intValue();
		int winH = height.intValue() - taskBarHeight;
		
		this.window.setSize(winW, winH);
		this.window.setLocation(0, 0);
		
		this.window.getContentPane().removeAll();
		this.window.setLayout(null);
		
		textAreaMap = new HashMap<String, JTextArea>();
		
		mainPanel = new JPanel();
		mainPanel.setBounds(0, 0, winW-6, winH-29);
		mainPanel.setLayout(null);
		
		actionsPanel = new JPanel();
		actionsPanel.setBounds(0, 0, mainPanel.getWidth(), 100);
		actionsPanel.setLayout(null);
		
		gamePanel = new JPanel();
		gamePanel.setBounds(0, actionsPanel.getHeight(), mainPanel.getWidth()-300, mainPanel.getHeight()-actionsPanel.getHeight());
		gamePanel.setLayout(null);
		
		displayPanel = new JPanel();
		displayPanel.setBounds(gamePanel.getWidth(), actionsPanel.getHeight(),
				mainPanel.getWidth()-gamePanel.getWidth(), mainPanel.getHeight()-actionsPanel.getHeight());
		displayPanel.setLayout(null);
		
		this.window.add(mainPanel);
		
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
		infoTextArea.setLocation(1200, 0);
		infoTextArea.setEditable(false);
		setInformation();
		
		Border border = BorderFactory.createLineBorder(Color.BLUE, 2);

		playerBase = new JLabel("BASE");
		playerBase.setBounds(0, gamePanel.getHeight()-300, 50, 300);
		playerBase.setText("<html>BASE<br>"+playerBaseHealth+"</html>");
		playerBase.setBorder(border);
		
		aiBase = new JLabel("BASE");
		aiBase.setBounds(gamePanel.getWidth()-50, gamePanel.getHeight()-300, 50, 300);
		aiBase.setText("<html>BASE<br>"+aiBaseHealth+"</html>");
		aiBase.setBorder(border);
		
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

		gamePanel.add(playerBase);
		gamePanel.add(aiBase);
		
        timer = new Timer(20, this);
        timer.start();
        
		refresher();
	}
	
	private void setInformation() {
		infoTextArea.setText(" GOLD :\n " + playerGold + "\n EXPERIENCE :\n " + playerExperience);
		infoTextArea.setFont(new Font("", 20, 20));
	}
	
	public void refresher() {
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
			textArea.append(list.get(i).getName() + "(" + list.get(i).statistics.health + ")" + "\n");
		}
	}
	
	public void setDeadCharactersInformation(JTextArea textArea, String text){
		textArea.append(text + "\n");
	}
	
	
	private void addLabel(LinkedList<JLabel> labelList, CharacterAbstract charac, int posX) {
		Border border = BorderFactory.createLineBorder(Color.BLUE, 1);

		JLabel label = new JLabel(charac.getName());
		label.setBounds(posX , gamePanel.getHeight()-175, 100, 175);
		
		label.setBorder(border);
		labelList.add(label);
	}

	public void showLabels() {
		gamePanel.removeAll();
		for (int i = 0; i < playerLabelList.size(); i++) {
			gamePanel.add(playerLabelList.get(i));
		}
		for (int i = 0; i < aiLabelList.size(); i++) {
			gamePanel.add(aiLabelList.get(i));
		}
		gamePanel.add(playerBase);
		gamePanel.add(aiBase);
		
		gamePanel.revalidate();
		gamePanel.repaint();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (gameRunning) {
			if (count == 150) {
				aiManager();
				count = 0;
			} else {
				count++;
			}
			
			if (e.getSource() == characterButton1) {
				if (playersCharacters.size()<10) {
					characterCreator(0);
				}
			}
			if (e.getSource() == characterButton2) {
				if (playersCharacters.size()<10) {
					characterCreator(1);
				}
			}
			if (e.getSource() == characterButton3) {
				if (playersCharacters.size()<10) {
					characterCreator(2);
				}
			}
			if (e.getSource() == characterButton4) {
				if (playersCharacters.size()<10) {
					characterCreator(3);
				}
			}
			
			if (e.getSource() == weaponBonus) {
				if (playerExperience >= 1000) {
					for (int i = 0; i < playersCharacters.size(); i++) {
						WeaponAbstract weapon = playersCharacters.get(i).getWeapon();
						System.out.println(weapon.getAttackRate() + " " + weapon.getDamage() + " " + weapon.getRange());
						Damage d = new Damage(weapon);
						AttackRate a = new AttackRate(weapon);
						Range r = new Range(weapon);
						
						weapon.setAttackRate(a.getAttackRate());
						weapon.setDamage(d.getDamage());
						weapon.setRange(r.getRange());
						
						System.out.println(weapon.getAttackRate() + " " + weapon.getDamage() + " " + weapon.getRange());
					}
					playerExperience -= 1000;
				}
			}

			if (e.getSource() == characterBonus) {
				if (playerExperience >= 1000) {
					for (int i = 0; i < playersCharacters.size(); i++) {
						StatisticsAbstract statistics = playersCharacters.get(i).statistics;
						Defence d = new Defence(statistics);
						Health h = new Health(statistics);
						Speed s = new Speed(statistics);
						
						statistics.setHealth(h.getHealth());
						statistics.setDefence(d.getDefence());
						statistics.setSpeed(s.getSpeed());
					}
					playerExperience -= 1000;
				}
			}
			
			if (e.getSource() == specialAttack) {
				if (playerExperience > 500) {
					for (int i = 0; i < artifIntelCharacters.size(); i++) {
						artifIntelCharacters.get(i).statistics.setHealth(artifIntelCharacters.get(i).statistics.getHealth()-100);
						if (artifIntelCharacters.get(i).statistics.getHealth() < 0) {
							
							playerGold += (artifIntelCharacters.get(0).cost*120)/100;
							playerExperience += (artifIntelCharacters.get(0).cost*120)/100;
							
							artifIntelDeadCharacters.add(artifIntelCharacters.pop());
							setCharactersInformation(textAreaMap.get("aitextAreaAlive"), artifIntelCharacters);
							setCharactersInformation(textAreaMap.get("aitextAreaDead"), artifIntelDeadCharacters);
							aiLabelList.pop();
							
							setInformation();
						}
					}
					playerExperience -= 500;
				}
			}

			if (e.getSource() == ageButton) {
				if (agePlayer < 4 && playerExperience > 1000) {
					agePlayer++;
					playerExperience -= 1000;
				}
			}
			

			if (e.getSource() == goldExpBonus) {
				if (agePlayer < 4 && playerExperience > 1000) {
					goldExperienceBonus += 20;
					playerExperience -= 1000;
				}
			}
			
			if (e.getSource() == exitButton) {
				timer.stop();
				window.dispose();
				SimulationLauncher simulationLauncher = new SimulationLauncher();
				simulationLauncher.initComponents();
			}
			
			labelMovement();
			attacks();
			limitEliminater();
			setInformation();
			showLabels();
		}
		
	}

	public void characterCreator(int characterIndex) {
		if (playersCharacters.size() < 10) {
			CharacterAbstract charac = characterFatory.createCharacter(agePlayer, characterIndex, player);
			if (charac.cost <= playerGold) {
				playersCharacters.add(charac);
				charac.setMoveDirection(1);
				addLabel(playerLabelList, charac, 0);
				setCharactersInformation(textAreaMap.get("playertextAreaAlive"), playersCharacters);
				playerGold -= charac.cost;
			}
		}
	}
	
	private void limitEliminater() {
		for (int i = 0; i < playerLabelList.size(); i++) {
			if (playerLabelList.get(i).getLocation().x > gamePanel.getWidth()) {
				playersDeadCharacters.add(playersCharacters.pop());
				setCharactersInformation(textAreaMap.get("playertextAreaAlive"), playersCharacters);
				setCharactersInformation(textAreaMap.get("playertextAreaDead"), playersDeadCharacters);
				playerLabelList.pop();
			}
		}
		
		for (int i = 0; i < aiLabelList.size(); i++) {
			if (aiLabelList.get(0).getLocation().x < 0) {
				artifIntelDeadCharacters.add(artifIntelCharacters.pop());
				setCharactersInformation(textAreaMap.get("aitextAreaAlive"), artifIntelCharacters);
				setCharactersInformation(textAreaMap.get("aitextAreaDead"), artifIntelDeadCharacters);
				aiLabelList.pop();
			}
		}
	}

	private void attacks() {
		if (playersCharacters.size() > 0 && aiLabelList.size() > 0) {
			int aiLocation = aiLabelList.get(0).getLocation().x;
			int aiHealth = artifIntelCharacters.get(0).statistics.getHealth();
			int aiweaponRange = artifIntelCharacters.get(0).getWeapon().getRange();
			int aiweaponDamage = artifIntelCharacters.get(0).getWeapon().getDamage();
			int aiBaseLocation = aiBase.getLocation().x;
			
			int playerLocation = playerLabelList.get(0).getLocation().x;
			int playerHealth = playersCharacters.get(0).statistics.getHealth();
			int playerweaponRange = playersCharacters.get(0).getWeapon().getRange();
			int playerweaponDamage = playersCharacters.get(0).getWeapon().getDamage();
			int playerBaseLocation = playerBase.getLocation().x;

			if (aiLocation <= playerLocation + 100 + playerweaponRange) {
				artifIntelCharacters.get(0).statistics.setHealth(aiHealth - playerweaponDamage);
				if (artifIntelCharacters.get(0).statistics.getHealth() < 0) {
					
					playerGold += (artifIntelCharacters.get(0).cost*goldExperienceBonus)/100;
					playerExperience += (artifIntelCharacters.get(0).cost*goldExperienceBonus)/100;
					
					artifIntelDeadCharacters.add(artifIntelCharacters.pop());
					setCharactersInformation(textAreaMap.get("aitextAreaAlive"), artifIntelCharacters);
					setCharactersInformation(textAreaMap.get("aitextAreaDead"), artifIntelDeadCharacters);
					aiLabelList.pop();
					
					setInformation();
				}
			}

			if (playerLocation >= aiLocation - 100 - aiweaponRange) {
				playersCharacters.get(0).statistics.setHealth(playerHealth - aiweaponDamage);
				if (playersCharacters.get(0).statistics.getHealth() < 0) {
					

					aiGold += (playersCharacters.get(0).cost*100)/100;
					aiExperience += (playersCharacters.get(0).cost*150)/100;
					
					playersDeadCharacters.add(playersCharacters.pop());
					setCharactersInformation(textAreaMap.get("playertextAreaAlive"), playersCharacters);
					setCharactersInformation(textAreaMap.get("playertextAreaDead"), playersDeadCharacters);
					playerLabelList.pop();
				}
			}

			if (aiBaseLocation <= playerLocation + 100 + playerweaponRange) {
				aiBaseHealth -= playerweaponDamage;
				aiBase.setText("<html>BASE<br>"+aiBaseHealth+"</html>");
				if (aiBaseHealth < 0) {
					gameEnd("YOU WON!!");
				}
			}
			
			if (playerBaseLocation >= aiLocation - 100 - aiweaponRange) {
				playerBaseHealth -= aiweaponDamage;
				playerBase.setText("<html>BASE<br>"+playerBaseHealth+"</html>");
				if (playerBaseHealth < 0) {
					gameEnd("YOU LOST!!");
				}
			}
		}
	}

	public void gameEnd(String state) {
		gameRunning = false;
		timer.stop();
		mainPanel.removeAll();
		refresher();
		finalTextArea.setText(state);
		finalTextArea.setFont(new Font("", 50, 50));
		mainPanel.add(finalTextArea);
		refresher();
	}

	private void labelMovement() {
		if (playerLabelList.size() > 0) {
			for (int i = 0; i < playerLabelList.size(); i++) {
				if (playerLabelList.get(i).getLocation().x + 100 < aiBase.getLocation().x && ( aiLabelList.size() == 0 || playerLabelList.get(i).getLocation().x + 100 < aiLabelList.get(0).getLocation().x)) {
					playerLabelList.get(i).setLocation(playerLabelList.get(i).getLocation().x + 5, playerLabelList.get(i).getLocation().y);
				}
			}
		}
		if (aiLabelList.size() > 0) {
			for (int i = 0; i < aiLabelList.size(); i++) {
				if (aiLabelList.get(i).getLocation().x > playerBase.getLocation().x +50 && (playerLabelList.size() == 0 || (aiLabelList.get(i).getLocation().x-100 > playerLabelList.get(0).getLocation().x))) {
					aiLabelList.get(i).setLocation(aiLabelList.get(i).getLocation().x - 5, aiLabelList.get(i).getLocation().y);
				}
			}
		}
	}

	private void aiManager() {
		Random rand = new Random();
		int rN = rand.nextInt((3 - 0) + 1) + 0;
		if (artifIntelCharacters.size()<10) {
			CharacterAbstract charac = characterFatory.createCharacter(ageAI, rN, artifIntel);
			artifIntelCharacters.add(charac);
			charac.setMoveDirection(-1);
			addLabel(aiLabelList, charac, gamePanel.getWidth()-100);
			setCharactersInformation(textAreaMap.get("aitextAreaAlive"), artifIntelCharacters);
		}
		if (ageAI < 4 && aiExperience >= 1000) {
			ageAI++;
			aiExperience -= 1000;
		}
	}
}
