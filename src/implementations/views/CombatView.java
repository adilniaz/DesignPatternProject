package implementations.views;

import implementations.character.Character;
import implementations.combat.FightBehaviour;
import implementations.controller.Combat;
import implementations.media.character.CharacterImage;
import implementations.object.WeaponType;

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


public class CombatView {
    
    private final Combat combat;
    private Popup fenetreSimulationCombat;
    private final PopupFactory popupFactory;
    private final Window fenetre;
    private Popup fenetreCombat;
    private JLabel panelPvTextPerso1;
    private PanelImage panelPerso1;
    private JLabel panelPvTextPerso2;
    private PanelImage panelPerso2;
    private PanelPv panelPvPerso1;
    private PanelPv panelPvPerso2;
    
    public CombatView (Combat combat, Window fenetre) {
        this.combat = combat;
        this.fenetre = fenetre;
        this.popupFactory = PopupFactory.getSharedInstance();
        this.combat.addListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (evt.getPropertyName().equals(CombatView.this.combat.SIMULER_COMBAT)) {
                    simulerCombat((int[])evt.getOldValue(), (int[])evt.getNewValue());
                } else if (evt.getPropertyName().equals(CombatView.this.combat.COMBAT)) {
                    combat((int[])evt.getOldValue(), (int[])evt.getNewValue());
                } else if (evt.getPropertyName().equals(CombatView.this.combat.ANNIMATION_ATTAQUE_PERSO)) {
                    annimationAttaquePerso((Character)evt.getOldValue());
                } else if (evt.getPropertyName().equals(CombatView.this.combat.ANNIMATION_CRITIQUE_PERSO)) {
                    annimationCritiquePerso((Character)evt.getOldValue());
                } else if (evt.getPropertyName().equals(CombatView.this.combat.ANNIMATION_DISTANCE_PERSO)) {
                    annimationDistancePerso((Character)evt.getOldValue());
                } else if (evt.getPropertyName().equals(CombatView.this.combat.ANNIMATION_DISTANCE_CRITIQUE_PERSO)) {
                    annimationDistanceCritiquePerso((Character)evt.getOldValue());
                } else if (evt.getPropertyName().equals(CombatView.this.combat.ANNIMATION_ESQUIVE_PERSO)) {
                    annimationEsquivePerso((Character)evt.getOldValue());
                } else if (evt.getPropertyName().equals(CombatView.this.combat.FIN_COMBAT)) {
                    finCombat();
                } else if (evt.getPropertyName().equals(CombatView.this.combat.FIN_SIMULER_COMBAT)) {
                	finSimulationCombat();
                } else if (evt.getPropertyName().equals(CombatView.this.combat.MODIFY_PV_PERSO1)) {
                    modifyPvPerso1((int)evt.getOldValue());
                } else if (evt.getPropertyName().equals(CombatView.this.combat.MODIFY_PV_PERSO2)) {
                    modifyPvPerso2((int)evt.getOldValue());
                } else if (evt.getPropertyName().equals(CombatView.this.combat.MODIFY_EXP_PERSO)) {
                    modifyExpPerso((int)evt.getOldValue(), (Character)evt.getNewValue());
                } else if (evt.getPropertyName().equals(CombatView.this.combat.MODIFY_NIV_PERSO)) {
                	modifyNivPerso((Character)evt.getOldValue(), (Character)evt.getNewValue());
                } else if (evt.getPropertyName().equals(CombatView.this.combat.MODIFY_CLASS_PERSO)) {
                	modifyClassPerso((Character)evt.getOldValue(), (Character)evt.getNewValue());
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
        
    }
    
    private void finSimulationCombat () {
    	this.fenetreSimulationCombat.hide();
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
        panelPerso1 = new PanelImage(CharacterImage.getImageCombatFromPersonnage(this.combat.getPerso1()), 150, 150);
        panelPerso2 = new PanelImage(CharacterImage.getImageCombatFromPersonnage(this.combat.getPerso2()), 150, 150);
        
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
        
        this.fenetreCombat = this.popupFactory.getPopup(this.fenetre, panel, 400, 200);
        this.fenetreCombat.show();
        
    }
    
    private void finCombat () {
    	this.fenetreCombat.hide();
    }
    
    private void modifyPvPerso1 (int pv) {
        this.panelPvTextPerso1.setText(Integer.toString(this.combat.getPerso1().getPv()));
        this.panelPvPerso1.enlevePv(pv);
    }
    
    private void modifyPvPerso2 (int pv) {
        this.panelPvTextPerso2.setText(Integer.toString(this.combat.getPerso2().getPv()));
        this.panelPvPerso2.enlevePv(pv);
    }
    
    private void modifyExpPerso (int exp, Character perso) {
        PanelExp panelExp = new PanelExp(perso.getExperience(), 300, 30);
        Popup popup = popupFactory.getPopup(fenetre, panelExp, 600, 450);
    	popup.show();
    	panelExp.addExp(exp);
    	this.attendre(2000);
    	popup.hide();
    }
    
    private void modifyNivPerso (Character oldPerso, Character perso) {
        Popup popupTexte = popupFactory.getPopup(fenetre, new JLabel("Changement de niveau"), 650, 450);
        popupTexte.show();
    	this.attendre(2000);
    	popupTexte.hide();
    	
    	Color backgroungColor = new Color(0, 0, 255, 200);
    	JPanel panel = new JPanel();
    	Box boxStatPerso = Box.createVerticalBox();
    	JPanel panelInfoPerso = new JPanel();
    	panelInfoPerso.add(new JLabel(perso.getComportementPersonnage().getName() + "   Niv   " + perso.getNiv()));
    	panelInfoPerso.setBackground(backgroungColor);
    	boxStatPerso.add(panelInfoPerso);
    	JPanel panelStatPerso = new JPanel (new GridLayout(4, 2));
    	if (oldPerso.getPvMax() == perso.getPvMax()) {
    		panelStatPerso.add(new JLabel("PV   " + perso.getPvMax()));
    	} else {
    		panelStatPerso.add(new JLabel("PV   " + perso.getPvMax() + "  (+"+(perso.getPvMax()-oldPerso.getPvMax())+")"));
    	}
    	if (oldPerso.getChance() == perso.getChance()) {
    		panelStatPerso.add(new JLabel("Ch   " + perso.getChance()));
    	} else {
    		panelStatPerso.add(new JLabel("Ch   " + perso.getChance() + "  (+"+(perso.getChance()-oldPerso.getChance())+")"));
    	}
    	if (oldPerso.getPuissance() == perso.getPuissance()) {
    		panelStatPerso.add(new JLabel("Frc   " + perso.getPuissance()));
    	} else {
    		panelStatPerso.add(new JLabel("Frc   " + perso.getPuissance() + "  (+"+(perso.getPuissance()-oldPerso.getPuissance())+")"));
    	}
    	if (oldPerso.getDef() == perso.getDef()) {
    		panelStatPerso.add(new JLabel("Def   " + perso.getDef()));
    	} else {
    		panelStatPerso.add(new JLabel("Def   " + perso.getDef() + "  (+"+(perso.getDef()-oldPerso.getDef())+")"));
    	}
    	if (oldPerso.getCapacite() == perso.getCapacite()) {
    		panelStatPerso.add(new JLabel("Tec   " + perso.getCapacite()));
    	} else {
    		panelStatPerso.add(new JLabel("Tec   " + perso.getCapacite() + "  (+"+(perso.getCapacite()-oldPerso.getCapacite())+")"));
    	}
    	if (oldPerso.getResistance() == perso.getResistance()) {
    		panelStatPerso.add(new JLabel("Res   " + perso.getResistance()));
    	} else {
    		panelStatPerso.add(new JLabel("Res   " + perso.getResistance() + "  (+"+(perso.getResistance()-oldPerso.getResistance())+")"));
    	}
    	if (oldPerso.getVitesse() == perso.getVitesse()) {
    		panelStatPerso.add(new JLabel("Vit   " + perso.getVitesse()));
    	} else {
    		panelStatPerso.add(new JLabel("Vit   " + perso.getVitesse() + "  (+"+(perso.getVitesse()-oldPerso.getVitesse())+")"));
    	}
    	if (oldPerso.getConstitution() == perso.getConstitution()) {
    		panelStatPerso.add(new JLabel("Cons   " + perso.getConstitution()));
    	} else {
    		panelStatPerso.add(new JLabel("Cons   " + perso.getConstitution() + "  (+"+(perso.getConstitution()-oldPerso.getConstitution())+")"));
    	}
    	panelStatPerso.setBackground(backgroungColor);
    	boxStatPerso.add(panelStatPerso);
    	panel.add(boxStatPerso);
    	PanelImage panelImage = new PanelImage(CharacterImage.getImageMenuFromPersonnage(perso), 100, 100);
    	panel.add(panelImage);
        Popup popup = popupFactory.getPopup(fenetre, panel, 600, 400);
    	popup.show();
    	this.attendre(5000);
    	popup.hide();
    	
    }
    
    private void modifyClassPerso (Character oldPerso, Character perso) {
        Popup popupTexte = popupFactory.getPopup(fenetre, new JLabel("Changement de classe"), 650, 450);
        popupTexte.show();
    	this.attendre(2000);
    	popupTexte.hide();
    	
    	JPanel panel = new JPanel();
    	Box boxStatPerso = Box.createVerticalBox();
    	JPanel panelInfoPerso = new JPanel();
    	panelInfoPerso.add(new JLabel(perso.getComportementPersonnage().getName() + "   Niv   " + perso.getNiv()));
    	boxStatPerso.add(panelInfoPerso);
    	JPanel panelStatPerso = new JPanel (new GridLayout(4, 2));
    	if (oldPerso.getPvMax() == perso.getPvMax()) {
    		panelStatPerso.add(new JLabel("PV   " + perso.getPvMax()));
    	} else {
    		panelStatPerso.add(new JLabel("PV   " + perso.getPvMax() + "  (+"+(perso.getPvMax()-oldPerso.getPvMax())+")"));
    	}
    	if (oldPerso.getPuissance() == perso.getPuissance()) {
    		panelStatPerso.add(new JLabel("Frc   " + perso.getPuissance()));
    	} else {
    		panelStatPerso.add(new JLabel("Frc   " + perso.getPuissance() + "  (+"+(perso.getPuissance()-oldPerso.getPuissance())+")"));
    	}
    	if (oldPerso.getCapacite() == perso.getCapacite()) {
    		panelStatPerso.add(new JLabel("Tec   " + perso.getCapacite()));
    	} else {
    		panelStatPerso.add(new JLabel("Tec   " + perso.getCapacite() + "  (+"+(perso.getCapacite()-oldPerso.getCapacite())+")"));
    	}
    	if (oldPerso.getVitesse() == perso.getVitesse()) {
    		panelStatPerso.add(new JLabel("Vit   " + perso.getVitesse()));
    	} else {
    		panelStatPerso.add(new JLabel("Vit   " + perso.getVitesse() + "  (+"+(perso.getVitesse()-oldPerso.getVitesse())+")"));
    	}
    	if (oldPerso.getChance() == perso.getChance()) {
    		panelStatPerso.add(new JLabel("Ch   " + perso.getChance()));
    	} else {
    		panelStatPerso.add(new JLabel("Ch   " + perso.getChance() + "  (+"+(perso.getChance()-oldPerso.getChance())+")"));
    	}
    	if (oldPerso.getDef() == perso.getDef()) {
    		panelStatPerso.add(new JLabel("Def   " + perso.getDef()));
    	} else {
    		panelStatPerso.add(new JLabel("Def   " + perso.getDef() + "  (+"+(perso.getDef()-oldPerso.getDef())+")"));
    	}
    	if (oldPerso.getResistance() == perso.getResistance()) {
    		panelStatPerso.add(new JLabel("Res   " + perso.getResistance()));
    	} else {
    		panelStatPerso.add(new JLabel("Res   " + perso.getResistance() + "  (+"+(perso.getResistance()-oldPerso.getResistance())+")"));
    	}
    	if (oldPerso.getConstitution() == perso.getConstitution()) {
    		panelStatPerso.add(new JLabel("Cons   " + perso.getConstitution()));
    	} else {
    		panelStatPerso.add(new JLabel("Cons   " + perso.getConstitution() + "  (+"+(perso.getConstitution()-oldPerso.getConstitution())+")"));
    	}
    	boxStatPerso.add(panelStatPerso);
    	panel.add(boxStatPerso);
    	PanelImage panelImage = new PanelImage(CharacterImage.getImageMenuFromPersonnage(perso), 100, 100);
    	panel.add(panelImage);
        Popup popup = popupFactory.getPopup(fenetre, panel, 600, 400);
    	popup.show();
    	this.attendre(5000);
    	popup.hide();
    	WeaponType[] oldWeapons = ((FightBehaviour)oldPerso.getBehaviour()).getWeaponTypes();
    	WeaponType[] newWeapons = ((FightBehaviour)perso.getBehaviour()).getWeaponTypes();
    	if (oldWeapons.length < newWeapons.length) {
    		WeaponType[] winWeapons = new WeaponType[newWeapons.length-oldWeapons.length];
    		int indice = 0;
    		for (WeaponType newWeapon : newWeapons) {
    			boolean find = false;
    			for (WeaponType oldWeapon : oldWeapons) {
        			if (newWeapon == oldWeapon) {
        				find = true;
        			}
        		}
    			if (!find) {
    				winWeapons[indice] = newWeapon;
    				indice++;
    			}
    		}
    		String winWeapon = "";
    		for (WeaponType weapon : winWeapons) {
    			winWeapon += weapon.name();
    		}
    		JLabel weaponLabel = new JLabel(winWeapon + " peut maintenant être utilisé");
    		Popup weaponPopup = popupFactory.getPopup(fenetre, weaponLabel, 630, 450);
    		weaponPopup.show();
        	this.attendre(3000);
        	weaponPopup.hide();
    	}
    }
    
    private void annimationAttaquePerso (Character perso) {
        this.runAnnimation(CharacterImage.getImageCombatAttaqueFromPersonnage(perso), perso, true);
    }
    
    private void annimationCritiquePerso (Character perso) {
        this.runAnnimation(CharacterImage.getImageCombatAttaqueCritiqueFromPersonnage(perso), perso, true);
    }
    
    private void annimationDistancePerso (Character perso) {
        this.runAnnimation(CharacterImage.getImageCombatDistantFromPersonnage(perso), perso, true);
    }
    
    private void annimationDistanceCritiquePerso (Character perso) {
        this.runAnnimation(CharacterImage.getImageCombatDistantCritiqueFromPersonnage(perso), perso, true);
    }
    
    private void annimationEsquivePerso (Character perso) {
        this.runAnnimation(CharacterImage.getImageCombatEsquiveFromPersonnage(perso), perso, false);
    }
    
    protected void runAnnimation (Map<Integer, ImageManager> images, Character perso, boolean continuer) {
		PanelImage panelImage;
    	if (combat.getPerso1().equals(perso)) {
    		panelImage = panelPerso1;
        } else {
        	panelImage = panelPerso2;
        }
    	for (Map.Entry<Integer, ImageManager> e : images.entrySet()){
    		panelImage.setBufferedImage(e.getValue().getImage(), 150, 150);
            if (e.getValue().getDisplayTime() == -1) {
            	break;
            } else {
            	this.attendre(e.getValue().getDisplayTime());
            }
        }
    	if (continuer)
    	combat.doContinue();
	}
    
    public synchronized void attendre (int time) {
        try {
            this.wait(time);
        } catch (InterruptedException ex) {
            Logger.getLogger(CombatView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
