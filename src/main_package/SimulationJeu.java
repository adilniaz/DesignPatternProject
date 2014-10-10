package main_package;

import implementations.factories.characters.CreateCharactersMiddleAgeFactory;
import implementations.factories.gameplatforms.GameEnvironment;
import implementations.factories.gameplatforms.platformtypes.MazeFactory;
import implementations.organisations.Organisation;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import abstracts_interfaces.CharacterAbstract;
import abstracts_interfaces.factories.characters.CreateCharactersFactoryAbstract;
import abstracts_interfaces.factories.gameplatforms.GamePlatformFactoryAbstract;

public class SimulationJeu extends JFrame implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8963259860157176989L;
	ArrayList<CharacterAbstract> listDesPersonnages;
	Organisation subj;
	
	private JPanel buttonsPanel, textAreaPanel;
	
	private JScrollPane scrollPane;
	
	private JTextArea textArea;
	
	
	private JButton buttonAfficher;
	private JButton buttonParler;
	private JButton buttonAttaquer;
	private JButton buttonModeGuerre;
	private JButton buttonModePaix;
	private JButton buttonModeNonDefini;
	private JButton buttonModeRepli;
	private JButton buttonClearText;

	public enum typeCharacters{
		UNDEFINED,
		TANK, SOLDIER, FIGHTERAIRCRAFT, SHIP,
		PRINCESS, KNIGHT, ARCHER, FOOTSOLDIER
	}
	
	public typeCharacters tCharacs;
	
	public enum typeGamePlatform{
		UNDEFINED,
		MAZE, ANTCOLONY, WORLDMAP
	}
	
	public typeGamePlatform tGameType;
	
	public SimulationJeu(){
		
		initComponents();
		
		listDesPersonnages = new ArrayList<CharacterAbstract>();
		
		CreateCharactersFactoryAbstract middleAgeFactoryAbstract = new CreateCharactersMiddleAgeFactory();
		
		CreationPersonnage(middleAgeFactoryAbstract);
		
		GamePlatformFactoryAbstract maze = new MazeFactory();
		createGamePlatform(maze);
		
		
		GameEnvironment gE = new GameEnvironment();
		
		gE.CreateGamePlatform2(typeGamePlatform.WORLDMAP);
		
	}
	
	public void createGamePlatform(GamePlatformFactoryAbstract factory){
		
	}
	
	public void CreationPersonnage(CreateCharactersFactoryAbstract factory){
		subj = new Organisation();
		CharacterAbstract chev = factory.createCharacter("Sir Gwain", subj, tCharacs.KNIGHT);
		CharacterAbstract arch = factory.createCharacter("Robin", subj, tCharacs.ARCHER);
		addCharactersToList(chev, arch);
	}
	
	public void addCharactersToList(CharacterAbstract...characs) {
		for (CharacterAbstract charac : characs) {
			listDesPersonnages.add(charac);
		}
	}
	
	private void initComponents() {
		setLocation(200, 100);
		setSize(600, 600);
		
		buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new GridLayout(7,1));
		
		textAreaPanel = new JPanel();
		
		buttonAfficher = new JButton("AFFICHER");
		buttonAfficher.addActionListener(this);
		
		buttonParler = new JButton("PARLER");
		buttonParler.addActionListener(this);
		
		buttonAttaquer = new JButton("ATTAQUER");
		buttonAttaquer.addActionListener(this);
		
		buttonModeGuerre = new JButton("MODE GUERRE");
		buttonModeGuerre.addActionListener(this);
		
		buttonModePaix = new JButton("MODE PAIX");
		buttonModePaix.addActionListener(this);
		
		buttonModeNonDefini = new JButton("MODE NON DEFINI");
		buttonModeNonDefini.addActionListener(this);

		buttonModeRepli = new JButton("MODE REPLI");
		buttonModeRepli.addActionListener(this);
		
		buttonClearText = new JButton("CLEAR TEXT");
		buttonClearText.setPreferredSize(new Dimension(200,51));
		buttonClearText.addActionListener(this);
		
		
		GridLayout myLayout = new GridLayout(1,2);
		setLayout(myLayout);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		
		scrollPane = new JScrollPane(textArea);
		scrollPane.setPreferredSize(new Dimension(280, 500));

		textAreaPanel.add(scrollPane, BorderLayout.CENTER);
		textAreaPanel.add(buttonClearText, BorderLayout.SOUTH);
		
		
		add(buttonsPanel);
		add(textAreaPanel);
		
		buttonsPanel.add(buttonAfficher);
		buttonsPanel.add(buttonParler);
		buttonsPanel.add(buttonAttaquer);
		buttonsPanel.add(buttonModeGuerre);
		buttonsPanel.add(buttonModePaix);
		buttonsPanel.add(buttonModeNonDefini);
		buttonsPanel.add(buttonModeRepli);
		
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==buttonAfficher){
			Displayer(Afficheur());
		}
		if(e.getSource()==buttonParler){
			Displayer(EmettreSontous());
		}
		if(e.getSource()==buttonAttaquer){
			Displayer(LancerCombat());
		}
		if(e.getSource()==buttonModeGuerre){
			Displayer(modeFonct("MODE_GUERRE"));
		}
		if(e.getSource()==buttonModePaix){
			Displayer(modeFonct("MODE_PAIX"));
		}
		if(e.getSource()==buttonModeNonDefini){
			Displayer(modeFonct("MODE_NON_DEFINI"));
		}
		if(e.getSource()==buttonModeRepli){
			Displayer(modeFonct("MODE_REPLI"));
		}
		if(e.getSource()==buttonClearText){
			textArea.setText("");
		}
	}
	
	public String modeFonct(String mFonct) {
		subj.setModeFonctionnement(mFonct);
		String text=textArea.getText() + "\n";
		for (int i = 0; i < listDesPersonnages.size(); i++) {
			text+=listDesPersonnages.get(i).getEtatObservé();
			text+="\n";
		}
		return text;
	}
	
	public void Displayer(String text){
		textArea.setText(text);
	}
	
	public String Afficheur(){
		String text=textArea.getText() + "\nNoms :\n";
		for (int i = 0; i < listDesPersonnages.size(); i++) {
			text+=listDesPersonnages.get(i).getNom();
			text+="\n";
		}
		return text;
	}
	
	public String EmettreSontous(){
		String text=textArea.getText() + "\nSons :\n";
		for (int i = 0; i < listDesPersonnages.size(); i++) {
			text+=listDesPersonnages.get(i).EmmetreUnSon();
			text+="\n";
		}
		return text;
	}
	
	public String LancerCombat(){
		String text=textArea.getText() + "\nCombattre :\n";
		for (int i = 0; i < listDesPersonnages.size(); i++) {
			text+=listDesPersonnages.get(i).Combattre();
			text+="\n";
		}
		return text;
	}
	
	public void ChangerComportement(){}
	
}
