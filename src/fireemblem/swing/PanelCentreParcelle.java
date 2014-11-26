package fireemblem.swing;

import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.JPanel;

public class PanelCentreParcelle extends PanelCentre {
    
    private Parcelle[][] parcelles;
    
    public PanelCentreParcelle () {
        super();
        this.parcelles = null;
    }
    
    public PanelCentreParcelle (int ligne, int colone, int width, int height) {
        super(new GridLayout(ligne, colone));
        this.parcelles = new Parcelle[ligne][colone];
        for (int i = 0 ; i  < ligne ; i++) {
            for (int j = 0 ; j  < colone ; j++) {
                this.parcelles[i][j] = new Parcelle((width/colone), (height/ligne));
                this.add(this.parcelles[i][j]);
            }
        }
        this.redimenssionner(width, height);
    }
    
    @Override
    public void ajouterContent (JPanel panel, int i, int j, int niv) {
        this.parcelles[i][j].ajouterContent(panel, niv);
        this.repaint();
        this.validate();
    }
    
    @Override
    public void ajouterBackground (Component panel, int i, int j) {
        this.parcelles[i][j].ajouterBackground(panel);
        this.repaint();
        this.validate();
    }

    @Override
    public void ajouterViewContent(ViewComponent content, int i, int j, int niv) {
        if (content != null && content.getComponent() != null) {
            this.parcelles[i][j].ajouterContent((JPanel)content.getComponent(), niv);
        } else {
            this.parcelles[i][j].ajouterContent(null, niv);
        }
        this.repaint();
        this.validate();
    }

    @Override
    public void ajouterViewBackground(ViewComponent content, int i, int j) {
        this.parcelles[i][j].ajouterBackground(content.getComponent());
        this.repaint();
        this.validate();
    }
    
    @Override
    public int getLargeur () {
        return this.parcelles[0].length;
    }
    
    @Override
    public int getHauteur () {
        return this.parcelles.length;
    }
    
    @Override
    public int getNivMax () {
        return 3;
    }
    
    @Override
    public Parcelle getParcelle () {
        return this.parcelles[0][0];
    }
    
    @Override
    public void ajouterParcelle (Parcelle parcelle, int i, int j) {
    	this.remove(this.parcelles[i][j]);
    	this.parcelles[i][j] = parcelle;
    	this.add(this.parcelles[i][j]);
    }
    
    @Override
    public Parcelle getParcelle (int i, int j) {
        return this.parcelles[i][j];
    }

    @Override
    public ViewComponent getViewBackground(int i, int j) {
        return new ViewComponent(this.parcelles[i][j].getBackgroundComponent());
    }

    @Override
    public ViewComponent getViewComponent(int niv, int i, int j) {
        return new ViewComponent(this.parcelles[i][j].getContent(niv));
    }
    
    @Override
    public boolean estSeul () {
        return this.parcelles == null;
    }

    @Override
    public void refreshComponent(ViewComponent component) {
        
    }
}
