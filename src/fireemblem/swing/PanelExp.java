package fireemblem.swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JPanel;

public class PanelExp extends JPanel {
    
    private static final long serialVersionUID = -6637543763282968353L;
	private int exp;
    private final int expMax;
    private int width;
    private int height;
    
    public PanelExp () {
        this.exp = 0;
        this.expMax = 99;
        this.width = 80;
        this.height = 15;
    }
    
    public PanelExp (int exp) {
    	this();
        this.exp = exp;
    }
    
    public PanelExp (int exp, int width, int height) {
    	this();
        this.exp = exp;
        this.width = width;
        this.height = height;
        this.setPreferredSize(new Dimension(this.width, this.height));
    }
    
    public void addExp (int exp) {
        for (int i = 0 ; i < exp ; i++) {
        	if (this.exp == this.expMax) {
        		this.exp = 0;
        	} else {
        		this.exp++;
        	}
            this.repaint();
            this.attendre(100);
        }
    }
    
    public synchronized void attendre (int time) {
        try {
            this.wait(time);
        } catch (InterruptedException ex) {
            Logger.getLogger(VueSwing_combat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void paintComponent(Graphics g){
    	g.clearRect(0, 0, this.width, this.height);
        g.drawRect(0, 0, this.width, this.height);
        Font font = new Font("Arial", Font.BOLD, 12);
        g.setFont(font);
        g.setColor(Color.BLACK);
        g.drawString("EXP", 10, 12);
        int barreSize = this.width - 70;
        int pourcentage = (int)Math.floor((barreSize * this.exp) / this.expMax);
        g.setColor(Color.GRAY);
        g.fillRect(40, 0, barreSize, this.height);
        g.setColor(Color.BLUE);
        g.fillRect(40, 0, pourcentage, this.height);
        g.setColor(Color.BLACK);
        g.drawString(Integer.toString(this.exp), barreSize+50, 12);
    }
}