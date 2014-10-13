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
	private JButton buttonExit;
	
	
	public void initComponents(){
		
		setLocation(200, 100);
		setSize(600, 600);
		
		panel = new JPanel();
		panel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		int buttonsWidth = 200;
		int buttonsHeight = 40;
		int centerWidth = getWidth()/2-buttonsWidth/2;
		int centerHeight = getHeight()/2-buttonsHeight/2;
		
		buttonMiddleAge = new JButton("MIDDLE AGE");
		buttonMiddleAge.addActionListener(this);
		buttonMiddleAge.setSize(buttonsWidth, buttonsHeight);
		buttonMiddleAge.setBounds(centerWidth, centerHeight,
				buttonsWidth, buttonsHeight);

		buttonGulfWar = new JButton("GULF WAR");
		buttonGulfWar.addActionListener(this);
		buttonGulfWar.setSize(buttonsWidth, buttonsHeight);
		buttonGulfWar.setBounds(centerWidth, centerHeight+buttonsHeight,
				buttonsWidth, buttonsHeight);

		buttonExit = new JButton("EXIT");
		buttonExit.addActionListener(this);
		buttonExit.setSize(buttonsWidth, buttonsHeight);
		buttonExit.setBounds(centerWidth, centerHeight+buttonsHeight*4,
				buttonsWidth, buttonsHeight);
		
		add(panel);
		
		panel.setLayout(null);
		panel.add(buttonMiddleAge);
		panel.add(buttonGulfWar);
		panel.add(buttonExit);
		
		setUndecorated(true);
		setVisible(true);
	}
	
	ArrayList<String> charList;
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==buttonExit){
			dispose();
		}
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
	}

}
