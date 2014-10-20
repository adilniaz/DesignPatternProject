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
	private JButton buttonStart;
	private JButton buttonExit;

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
	
	public MainGUI() {

		measurements = new Measurements();

		this.buttonWidth = measurements.buttonWidth;
		this.buttonHeight = measurements.buttonHeight;
		this.frameWidth = measurements.frameWidth;
		this.frameHeight = measurements.frameHeight;
		this.framePositionX = measurements.framePositionX;
		this.framePositionY = measurements.framePositionY;
		
	}
	
	public void initComponentsS() {
		
		setLocation(framePositionX, framePositionY);
		setSize(frameWidth, frameHeight);
		
		mainFramePanel = new JPanel();

		int centerWidth = frameWidth/2-buttonWidth/2;
		int centerHeight = frameHeight/2-buttonHeight/2;
		
		buttonStart = new JButton("START");
		buttonStart.addActionListener(this);
		buttonStart.setSize(buttonWidth, buttonHeight);
		buttonStart.setBounds(centerWidth, centerHeight, buttonWidth, buttonHeight);
		
		buttonExit = new JButton("EXIT");
		buttonExit.addActionListener(this);
		buttonExit.setSize(buttonWidth, buttonHeight);
		buttonExit.setBounds(centerWidth, centerHeight+buttonHeight*5,
				buttonWidth, buttonHeight);
		
		add(mainFramePanel);
		
		mainFramePanel.setLayout(null);
		mainFramePanel.add(buttonStart);
		mainFramePanel.add(buttonExit);
		
		mainFramePanel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		setUndecorated(true);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==buttonStart){
			GUIPlatformMenu guiPM = new GUIPlatformMenu();
			guiPM.initComponents3();
			dispose();
		}
		if(e.getSource()==buttonExit){
			dispose();
		}
	}
	
}
