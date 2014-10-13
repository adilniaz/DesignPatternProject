package main_package;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainGUI extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3541569152389829042L;
	private JPanel mainFramePanel;
	private JButton buttonOne;
	private JButton buttonTwo;
	
	public void initComponentsS() {
		
		setLocation(200, 100);
		setSize(600, 600);
		
		mainFramePanel = new JPanel();

		int buttonsWidth = 100;
		int buttonsHeight = 40;
		int centerWidth = getWidth()/2-buttonsWidth/2;
		int centerHeight = getHeight()/2-buttonsHeight/2;
		
		
		buttonOne = new JButton("START");
		buttonOne.addActionListener(this);
		buttonOne.setSize(buttonsWidth, buttonsHeight);
		buttonOne.setBounds(centerWidth, centerHeight, buttonsWidth, buttonsHeight);
		
		buttonTwo = new JButton("EXIT");
		buttonTwo.addActionListener(this);
		buttonTwo.setSize(buttonsWidth, buttonsHeight);
		buttonTwo.setBounds(centerWidth, getHeight()-buttonsHeight-10,
				buttonsWidth, buttonsHeight);
		
		add(mainFramePanel);
		
		mainFramePanel.setLayout(null);
		mainFramePanel.add(buttonOne);
		mainFramePanel.add(buttonTwo);
		
		mainFramePanel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		setUndecorated(true);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource()==buttonOne){
			GUIPlatformMenu guiPM = new GUIPlatformMenu();
			guiPM.initComponents3();
			dispose();
		}
		if(e.getSource()==buttonTwo){
			dispose();
		}
	}
	
}
