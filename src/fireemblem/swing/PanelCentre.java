package fireemblem.swing;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public abstract class PanelCentre extends JPanel {
    
	private static final long serialVersionUID = 6507818571305439376L;

	public PanelCentre () {
        super();
    }
    
    public PanelCentre (LayoutManager manager) {
        super(manager);
    }
    
    public void ajouter (Component panel) {
        this.removeAll();
        this.add(panel);
        this.repaint();
        this.validate();
    }
    
    public void ajouterContent (JPanel panel, int i, int j) {
        this.ajouterContent(panel, i, j, 0);
    }
    
    public void ajouterContentImage (BufferedImage image, int i, int j) {
        this.ajouterContentImage(image, i, j, 0);
    }
    
    public void ajouterViewContent (ViewComponent content, int i, int j) {
        this.ajouterViewContent(content, i, j, 0);
    }
    
    public void ajouterContentImage (BufferedImage image, int i, int j, int niv) {
    }
    
    public abstract void ajouterContent (JPanel panel, int i, int j, int niv);
    
    public abstract void ajouterBackground (Component panel, int i, int j);
    
    public abstract void ajouterViewContent (ViewComponent content, int i, int j, int niv);
    
    public abstract void ajouterViewBackground (ViewComponent content, int i, int j);
    
    public abstract int getLargeur ();
    
    public abstract int getHauteur ();
    
    public abstract int getNivMax ();
    
    public abstract Parcelle getParcelle ();
    
    public abstract void ajouterParcelle (Parcelle parcelle, int i, int j);
    
    public abstract Parcelle getParcelle (int i, int j);
    
    public abstract ViewComponent getViewBackground (int i, int j);
    
    public abstract ViewComponent getViewComponent (int niv, int i, int j);
    
    public abstract void refreshComponent (ViewComponent component); 
    
    public void redimenssionner (int width, int height) {
        this.setPreferredSize(new Dimension(width, height));
        this.setBounds(0, 0, width, height);
        this.setSize(width, height);
    }
    
    public abstract boolean estSeul ();

    @Override
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);
    }
    
}
