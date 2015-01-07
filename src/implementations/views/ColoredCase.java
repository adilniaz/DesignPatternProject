package implementations.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class ColoredCase extends JPanel {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 9007222209085446362L;
	private int width;
    private int height;
    private final Color color;
    
    public ColoredCase(int width, int height, Color color) {
        this.width = width;
        this.height = height;
        this.color = color;
    }

    @Override
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        g.setColor(this.color);
        g.fillRect(0, 0, this.width, this.height);
        this.setSize(new Dimension(this.width, this.height));
        this.setPreferredSize(new Dimension(this.width, this.height));
        this.revalidate();
        this.setOpaque(false);
    }
}
