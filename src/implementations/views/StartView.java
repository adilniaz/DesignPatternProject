package implementations.views;

import implementations.controller.Chapter;
import implementations.controller.Start;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JButton;
import javax.swing.JPanel;


public class StartView {
    
    private final Start start;
    private Window window;
    
    public StartView (Start start, Window window) {
        this.start = start;
        this.window = window;
        
        this.start.addListener(new PropertyChangeListener() {
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
    	if (this.window == null) {
    		this.window = new Window("fire emblem");
    	} else {
    		this.window.setVisible(true);
    	}
        JPanel panel = new JPanel();
        JButton button = new JButton("chapitres");
        button.addActionListener(new boutonChapitres());
        JButton buttonLoad = new JButton("load");
        buttonLoad.addActionListener(new boutonLoad());
        panel.add(button);
        panel.add(buttonLoad);
        this.window.ajouterPanel(panel);
    }
    
    private void chapitres () {
        JPanel panel = new JPanel(new GridLayout(this.start.getChapitres().size(), 1));
        int choix = 0;
        for (Chapter chapitre : this.start.getChapitres()) {
            JButton button = new JButton(chapitre.getNom());
            button.addActionListener(new boutonChapitre(choix));
            panel.add(button);
            choix++;
        }
        this.window.ajouterPanel(panel);
    }
    
    private class boutonChapitres implements ActionListener {
        @Override
        public void actionPerformed (ActionEvent event) {
            start.chapitres();
        }
    }
    
    private class boutonLoad implements ActionListener {
        @Override
        public void actionPerformed (ActionEvent event) {
            start.continuerChapitre();
            window.dispose();
        }
    }
    
    private class boutonChapitre implements ActionListener {
        
        private final int choix;
        
        public boutonChapitre (int choix) {
            this.choix = choix;
        }
        
        @Override
        public void actionPerformed (ActionEvent event) {
            start.chapitre(this.choix);
            window.dispose();
        }
    }
    
}
