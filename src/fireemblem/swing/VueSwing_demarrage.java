package fireemblem.swing;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import fireemblem.controlleur.Chapitre;
import fireemblem.controlleur.Demarrage;

public class VueSwing_demarrage {
    
    private final Demarrage demarrage;
    private Fenetre fenetre;
    
    public VueSwing_demarrage (Demarrage demarrage, Fenetre fenetre) {
        this.demarrage = demarrage;
        this.fenetre = fenetre;
        
        this.demarrage.ajouterEcouteur(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (evt.getPropertyName().equals("menu")) {
                    menu();
                } else if (evt.getPropertyName().equals("chapitres")) {
                    chapitres();
                }
            }
        });
    }
    
    public void menu() {
    	if (this.fenetre == null) {
    		this.fenetre = new Fenetre("fire emblem");
    	} else {
    		this.fenetre.setVisible(true);
    	}
        JPanel panel = new JPanel();
        JButton button = new JButton("chapitres");
        button.addActionListener(new boutonChapitres());
        JButton buttonLoad = new JButton("load");
        buttonLoad.addActionListener(new boutonLoad());
        panel.add(button);
        panel.add(buttonLoad);
        this.fenetre.ajouterPanel(panel);
    }
    
    private void chapitres () {
        JPanel panel = new JPanel(new GridLayout(this.demarrage.getChapitres().size(), 1));
        int choix = 0;
        for (Chapitre chapitre : this.demarrage.getChapitres()) {
            JButton button = new JButton(chapitre.getNom());
            button.addActionListener(new boutonChapitre(choix));
            panel.add(button);
            choix++;
        }
        this.fenetre.ajouterPanel(panel);
    }
    
    private class boutonChapitres implements ActionListener {
        @Override
        public void actionPerformed (ActionEvent event) {
            demarrage.chapitres();
        }
    }
    
    private class boutonLoad implements ActionListener {
        @Override
        public void actionPerformed (ActionEvent event) {
            demarrage.continuerChapitre();
            fenetre.dispose();
        }
    }
    
    private class boutonChapitre implements ActionListener {
        
        private final int choix;
        
        public boutonChapitre (int choix) {
            this.choix = choix;
        }
        
        @Override
        public void actionPerformed (ActionEvent event) {
            demarrage.chapitre(this.choix);
            fenetre.dispose();
        }
    }
    
}
