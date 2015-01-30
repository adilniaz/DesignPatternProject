package simulationlauncher;

import implementations.views.Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import abstracts_interfaces.SimulationAbstract;

public class SimulationLauncher {
	
	public void initComponents() {
		Window window = new Window("Game simulation");
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		
		int index = 0;
		SimulationLauncher simulationLauncher = new SimulationLauncher();
		for (SimulationType simulationType : SimulationType.values()) {
			JButton button = new JButton(simulationType.name());
			button.setBounds(window.getWidth()/2-100, 75 * index + 20, 200, 75);
			button.addActionListener(simulationLauncher.new StartButton(simulationType, window));
            panel.add(button);
            index++;
		}
		window.ajouterPanel(panel);
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
		}
		
	}

}
