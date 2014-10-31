package fireemblem.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Popup;
import javax.swing.PopupFactory;

import fireemblem.controlleur.Combat;
import fireemblem.media.personnage.ImagePersonnage;

public class VueSwing_combat {
    
    private final Combat combat;
    private Popup fenetreSimulationCombat;
    private final PopupFactory popupFactory;
    private final Fenetre fenetre;
    private Popup fenetreCombat;
    private JLabel panelPvTextPerso1;
    private PanelImage panelPerso1;
    private JLabel panelPvTextPerso2;
    private PanelImage panelPerso2;
    private PanelPv panelPvPerso1;
    private PanelPv panelPvPerso2;
    
    public VueSwing_combat (Combat combat, Fenetre fenetre) {
        this.combat = combat;
        this.fenetre = fenetre;
        this.popupFactory = PopupFactory.getSharedInstance();
        this.combat.ajouterEcouteur(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (evt.getPropertyName().equals(VueSwing_combat.this.combat.SIMULER_COMBAT)) {
                    simulerCombat((int[])evt.getOldValue(), (int[])evt.getNewValue());
                } else if (evt.getPropertyName().equals(VueSwing_combat.this.combat.COMBAT)) {
                    combat((int[])evt.getOldValue(), (int[])evt.getNewValue());
                } else if (evt.getPropertyName().equals(VueSwing_combat.this.combat.ANNIMATION_ATTAQUE_PERSO1)) {
                    annimationAttaquePerso1();
                } else if (evt.getPropertyName().equals(VueSwing_combat.this.combat.ANNIMATION_CRITIQUE_PERSO1)) {
                    annimationCritiquePerso1();
                } else if (evt.getPropertyName().equals(VueSwing_combat.this.combat.ANNIMATION_DISTANCE_PERSO1)) {
                    annimationDistancePerso1();
                } else if (evt.getPropertyName().equals(VueSwing_combat.this.combat.ANNIMATION_DISTANCE_CRITIQUE_PERSO1)) {
                    annimationDistanceCritiquePerso1();
                } else if (evt.getPropertyName().equals(VueSwing_combat.this.combat.ANNIMATION_ESQUIVE_PERSO1)) {
                    annimationEsquivePerso1();
                } else if (evt.getPropertyName().equals(VueSwing_combat.this.combat.ANNIMATION_ATTAQUE_PERSO2)) {
                    annimationAttaquePerso2();
                } else if (evt.getPropertyName().equals(VueSwing_combat.this.combat.ANNIMATION_CRITIQUE_PERSO2)) {
                    annimationCritiquePerso2();
                } else if (evt.getPropertyName().equals(VueSwing_combat.this.combat.ANNIMATION_DISTANCE_PERSO2)) {
                    annimationDistancePerso2();
                } else if (evt.getPropertyName().equals(VueSwing_combat.this.combat.ANNIMATION_DISTANCE_CRITIQUE_PERSO2)) {
                    annimationDistanceCritiquePerso2();
                } else if (evt.getPropertyName().equals(VueSwing_combat.this.combat.ANNIMATION_ESQUIVE_PERSO2)) {
                    annimationEsquivePerso2();
                } else if (evt.getPropertyName().equals(VueSwing_combat.this.combat.MODIFY_PV_PERSO1)) {
                    modifyPvPerso1((int)evt.getOldValue());
                } else if (evt.getPropertyName().equals(VueSwing_combat.this.combat.MODIFY_PV_PERSO2)) {
                    modifyPvPerso2((int)evt.getOldValue());
                }
            }
        });
    }
    
    private void simulerCombat (int statPerso1[], int statPerso2[]) {
        Box boxCombat = Box.createVerticalBox();
        
        Color myBlueColor = new Color(0, 0, 200, 170);
        Color myRedColor = new Color(200, 0, 0, 170);
        
        Box boxPerso1 = Box.createHorizontalBox();
        JPanel panelArmePerso1 = new JPanel();
        panelArmePerso1.add(new JLabel(this.combat.getPerso1().getArme().getName()));
        panelArmePerso1.setBackground(myBlueColor);
        JPanel panelNomPerso1 = new JPanel();
        panelNomPerso1.add(new JLabel(this.combat.getPerso1().getName()));
        panelNomPerso1.setBackground(myBlueColor);
        boxPerso1.add(panelArmePerso1);
        boxPerso1.add(panelNomPerso1);
        
        JPanel panelCombat = new JPanel(new GridLayout(4, 3));
        JPanel panelPvPerso2 = new JPanel();
        panelPvPerso2.add(new JLabel(Integer.toString(statPerso2[0])));
        panelPvPerso2.setBackground(myRedColor);
        panelCombat.add(panelPvPerso2);
        panelCombat.add(new JLabel("PV"));
        JPanel panelPvPerso1 = new JPanel();
        panelPvPerso1.add(new JLabel(Integer.toString(statPerso1[0])));
        panelPvPerso1.setBackground(myBlueColor);
        panelCombat.add(panelPvPerso1);
        JPanel panelFrcPerso2 = new JPanel();
        panelFrcPerso2.add(new JLabel(statPerso2[1] + " (X" + statPerso2[2] + ")"));
        panelFrcPerso2.setBackground(myRedColor);
        panelCombat.add(panelFrcPerso2);
        panelCombat.add(new JLabel("Frc"));
        JPanel panelFrcPerso1 = new JPanel();
        panelFrcPerso1.add(new JLabel(statPerso1[1] + " (X" + statPerso1[2] + ")"));
        panelFrcPerso1.setBackground(myBlueColor);
        panelCombat.add(panelFrcPerso1);
        JPanel panelPrcPerso2 = new JPanel();
        panelPrcPerso2.add(new JLabel(Integer.toString(statPerso2[3])));
        panelPrcPerso2.setBackground(myRedColor);
        panelCombat.add(panelPrcPerso2);
        panelCombat.add(new JLabel("Prc"));
        JPanel panelPrcPerso1 = new JPanel();
        panelPrcPerso1.add(new JLabel(Integer.toString(statPerso1[3])));
        panelPrcPerso1.setBackground(myBlueColor);
        panelCombat.add(panelPrcPerso1);
        JPanel panelCrtPerso2 = new JPanel();
        panelCrtPerso2.add(new JLabel(Integer.toString(statPerso2[4])));
        panelCrtPerso2.setBackground(myRedColor);
        panelCombat.add(panelCrtPerso2);
        panelCombat.add(new JLabel("Crt"));
        JPanel panelCrtPerso1 = new JPanel();
        panelCrtPerso1.add(new JLabel(Integer.toString(statPerso1[4])));
        panelCrtPerso1.setBackground(myBlueColor);
        panelCombat.add(panelCrtPerso1);
        
        Box boxPerso2 = Box.createHorizontalBox();
        JPanel panelNomPerso2 = new JPanel();
        panelNomPerso2.add(new JLabel(this.combat.getPerso2().getName()));
        panelNomPerso2.setBackground(myRedColor);
        JPanel panelArmePerso2 = new JPanel();
        panelArmePerso2.add(new JLabel(this.combat.getPerso2().getArme().getName()));
        panelArmePerso2.setBackground(myRedColor);
        boxPerso2.add(panelNomPerso2);
        boxPerso2.add(panelArmePerso2);
        
        boxCombat.add(boxPerso1);
        boxCombat.add(panelCombat);
        boxCombat.add(boxPerso2);
        boxCombat.add(panelArmePerso2);
        
        this.fenetreSimulationCombat = this.popupFactory.getPopup(this.fenetre, boxCombat, 1000, 300);
        this.fenetreSimulationCombat.show();
        
        /*this.fenetreSimulationCombat.setContentPane(boxCombat);
        this.fenetreSimulationCombat.setSize(150, 200);
        this.fenetreSimulationCombat.setLocation(1000, 300);
        this.fenetreSimulationCombat.setVisible(true);*/
        
    }
    
    private void combat (int statPerso1[], int statPerso2[]) {
        JPanel panel = new JPanel(new BorderLayout());
        
        JPanel panelTop = new JPanel();
        JPanel panelCentre = new JPanel();
        JPanel panelBottom = new JPanel();
        
        JPanel panelNomPerso2 = new JPanel();
        JLabel labelNomPerso2 = new JLabel(this.combat.getPerso2().getName());
        labelNomPerso2.setLocation(50, 50);
        panelNomPerso2.add(labelNomPerso2);
        panelNomPerso2.setPreferredSize(new Dimension(300, 30));
        
        JPanel panelNomPerso1 = new JPanel();
        JLabel labelNomPerso1 = new JLabel(this.combat.getPerso1().getName());
        labelNomPerso1.setLocation(500, 50);
        panelNomPerso1.add(labelNomPerso1);
        panelNomPerso1.setPreferredSize(new Dimension(300, 30));
        
        panelTop.add(panelNomPerso2);
        panelTop.add(panelNomPerso1);
        
        JPanel panelCombat = new JPanel();
        panelPerso1 = new PanelImage(ImagePersonnage.getImageCombatFromPersonnage(this.combat.getPerso1()), 150, 150);
        panelPerso2 = new PanelImage(ImagePersonnage.getImageCombatFromPersonnage(this.combat.getPerso2()), 150, 150);
        
        panelCombat.add(panelPerso2);
        panelCombat.add(panelPerso1);
        
        panelCentre.add(panelCombat);
        panelCentre.setPreferredSize(new Dimension(600, 300));
        
        JPanel panelInfoCombat = new JPanel(new GridLayout(1, 2));
        
        Box panelInfoCombatPerso2 = Box.createVerticalBox();
        JPanel panelInfoHautPerso2 = new JPanel();
        JPanel panelStatPerso2 = new JPanel(new GridLayout(3, 2));
        panelStatPerso2.add(new JLabel("PRC   "));
        panelStatPerso2.add(new JLabel(Integer.toString(statPerso2[1])));
        panelStatPerso2.add(new JLabel("DMG   "));
        panelStatPerso2.add(new JLabel(Integer.toString(statPerso2[0])));
        panelStatPerso2.add(new JLabel("CRT   "));
        panelStatPerso2.add(new JLabel(Integer.toString(statPerso2[2])));
        panelInfoHautPerso2.add(panelStatPerso2);
        
        JPanel panelArmePerso2 = new JPanel();
        panelArmePerso2.add(new JLabel(this.combat.getPerso2().getArme().getName()));
        panelInfoHautPerso2.add(panelArmePerso2);
        
        Box panelPVPerso2 = Box.createHorizontalBox();
        panelPVPerso2.setPreferredSize(new Dimension(300, 50));
        this.panelPvTextPerso2 = new JLabel(Integer.toString(this.combat.getPerso2().getPv()));
        panelPVPerso2.add(this.panelPvTextPerso2);
        this.panelPvPerso2 = new PanelPv(this.combat.getPerso2().getPv(), this.combat.getPerso2().getPvMax(), 240, 50);
        panelPVPerso2.add(this.panelPvPerso2);
        
        panelInfoCombatPerso2.add(panelInfoHautPerso2);
        panelInfoCombatPerso2.add(panelPVPerso2);
        
        Box panelInfoCombatPerso1 = Box.createVerticalBox();
        JPanel panelInfoHautPerso1 = new JPanel();
        JPanel panelStatPerso1 = new JPanel(new GridLayout(3, 2));
        panelStatPerso1.add(new JLabel("PRC   "));
        panelStatPerso1.add(new JLabel(Integer.toString(statPerso1[1])));
        panelStatPerso1.add(new JLabel("DMG   "));
        panelStatPerso1.add(new JLabel(Integer.toString(statPerso1[0])));
        panelStatPerso1.add(new JLabel("CRT   "));
        panelStatPerso1.add(new JLabel(Integer.toString(statPerso1[2])));
        panelInfoHautPerso1.add(panelStatPerso1);
        
        JPanel panelArmePerso1 = new JPanel();
        panelArmePerso1.add(new JLabel(this.combat.getPerso1().getArme().getName()));
        panelInfoHautPerso1.add(panelArmePerso1);
        
        Box panelPVPerso1 = Box.createHorizontalBox();
        panelPVPerso1.setPreferredSize(new Dimension(300, 50));
        this.panelPvTextPerso1 = new JLabel(Integer.toString(this.combat.getPerso1().getPv()));
        panelPVPerso1.add(this.panelPvTextPerso1);
        this.panelPvPerso1 = new PanelPv(this.combat.getPerso1().getPv(), this.combat.getPerso1().getPvMax(), 240, 50);
        panelPVPerso1.add(this.panelPvPerso1);
        
        panelInfoCombatPerso1.add(panelInfoHautPerso1);
        panelInfoCombatPerso1.add(panelPVPerso1);
        
        panelInfoCombat.add(panelInfoCombatPerso2);
        panelInfoCombat.add(panelInfoCombatPerso1);
        panelNomPerso2.setSize(600, 100);
        
        panelBottom.add(panelInfoCombat);
        
        panel.add(panelTop, BorderLayout.NORTH);
        panel.add(panelCentre, BorderLayout.CENTER);
        panel.add(panelBottom, BorderLayout.SOUTH);
        
        this.fenetreSimulationCombat = this.popupFactory.getPopup(this.fenetre, panel, 400, 200);
        this.fenetreSimulationCombat.show();
        
    }
    
    private void modifyPvPerso1 (int pv) {
        this.panelPvTextPerso1.setText(Integer.toString(this.combat.getPerso1().getPv()));
        this.panelPvPerso1.enlevePv(pv);
    }
    
    private void modifyPvPerso2 (int pv) {
        this.panelPvTextPerso2.setText(Integer.toString(this.combat.getPerso2().getPv()));
        this.panelPvPerso2.enlevePv(pv);
    }
    
    private void annimationAttaquePerso1 () {
        threadAnnimationAttaquePerso1 monthread = new threadAnnimationAttaquePerso1();
        monthread.start();
    }
    
    private void annimationCritiquePerso1 () {
        threadAnnimationCritiquePerso1 monthread = new threadAnnimationCritiquePerso1();
        monthread.start();
    }
    
    private void annimationDistancePerso1 () {
        threadAnnimationDistancePerso1 monthread = new threadAnnimationDistancePerso1();
        monthread.start();
    }
    
    private void annimationDistanceCritiquePerso1 () {
        threadAnnimationDistanceCritiquePerso1 monthread = new threadAnnimationDistanceCritiquePerso1();
        monthread.start();
    }
    
    private void annimationEsquivePerso1 () {
        threadAnnimationEsquivePerso1 monthread = new threadAnnimationEsquivePerso1();
        monthread.start();
    }
    
    private void annimationAttaquePerso2 () {
        threadAnnimationAttaquePerso2 monthread = new threadAnnimationAttaquePerso2();
        monthread.start();
    }
    
    private void annimationCritiquePerso2 () {
        threadAnnimationCritiquePerso2 monthread = new threadAnnimationCritiquePerso2();
        monthread.start();
    }
    
    private void annimationDistancePerso2 () {
        threadAnnimationDistancePerso2 monthread = new threadAnnimationDistancePerso2();
        monthread.start();
    }
    
    private void annimationDistanceCritiquePerso2 () {
        threadAnnimationDistanceCritiquePerso2 monthread = new threadAnnimationDistanceCritiquePerso2();
        monthread.start();
    }
    
    private void annimationEsquivePerso2 () {
        threadAnnimationEsquivePerso2 monthread = new threadAnnimationEsquivePerso2();
        monthread.start();
    }
    
    public abstract class annimationThread extends Thread {
        public synchronized void attendre (int time) {
            try {
                this.wait(time);
            } catch (InterruptedException ex) {
                Logger.getLogger(VueSwing_combat.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public class threadAnnimationAttaquePerso1 extends annimationThread {
        @Override
        public void run() {
          Map<Integer, ImageManager> images = ImagePersonnage.getImageCombatAttaqueFromPersonnage(combat.getPerso1());
            for (Map.Entry<Integer, ImageManager> e : images.entrySet()){
                panelPerso1.setBufferedImage(e.getValue().getImage(), 150, 150);
                this.attendre(e.getValue().getDisplayTime());
            }
        }
    }
    
    public class threadAnnimationCritiquePerso1 extends annimationThread {
        @Override
        public void run() {
          Map<Integer, ImageManager> images = ImagePersonnage.getImageCombatAttaqueCritiqueFromPersonnage(combat.getPerso1());
            for (Map.Entry<Integer, ImageManager> e : images.entrySet()){
                panelPerso1.setBufferedImage(e.getValue().getImage(), 150, 150);
                this.attendre(e.getValue().getDisplayTime());
            }
        }
    }
    
    public class threadAnnimationDistancePerso1 extends annimationThread {
        @Override
        public void run() {
          Map<Integer, ImageManager> images = ImagePersonnage.getImageCombatDistantFromPersonnage(combat.getPerso1());
            for (Map.Entry<Integer, ImageManager> e : images.entrySet()){
                panelPerso1.setBufferedImage(e.getValue().getImage(), 150, 150);
                this.attendre(e.getValue().getDisplayTime());
            }
        }
    }
    
    public class threadAnnimationDistanceCritiquePerso1 extends annimationThread {
        @Override
        public void run() {
          Map<Integer, ImageManager> images = ImagePersonnage.getImageCombatDistantCritiqueFromPersonnage(combat.getPerso1());
            for (Map.Entry<Integer, ImageManager> e : images.entrySet()){
                panelPerso1.setBufferedImage(e.getValue().getImage(), 150, 150);
                this.attendre(e.getValue().getDisplayTime());
            }
        }
    }
    
    public class threadAnnimationEsquivePerso1 extends annimationThread {
        @Override
        public void run() {
          Map<Integer, ImageManager> images = ImagePersonnage.getImageCombatEsquiveFromPersonnage(combat.getPerso1());
            for (Map.Entry<Integer, ImageManager> e : images.entrySet()){
                panelPerso1.setBufferedImage(e.getValue().getImage(), 150, 150);
                this.attendre(e.getValue().getDisplayTime());
            }
        }
    }
    
    public class threadAnnimationAttaquePerso2 extends annimationThread {
        @Override
        public void run() {
          Map<Integer, ImageManager> images = ImagePersonnage.getImageCombatAttaqueFromPersonnage(combat.getPerso2());
            for (Map.Entry<Integer, ImageManager> e : images.entrySet()){
                panelPerso2.setBufferedImage(e.getValue().getImage(), 150, 150);
                this.attendre(e.getValue().getDisplayTime());
            }
        }
    }
    
    public class threadAnnimationCritiquePerso2 extends annimationThread {
        @Override
        public void run() {
          Map<Integer, ImageManager> images = ImagePersonnage.getImageCombatAttaqueCritiqueFromPersonnage(combat.getPerso2());
            for (Map.Entry<Integer, ImageManager> e : images.entrySet()){
                panelPerso2.setBufferedImage(e.getValue().getImage(), 150, 150);
                this.attendre(e.getValue().getDisplayTime());
            }
        }
    }
    
    public class threadAnnimationDistancePerso2 extends annimationThread {
        @Override
        public void run() {
          Map<Integer, ImageManager> images = ImagePersonnage.getImageCombatDistantFromPersonnage(combat.getPerso2());
            for (Map.Entry<Integer, ImageManager> e : images.entrySet()){
                panelPerso2.setBufferedImage(e.getValue().getImage(), 150, 150);
                this.attendre(e.getValue().getDisplayTime());
            }
        }
    }
    
    public class threadAnnimationDistanceCritiquePerso2 extends annimationThread {
        @Override
        public void run() {
          Map<Integer, ImageManager> images = ImagePersonnage.getImageCombatDistantCritiqueFromPersonnage(combat.getPerso2());
            for (Map.Entry<Integer, ImageManager> e : images.entrySet()){
                panelPerso2.setBufferedImage(e.getValue().getImage(), 150, 150);
                this.attendre(e.getValue().getDisplayTime());
            }
        }
    }
    
    public class threadAnnimationEsquivePerso2 extends annimationThread {
        @Override
        public void run() {
          Map<Integer, ImageManager> images = ImagePersonnage.getImageCombatEsquiveFromPersonnage(combat.getPerso2());
            for (Map.Entry<Integer, ImageManager> e : images.entrySet()){
                panelPerso2.setBufferedImage(e.getValue().getImage(), 150, 150);
                this.attendre(e.getValue().getDisplayTime());
            }
        }
    }
    
}
