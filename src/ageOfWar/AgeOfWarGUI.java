package ageOfWar;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class AgeOfWarGUI implements ActionListener{
	
	private JFrame frame;
	private JPanel panel;
	private JPanel menuPanel;
	
	private int screenWidth;
	private int screenHeight;
	
	private int menuPanelComponentsUnitLength;
	
	private int numberOfCharacters;
	
	private ArrayList<JButton> charactersButtonsList;
	private ArrayList<JButton> charactersBonusButtonsList;

	private JButton newAge;
	private JButton specialAttack;
	
	private JTextArea textArea;
	
	private String xpAndGoldValues;
	private int goldValue, xpValue;
	
	public AgeOfWarGUI() {
		xpValue = 0;
		goldValue = 0;
		xpAndGoldValues = " GOLD : "+ goldValue
						+ "\n" 
						+  "    XP   : " + xpValue;
		menuPanelComponentsUnitLength = 80;
		
		Rectangle rectangle = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
		Dimension screenSize = new Dimension(rectangle.width, rectangle.height);
		screenWidth = (int) screenSize.getWidth();
		screenHeight = (int) screenSize.getHeight();
		
		numberOfCharacters = 4;
		
		initComponents();
	}

	private void initComponents() {
		frame = new JFrame();
		
		panel = new JPanel();
		menuPanel = new JPanel();

		charactersButtonsList = new ArrayList<>();
		for (int i = 0; i < numberOfCharacters; i++) {
			int num = i+1;
			String str = "C" + num;
			charactersButtonsList.add(new JButton(str));
		}
		
		charactersBonusButtonsList = new ArrayList<>();
		int num = 0, num2 = 0;
		for (int i = 0; i < numberOfCharacters*2; i++) {
			if (i%2==0) {
				num++;
				num2 = 1;
			}
			String str = "<html>C" + num + "<br/>B" + num2 +"</html>";
			charactersBonusButtonsList.add(new JButton(str));
			num2++;
		}

		newAge = new JButton("<html>NEW<br/>AGE</html>");
		
		specialAttack = new JButton("<html>SPECIAL<br/>ATTACK</html>");
		
		textArea = new JTextArea(xpAndGoldValues);
		
		componentProperties();
		
		frame.add(panel);
		
		panel.add(menuPanel);
		
		menuPanel.add(textArea);
		
		frame.setVisible(true);
	}

	private void componentProperties() {
		
		frame.setSize(screenWidth, screenHeight);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		/*
	    frame.setUndecorated(true);
	    frame.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
	    */

		panel.setLayout(null);
		panel.setBackground(Color.BLACK);
		panel.setSize(screenWidth-6, screenHeight-28);
		
		
		menuPanel.setLayout(null);
		menuPanel.setBackground(Color.GRAY);
		menuPanel.setBounds(0, 0, panel.getWidth(), menuPanelComponentsUnitLength);
		
		for (int i = 0; i < charactersButtonsList.size(); i++) {
			createButton(charactersButtonsList.get(i), i, 0, "");
		}
		
		int num2 = 0;
		for (int i = 0; i < 8; i++) {
			int num = menuPanelComponentsUnitLength/2;
			if (i%2==0) {
				num = 0;
				num2++;
			}
			createButton(charactersBonusButtonsList.get(i), numberOfCharacters+2+num2, num, "bonus");
		}

		createButton(newAge, numberOfCharacters+num2+6, 0, "");
		createButton(specialAttack, numberOfCharacters+num2+7, 0, "");
		
		int textAreaWidth = menuPanelComponentsUnitLength*2 + menuPanelComponentsUnitLength/2;
		int textAreaPositionX = panel.getWidth() - textAreaWidth;
		textArea.setBounds(textAreaPositionX, 0, textAreaWidth, menuPanelComponentsUnitLength);
		textArea.setEditable(false);
		textArea.setFont(new Font(null, 20, 20));
		textArea.setBackground(Color.WHITE);
	}

	public void createButton(JButton button, int X, int Y, String str) {
		int height=menuPanelComponentsUnitLength;
		if (!str.equals("")) {
			button.setFont(new Font(null, 10, 10));
			height = height/2;
		}
		button.setBounds(menuPanelComponentsUnitLength*X, Y, menuPanelComponentsUnitLength, height);
		button.setBackground(Color.WHITE);
		button.addActionListener(this);
		menuPanel.add(button);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
