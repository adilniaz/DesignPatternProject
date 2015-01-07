package implementations.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Panel extends JPanel {
    
	private static final long serialVersionUID = -186972397316975499L;
	private JPanel nord;
    private JPanel est;
    private PanelCentre panelCentre;
    private JPanel centre;
    private JPanel ouest;
    private JPanel sud;
    
    public Panel () {
        this(new PanelCentreParcelle());
    }
    
    public Panel (PanelCentre panelCentre) {
        super(new BorderLayout());
        this.nord = new JPanel();
        this.est = new JPanel();
        this.panelCentre = panelCentre;
        this.centre = new JPanel();
        this.ouest = new JPanel();
        this.sud = new JPanel();
        this.add(this.centre, BorderLayout.CENTER);
        this.add(this.sud, BorderLayout.SOUTH);
        this.add(this.nord, BorderLayout.NORTH);
        this.add(this.est, BorderLayout.EAST);
        this.add(this.ouest, BorderLayout.WEST);
    }
    
    public void actualise () {
        this.nord.repaint();
        this.nord.validate();
        this.sud.repaint();
        this.sud.validate();
        this.est.repaint();
        this.est.validate();
        this.ouest.repaint();
        this.ouest.validate();
        this.centre.repaint();
        this.centre.validate();
        this.panelCentre.repaint();
        this.panelCentre.validate();
        this.repaint();
        this.validate();
    }
    
    public void ajouterNord (Component panel) {
        this.nord.removeAll();
        this.nord.add(panel);
        this.nord.setBackground(Color.yellow);
        this.nord.repaint();
        this.nord.validate();
        this.afficherNord();
    }
    
    public void viderNord () {
        this.nord.removeAll();
        this.nord.repaint();
        this.nord.validate();
    }
    
    public void afficherNord () {
        this.nord.setVisible(true);
    }
    
    public void cacherNord () {
        this.nord.setVisible(false);
    }
    
    public JPanel getNord () {
        return this.nord;
    }
    
    public void redimenssionnerNord (int width, int height) {
        this.nord.setPreferredSize(new Dimension(width, height));
    }
    
    public void ajouterEst (Component panel) {
        this.est.removeAll();
        this.est.add(panel);
        this.est.setBackground(Color.ORANGE);
        this.est.repaint();
        this.est.validate();
        this.afficherEst();
    }
    
    public void viderEst () {
        this.est.removeAll();
        this.est.repaint();
        this.est.validate();
    }
    
    public void afficherEst () {
        this.est.setVisible(true);
    }
    
    public void cacherEst () {
        this.est.setVisible(false);
    }
    
    public JPanel getEst () {
        return this.est;
    }
    
    public void redimenssionnerEst (int width, int height) {
        this.est.setPreferredSize(new Dimension(width, height));
    }
    
    public void ajouterCentre (Component panel) {
        this.centre.removeAll();
        System.out.println("panel : " + panel);
        System.out.println("this.centre : " + this.centre);
        this.centre.add(panel);
        this.add(this.centre, BorderLayout.CENTER);
        this.centre.repaint();
        this.centre.validate();
        this.afficherCentre();
    }
    
    public void viderPanelCentre () {
        this.panelCentre.removeAll();
        this.panelCentre.repaint();
        this.panelCentre.validate();
    }
    
    public void viderCentre () {
        this.centre.removeAll();
        this.centre.repaint();
        this.centre.validate();
    }
    
    public void afficherCentre () {
        this.centre.setVisible(true);
        this.panelCentre.setVisible(false);
    }
    
    public void afficherPanelCentre () {
        this.centre.setVisible(false);
        this.panelCentre.setVisible(true);
    }
    
    public void cacherPanelCentre () {
        this.panelCentre.setVisible(false);
    }
    
    public void cacherCentre () {
        this.centre.setVisible(false);
    }
    
    public void redimenssionnerPanelCentre (int width, int height) {
        this.panelCentre.redimenssionner(width, height);
    }
    
    public void redimenssionnerCentre (int width, int height) {
        this.centre.setSize(width, height);
    }
    
    public PanelCentre getPanelCentre () {
        return this.panelCentre;
    }
    
    public JPanel getCentre () {
        return this.centre;
    }
    
    public void changerCentre (int ligne, int colone, int width, int height) {
        this.changerCentre(new PanelCentreParcelle(ligne, colone, width, height), ligne, colone, width, height);
    }
    
    public void changerCentre (PanelCentre panelCentre, int ligne, int colone, int width, int height) {
        if (this.panelCentre != null) {
            this.remove(this.panelCentre);
        }
        this.panelCentre = panelCentre;
        this.add(this.panelCentre, BorderLayout.CENTER);
        this.afficherPanelCentre();
        this.actualise();
    }
    
    public void ajouterOuest (Component panel) {
        this.ouest.removeAll();
        this.ouest.add(panel);
        this.ouest.setBackground(Color.RED);
        this.ouest.repaint();
        this.ouest.validate();
        this.afficherOuest();
    }
    
    public void viderOuest () {
        this.ouest.removeAll();
        this.ouest.repaint();
        this.ouest.validate();
    }
    
    public void afficherOuest () {
        this.ouest.setVisible(true);
    }
    
    public JPanel getOuest () {
        return this.ouest;
    }
    
    public void cacherOuest () {
        this.ouest.setVisible(false);
    }
    
    public void redimenssionnerOuest (int width, int height) {
        this.ouest.setPreferredSize(new Dimension(width, height));
    }
    
    public void ajouterSud (Component panel) {
        this.sud.removeAll();
        this.sud.add(panel);
        this.sud.setBackground(Color.BLUE);
        this.sud.repaint();
        this.sud.validate();
        this.afficherSud();
    }
    
    public void viderSud () {
        this.sud.removeAll();
        this.sud.repaint();
        this.sud.validate();
    }
    
    public void afficherSud () {
        this.sud.setVisible(true);
    }
    
    public void cacherSud () {
        this.sud.setVisible(false);
    }
    
    public JPanel getSud () {
        return this.sud;
    }
    
    public void redimenssionnerSud (int width, int height) {
        this.sud.setPreferredSize(new Dimension(width, height));
    }

    @Override
    public void paintComponent(final Graphics g) {
    }
}
