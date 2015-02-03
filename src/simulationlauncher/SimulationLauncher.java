package simulationlauncher;

import implementations.views.Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import abstracts_interfaces.SimulationAbstract;

public class SimulationLauncher {
	
	private Window window;
	
	public SimulationLauncher () {
		this.window = new Window("Game simulation");
	}
	
	public void initComponents() {
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		
		int index = 0;
		for (SimulationType simulationType : SimulationType.values()) {
			JButton button = new JButton(simulationType.name());
			button.setBounds(this.window.getWidth()/2-100, 75 * index + 20, 200, 75);
			button.addActionListener(new StartButton(simulationType, this.window));
            panel.add(button);
            index++;
		}
		this.window.ajouterPanel(panel);
	}
    public class StartButton implements ActionListener {
        
        private final SimulationType simulationType;
        private final Window window;
        
        public StartButton (SimulationType simulationType, Window window) {
            this.simulationType = simulationType;
            this.window = window;
        }
        
        @Override
        public void actionPerformed (ActionEvent event) {
            SimulationFactory factory = new SimulationFactory();
            new RunSimulation(factory.createSimulation(simulationType), window).start();
        }
    }
	
	public class RunSimulation extends Thread {
		
		private SimulationAbstract simulationAbstract;
		Window window;
		
		public RunSimulation(SimulationAbstract simulationAbstract, Window window) {
			this.simulationAbstract = simulationAbstract;
			this.window = window;
		}
		
		@Override
		public void run () {
			this.simulationAbstract.run(window);
			initComponents();
		}
		
	}

}
