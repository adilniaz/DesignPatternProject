package fireemblem.swing;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class Parcelle extends JLayeredPane {
    
    private JPanel[] content;
    
    private Parcelle () {
        super();
        this.content = new JPanel[3];
        for (int i = 0 ; i < 2 ; i++) {
            this.content[i] = null;
        }
    }
    
    public Parcelle (int width, int height) {
        this();
        this.initSize(width, height);
        this.setBackground(Color.ORANGE);
    }
    
    private void initSize (int width, int height) {
        this.setBounds(0, 0, width, height);
        this.setPreferredSize(new Dimension(width, height));
    }
    
    public void ajouterBackground (Component panel) {
        panel.setSize(WIDTH, HEIGHT);
        this.add(panel, 0, 0);
    }
    
    public void ajouterContent (JPanel panel) {
        this.ajouterContent(panel, 0);
    }
    
    public void ajouterContent (JPanel panel, int niv) {
        if (panel == null) {
            if (this.content[niv] != null) {
                this.remove(this.content[niv]);
                JPanel Transparentpanel = new JPanel();
                Transparentpanel.setSize(WIDTH, HEIGHT);
                Transparentpanel.setOpaque(false);
                this.add(Transparentpanel, niv+1, 0);
                this.content[niv].removeAll();
            }
        } else {
            if (this.content[niv] != null) {
                this.remove(this.content[niv]);
            }
            this.content[niv] = panel;
            panel.setSize(WIDTH, HEIGHT);
            panel.setOpaque(false);
            this.add(panel, niv+1, 0);
        }
    }
    
    public void redimenssionnerContent (int width, int height) {
    }
    
    public void redimenssionnerContent (int width, int height, int niv) {
    }
    
    public void redimenssionnerBackground (int width, int height) {
    }

    @Override
    public void paintComponent(final Graphics g) {
    }
    
}
