package survivalPotager.vue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import survivalPotager.modele.jeu.Jeu;

public class StartScreen extends JFrame {

	private static final long serialVersionUID = 5344028866343351024L;
	private Jeu jeu;
    private JButton imageDefond;

    public StartScreen() {
        this.jeu = new Jeu();
        this.imageDefond = new JButton();

        this.imageDefond.setIcon(new ImageIcon(this.getClass().getResource("accueil.jpg")));

        this.imageDefond.setBorderPainted(false);
        this.imageDefond.addActionListener(new EcouteurBoutonJouer());
        this.setTitle("Accueil");

        this.setSize(600, 400);
       this.setLocationRelativeTo(null);
       this.add(this.imageDefond);
       this.setVisible(true);

    }

    private class EcouteurBoutonJouer implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            new SurvivalIHM(jeu);
            dispose();
        }
    }
}
