package fireemblem.swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class PanelPv extends JPanel {
    
    private static final long serialVersionUID = -4160521903730589758L;
	private int pv;
    private final int pvMax;
    private final int width;
    private final int height;
    
    public PanelPv (int pv, int pvMax, int width, int height) {
        this.pv = pv;
        this.pvMax = pvMax;
        this.width = width;
        this.height = height;
    }
    
    public void enlevePv (int pv) {
        for (int i = 0 ; i < pv ; i++) {
            this.pv--;
            this.repaint();
        }
    }

    @Override
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        if (this.pvMax > 40) {
            int rectYPosition = this.height /3;
            int rectHeight = this.height /3;
            g.setColor(Color.black);
            g.drawRect(0, rectYPosition, this.width, rectHeight);
            int separatorWidth = (this.width / 10) / 40;
            int rectWidth = (this.width - (this.width / 10)) / 40;
            int newPosition = 0;
            for (int i = 0; i < this.pv ; i++) {
                g.setColor(Color.black);
                g.fillRect(newPosition, rectYPosition, separatorWidth, rectHeight);
                newPosition += separatorWidth;
                g.setColor(Color.green);
                g.fillRect(newPosition, rectYPosition, rectWidth, rectHeight);
                newPosition += rectWidth;
            }
            for (int i = this.pv ; i < this.pvMax ; i++) {
                g.setColor(Color.black);
                g.fillRect(newPosition, rectYPosition, separatorWidth, rectHeight);
                newPosition += separatorWidth;
                g.setColor(Color.gray);
                g.fillRect(newPosition, rectYPosition, rectWidth, rectHeight);
                newPosition += rectWidth;
            }
        } else {
            int rectYPosition = this.height /3;
            int rectHeight = this.height /3;
            g.setColor(Color.black);
            int separatorWidth = 1;
            int rectWidth = 5;
            int newPosition = 0;
            for (int i = 0; i < this.pv ; i++) {
                g.setColor(Color.black);
                g.fillRect(newPosition, rectYPosition, separatorWidth, rectHeight);
                newPosition += separatorWidth;
                g.setColor(Color.green);
                g.fillRect(newPosition, rectYPosition, rectWidth, rectHeight);
                newPosition += rectWidth;
            }
            for (int i = this.pv ; i < this.pvMax ; i++) {
                g.setColor(Color.black);
                g.fillRect(newPosition, rectYPosition, separatorWidth, rectHeight);
                newPosition += separatorWidth;
                g.setColor(Color.gray);
                g.fillRect(newPosition, rectYPosition, rectWidth, rectHeight);
                newPosition += rectWidth;
            }
            g.drawRect(0, rectYPosition, newPosition, rectHeight);
        }
        this.setPreferredSize(new Dimension(this.width, this.height));
    }
    
}
